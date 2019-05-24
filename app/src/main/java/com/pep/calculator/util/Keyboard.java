package com.pep.calculator.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pep.calculator.R;


public class Keyboard extends LinearLayout implements View.OnClickListener {



    public Keyboard(Context context) {
        this(context, null, 0);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Keyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        find(context);

    }




    SparseArray<String> keyValues = new SparseArray<>();

    InputConnection inputConnection;


    private void find(Context context) {

        TextView mButton1;
        TextView mButton2;
        TextView mButton3;
        TextView mButton4;
        TextView mButton5;
        TextView mButton6;
        TextView mButton7;
        TextView mButton8;
        TextView mButton9;
        TextView mButton0;

        TextView mSum;
        TextView mSubtraction;
        TextView mMultply;
        TextView mDivision;


        ImageView mButtonDelete;
        TextView mButtonPunto;



        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        mButton1 = findViewById(R.id.b1keyboard);
        mButton2 = findViewById(R.id.b2keyboard);
        mButton3 = findViewById(R.id.b3keyboard);
        mButton4 = findViewById(R.id.b4keyboard);
        mButton5 = findViewById(R.id.b5keyboard);
        mButton6 = findViewById(R.id.b6keyboard);
        mButton7 = findViewById(R.id.b7keyboard);
        mButton8 = findViewById(R.id.b8keyboard);
        mButton9 = findViewById(R.id.b9keyboard);
        mButton0 =  findViewById(R.id.b0keyboard);
        mButtonDelete =  findViewById(R.id.delete_keyboard);
        mButtonPunto = findViewById(R.id.punto_keyboard);
        mSum = findViewById(R.id.sum);
        mSubtraction =  findViewById(R.id.subtraction);
        mMultply =  findViewById(R.id.multiply);
        mDivision = findViewById(R.id.division);





        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton0.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
        mButtonPunto.setOnClickListener(this);
        mSum.setOnClickListener(this);
        mSubtraction.setOnClickListener(this);
        mMultply.setOnClickListener(this);
        mDivision.setOnClickListener(this);


        keyValues.put(R.id.b1keyboard, "1");
        keyValues.put(R.id.b2keyboard, "2");
        keyValues.put(R.id.b3keyboard, "3");
        keyValues.put(R.id.b4keyboard, "4");
        keyValues.put(R.id.b5keyboard, "5");
        keyValues.put(R.id.b6keyboard, "6");
        keyValues.put(R.id.b7keyboard, "7");
        keyValues.put(R.id.b8keyboard, "8");
        keyValues.put(R.id.b9keyboard, "9");
        keyValues.put(R.id.b0keyboard, "0");
        keyValues.put(R.id.punto_keyboard, ".");

        keyValues.put(R.id.sum, "+");
        keyValues.put(R.id.subtraction, "-");
        keyValues.put(R.id.multiply, "*");
        keyValues.put(R.id.division, "/");


    }


    @Override

    public void onClick(View v) {

        if (inputConnection == null) return;

        if (v.getId() == R.id.delete_keyboard) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {

                inputConnection.deleteSurroundingText(1, 0);
            } else {

                inputConnection.commitText("", 1);
            }
        } else {
            String value = keyValues.get(v.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }
}
