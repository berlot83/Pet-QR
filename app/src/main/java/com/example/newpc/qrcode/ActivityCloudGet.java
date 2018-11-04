package com.example.newpc.qrcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newpc.model.Pet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityCloudGet extends AppCompatActivity {

    private TextView result;
    private RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_get);

        result = findViewById(R.id.result);
        String user = "clivet";
        String url = "http://192.168.0.6:8079/TrackPetService/rest/pet/lost?user="+user;

        request = Volley.newRequestQueue(ActivityCloudGet.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Gson gson = new Gson();
                            //Pet petJson = gson.fromJson(response, Pet.class);
                            //result.setText(petJson.toString());
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("locations");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject explrObject = jsonArray.getJSONObject(i);
                                result.setText(explrObject.getString(""));
                            }
                            request.stop();
                        }catch(Exception error){
                            Toast.makeText(ActivityCloudGet.this, "No funciona la DB", Toast.LENGTH_LONG);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        request.stop();
                    }
                }
        );
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(stringRequest);
        request.stop();
        }

}
