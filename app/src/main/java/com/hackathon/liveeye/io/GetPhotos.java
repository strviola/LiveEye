package com.hackathon.liveeye.io;

import android.content.Context;

import java.util.Map;

/**
 * Created by SuzukiRyota on 2014/09/07.
 */
public class GetPhotos {
    public static void get(Context ctx) {
        Map<String, Object> images = Common.connectDB(ctx, "frame");
    }
}
