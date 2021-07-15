package jp.toufu3.ninech;

import jp.toufu3.ninech.http.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.net.http.HttpRequest;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ノンブロッキングHTTP Server
 */
public class HTTPServer {

    private final static HTTPServer hs = new HTTPServer();

    private AsynchronousServerSocketChannel server;

    private final int TIMEOUT = 10;

    //Map<> handles = new HashMap<>();

    public static HTTPServer get(){
        return hs;
    }

    public boolean setup(int port){
        try {
            server = AsynchronousServerSocketChannel.open();
            server.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            server.bind(new InetSocketAddress(port));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    private static void printRequest(ByteBuffer buff){
        buff.flip();
        byte[] bytes = new byte[buff.limit()];
        buff.get(bytes);
        buff.compact();

        new HttpRequestHandle(buff,new Scanner(new String(bytes)));
//        Scanner sc = new Scanner(new String(bytes));
//        String[] method = sc.nextLine().split(" ");
//
//        HttpRequestBuilder hrb = new HttpRequestBuilder();
//
//        System.out.println(method[0]+ " and " +method[1]);
//
//        for(String s;sc.hasNextLine();){
//            s = sc.nextLine();
//            System.out.println(s);
//        }
    }

    private static void mkResponse(ByteBuffer buff) {
        buff.clear();
        String text = new HttpResponse()
                .setHeader("authorization",UUID.randomUUID().toString())
                .setCookie("C","k")
                .setStatusCode(StatusCode.OK)
                .setJSON(Map.of("key","fine","value","thank_you"))
                .convertHTTPResponse();
        buff.put(text.getBytes());
    }

    private void handleRequest(AsynchronousSocketChannel channel) {

        try (AsynchronousSocketChannel acceptedChannel = channel) {

            ByteBuffer buff = ByteBuffer.allocateDirect(8192);

            acceptedChannel.read(buff).get(TIMEOUT, TimeUnit.SECONDS);

            printRequest(buff);
            mkResponse(buff);
            buff.flip();

            acceptedChannel.write(buff).get(TIMEOUT, TimeUnit.SECONDS);

        } catch (InterruptedException | ExecutionException | TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){

        while(true) {
            Future<AsynchronousSocketChannel> acceptFuture = server.accept();
            try {
                handleRequest(acceptFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}
