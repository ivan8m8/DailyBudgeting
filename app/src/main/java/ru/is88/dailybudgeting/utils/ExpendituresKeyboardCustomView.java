package ru.is88.dailybudgeting.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.GridLayout;
import android.view.View;

import ru.is88.dailybudgeting.R;

public class ExpendituresKeyboardCustomView extends GridLayout implements View.OnClickListener {

    // to map the button ids to the symbols that are needed to be displayed
    SparseArray<String> keyValues;

    // communication link to the EditText
    InputConnection inputConnection;

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonPlus;
    private Button buttonBackSpace;
    private Button buttonDone;

    public ExpendituresKeyboardCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ExpendituresKeyboardCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public void onClick(View v) {

    }

    private void init(Context context, AttributeSet attrs){

        keyValues = new SparseArray<>();

        LayoutInflater.from(context).inflate(R.layout.expenditures_keyboard, this, true);
    }

    public void setInputConnection(InputConnection inputConnection){
        this.inputConnection = inputConnection;
    }
}
