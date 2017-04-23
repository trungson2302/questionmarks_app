package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BangdiemActivity extends AppCompatActivity {
    TextView textView;
    Button btnOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdiem);
        textView=(TextView)findViewById(R.id.textView2);
        btnOK=(Button)findViewById(R.id.button8);
        final  MediaPlayer btnSound=MediaPlayer.create(BangdiemActivity.this,R.raw.buttonsoundclick);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2949508366818582~7558872153");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        overridePendingTransition(0,0);
        textView.setText(getRecord());
        String text=textView.getText().toString();
        if(text==""){
            textView.setText("0");
            writeRecord("0");
            textView.setText(getRecord());
        }
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSound.start();
                finish();
                overridePendingTransition(R.anim.slidedown,R.anim.slidedowndown);
            }
        });

    }
    public String getRecord(){
        String chuoi="";
        String path = getFilesDir().getAbsolutePath();
        File file = new File(path+"/record.txt");
        if(!file.exists())
        {
            try {

                file.createNewFile();
                String s="0";
                FileOutputStream out=openFileOutput("record.txt",MODE_PRIVATE);
                out.write(s.getBytes());
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            // write code for saving data to the file
        }
        try {
            FileInputStream in=openFileInput("record.txt");
            byte[] buffer=new byte[in.available()];
            in.read(buffer);
            chuoi=new String(buffer);
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chuoi;
    }
    public void writeRecord(String chuoi){

        String path = getFilesDir().getAbsolutePath();
        File file = new File(path+"/record.txt");
        if(!file.exists())
        {
            try {
                file.createNewFile();
                String s="0";
                FileOutputStream out=openFileOutput("record.txt",MODE_PRIVATE);
                out.write(s.getBytes());
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // write code for saving data to the file
        }
        try {
            FileOutputStream out=openFileOutput("record.txt",MODE_PRIVATE);
            out.write(chuoi.getBytes());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startService(new Intent(BangdiemActivity.this,PlayMusicService.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slidedown,R.anim.slidedowndown);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        stopService(new Intent(BangdiemActivity.this, PlayMusicService.class));

    }
}
