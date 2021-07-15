package jp.toufu3.ninech;

public class Response {

    public final String id;
    public final String name;
    public final String body;
    public final String date;

    public Response(String id, String name, String body, String date) {
        this.id = id;
        this.name = name;
        this.body = body;
        this.date = date;
    }
}
