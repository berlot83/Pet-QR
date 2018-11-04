package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;


public class ReaderActivity extends AppCompatActivity {
    private Button scan_btn;
    //private TextView responseOnTextView;
    private TextView textViewRaze;
    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewOwnerName;
    private TextView textViewOwnerLastname;
    private TextView textViewOwnerDni;
    private TextView textViewStreet1;
    private TextView textViewPhone1;
    private TextView textViewPhone2;
    private TextView textViewEmail;
    private TextView textViewSocial;
    private TextView textViewDateAntiRabicVaccine;
    private TextView textViewDatePolivalentVaccine;
    private TextView textViewDateSextupleVaccine;
    private TextView textViewDateOctupleVaccine;
    private TextView textViewClinicHistory;
    private TextView textViewIllness;
    private TextView textViewMedicated;
    private TextView textViewStatus;
    private TextView textViewSubscription;
    private Button map_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        scan_btn = findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
        map_btn = findViewById(R.id.map);
        map_btn.setEnabled(false);
        setupActionBar();
    }

    public String responseOnJson(){
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        /* Parameters required to query */
        String name = null;
        String ownerLastname = null;
        String ownerDni = null;
        String user = null;



        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "Se canceló la operación", Toast.LENGTH_LONG).show();
            }
            else {
                /* Alert prompt on android shows */
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();

                String jsonContentOnQR = result.getContents();

                try {
                    JSONObject json = new JSONObject(jsonContentOnQR);
                    name = json.getString("name");
                    ownerLastname = json.getString("ownerLastname");
                    ownerDni = json.getString("ownerDni");
                    user = json.getString("collection");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /* Start call api with ipv4 address only */
                final String url = "https://pets2018.herokuapp.com/rest/pet/info?name="+name+"&ownerLastname="+ownerLastname+"&ownerDni="+ownerDni+"&user="+user;
                final RequestQueue queue = Volley.newRequestQueue(ReaderActivity.this);

                //responseOnTextView = (TextView) findViewById(R.id.test);
                // Request a string response from the provided URL.
                textViewRaze = findViewById(R.id.tableRaze);
                textViewName = findViewById(R.id.tableName);
                textViewAge = findViewById(R.id.tableAge);
                textViewOwnerName = findViewById(R.id.tableOwnerName);
                textViewOwnerLastname = findViewById(R.id.tableOwnerLastname);
                textViewOwnerDni = findViewById(R.id.tableDni);
                textViewStreet1 = findViewById(R.id.tableStreet1);
                textViewPhone1 = findViewById(R.id.tablePhone1);
                textViewPhone2 = findViewById(R.id.tablePhone2);
                textViewEmail = findViewById(R.id.tableEmail);
                textViewSocial = findViewById(R.id.tableSocial);
                textViewDateAntiRabicVaccine = findViewById(R.id.tableDateAntiRabicVaccine);
                textViewDatePolivalentVaccine = findViewById(R.id.tableDatePolivalentVaccine);
                textViewDateSextupleVaccine = findViewById(R.id.tableDateSextupleVaccine);
                textViewDateOctupleVaccine = findViewById(R.id.tableDateOctupleVaccine);
                textViewClinicHistory = findViewById(R.id.tableClinicHistory);
                textViewIllness = findViewById(R.id.tableIllness);
                textViewMedicated = findViewById(R.id.tableMedicated);
                textViewStatus = findViewById(R.id.tableStatus);
                textViewSubscription = findViewById(R.id.tableSubscription);

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                /* Variables used to parse Json, all declared to null initialized and next assign a data from response */
                                String responseRaze = null, responseName = null, responseOwnerName = null, responseOwnerLastname = null, responseOwnerDni = null, responseAge = null, responseStreet1 = null, responsePhone1 = null, responsePhone2 = null, responseEmail = null, responseIllness = null, responseMedicated = null, responseStatus = null, responseSubscription = null, responseDateAntiRabicVaccine = null, responseDatePolivalentVaccine = null, responseDateSextupleVaccine = null, responseDateOctupleVaccine = null, responseClinicHistory = null;

                                try {
                                    JSONObject responseJson = new JSONObject(response);
                                    responseRaze = responseJson.getString("raze");
                                    responseName = responseJson.getString("name");
                                    responseOwnerName = responseJson.getString("ownerName");
                                    responseOwnerLastname = responseJson.getString("ownerLastname");
                                    responseOwnerDni = responseJson.getString("ownerDni");
                                    responseAge = responseJson.getString("age");
                                    responseStreet1 = responseJson.getString("street1");
                                    responsePhone1 = responseJson.getString("phone1");
                                    responsePhone2 = responseJson.getString("phone2");
                                    responseEmail = responseJson.getString("email");
                                    responseDateAntiRabicVaccine = responseJson.getString("dateAntiRabicVaccine");
                                    responseDatePolivalentVaccine = responseJson.getString("datePolivalentVaccine");
                                    responseDateSextupleVaccine = responseJson.getString("dateSextupleVaccine");
                                    responseDateOctupleVaccine = responseJson.getString("dateOctupleVaccine");
                                    responseClinicHistory = responseJson.getString("clinicHistory");
                                    responseIllness = responseJson.getString("illness");
                                    responseMedicated = responseJson.getString("medicated");
                                    responseStatus = responseJson.getString("status");
                                    responseSubscription = responseJson.getString("subscription");

                                    Iterator<?> permisos = responseJson.keys();
                                    while(permisos.hasNext() ){
                                        String key = (String)permisos.next();
                                        Iterator<?> tipo = responseJson.getJSONObject(key).keys();
                                        while(tipo.hasNext()){
                                            TableLayout table = findViewById(R.id.table_reader);
                                            String key2 = (String)tipo.next();
                                            TableRow row = new TableRow(ReaderActivity.this);
                                            TextView tv = new TextView(ReaderActivity.this);
                                            tv.setText(responseJson.getJSONObject(key).getJSONArray(key2).toString());
                                            row.addView(tv);
                                            table.addView(row);
                                        }

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                /* Modify Boolean data */
                                if(responseEmail == null || responseEmail.equals("")){
                                    responseEmail = "Aca esta";
                                }
                                /* Illness boolean */
                                if(responseIllness.equals("Ninguna")){
                                    /* Nothing to do stays equals*/
                                }else{
                                    textViewIllness.setTextColor(Color.parseColor("#FF0000"));
                                }
                                /* Medicated boolean */
                                if(responseMedicated.equals("true")){
                                    responseMedicated = "Si";
                                    textViewMedicated.setTextColor(Color.parseColor("#FF0000"));
                                }else{
                                    responseMedicated = "No";
                                }
                                /* Status boolean */
                                if(responseStatus.equals("false")){
                                    /* Change color */
                                    responseStatus = "Perdido";
                                    textViewStatus.setText(responseStatus);
                                    textViewStatus.setTextColor(Color.parseColor("#FF0000"));

                                    /* Sound */
                                    MediaPlayer mediaPlayer = MediaPlayer.create(ReaderActivity.this, R.raw.warning);
                                    mediaPlayer.start();

                                    /* Alert */
                                    Toast.makeText(ReaderActivity.this, "Esta mascota está declarada como perdida por favor contáctese con sus tutores.", Toast.LENGTH_LONG).show();

                                }else{
                                    responseStatus = "Normal";
                                }
                                /* Subscription boolean */
                                if(responseSubscription.equals("true")){
                                    responseSubscription = "Sí";
                                    textViewSubscription.setTextColor(Color.parseColor("#FFD700"));
                                }else{
                                    responseSubscription = "No";
                                }
                                /* En Modify Boolean data */

                                //responseOnTextView.setText(response);
                                textViewName.setText(responseName);
                                textViewRaze.setText(responseRaze);
                                textViewAge.setText(responseAge);
                                textViewOwnerName.setText(responseOwnerName);
                                textViewOwnerLastname.setText(responseOwnerLastname);
                                textViewOwnerDni.setText(responseOwnerDni);
                                textViewStreet1.setText(responseStreet1);
                                textViewPhone1.setText(responsePhone1);
                                textViewPhone2.setText(responsePhone2);
                                textViewEmail.setText(responseEmail);
                                textViewDateAntiRabicVaccine.setText(responseDateAntiRabicVaccine);
                                textViewDatePolivalentVaccine.setText(responseDatePolivalentVaccine);
                                textViewDateSextupleVaccine.setText(responseDateSextupleVaccine);
                                textViewDateOctupleVaccine.setText(responseDateOctupleVaccine);
                                textViewClinicHistory.setText("Ver Datos");
                                textViewClinicHistory.setTextColor(Color.parseColor("#58D68D"));

                                /* Transaction from a dynamic to final string bacause onclick is on the inner class*/
                                final String clinicHistoryFinal = responseClinicHistory;
                                /* Enable the button to see the new Activity */
                                map_btn.setEnabled(true);
                                /* Convert to a button the textView to access to information */
                                textViewClinicHistory.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if(textViewClinicHistory.equals("") || textViewClinicHistory == null){
                                            textViewClinicHistory.setText("No hay datos");
                                        }else{
                                            textViewClinicHistory.setText("Acceso a H.C.");

                                            Intent intentClinicHistory = new Intent(ReaderActivity.this, ClinicHistoryActivity.class);
                                            intentClinicHistory.putExtra("clinicHistoryFinal", clinicHistoryFinal);
                                            startActivity(intentClinicHistory);

                                        }
                                    }
                                });
                                /* Finish to access */

                                textViewIllness.setText(responseIllness);
                                textViewMedicated.setText(responseMedicated);
                                textViewStatus.setText(responseStatus);
                                textViewSubscription.setText(responseSubscription);

                                final String address = responseStreet1;

                                map_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                     Intent mapIntent = new Intent(ReaderActivity.this, MapsActivity.class);
                                     mapIntent.putExtra("address",address);
                                     startActivity(mapIntent);
                                    }
                                });

                                //queue.stop();
                            }
                        },
                        new Response.ErrorListener(){

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //responseOnTextView.setText("Error in call");
                                Toast.makeText(ReaderActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i(error.getMessage(),"");
                                //queue.stop();
                            }
                        }
                );
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(stringRequest);

                /* End call api */
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    private void setupActionBar(){
        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.setDefaultDisplayHomeAsUpEnabled(true);
            CharSequence subtitle = "Porque ella lo vale";
            bar.setSubtitle(subtitle);
        }
    }


}
