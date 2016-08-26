package bearkid.com.bearkiddiaryfamily.utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by YarenChoi on 2016/7/29.
 * 日期选择器、时间选择器工具类
 *
 */
public class DateTimePickerUtil {
    public static final String TAG = "DateTimePickerUtil";

    private static final DecimalFormat decimalFormat = new DecimalFormat("00");

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     *
     * @param context The context the dialog is to run in.
     * @param onDateSetListener How the parent is notified that the date is set.
     */
    public static void showDatePicker(Context context, OnDateSetListener onDateSetListener) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance();
                    cal.set(year, monthOfYear, dayOfMonth);
                    onDateSetListener.onDateSet(getFormatDate(year, monthOfYear, dayOfMonth), cal.getTimeInMillis());
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     *
     * @param context The context the dialog is to run in.
     * @param onMonthSetListener How the parent is notified that the date is set.
     */
    public static void showMonthPicker(Context context, OnMonthSetListener onMonthSetListener) {
        Calendar calendar = Calendar.getInstance();
        MonthPickerDialog monthPickerDialog = new MonthPickerDialog(
                context,
                (view, year, monthOfYear, dayOfMonth) ->
                        onMonthSetListener.onMonthSet(getFormatDate(year, monthOfYear)),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        monthPickerDialog.show();
    }

    /**
     *
     * @param context The context the dialog is to run in.
     * @param calendar 传入Calendar对象，方法中对其修改赋值为用户选择后的Calendar对象
     * @param onWeekSetListener 通知用户刷新UI
     */
    public static void showWeekAndDatePicker(Context context, Calendar calendar, OnWeekSetListener onWeekSetListener) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(year, monthOfYear, dayOfMonth);
                    onWeekSetListener.onWeekSet();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     *
     * @param context The context the dialog is to run in.
     * @param onTimeSetListener How the parent is notified that the time is set.
     */
    public static void showTimePicker(Context context, OnTimeSetListener onTimeSetListener) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                context,
                (view, hourOfDay, minute) ->
                        onTimeSetListener.onTimeSet(getFormatTime(hourOfDay, minute)),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false);
        timePickerDialog.show();
    }

    /**
     *
     * @param calendar 传入选择好的Calendar对象
     * @return 格式化后的日期，例 星期四 2016-08-04
     */
    public static String getFormatDate(Calendar calendar) {
        String date = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                date += "星期一 ";
                break;
            case Calendar.TUESDAY:
                date += "星期二 ";
                break;
            case Calendar.WEDNESDAY:
                date += "星期三 ";
                break;
            case Calendar.THURSDAY:
                date += "星期四 ";
                break;
            case Calendar.FRIDAY:
                date += "星期五 ";
                break;
            case Calendar.SATURDAY:
                date += "星期六 ";
                break;
            case Calendar.SUNDAY:
                date += "星期日 ";
                break;
            default:
                break;
        }
        date = date +
                calendar.get(Calendar.YEAR) + "-" +
                decimalFormat.format(calendar.get(Calendar.MONTH)+1) + "-" +
                decimalFormat.format(calendar.get(Calendar.DAY_OF_MONTH));
        return date;
    }

    /**
     *
     * @param year 年份
     * @param monthOfYear 月份（需+1）
     * @param dayOfMonth 日期
     * @return 格式化后的日期字符串，例 2016-01-01
     */
    public static String getFormatDate(int year, int monthOfYear, int dayOfMonth) {
        return year + "-" + decimalFormat.format(monthOfYear + 1) + "-" + decimalFormat.format(dayOfMonth);
    }

    /**
     *
     * @param year 年份
     * @param monthOfYear 月份（需+1）
     * @return 格式化后的年/月字符串，例 2016-01
     */
    public static String getFormatDate(int year, int monthOfYear) {
        return year + "-" + decimalFormat.format(monthOfYear + 1);
    }

    /**
     *
     * @param time 毫秒级时间
     * @return 格式化后的日期字符串，例 2016-01-01
     */
    public static String getFormatDate(long time) {
        Date birthday = new Date(time);
        return dateFormat.format(birthday);
    }

    /**
     *
     * @param hour 小时
     * @param minute 分钟
     * @return 格式化后的时/分，例 09:00
     */
    public static String getFormatTime(int hour, int minute) {
        return decimalFormat.format(hour) + ":" + decimalFormat.format(minute);
    }


    /**
     *
     * @param startTime 已格式化的起始日期字符串
     * @param endTime 已格式化的结束日期字符串
     * @return 日期相减天数
     */
    public static long getComparedDayCount(String startTime, String endTime) {
        long dayCount = 0;
        try {
            dayCount = dateFormat.parse(startTime).getTime()
                    - dateFormat.parse(endTime).getTime()
                    /(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "parse date failed!");
        }
        return dayCount;
    }

    public interface OnDateSetListener {
        void onDateSet(String date, long realTime);
    }

    public interface OnMonthSetListener {
        void onMonthSet(String monthOfYear);
    }

    public interface OnWeekSetListener {
        void onWeekSet();
    }

    public interface OnTimeSetListener {
        void onTimeSet(String time);
    }

    /**
     * 自定义年月选择器对话框
     */
    private static class MonthPickerDialog extends DatePickerDialog {
        private static final String TITLE = "请选择年月";

        public MonthPickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
            super(context, callBack, year, monthOfYear, dayOfMonth);
            hidDay(getDatePicker());
        }

        private void hidDay(DatePicker mDatePicker) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //高版本无法隐藏日期选择
                int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
                if (daySpinnerId != 0) {
                    Log.e(TAG, "daySpinnerId != 0");
                    View daySpinner = getDatePicker().findViewById(daySpinnerId);
                    if (daySpinner != null) {
                        Log.e(TAG, "daySpinner != null");
                        daySpinner.setVisibility(View.GONE);
                    }
                }
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                setTitle(TITLE);
                //低版本无法更改颜色
                //((ViewGroup) ((ViewGroup) datePickerDialog.getDatePicker().getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
                Field[] datePickerfFields = mDatePicker.getClass().getDeclaredFields();
                for (Field datePickerField : datePickerfFields) {
                    if ("mDaySpinner".equals(datePickerField.getName())) {
                        datePickerField.setAccessible(true);
                        Object dayPicker = new Object();
                        try {
                            dayPicker = datePickerField.get(mDatePicker);
                        } catch (IllegalAccessException | IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        ((View) dayPicker).setVisibility(View.GONE);
                    }
                }
            }
        }

        @Override
        public void onDateChanged(DatePicker view, int year, int month, int day) {
            getDatePicker().init(year, month, day, this);
            setTitle(TITLE);
        }
    }

}
