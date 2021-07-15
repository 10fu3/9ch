import java.util.List;
import java.util.Map;

public class JSONTestObject {
    public String a = "B";
    public Integer b = 1000;
    public List<Integer> l = List.of(1,2,3,100,110);
    public Map<String,Map<String,Integer>> j = Map.of("A",Map.of("I",100));
}
