package com.example.administrator.baseproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.greenrobot.event.EventBus;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post("123");
            }
        });

    }
}
