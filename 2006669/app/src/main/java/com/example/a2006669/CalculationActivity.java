package com.example.a2006669;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculationActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        //initialize SharedPreference

        sharedPreferences =getSharedPreferences("Preferences",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        // Find the EditTexts and TextView by their IDs
        EditText etTotalAmount = findViewById(R.id.etTotalAmount);
        EditText etTotalPeople = findViewById(R.id.etTotalPeople);
        TextView tvResult = findViewById(R.id.tvResult);
        Button calShare = findViewById(R.id.calShare);
        Button btnBackToMainPage = findViewById(R.id.btnBackToMainPage);
        Button reset = findViewById(R.id.reset);
        Button btnHist = findViewById(R.id.btnHist);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTotalAmount.getText().clear();
                etTotalPeople.getText().clear();

                tvResult.setText(" ");
                tvResult.setError(null);
            }
        });

        btnHist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculationActivity.this, cal_share_history.class);
                startActivity(intent);
            }
        });


        calShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditTexts
                String totalAmountString = etTotalAmount.getText().toString();
                String totalPeopleString = etTotalPeople.getText().toString();

                // Check empty field
                if(totalAmountString.isEmpty() || totalPeopleString.isEmpty()){
                    tvResult.setText("Please DO NOT leave it blank!");
                    return;

                }
                // Convert the entered data to numeric values
                double totalAmount = Double.parseDouble(totalAmountString);
                int totalPeople = Integer.parseInt(totalPeopleString);

                // Calculate the equal share
                double equalShare = totalAmount / totalPeople;

                DecimalFormat deciFmt = new DecimalFormat("#.00");
                tvResult.setText("Equal Share: " + deciFmt.format(equalShare));

                //Save calculated result
                saveHistory(String.valueOf(equalShare));


            }
        });
        //back to main page button
        btnBackToMainPage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculationActivity.this, MainActivity.class);
                startActivity(intent);

                finish();

            }
        });
    }

    private void saveHistory(String equalShare) {


        SharedPreferences.Editor editor = sharedPreferences.edit();
        String curHist = sharedPreferences.getString("Record", "");
        curHist = curHist + equalShare +",";
        editor.putString("Record", curHist);
        editor.apply();


    }
}
