package com.example.newpc.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.Toast;


public class ActivityCloudTransition extends AppCompatActivity {

    private ImageButton get_lost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_transition);
    get_lost = findViewById(R.id.get_lost);

    get_lost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final Intent newActivity = new Intent(ActivityCloudTransition.this, ActivityCloudGet.class);
            startActivity(newActivity);
        }
    });
    }
}
