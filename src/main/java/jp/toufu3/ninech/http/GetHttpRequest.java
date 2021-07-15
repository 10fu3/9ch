package jp.toufu3.ninech.http;

import jp.toufu3.ninech.IllegalAccessHttpRequestField;

import java.util.Map;

public class GetHttpRequest extends HttpRequest{
    /**
     * フォームパラメーター
     *
     * @return [Key:Value]
     */
    @Override
    public Map<String, String> getFormParams() {
        throw new IllegalAccessHttpRequestField();
    }

    /**
     * JSONオブジェクト
     *
     * @return json
     */
    @Override
    public Map<String, Object> getJSON() {
        throw new IllegalAccessHttpRequestField();
    }

    /**
     * リクエスト本体
     * @return リクエスト本文
     */
    @Override
    public String getBody() {
        throw new IllegalAccessHttpRequestField();
    }
}
