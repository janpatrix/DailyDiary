package com.example.janpatrix.dailydiary.RoomEvents;

/**
 * Created by patrickgross on 08.01.18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Card.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract CardDao cardDao();
}