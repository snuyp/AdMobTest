package com.example.dima.admobtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private Button buttonRu, buttonEn, buttonFr, buttonDe;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRu = findViewById(R.id.button_ru);
        buttonEn = findViewById(R.id.button_en);
        buttonDe = findViewById(R.id.button_de);
        buttonFr = findViewById(R.id.button_fr);

        MobileAds.initialize(this,"ca-app-pub-4362588175476230~7331039777");


        //for example
        final Intent intent = new Intent(this,PreviewActivity.class);
        buttonRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intent.putExtra("language","ru");
                    startActivity(intent);
            }
        });
        buttonEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("language","en");
                startActivity(intent);
            }
        });
        buttonDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("language","de");
                startActivity(intent);
            }
        });
        buttonFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("language","fr");
                startActivity(intent);
            }
        });
    }
}
