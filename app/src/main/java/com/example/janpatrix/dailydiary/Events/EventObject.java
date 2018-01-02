package com.example.janpatrix.dailydiary.Events;

import java.util.Date;

/**
 * Created by janpatrix on 02.01.2018.
 */

public class EventObject {

    private int mId;
    private String mMessage;
    private Date mDate;
    private Date mEnd;

    public EventObject(String message, Date date, Date end) {
        mMessage = message;
        mDate = date;
        mEnd = end;
    }

    public EventObject(int id, String message, Date date, Date end){
        mId = id;
        mMessage = message;
        mDate = date;
        mEnd = end;
    }

    public Date getEnd(){
        return mEnd;
    }

    public Date getDate(){
        return mDate;
    }

    public String getMessage(){
        return mMessage;
    }

    public int getID(){
        return mId;
    }
}
