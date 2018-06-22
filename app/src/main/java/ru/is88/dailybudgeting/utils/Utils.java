package ru.is88.dailybudgeting.utils;

public final class Utils {

    public static final String LOG_TAG = "KSI";

    /**
     * The amount of pages within the viewpager.
     * In this app, this value is equal to the number of months in a year.
     * Pages are 0-indexed!
     */
    public static final int VIEW_PAGER_ITEMS_COUNT = 12;

    /**
     * Since I want user to have ability to watch previous and future months,
     * it' needed to start view pager from the middle items position.
     */
    public static final int VIEW_PAGER_START_POSITION = 5;
}
