package ru.is88.dailybudgeting.utils;

import java.util.Calendar;

public final class Utils {

    public static final String LOG_TAG = "KSI";

    public static final int DEFAULT_VALUE = -1008;

    public static final String AMOUNT_REGEX = "^\\d+(.\\d+)?(\\s\\d+(.\\d+)?)*$";

    public static final String MONTH_KEY = "month_key";
    public static final String YEAR_KEY = "year_key";

    public static final String ID_KEY = "ID_key";
    public static final String POSITION_KEY = "position_key";

    /**
     * The amount of pages within the viewpager.
     * In this app, this value is equal to the number of months in a year.
     * Pages are 0-indexed!
     */
    public static final int VIEW_PAGER_ITEMS_COUNT = 12;

    /**
     * Since I want user to have ability to watch previous and future months,
     * it' needed to start mView pager from the middle items position.
     */
    public static final int VIEW_PAGER_START_POSITION = 5;

    public static int buildMonthDayID(int year, int month, int day) {

        String idString = String.valueOf(year) +
                String.valueOf(month < 10 ? "0" : "") + month +
                String.valueOf(day < 10 ? "0" : "") + day;
        return Integer.parseInt(idString);
    }


    // returns a 0-indexed calendar instance
    public static Calendar getActualCalendarByViewPagerPosition(int viewPagerPosition) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, viewPagerPosition - VIEW_PAGER_START_POSITION);
        return calendar;
    }
}
