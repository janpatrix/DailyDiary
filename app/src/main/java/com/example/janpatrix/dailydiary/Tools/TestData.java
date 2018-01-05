package com.example.janpatrix.dailydiary.Tools;

import android.content.Context;

import com.example.janpatrix.dailydiary.Events.EventLab;
import com.example.janpatrix.dailydiary.Events.EventObject;

import java.util.Calendar;

import static com.example.janpatrix.dailydiary.Tools.helper.convertDateToString;

/**
 * Created by patrickgross on 05.01.18.
 */

public class TestData {

    private EventLab eventLab;
    private EventObject eventDate1;
    private EventObject eventDate2;
    private EventObject event1;
    private EventObject event2;
    private EventObject event3;
    private EventObject event4;
    private EventObject event5;

    private Calendar calendar = Calendar.getInstance();
    private Calendar fromCalendar = Calendar.getInstance();
    private Calendar toCalendar = Calendar.getInstance();

    public void createTestData(Context context) {

        fromCalendar.add(Calendar.HOUR_OF_DAY, 1);
        toCalendar.add(Calendar.HOUR_OF_DAY, 2);
        eventLab = EventLab.get(context);
        eventLab.deleteDatabaseItems();

        eventDate1 = new EventObject(0, convertDateToString(calendar.getTime()), calendar.getTime(), calendar.getTime(), EventObject.DATE_TYPE);
        eventDate2 = new EventObject(0, convertDateToString(calendar.getTime()), calendar.getTime(), calendar.getTime(), EventObject.DATE_TYPE);
        event1 = new EventObject(1, "Cool message", fromCalendar.getTime(), toCalendar.getTime(), EventObject.ITEM_TYPE);
        event2 = new EventObject(2, "HOT message", fromCalendar.getTime(), toCalendar.getTime(), EventObject.EVENT_TYPE);
        event3 = new EventObject(2, "Mega message", fromCalendar.getTime(), toCalendar.getTime(), EventObject.EVENT_TYPE);
        event4 = new EventObject(2, "Giga message", fromCalendar.getTime(), toCalendar.getTime(), EventObject.ITEM_TYPE);
        event5 = new EventObject(2, "Omega message", fromCalendar.getTime(), toCalendar.getTime(), EventObject.EVENT_TYPE);

        eventLab.addEvent(eventDate1);
        eventLab.addEvent(event1);
        eventLab.addEvent(event2);
        eventLab.addEvent(event3);
        eventLab.addEvent(event4);
        eventLab.addEvent(event5);
        eventLab.addEvent(eventDate2);
    }
}
