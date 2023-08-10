package com.example.a2006669;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class cal_share_history extends AppCompatActivity {
    private TextView tvRecord;
    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_share_history);

        Button btnClear = findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                clearRecord();
            }
        });

        //initialization
        sharedPreferences = getSharedPreferences("Preferences",MODE_PRIVATE);

        tvRecord = findViewById(R.id.tvRecord);

        //Load to display the record
        String history = sharedPreferences.getString("Record", "");
        List<String> historyList = Arrays.asList(history.split(","));
        StringBuilder forHist = new StringBuilder();

        for(int i =0; i<historyList.size();i++){
            String entry = historyList.get(i);
            if(!entry.isEmpty()){
                double val = Double.parseDouble(entry);
                forHist.append(i+1).append(". ").append(twoDec(val)).append("\n");

            }
        }
        tvRecord.setText(forHist.toString());
    }
    private String twoDec(double val){
        DecimalFormat decFmt = new DecimalFormat("#.00");
        return decFmt.format(val);
    }
    private void clearRecord() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Record", "");
        editor.apply();

        tvRecord.setText("");
    }
}
