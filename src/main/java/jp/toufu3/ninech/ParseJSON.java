package jp.toufu3.ninech;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Field;
import java.util.*;

public class ParseJSON {

    private static boolean isPrimitive(Object i){
        return (i instanceof Integer || i instanceof Long || i instanceof Float || i instanceof Double || i instanceof Character || i instanceof Boolean);
    }

    private static StringBuilder parseObject(Object i){
        StringBuilder sss = new StringBuilder();
        if(i instanceof Map){
            sss.append(mapToString((Map<String, Object>) i));
        }else if(i instanceof List) {
            sss.append(listToString((List<Object>) i));
        }else if(i instanceof String){
            sss.append("\"");
            sss.append(i);
            sss.append("\"");
        } else if(isPrimitive(i)){
            sss.append(i);
        }else{
            sss.append(objToString(i));
        }
        return sss;
    }

    public static String listToString(List<Object> l){
        StringBuilder sss = new StringBuilder("[");

        for(Object i : l){
            sss.append(parseObject(i));
            sss.append(",");
        }

        return sss.substring(0, sss.length() - 1) + "]";
    }

    public static String objToString(Object obj){

        if(obj instanceof Map){
            return mapToString((Map<String, Object>) obj);
        }else if(obj instanceof List){
            return listToString((List<Object>) obj);
        }

        StringBuilder sss = new StringBuilder("{");
        for (Field field : obj.getClass().getFields()) {
            try {
                Object v = field.get(obj);
                sss.append("\"").append(field.getName()).append("\":");
                sss.append(parseObject(v));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sss.append(",");
        }
        return sss.substring(0, sss.length() - 1) + "}";
    }

    public static String mapToString(Map<String, Object> map) {
        StringBuilder sss = new StringBuilder("{");
        for (Map.Entry<String, Object> e : map.entrySet()) {
            if ( e.getValue() instanceof Map ) {
                sss.append("\"").append(e.getKey()).append("\":");
                sss.append(mapToString((Map<String, Object>) e.getValue()));
                sss = new StringBuilder(sss.substring(0, sss.length() - 1) + "},");
            }else if(e.getValue() instanceof List) {
                sss.append("\"")
                   .append(e.getKey())
                   .append("\":")
                   .append(listToString((List<Object>)e.getValue()))
                   .append(",");
            }else if(e.getValue() instanceof String){
                sss.append("\"")
                        .append(e.getKey())
                        .append("\":")
                        .append("\"")
                        .append(e.getValue())
                        .append("\"")
                        .append(",");
            }else if(isPrimitive(e.getValue())){
                sss.append("\"")
                   .append(e.getKey())
                   .append("\":")
                   .append(e.getValue())
                   .append(",");
            } else {
                sss.append("\"").
                   append(e.getKey())
                   .append("\":")
                   .append(objToString(e.getValue()))
                   .append(",");
            }
        }
        return sss.substring(0, sss.length() - 1) + "}";
    }

    public static Object jsonToObj(String json) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        String script = "var obj = " + json + ";";
        try {
            return engine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

}
