package com.example.nasrf.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nasrf on 14/9/2017.
 */

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper (Context context){ super(context, "task", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tasks(id integer primary key autoincrement,task_name text,note text,start_date text,end_date text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table tasks");
        onCreate(db);
    }
}
