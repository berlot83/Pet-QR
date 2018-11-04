package com.example.newpc.qrcode;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    TextView charging;
    int mProgressStatus;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.progress_bar);
        charging = findViewById(R.id.charging);

        /* Progress bar manager */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while( mProgressStatus < 100){
                        mProgressStatus++;
                        android.os.SystemClock.sleep(50);
                        handler.post(new Runnable(){

                            @Override
                            public void run() {
                                bar.setProgress(mProgressStatus);
                            }
                        });
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            bar.setVisibility(View.INVISIBLE);
                            charging.setVisibility(View.INVISIBLE);
                            finish();
                            Intent newActivity = new Intent(MainActivity.this, MenuActivity.class);
                            startActivity(newActivity);

                        }
                    });
                }
            }).start();

            setupActionBar();
    }

    private void setupActionBar(){
        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.hide();
        }
    }
}
