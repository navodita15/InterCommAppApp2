package com.example.addsubsecondapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datasharing.ProcessData;
import com.example.datasharing.ReceiveData;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int result;
    private String action;
    private int a;
    private int b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: here");


        IntentFilter intentFilter = new IntentFilter("com.example.addsubsecondapp");
        if (intentFilter != null) {
            Log.d(TAG, "Intent is not null");
            Intent mIntent = getIntent();
            if (mIntent != null) {

                action = mIntent.getStringExtra("action");
                a = mIntent.getIntExtra("firstNum", -1);
                b = mIntent.getIntExtra("secondNum", -1);

                if (action != null && action.equalsIgnoreCase("ADDITION")) {
                    result = ProcessData.getSum(a, b);
                } else if (action != null && action.equalsIgnoreCase("SUBTRACTION")) {
                    result = ProcessData.getDifference(a, b);
                }
            }
        }


    }

    @Override
    public void onBackPressed() {
        new ReceiveData(getApplicationContext(), action, a, b, result, "com.example.datasharing");
        super.onBackPressed();
    }

}