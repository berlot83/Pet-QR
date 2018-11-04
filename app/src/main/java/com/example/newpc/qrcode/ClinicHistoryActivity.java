package com.example.newpc.qrcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClinicHistoryActivity extends AppCompatActivity {
    private TextView data = null;
    private Button back_button = null;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_history);
        back_button = findViewById(R.id.back_btn);
        data = findViewById(R.id.data);
        Bundle clinicHistoryFinal = getIntent().getExtras();

        if(clinicHistoryFinal.getString("clinicHistoryFinal")!= null){
            data.setText(clinicHistoryFinal.getString("clinicHistoryFinal"));
        }else{
            Toast.makeText(getBaseContext(), "No hay contenido", Toast.LENGTH_LONG).show();
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
