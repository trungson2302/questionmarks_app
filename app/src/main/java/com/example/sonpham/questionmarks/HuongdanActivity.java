package com.example.sonpham.questionmarks;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class HuongdanActivity extends AppCompatActivity {

    Button btnOK;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huongdan);
        btnOK=(Button)findViewById(R.id.button13);
        textView=(TextView)findViewById(R.id.textView8);
        final MediaPlayer buttonSound = MediaPlayer.create(HuongdanActivity.this,R.raw.buttonsoundclick);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2949508366818582~7558872153");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                finish();
                overridePendingTransition(R.anim.slidedown,R.anim.slidedowndown);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slidedown,R.anim.slidedowndown);
    }
}
