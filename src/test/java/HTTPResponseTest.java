
import jp.toufu3.ninech.http.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPResponseTest {
    @BeforeAll
    static void beforeStart(){
        System.out.println("HttpResponse test開始");
    }

    @Test
    void parseJSON(){
        HttpResponse r = new HttpResponse().setJSON(Map.of("K", List.of(1,100,50),"O",Map.of("R",1.0)));
        System.out.println(r.convertHTTPResponse());
    }
}
