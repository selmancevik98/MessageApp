package com.hebun.messageapp.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeManager {

    // Şu anki tarihi döndüren metod
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String currentDate = dateFormat.format(calendar.getTime());

        return currentDate;
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        String currentTime = timeFormat.format(calendar.getTime());

        return currentTime;
    }
}
