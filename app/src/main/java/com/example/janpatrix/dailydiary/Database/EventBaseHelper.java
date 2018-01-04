package com.example.janpatrix.dailydiary.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.janpatrix.dailydiary.Database.EventDbSchema.EventTable;

/**
 * Created by janpatrix on 02.01.2018.
 */

public class EventBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "eventBase.db";

    public EventBaseHelper(Context context) {
        super (context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EventTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                EventTable.Cols.ID + "," +
                EventTable.Cols.MESSAGE + "," +
                EventTable.Cols.DATE + "," +
                EventTable.Cols.END + "," +
                EventTable.Cols.TYPE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
