package ru.is88.dailybudgeting.domain.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Cell {

    private String mValue;

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

    public void setValue(String value) {
        mValue = value;
    }
}
