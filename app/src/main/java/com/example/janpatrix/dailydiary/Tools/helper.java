package com.example.janpatrix.dailydiary.Tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by patrickgross on 05.01.18.
 */

public class helper {

    public static String convertDateToString(Date mDate){

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd", Locale.ENGLISH);
        return dateFormat.format(mDate);
    }

    public static long[] getDateDay(long date){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);

        long[] dateList = new long[2];

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        long beginning = cal.getTimeInMillis();

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        long end = cal.getTimeInMillis();

        dateList[0] = beginning;
        dateList[1] = end;

        return dateList;
    }
}
