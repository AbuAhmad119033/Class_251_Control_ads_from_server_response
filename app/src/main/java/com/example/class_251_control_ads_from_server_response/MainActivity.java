package com.example.class_251_control_ads_from_server_response;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    AdView mAdView;

    public static boolean SHOW_AD = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.mAdView);
        progressBar = findViewById(R.id.progressBar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://masterbari69.000webhostapp.com/apps/hello.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        progressBar.setVisibility(View.GONE);

                        if(response.contains("Show Ads")){
                            SHOW_AD = true;
                            mAdView.setVisibility(View.VISIBLE);
                            AdRequest adRequest = new AdRequest.Builder().build();
                            mAdView.loadAd(adRequest);
                        }else {
                            mAdView.setVisibility(View.GONE);
                            SHOW_AD = false;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}