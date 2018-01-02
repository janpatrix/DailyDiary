package com.example.janpatrix.dailydiary.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.janpatrix.dailydiary.Events.EventLab;
import com.example.janpatrix.dailydiary.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.example.janpatrix.dailydiary.Events.EventObject;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private ImageView previousDay;
    private ImageView nextDay;
    private TextView currentDate;
    private Calendar calendar = Calendar.getInstance();
    private RelativeLayout mLayout;
    private int eventIndex;
    private EventLab eventLab;
    private EventObject event1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        eventLab = EventLab.get(this);
        event1 = new EventObject(1, "Cool message", calendar.getTime(), calendar.getTime());

        eventLab.addEvent(event1);
        mLayout = findViewById(R.id.left_event_column);
        eventIndex = mLayout.getChildCount();
        currentDate = findViewById(R.id.display_current_date);
        currentDate.setText(convertDateToString(calendar.getTime()));

        displayDailyEvents();

        nextDay = findViewById(R.id.next_day);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCalendarDate();
            }
        });

        previousDay = findViewById(R.id.previous_day);
        previousDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousCalendarDate();
            }
        });
        nextDay = findViewById(R.id.next_day);

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

    private String convertDateToString(Date mDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
        return dateFormat.format(mDate);
    }

    private void displayDailyEvents(){
        Date calendarDate = calendar.getTime();
        List<EventObject> dailyEvents = eventLab.getEvents();
        for(EventObject event : dailyEvents){
            Date eventDate = event.getDate();
        }
    }
}
