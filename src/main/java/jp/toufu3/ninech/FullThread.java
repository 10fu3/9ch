package jp.toufu3.ninech;

public class FullThread extends Thread {
    public final String id;
    public final String title;
    public final String date;

    public FullThread(String id, String title, String date) {
        super(false);
        this.id = id;
        this.title = title;
        this.date = date;
    }
}
