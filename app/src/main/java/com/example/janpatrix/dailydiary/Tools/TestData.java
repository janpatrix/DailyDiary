package com.example.janpatrix.dailydiary.Tools;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.janpatrix.dailydiary.RoomEvents.AppDatabase;
import com.example.janpatrix.dailydiary.RoomEvents.Card;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by patrickgross on 05.01.18.
 */

public class TestData {


    public void createTestData(Context context) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_WEEK, -1);
        long yesterday = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_WEEK, +2);
        long tomorrow = cal.getTimeInMillis();
        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, +2);
        long todayMoreHour = cal.getTimeInMillis();

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "database-name").allowMainThreadQueries().build();
        db.cardDao().deleteAll();

        List<Card> cards =  new ArrayList<>();

        cards.add(new Card("Cool message", Card.ITEM_TYPE));
        cards.add(new Card("HOT message", Card.EVENT_TYPE));
        cards.add(new Card("Mega message", Card.EVENT_TYPE));
        cards.add(new Card(todayMoreHour,"Hour message ", Card.ITEM_TYPE));
        cards.add(new Card(yesterday, "Yesterday message", Card.EVENT_TYPE));
        cards.add(new Card(tomorrow,"Tomorrow message", Card.EVENT_TYPE));

        db.cardDao().insertAll(cards);
    }
}
