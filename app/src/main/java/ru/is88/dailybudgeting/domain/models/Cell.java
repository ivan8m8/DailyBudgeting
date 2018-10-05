package ru.is88.dailybudgeting.domain.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * It's almost 100% similar to String,
 * but this class has getDouble method that sums up all the Cell values
 */

public class Cell {

    private String mValue;

    public Cell(String amountString) {
        mValue = amountString;
    }

    public String getValue() {
        return mValue;
    }

    public double getDouble() {
        double result = 0;
        NumberFormat numberFormat = new DecimalFormat("##.##");
        if (mValue.trim().length() > 0) {
            String[] stringAmounts = mValue.trim().split("\\s+");
            double[] amounts = new double[stringAmounts.length];
            for (int i=0; i<stringAmounts.length; i++) {
                amounts[i] = Double.parseDouble(stringAmounts[i]);
                result += amounts[i];
            }
        }
        return Double.parseDouble(numberFormat.format(result));
    }
}
