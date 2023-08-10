package com.example.a2006669;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AmountBreakdownActivity extends AppCompatActivity {
    private EditText tolBillAmtEditText, numOfPeopleEditText;
    private EditText[] indAmtEditTexts;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_breakdown);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        editor = sharedPreferences.edit();


        Button btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmountBreakdownActivity.this, amt_breakdown_hist.class);
                startActivity(intent);

            }
        });



        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tolBillAmtEditText.getText().clear();
                numOfPeopleEditText.getText().clear();
                indAmtEditTexts[0].getText().clear();
                indAmtEditTexts[1].getText().clear();
                indAmtEditTexts[2].getText().clear();
                indAmtEditTexts[3].getText().clear();

            }
        });


        tolBillAmtEditText = findViewById(R.id.editTextTolBill);
        numOfPeopleEditText = findViewById(R.id.editTextTolPeople);
        indAmtEditTexts = new EditText[]{
                findViewById(R.id.editTextP1),
                findViewById(R.id.editTextP2),
                findViewById(R.id.editTextP3),
                findViewById(R.id.editTextP4)
        };
        Button buttonBackMain = findViewById(R.id.btnRtnMain);
        buttonBackMain.setOnClickListener(v -> {
            Intent intent = new Intent(AmountBreakdownActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button buttonCalAmt = findViewById(R.id.btnCalAmt);
        buttonCalAmt.setOnClickListener((v -> {
            calculateAndVerify();
            hideKey();

        }));

    }

    private void hideKey() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMM = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMM.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void calculateAndVerify() {
        String tolBill = tolBillAmtEditText.getText().toString();
        String numPeo = numOfPeopleEditText.getText().toString();
        String result;
        DecimalFormat deciFmt = new DecimalFormat("#.00");

        if (tolBill.isEmpty() || numPeo.isEmpty()) {
            Toast.makeText(this, "Please do not leave blank! ", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double tolBillAmt = Double.parseDouble(tolBill);
            int numOfPeople = Integer.parseInt(numPeo);
            double sumIndividualAmounts = 0.0;
            for (int i = 0; i < numOfPeople; i++) {
                String indAmtStr = indAmtEditTexts[i].getText().toString();
                if (!indAmtStr.isEmpty()) {
                    sumIndividualAmounts += Double.parseDouble(indAmtStr);

                }
            }

            if (sumIndividualAmounts == tolBillAmt) {
                result = "Tally amount!";

            } else {
                double diff = tolBillAmt - sumIndividualAmounts;
                result = "Discrepancy in amount, still owe " + deciFmt.format(diff);
            }

            Toast.makeText(this,result, Toast.LENGTH_LONG).show();

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("Result", result);
            editor.apply();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input is invalid. Please re-enter", Toast.LENGTH_SHORT).show();
        }

    }
}
