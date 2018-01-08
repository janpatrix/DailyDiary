package com.example.janpatrix.dailydiary.Tools;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.janpatrix.dailydiary.RoomEvents.AppDatabase;
import com.example.janpatrix.dailydiary.RoomEvents.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrickgross on 05.01.18.
 */

public class TestData {


    public void createTestData(Context context) {

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "database-name").allowMainThreadQueries().build();

        List<Card> cards =  new ArrayList<>();

        cards.add(new Card("Cool message", Card.ITEM_TYPE));
        cards.add(new Card("HOT message", Card.EVENT_TYPE));
        cards.add(new Card("Mega message", Card.EVENT_TYPE));

        db.cardDao().insertAll(cards);
    }
}
