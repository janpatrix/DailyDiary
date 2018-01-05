package com.example.janpatrix.dailydiary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.janpatrix.dailydiary.R;
import com.example.janpatrix.dailydiary.Tools.TestData;

/**
 * Created by patrickgross on 05.01.18.
 */

public class MenuViewActivity extends AppCompatActivity {

    private TestData testData;
    private Button btn_card_view;
    private Button btn_calendar_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        testData = new TestData();
        testData.createTestData(this);

        btn_calendar_view = findViewById(R.id.btn_menu_calendar_view);
        btn_calendar_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        btn_card_view = findViewById(R.id.btn_menu_card_view);
        btn_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CardViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
