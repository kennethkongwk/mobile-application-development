package com.example.a2006669;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PercentageCalculationActivity extends AppCompatActivity {
    private EditText etTotalAmount, etPercentages1, etPercentages2, etPercentages3;
    private TextView tvPerson1, tvPerson2, tvPerson3;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percentage_share);
        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Find their ID
        etTotalAmount = findViewById(R.id.etTotalAmount);
        etPercentages1 = findViewById(R.id.etPercentage1);
        etPercentages2 = findViewById(R.id.etPercentage2);
        etPercentages3 = findViewById(R.id.etPercentage3);
        tvPerson1 = findViewById(R.id.tvPerson1);
        tvPerson2 = findViewById(R.id.tvPerson2);
        tvPerson3 = findViewById(R.id.tvPerson3);




        //button to reset
        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etTotalAmount.getText().clear();
                etPercentages1.getText().clear();
                etPercentages2.getText().clear();
                etPercentages3.getText().clear();
                tvPerson1.setText("");
                tvPerson2.setText("");
                tvPerson3.setText("");


            }
        });

        // button to save and edit history
        Button btnHist = findViewById(R.id.btnHist);

        btnHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PercentageCalculationActivity.this, pct_share_history.class);
                startActivity(intent);

            }
        });

        //button back to main page
        Button btnRtnToMain = findViewById(R.id.btnRtnToMain);

        //button to calculate percentage
        Button btnPercentCal = findViewById(R.id.btnPercentCal);

        //handle the calculation button
        btnPercentCal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                calculateAndDisplay();
                hideKey();

            }
        });

        // back to main page
        btnRtnToMain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Intent to navigate back to MainActivity
                Intent intent = new Intent(PercentageCalculationActivity.this, MainActivity.class);
                startActivity(intent);
                finish ();

            }
        });

    }
//save history

    private void hideKey() {
        View view = getCurrentFocus();
        if(view != null){
            InputMethodManager inputMM = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMM.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    //calculation

    private void calculateAndDisplay() {

        DecimalFormat deciFmt = new DecimalFormat("#.00");


        double totalAmount = Double.parseDouble(etTotalAmount.getText().toString());
        double percentage1 = Double.parseDouble(etPercentages1.getText().toString());
        double percentage2 = Double.parseDouble(etPercentages2.getText().toString());
        double percentage3 = Double.parseDouble(etPercentages3.getText().toString());


        double[]sharesByPercentage = new double[3];

        sharesByPercentage[0] = (percentage1/100) * totalAmount;
        sharesByPercentage[1] = (percentage2/100) * totalAmount;
        sharesByPercentage[2] = (percentage3/100) * totalAmount;

        tvPerson1.setText("Person 1: "+ deciFmt.format(sharesByPercentage[0]));
        tvPerson2.setText("Person 2: "+ deciFmt.format(sharesByPercentage[1]));
        tvPerson3.setText("Person 3: "+ deciFmt.format(sharesByPercentage[2]));

        // Convert sharesByPercentage array to a string
        StringBuilder sharesStringBuilder = new StringBuilder();
        for (double share : sharesByPercentage) {
            sharesStringBuilder.append(String.valueOf(share)).append(",");
        }
        String sharesString = sharesStringBuilder.toString();

        // Save the formatted string in SharedPreferences
        editor.putString("SharesByPercentage", sharesString);
        editor.apply();

    }
}
