package com.example.a2006669;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pct_share_history extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView tvRecord;

    private SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pct_share_history);

        sharedPreferences = getSharedPreferences("Preferences",MODE_PRIVATE);
        tvRecord = findViewById(R.id.tvRecord);

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                clearHist();

            }
        });


        String history = sharedPreferences.getString("SharesByPercentage", "");
        String[] sharesArray = history.split(",");

        StringBuilder fmtHist = new StringBuilder();
        for (String share: sharesArray){
            fmtHist.append("Share: ").append(share).append("\n");


        }

        tvRecord.setText(fmtHist.toString());




    }

    private void clearHist() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("SharesByPercentage");
        editor.apply();

        tvRecord.setText("Records are cleared");

    }


}

