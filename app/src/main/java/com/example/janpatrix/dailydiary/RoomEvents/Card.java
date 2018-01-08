package com.example.janpatrix.dailydiary.RoomEvents;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by patrickgross on 08.01.18.
 */
@Entity
public class Card {

    public static final int ITEM_TYPE = 0;
    public static final int EVENT_TYPE = 1;

    @PrimaryKey
    @ColumnInfo(name = "cardId")
    @NonNull
    private String mUid;

    @ColumnInfo(name = "date")
    private long mDate;

    @ColumnInfo(name = "message")
    private String mMessage;

    @ColumnInfo(name = "type")
    private int mType;

    @Ignore
    public Card(String message, int type){
        Calendar cal = Calendar.getInstance();
        mUid = UUID.randomUUID().toString();
        mMessage = message;
        mDate = cal.getTimeInMillis();
        mType = type;
    }

    public Card(long date, String message, int type){
        mUid = UUID.randomUUID().toString();
        mMessage = message;
        mDate = date;
        mType = type;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid){
        this.mUid = uid;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        this.mDate = date;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }
}
