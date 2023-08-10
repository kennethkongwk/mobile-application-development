package com.example.a2006669;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class amt_breakdown_hist extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private TextView resultTextView;

    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.amt_breakdown_hist);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        resultTextView = findViewById(R.id.resultTextView);

        String resultSaved = sharedPreferences.getString("Result", "");

        resultTextView.setText(resultSaved);


    }
}
