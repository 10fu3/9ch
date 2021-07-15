package jp.toufu3.ninech.http;

import java.util.HashMap;
import java.util.Map;

class HttpRequest implements IHttpRequest{
    Map<String,String> formParams = new HashMap<>();
    Map<String,String> pathParams = new HashMap<>();
    Map<String,String> cookie = new HashMap<>();
    Map<String,Object> json = new HashMap<>();
    String body = "";
    String route = "";

    /**
     * フォームパラメーター
     *
     * @return [Key:Value]
     */
    @Override
    public Map<String, String> getFormParams() {
        return this.formParams;
    }

    /**
     * パスパラメーター
     *
     * @return [Key:Value]
     */
    @Override
    public Map<String, String> getPathParams() {
        return this.pathParams;
    }

    /**
     * Cookie
     *
     * @return cookie
     */
    @Override
    public Map<String, String> getCookie() {
        return this.cookie;
    }

    /**
     * JSONオブジェクト
     *
     * @return json
     */
    @Override
    public Map<String, Object> getJSON() {
        return this.json;
    }

    /**
     * リクエスト本体
     *
     * @return リクエスト本文
     */
    @Override
    public String getBody() {
        return this.body;
    }

    /**
     * リクエスト送信先
     *
     * @return リクエスト送信先
     */
    @Override
    public String getRoute() {
        return this.route;
    }
}
