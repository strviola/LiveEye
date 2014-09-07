package com.hackathon.liveeye.io;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;

import java.io.IOException;
import java.util.Map;

/**
 * Created by SuzukiRyota on 2014/09/07.
 */
public class Common {
    public static Map<String, Object> connectDB(Context ctx, String dbName) {
        Database db = getDatabase(ctx, dbName);
        try {
            return db.getAllDocs(null);

        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    return null;
    }

    public static Database getDatabase(Context ctx, String dbName) {
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
            db = dbManager.getDatabase(dbName);
            return db;
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        // failed to connect
        return null;
    }
}
