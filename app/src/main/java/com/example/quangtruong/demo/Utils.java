package com.example.quangtruong.demo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quangtruong.demo.constant.Constant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public static Date getCurrentDate(Date date) {
        try {
            Format formatter = new SimpleDateFormat(Constant.DateTimeFormat.YYYY_MM_DD, Locale.US);
            String s = formatter.format(date);
            SimpleDateFormat fm = new SimpleDateFormat(Constant.DateTimeFormat.YYYY_MM_DD, Locale.US);
            fm.setLenient(false);
            return fm.parse(s.trim());
        } catch (Throwable e) {
            return null;
        }
    }

    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat fm = new SimpleDateFormat(format, Locale.US);
        return fm.format(date);
    }

    public static Date stringToDate(String stringDate, String format) {
        if (stringDate == null || stringDate.trim().length() == 0) {
            return null;
        }
        try {
            SimpleDateFormat fm = new SimpleDateFormat(format, Locale.US);
            fm.setLenient(false);
            return fm.parse(stringDate.trim());
        } catch (ParseException e) {
            return null;
        }
    }

    public static void setError(Context context, View v, TextView tv, ImageView img, String ms) {
        setTextError(context, tv, ms);
        setBackgroundError(context, v);
        setImageError(context, img);
    }

    public static void setDefault(Context context, View v, TextView tv, ImageView img, String ms) {
        setTextDefault(context, tv, ms);
        setBackgroundDefault(context, v);
        setImageDefault(context, img);
    }

    private static void setTextDefault(Context context, TextView tvError, String messenger) {
        if (tvError != null) {
            tvError.setTextColor(ContextCompat.getColor(context, R.color.gray));
            tvError.setText(messenger);
        }
    }

    private static Locale getCurrentLocale(Context context) {
        return Locale.getDefault();
    }

    public static void disableDaysInWeek(DatePickerDialog datePickerDialog, int daysToCount, int[] disableDays) {
        Calendar mon, tue, wed, thurs, fri, saturday, sunday;
        boolean isMon = false, isTue = false, isWed = false, isThurs = false, isFri = false, isSat = false, isSun = false;
        List<Calendar> weekends = new ArrayList<>();
        for (int disableDay : disableDays) {
            switch (disableDay) {
                case 2:
                    isMon = true;
                    break;
                case 3:
                    isTue = true;
                    break;
                case 4:
                    isWed = true;
                    break;
                case 5:
                    isThurs = true;
                    break;
                case 6:
                    isFri = true;
                    break;
                case 7:
                    isSat = true;
                    break;
                case 8:
                    isSun = true;
                    break;
            }
        }
        for (int i = 0; i < daysToCount; i = i + 7) {
            if (isMon) {
                mon = Calendar.getInstance();
                mon.add(Calendar.DAY_OF_YEAR, (Calendar.MONDAY - mon.get(Calendar.DAY_OF_WEEK) + 7 + i));
                weekends.add(mon);
            }
            if (isTue) {
                tue = Calendar.getInstance();
                tue.add(Calendar.DAY_OF_YEAR, (Calendar.TUESDAY - tue.get(Calendar.DAY_OF_WEEK) + 7 + i));
                weekends.add(tue);
            }
            if (isWed) {
                wed = Calendar.getInstance();
                wed.add(Calendar.DAY_OF_YEAR, (Calendar.WEDNESDAY - wed.get(Calendar.DAY_OF_WEEK) + 7 + i));
                weekends.add(wed);
            }
            if (isThurs) {
                thurs = Calendar.getInstance();
                thurs.add(Calendar.DAY_OF_YEAR, (Calendar.THURSDAY - thurs.get(Calendar.DAY_OF_WEEK) + 7 + i));
                weekends.add(thurs);
            }
            if (isFri) {
                fri = Calendar.getInstance();
                fri.add(Calendar.DAY_OF_YEAR, (Calendar.FRIDAY - fri.get(Calendar.DAY_OF_WEEK) + 7 + i));
                weekends.add(fri);
            }
            if (isSat) {
                saturday = Calendar.getInstance();
                saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK) + i));
                weekends.add(saturday);
            }
            if (isSun) {
                sunday = Calendar.getInstance();
                sunday.add(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - sunday.get(Calendar.DAY_OF_WEEK) + 7 + i));
                weekends.add(sunday);
            }
        }
        Calendar[] disabledDays = weekends.toArray(new Calendar[weekends.size()]);
        datePickerDialog.setDisabledDays(disabledDays);
    }

    public static String getDayName(Date date, Context context) {
        return new SimpleDateFormat("EEEE", getCurrentLocale(context)).format(date);
    }

    private static void setTextError(Context context, TextView tvError, String messenger) {
        if (tvError != null) {
            tvError.setTextColor(ContextCompat.getColor(context, R.color.text_error_color));
            tvError.setText(messenger);
        }
    }

    private static void setBackgroundError(Context context, View view) {
        if (view != null)
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.et_bg_error));
    }

    private static void setBackgroundDefault(Context context, View view) {
        if (view != null)
            view.setBackground(ContextCompat.getDrawable(context, Utils.getEditTextBackground(context)));
    }

    private static void setImageError(Context context, ImageView imageView) {
        if (imageView != null)
            ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context, R.color.text_error_color)));
    }

    private static void setImageDefault(Context context, ImageView imageView) {
        if (imageView != null)
            ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context, R.color.gray)));
    }

    public static String format(String message, Object... objects) {
        MessageFormat form = new MessageFormat(message);
        return form.format(objects);
    }


    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (getTheme(activity)) {
            case Constant.Theme.THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case Constant.Theme.THEME_PURPLE:
                activity.setTheme(R.style.AppTheme2);
                break;
            default:
                activity.setTheme(R.style.AppTheme);
                break;
        }
    }

    public static void saveTheme(Context context, String theme) {
        SharedPreferences pref = context.getSharedPreferences(Constant.SharePreference.SHARE_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(Constant.SharePreference.PREF_THEME, theme);
        edit.apply();
    }

    public static String getTheme(Context context) {
        try {
            SharedPreferences pref = context.getSharedPreferences(Constant.SharePreference.SHARE_PREF_NAME, MODE_PRIVATE);
            return pref.getString(Constant.SharePreference.PREF_THEME, Constant.Theme.THEME_DEFAULT);
        } catch (Throwable e) {
            Log.e(TAG, "getTheme: ", e);
            return Constant.Theme.THEME_DEFAULT;
        }
    }

    public static int getColorPrimary(Context context) {
        if (Constant.Theme.THEME_DEFAULT.equals(getTheme(context))) {
            return ContextCompat.getColor(context, R.color.colorPrimary);
        } else {
            return ContextCompat.getColor(context, R.color.colorPrimary2);
        }
    }

    public static Drawable getButtonBackground(Context context) {
        if (Constant.Theme.THEME_DEFAULT.equals(getTheme(context))) {
            return ContextCompat.getDrawable(context, R.drawable.btn_bg);
        } else {
            return ContextCompat.getDrawable(context, R.drawable.btn_bg_purple);
        }
    }

    public static int getEditTextBackground(Context context) {
        if (Constant.Theme.THEME_DEFAULT.equals(getTheme(context))) {
            return R.drawable.et_bg;
        } else {
            return R.drawable.et_bg_purple;
        }
    }
}
