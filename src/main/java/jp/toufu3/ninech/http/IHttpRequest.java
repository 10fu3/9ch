package jp.toufu3.ninech.http;

import java.util.Map;

public interface IHttpRequest {

    /**
     * フォームパラメーター
     * @return [Key:Value]
     */
    Map<String,String> getFormParams();

    /**
     * パスパラメーター
     * @return [Key:Value]
     */
    Map<String,String> getPathParams();

    /**
     * Cookie
     * @return cookie
     */
    Map<String,String> getCookie();

    /**
     * JSONオブジェクト
     * @return json
     */
    Map<String,Object> getJSON();

    /**
     * リクエスト本体
     * @return リクエスト本文
     */
    String getBody();

    /**
     * リクエスト送信先
     * @return リクエスト送信先
     */
    String getRoute();

}
