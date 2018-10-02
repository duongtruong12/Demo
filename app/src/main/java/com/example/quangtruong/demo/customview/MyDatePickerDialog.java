package com.example.quangtruong.demo.customview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;

import com.example.quangtruong.demo.Utils;

import java.util.Date;

public class MyDatePickerDialog extends DatePickerDialog {

    public MyDatePickerDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, listener, year, monthOfYear, dayOfMonth);
        Window window = this.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
        Date date = Utils.getCurrentDate(new Date());
        if (date != null)
            getDatePicker().setMinDate(date.getTime());
    }

    @Override
    public void setView(View view) {
        super.setView(view);
    }

    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
    }


    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }
}