package com.example.a2006669;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEqualBreakdown = findViewById(R.id.btnEqualBreakdown);
        Button btnPercentageRatio = findViewById(R.id.btnPercentageRatio);
        Button btnByAmount = findViewById(R.id.btnByAmount);

        btnByAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AmountBreakdownActivity.class);

                startActivity(intent);
            }
        });
        btnPercentageRatio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PercentageCalculationActivity.class);
                startActivity(intent);
            }
        });
        btnEqualBreakdown.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculationActivity.class);
                startActivity(intent);

            }
        });
    }
}