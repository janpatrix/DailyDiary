package com.example.janpatrix.dailydiary.Activities;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janpatrix.dailydiary.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.example.janpatrix.dailydiary.RoomEvents.AppDatabase;
import com.example.janpatrix.dailydiary.RoomEvents.Card;

import java.util.List;
import java.util.Locale;

import static com.example.janpatrix.dailydiary.Tools.helper.convertDateToString;

public class CalendarActivity extends AppCompatActivity {

    private ImageView previousDay;
    private ImageView nextDay;
    private TextView currentDate;
    private Calendar calendar = Calendar.getInstance();
    private RelativeLayout mLayout;
    private int eventIndex;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        db = Room.databaseBuilder(this, AppDatabase.class, "database-name").allowMainThreadQueries().build();

        mLayout = findViewById(R.id.left_event_column);
        eventIndex = mLayout.getChildCount();
        currentDate = findViewById(R.id.display_current_date);
        currentDate.setText(convertDateToString(calendar.getTime()));

        displayDailyEvents();

        nextDay = findViewById(R.id.btn_calendar_next);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCalendarDate();
            }
        });

        previousDay = findViewById(R.id.btn_calendar_prev);
        previousDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousCalendarDate();
            }
        });
    }

    private void previousCalendarDate(){
        mLayout.removeViewAt(eventIndex -1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        currentDate.setText(convertDateToString(calendar.getTime()));
        displayDailyEvents();
    }

    private void nextCalendarDate() {
        mLayout.removeViewAt(eventIndex - 1);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        currentDate.setText(convertDateToString(calendar.getTime()));
        displayDailyEvents();
    }

    private void displayDailyEvents(){
        int count = 0;

        List<Card> cards = db.cardDao().getAll();
        for(Card card : cards){
            long eventDate = card.getDate();

            String eventMessage = card.getMessage();
            displayEventSection(eventDate, 100, eventMessage, count);
            count++;
        }
    }

    private void displayEventSection(Long eventDate, int height, String message, int count){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String displayValue = timeFormatter.format(eventDate);
        String[]hourMinutes = displayValue.split(":");
        int hours = Integer.parseInt(hourMinutes[0]);
        int minutes = Integer.parseInt(hourMinutes[1]);
        int topViewMargin = (hours * 60) + ((minutes * 60) / 100);
        createEventView(topViewMargin,height, message, count);
    }

    private void createEventView(int topMargin, int height, final String message, int count){

        TextView mEventView = new TextView(CalendarActivity.this);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = topMargin * 2;
        lParam.leftMargin = 24 + (count * height * 5);

        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(24, 0, 24, 0);
        mEventView.setHeight(height * 2);
        mEventView.setWidth(height * 5);
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(message);
        if (count == 0)
            mEventView.setBackgroundColor(Color.parseColor("#3F51B5"));
        else if(count == 1)
            mEventView.setBackgroundColor(Color.parseColor("#FF4081"));
        else
            mEventView.setBackgroundColor(Color.parseColor("#aaaaaa"));

        mLayout.addView(mEventView, eventIndex - 1);

        mEventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
