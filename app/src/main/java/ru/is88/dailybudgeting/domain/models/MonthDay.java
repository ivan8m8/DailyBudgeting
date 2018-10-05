package ru.is88.dailybudgeting.domain.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * It's one of those card views.
 */

public class MonthDay {

    /**
     * Id is three values that are: year, month and day
     * example: 20170803
     */
    private int mId;

    private String mAmountString;

    private String mDescription;

    private int day;
    private int month;
    private int year;

    public MonthDay(int id,
                    String amountString,
                    String description){
        mId = id;
        mAmountString = amountString;
        mDescription = description;

        this.year = Integer.parseInt(String.valueOf(id).substring(0, 4));
        this.month = Integer.parseInt(String.valueOf(id).substring(4, 6));
        this.day = Integer.parseInt(String.valueOf(id).substring(6, 8));
    }

    public double getAmount() {
        NumberFormat numberFormat = new DecimalFormat("##.##");
        numberFormat.setMinimumFractionDigits(0);
        double result = 0;
        if (mAmountString.trim().length() > 0) {
            String[] stringAmounts = mAmountString.trim().split("\\s+");
            double[] amounts = new double[stringAmounts.length];
            for (int i = 0; i < stringAmounts.length; i++) {
                amounts[i] = Double.parseDouble(stringAmounts[i]);
                result += amounts[i];
            }
        }
        return Double.parseDouble(numberFormat.format(result));
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getAmountString() {
        return mAmountString;
    }

    public void setAmountString(String amountString) {
        mAmountString = amountString;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
