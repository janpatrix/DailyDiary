package com.example.janpatrix.dailydiary.Activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.janpatrix.dailydiary.Adapter.RowAdapter;

import com.example.janpatrix.dailydiary.R;
import com.example.janpatrix.dailydiary.RoomEvents.AppDatabase;

import static com.example.janpatrix.dailydiary.Tools.helper.convertDateToString;
import static com.example.janpatrix.dailydiary.Tools.helper.getDateDay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by patrickgross on 05.01.18.
 */

public class CardViewActivity extends AppCompatActivity {

    private ImageView prev;
    private ImageView next;
    private TextView tv_date;
    private Calendar cal = Calendar.getInstance();
    private RowAdapter adapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        prev = findViewById(R.id.btn_card_prev);
        next = findViewById(R.id.btn_card_next);
        tv_date = findViewById(R.id.card_date);
        tv_date.setText(convertDateToString(cal.getTime()));

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "database-name").allowMainThreadQueries().build();

        long[] dayList = getDateDay(cal.getTimeInMillis());
        adapter = new RowAdapter(db.cardDao().queryDate(dayList[0], dayList[1]));

        linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
                tv_date.setText(convertDateToString(cal.getTime()));

                long[] dayList = getDateDay(cal.getTimeInMillis());
                adapter = new RowAdapter(db.cardDao().queryDate(dayList[0], dayList[1]));

                mRecyclerView.setAdapter(adapter);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                tv_date.setText(convertDateToString(cal.getTime()));

                long[] dayList = getDateDay(cal.getTimeInMillis());
                adapter = new RowAdapter(db.cardDao().queryDate(dayList[0], dayList[1]));

                mRecyclerView.setAdapter(adapter);
            }
        });
    }
}
