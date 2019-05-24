package com.pep.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pep.calculator.util.Keyboard;
import com.pep.calculator.util.Math;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mResult;
    private TextView mTxtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Keyboard keyboard = findViewById(R.id.keyboard_view);
        mEditText = keyboard.findViewById(R.id.editText_keyboard);
        mResult = keyboard.findViewById(R.id.result);
        mTxtResult = findViewById(R.id.txt_result);
        LinearLayout mLinear = keyboard.findViewById(R.id.linear_vertical);
        mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        mEditText.setTextIsSelectable(true);
        mEditText.setShowSoftInputOnFocus(false);
        InputConnection ic = mEditText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        ImageView deleteKey = keyboard.findViewById(R.id.delete_keyboard);

        deleteKey.setOnLongClickListener(view -> {
            mEditText.setText("");
            return false;
        });



        mResult.setOnClickListener(view ->{
            MathOperation(mEditText.getText().toString());
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = s.toString();
                if (!TextUtils.isEmpty(s)) {
                    if (text.contains("*") || text.contains("+") || text.contains("-") || text.contains("/")) {
                        mLinear.setVisibility(View.GONE);
                    } else {
                        mLinear.setVisibility(View.VISIBLE);
                    }
                } else {
                    mLinear.setVisibility(View.VISIBLE);
                    mTxtResult.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }



    private void MathOperation(String text) {
        Math math = new Math();
        String [] readText = math.readText(text);
        if (readText != null) {
            try {
                double result = math.mathOperation(readText);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.result));
                stringBuilder.append(result);
                mTxtResult.setText(stringBuilder);

            } catch (Exception e) {
                showToast(getString(R.string.invalid));
            }
        }  else {
            showToast(getString(R.string.invalid));
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
