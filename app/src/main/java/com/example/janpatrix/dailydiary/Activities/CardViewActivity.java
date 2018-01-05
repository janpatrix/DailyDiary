package com.example.janpatrix.dailydiary.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.janpatrix.dailydiary.Adapter.RowAdapter;

import com.example.janpatrix.dailydiary.Events.EventLab;
import com.example.janpatrix.dailydiary.R;
import com.example.janpatrix.dailydiary.Tools.TestData;


/**
 * Created by patrickgross on 05.01.18.
 */

public class CardViewActivity extends AppCompatActivity {

    private EventLab eventLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        eventLab = EventLab.get(this);

        RowAdapter adapter = new RowAdapter(eventLab.getEvents());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }
}
