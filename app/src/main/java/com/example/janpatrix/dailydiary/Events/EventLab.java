package com.example.janpatrix.dailydiary.Events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.janpatrix.dailydiary.Database.EventBaseHelper;
import com.example.janpatrix.dailydiary.Database.EventCursorWrapper;
import com.example.janpatrix.dailydiary.Database.EventDbSchema.EventTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by janpatrix on 02.01.2018.
 */

public class EventLab {

    private static EventLab mEventLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private EventLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new EventBaseHelper(mContext).getWritableDatabase();
    }

    public void addEvent(EventObject event){
        ContentValues values = getContentValues(event);
        mDatabase.insert(EventTable.NAME, null, values );
    }

    public static EventLab get(Context context) {
        if(mEventLab == null) {
            mEventLab = new EventLab(context);
        }

        return mEventLab;
    }

    public void deleteDatabaseItems(){
        try {
            mDatabase.execSQL("DELETE FROM " + EventTable.NAME);
        } catch (SQLException e) {
            Log.d("SQL", e.toString());
        }
    }

    public List<EventObject> getEvents(){
        List<EventObject> events = new ArrayList<>();

        EventCursorWrapper cursor = queryEvents( null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                events.add(cursor.getEvent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return events;
    }

    private static ContentValues getContentValues(EventObject event){

        ContentValues values = new ContentValues();
        values.put(EventTable.Cols.ID, event.getID());
        values.put(EventTable.Cols.MESSAGE, event.getMessage());
        values.put(EventTable.Cols.DATE, event.getDate().getTime());
        values.put(EventTable.Cols.END, event.getEnd().getTime());

        return values;
    }

    private EventCursorWrapper queryEvents(String whereClause, String[] whereArgs){

        Cursor cursor = mDatabase.query(
                EventTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new EventCursorWrapper(cursor);
    }
}
