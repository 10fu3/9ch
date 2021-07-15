package jp.toufu3.ninech;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Router {

    public static void setup(){

        HTTPServer.get().setup(80);
        HTTPServer.get().start();

//        server = Javalin.create().start(80);
//
//        server.get("/",(ctx)->{
//
//        });
//
//        server.get("/thread/:id",(ctx)->{
//            File dat = new File(ctx.pathParam("id")+".dat");
//            if(!dat.exists() || dat.isDirectory()){
//                ctx.status(404).result("不正なリンクです");
//                return;
//            }
//            ctx.json(FileReaderUtil.convertDatToObject(dat));
//        });
//
//        server.post("/thread/:id",(ctx)->{
//            File dat = new File(ctx.pathParam("id")+".dat");
//            if(!dat.exists() || dat.isDirectory()){
//                ctx.status(404).result("不正なリンクです");
//                return;
//            }
//
//        });
//
//        server.get("/threads",(ctx)->{
//            ctx.json(FileReaderUtil.getThreads());
//        });

    }
}
