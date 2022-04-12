package com.example.todoapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    public static SimpleDateFormat BASIC_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public static void pickDate(Activity activity, DatePickerDialog.OnDateSetListener listener) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                activity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

}

