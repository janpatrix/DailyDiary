package com.example.janpatrix.dailydiary.RoomEvents;

/**
 * Created by patrickgross on 08.01.18.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CardDao {

    @Query("SELECT * FROM card")
    List<Card> getAll();

    @Query("DELETE FROM card")
    void deleteAll();

    @Insert
    void insertAll(List<Card> cards);

    @Delete
    void delete(Card card);
}