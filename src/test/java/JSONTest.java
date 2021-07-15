import jp.toufu3.ninech.ParseJSON;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

public class JSONTest {

    @BeforeAll
    static void beforeStart(){
        System.out.println("JSON test開始");
    }

    @Test
    void parseJSON(){
        JSONTestObject jto = new JSONTestObject();
        assertEquals("{\"AAA\":{\"a\":\"B\",\"b\":1000,\"l\":[1,2,3,100,110],\"j\":{\"A\":{\"I\":100}}}}",ParseJSON.mapToString(Map.of("AAA",jto)));
    }
}
