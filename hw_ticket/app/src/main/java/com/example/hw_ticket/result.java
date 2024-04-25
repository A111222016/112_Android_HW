package com.example.hw_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView result=(TextView) findViewById(R.id.txtresult);
        result.setText(getIntent().getStringExtra("result"));
    }
}