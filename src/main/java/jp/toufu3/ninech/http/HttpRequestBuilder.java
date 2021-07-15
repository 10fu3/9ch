package jp.toufu3.ninech.http;

import java.util.Map;

public class HttpRequestBuilder {
    private HttpRequest req = new HttpRequest();

    public HttpRequestBuilder setFormParam(String key,String val){
        this.req.formParams.put(key,val);
        return this;
    }

    public HttpRequestBuilder setPathParam(String key,String val){
        this.req.pathParams.put(key,val);
        return this;
    }

    public HttpRequestBuilder setCookie(String key,String val){
        this.req.cookie.put(key,val);
        return this;
    }

    public HttpRequestBuilder setCookie(Map<String,String> cookie){
        this.req.cookie = cookie;
        return this;
    }

    public HttpRequestBuilder setJSON(Map<String,Object> json){
        this.req.json = json;
        return this;
    }

    public HttpRequestBuilder setBody(String body){
        this.req.body = body;
        return this;
    }

    public HttpRequestBuilder setRoute(String route){
        this.req.route = route;
        return this;
    }
}
