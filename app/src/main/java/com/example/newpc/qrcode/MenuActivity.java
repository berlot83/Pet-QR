package com.example.newpc.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    ImageView gen, scan, about, exit, cloud;
    TextView response;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);

            gen = findViewById(R.id.gen); // deshuso
            scan = findViewById(R.id.scan);
            cloud = findViewById(R.id.cloud);
            about = findViewById(R.id.about);
            exit = findViewById(R.id.exit);

            scan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent rIntent = new Intent(MenuActivity.this, ReaderActivity.class);
                    startActivity(rIntent);
                }
            });

            cloud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent rIntent = new Intent(MenuActivity.this, ActivityCloudTransition.class);
                    startActivity(rIntent);
                }
            });

            about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                    startActivity(intent);
                }
            });

            gen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                    startActivity(intent);
                }
            });

            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.exit(0);
                }
            });
            setupActionBar();
        }

    private void setupActionBar(){
        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.hide();
        }
    }

}
