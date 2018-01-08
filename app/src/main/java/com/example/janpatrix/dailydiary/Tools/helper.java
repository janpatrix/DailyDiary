package com.example.janpatrix.dailydiary.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by patrickgross on 05.01.18.
 */

public class helper {

    public static String convertDateToString(Date mDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd", Locale.ENGLISH);
        return dateFormat.format(mDate);
    }
}
