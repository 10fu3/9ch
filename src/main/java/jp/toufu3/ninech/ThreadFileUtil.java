package jp.toufu3.ninech;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ThreadFileUtil {
    public static List<Response> convertDatToObject(File dat){
        List<Response> r = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(dat));

            String l;
            Scanner sc;

            br.readLine();

            while((l = br.readLine()) != null){
                sc = new Scanner(l);
                sc.useDelimiter("<>");
                r.add(new Response(sc.next(),sc.next(),sc.next(),sc.next()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    private static String getExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }

    public static List<FullThread> getThreads(){
        return Arrays.stream(new File("").listFiles())
                .filter(File::isFile)
                .filter(f->"dat".equalsIgnoreCase(getExtension(f.getName())))
                .map(f->{
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(f.getName()));
                        Scanner sc = new Scanner(br.readLine());
                        sc.useDelimiter("<>");
                        return new FullThread(sc.next(),sc.next(),sc.next());
                    } catch (IOException e) {
                        return new EmptyThread();
                    }
                })
                .filter(t->t instanceof FullThread)
                .map(t->(FullThread)t)
                .collect(Collectors.toList());

    }

    public static boolean createThread(FullThread t){
        if(!new File(t.id+".dat").exists()){
            return false;
        }
        try {
            new FileWriter(t.id+".dat")
                .append(t.id)
                .append("<>")
                .append(t.title)
                .append("<>")
                .append(t.date)
                .close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addObjectToDat(String id,Response r){
        try {

            if(!new File(id+".dat").exists()){
                return false;
            }

            FileWriter fw = new FileWriter(id+".dat");

            fw.append(r.id)
              .append("<>")
              .append(r.name)
              .append("<>")
              .append(r.body)
              .append("<>")
              .append(r.date)
              .close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
