package com.example.janpatrix.dailydiary.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private Calendar eventCalendar = Calendar.getInstance();
    private RelativeLayout mLayout;
    private int eventIndex;
    private EventLab eventLab;
    private EventObject event1;
    private EventObject event2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        eventCalendar.add(Calendar.HOUR_OF_DAY, 1);
        eventLab = EventLab.get(this);
        event1 = new EventObject(1, "Cool message", calendar.getTime(), eventCalendar.getTime());

        eventCalendar.add(Calendar.HOUR_OF_DAY, 3);

        event2 = new EventObject(2, "HOT message", calendar.getTime(), eventCalendar.getTime());

        eventLab.addEvent(event1);
        eventLab.addEvent(event2);

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
            Date endDate = event.getEnd();
            String eventMessage = event.getMessage();
            int eventBlockHeight = getEventTimeFrame(eventDate, endDate);
            Log.d("TEST", "Height " + eventBlockHeight);
            displayEventSection(eventDate, eventBlockHeight, eventMessage);
        }
    }

    private int getEventTimeFrame(Date start, Date end){
        long timeDifference = end.getTime() - start.getTime();
        Calendar mCal = Calendar.getInstance();
        mCal.setTimeInMillis(timeDifference);
        int hours = mCal.get(Calendar.HOUR);
        int minutes = mCal.get(Calendar.MINUTE);

        return (hours * 60) + ((minutes * 60) / 100);
    }

    private void displayEventSection(Date eventDate, int height, String message){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String displayValue = timeFormatter.format(eventDate);
        String[]hourMinutes = displayValue.split(":");
        int hours = Integer.parseInt(hourMinutes[0]);
        int minutes = Integer.parseInt(hourMinutes[1]);
        Log.d("Test", "Hour value " + hours);
        Log.d("Test", "Minutes value " + minutes);
        int topViewMargin = (hours * 60) + ((minutes * 60) / 100);
        Log.d("Test", "Margin top " + topViewMargin);
        createEventView(topViewMargin,height, message);
    }

    private void createEventView(int topMargin, int height, String message){
        TextView mEventView = new TextView(CalendarActivity.this);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = topMargin * 2;
        lParam.leftMargin = 24;
        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(24, 0, 24, 0);
        mEventView.setHeight(height * 2);
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(message);
        mEventView.setBackgroundColor(Color.parseColor("#3F51B5"));
        mLayout.addView(mEventView, eventIndex - 1);
    }
}
