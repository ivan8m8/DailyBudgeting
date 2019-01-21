package ru.is88.dailybudgeting;

import java.util.Calendar;

public final class Utils {

    public static final String LOG_TAG = "KSI";

    public static final int DEFAULT_VALUE = -1008;

    public static final String AMOUNT_REGEX = "^\\d+(.\\d+)?(\\s\\d+(.\\d+)?)*$";

    public static final String MONTH_KEY = "month_key";
    public static final String YEAR_KEY = "year_key";

    // These two are the fragment positions within AccountsViewPager
    // They are needed for safely using of android:switcher + ViewPager + FragmentPosition
    // within AccountsActivity.onItemAdded
    public static final int INCOME_PAGE_FRAGMENT_ID = 0;
    public static final int FIXED_EXPENSES_PAGE_FRAGMENT_ID = 1;

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

    public static class Pair<L, R> {

        private L left;
        private R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public R getRight() {
            return right;
        }
    }
}