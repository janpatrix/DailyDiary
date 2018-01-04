package com.example.janpatrix.dailydiary.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.janpatrix.dailydiary.Events.EventObject;

import java.util.Date;

/**
 * Created by janpatrix on 02.01.2018.
 */

public class EventCursorWrapper extends CursorWrapper{

    public EventCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public EventObject getEvent() {

        int id = getInt(getColumnIndex(EventDbSchema.EventTable.Cols.ID));
        String message = getString(getColumnIndex(EventDbSchema.EventTable.Cols.MESSAGE));
        long date = getLong(getColumnIndex(EventDbSchema.EventTable.Cols.DATE));
        long end = getLong(getColumnIndex(EventDbSchema.EventTable.Cols.END));
        int type = getInt(getColumnIndex(EventDbSchema.EventTable.Cols.TYPE));

        EventObject event = new EventObject(id, message, new Date(date), new Date(end), type);

        return event;
    }
}
