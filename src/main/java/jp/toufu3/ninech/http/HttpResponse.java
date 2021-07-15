package jp.toufu3.ninech.http;

import jp.toufu3.ninech.ParseJSON;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private static String mapToCookie(Map<String,String> m){
        StringBuilder sb = new StringBuilder();
        for(String k:m.keySet()){
            sb.append(k);
            sb.append("=");
            sb.append(m.get(k));
            sb.append(";");
        }
        return sb.toString();
    }


    private StatusCode statusCode = StatusCode.OK;
    private Map<String,String> sendCookie = new HashMap<>();
    private Map<String,String> sendHeader = new HashMap<>();
    private boolean json = false;
    private String body = "";

    public boolean isJSONBody(){
        return this.json;
    }

    public HttpResponse setCookie(String k,String v){
        this.sendCookie.put(k,v);
        return this;
    }

    public HttpResponse setHeader(String k,String v){
        this.sendHeader.put(k,v);
        return this;
    }

    public HttpResponse setBody(String body){
        this.body = body;
        this.json = false;
        return this;
    }

    public HttpResponse setJSON(Object obj){
        this.body = ParseJSON.objToString(obj);
        this.json = true;
        return this;
    }

    public HttpResponse setStatusCode(StatusCode status){
        this.statusCode = status;
        return this;
    }

    public String convertHTTPResponse(){
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ");
        sb.append(statusCode.code());
        sb.append(" ");
        sb.append(statusCode);
        sb.append("\n");
        sb.append("Access-Control-Allow-Origin: *");
        sb.append("\n");
        if(sendCookie.size() > 0){
            sb.append("Set-Cookie: ");
            sb.append(mapToCookie(sendCookie));
            sb.append("\n");
        }
        sb.append("Content-Type: ");
        sb.append(isJSONBody() ? "application/json; " : "text/html; ");
        sb.append("charset=utf-8");
        sb.append("\n");

        for(String k : sendHeader.keySet()){
            sb.append(k);
            sb.append(": ");
            sb.append(sendHeader.get(k));
            sb.append("\n");
        }
        sb.append("\n");
        sb.append(body);
        return sb.toString();
    }


}
