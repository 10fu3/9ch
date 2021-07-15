package jp.toufu3.ninech.http;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Tuple<A,B>{
    A key;
    B value;
    Tuple(A a,B b){
        this.key = a;
        this.value = b;
    }
}

public class HttpRequestHandle {

    private final ByteBuffer buff;

    public HttpRequestHandle(ByteBuffer bb,Scanner sc){
        this.buff = bb;

        buff.flip();
        byte[] bytes = new byte[buff.limit()];
        buff.get(bytes);
        buff.compact();

        HttpRequestBuilder hrb = new HttpRequestBuilder();

        for(String s;sc.hasNextLine();){
            s = sc.nextLine();

            if(s.startsWith("Cookie: ")){
                hrb.setCookie(
                        Arrays.stream(s.replaceFirst("Cookie: ", "").split(" ,"))
                        .map(d -> d.split(","))
                        .collect(Collectors.toList())
                        .stream()
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList())
                        .stream()
                        .map(i-> i.split("="))
                        .filter(i->i.length == 2)
                        .map(i->new Tuple<>(i[0],i[1]))
                        .collect(Collectors.toMap(i->i.key,i->i.value)));
                continue;
            }
            System.out.println(s+" "+s.equalsIgnoreCase("\n"));
        }
    }
}
