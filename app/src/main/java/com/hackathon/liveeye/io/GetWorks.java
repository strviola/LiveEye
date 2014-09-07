package com.hackathon.liveeye.io;

import android.content.Context;
import android.util.Log;

import com.hackathon.liveeye.dto.WorkTitle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by SuzukiRyota on 2014/09/07.
 */
public class GetWorks {
    public static List<WorkTitle> getWorksFromDB(Context ctx) {
        Map<String, Object> dbMap = Common.connectDB(ctx, "work");
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

    private static List<WorkTitle> mapToWork(Map<String, Object> map, Context ctx) {
        Map<String, Object> fromDB = Common.connectDB(ctx, "work");
        if (fromDB == null) {
            return null;
        }
        List<WorkTitle> works = new ArrayList<WorkTitle>();

        for (Map.Entry<String, Object> kv : fromDB.entrySet()) {
            Log.i("GetWorks", kv.getValue().toString());
            String posted = (String) kv.getValue();
            works.add(new WorkTitle(kv.getKey(), (String) kv.getValue()));
        }
        return works;
    }
}
