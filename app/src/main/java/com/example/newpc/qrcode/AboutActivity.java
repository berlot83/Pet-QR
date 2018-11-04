package com.example.newpc.qrcode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageView = findViewById(R.id.molokotech);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://molokotech.com"));
                startActivity(openBrowser);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        imageView.startAnimation(animation);

    }



}
