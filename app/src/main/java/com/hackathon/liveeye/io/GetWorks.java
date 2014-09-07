package com.hackathon.liveeye.io;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.hackathon.liveeye.dto.WorkTitle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by SuzukiRyota on 2014/09/07.
 */
public class GetWorks {
    public static List<WorkTitle> getWorksFromDB(Context ctx) {
        Map<String, Object> dbMap = connectDB(ctx);
        return mapToWork(dbMap, ctx);
    }

    public static List<WorkTitle> getWorksFromDB() {
        // テスト用
        String now = "2014/09/07 12:00";
        String yesterday = "2014/09/06 12:00";
        return Arrays.asList(
                new WorkTitle("1", now).addFrameId("1001").addFrameId("1002"),
                new WorkTitle("2", yesterday).addFrameId("2001").addFrameId("2002"));
    }

    private static Map<String, Object> connectDB(Context ctx) {
        // get db manager
        Manager dbManager;
        try {
            dbManager = new Manager(ctx.getFilesDir(), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // create database
        Database db = null;
        try {
            db = dbManager.getDatabase("work");
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        try {
            return db.getAllDocs(null);

        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        // failed to connect
        return null;
    }

    private static List<WorkTitle> mapToWork(Map<String, Object> map, Context ctx) {
        Map<String, Object> fromDB = connectDB(ctx);
        List<WorkTitle> works = new ArrayList<WorkTitle>();

        for (Map.Entry<String, Object> kv : fromDB.entrySet()) {
            String posted = (String) kv.getValue();
            works.add(new WorkTitle(kv.getKey(), (String) kv.getValue()));
        }
        return works;
    }
}
