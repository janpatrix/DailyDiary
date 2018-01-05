package com.example.janpatrix.dailydiary.Events;

import java.util.Date;

/**
 * Created by janpatrix on 02.01.2018.
 */

public class EventObject {

    public static final int ITEM_TYPE = 0;
    public static final int EVENT_TYPE = 1;
    public static final int DATE_TYPE = 2;

    private int mId;
    private String mMessage;
    private Date mDate;
    private Date mEnd;
    private int mType;

    public EventObject(int id, String message, Date date, Date end, int type){
        mId = id;
        mMessage = message;
        mDate = date;
        mEnd = end;
        mType = type;
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

    public int getType(){
        return mType;
    }
}
