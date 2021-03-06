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
import java.io.FileOutputStream;

public class MainMenuActivity extends AppCompatActivity {
    Button btnChoimoi,btnDiemcao, btnSettings,btnThoat,btnHuongdan;
    TextView tvName;
    Intent playMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnChoimoi=(Button)findViewById(R.id.button3);
        btnDiemcao=(Button)findViewById(R.id.button4);
        btnHuongdan=(Button)findViewById(R.id.button5);
        btnSettings =(Button)findViewById(R.id.button6);
        btnThoat=(Button)findViewById(R.id.button7);
        tvName=(TextView)findViewById(R.id.textView12);

        final MediaPlayer buttonSound = MediaPlayer.create(this,R.raw.buttonsoundclick);
        final Intent choimoi=new Intent(MainMenuActivity.this,TheTalkActivity.class);
        final Intent diemcao=new Intent(MainMenuActivity.this,BangdiemActivity.class);
        final Intent huongdan =new Intent(MainMenuActivity.this,HuongdanActivity.class);
        final Intent settings=new Intent(MainMenuActivity.this, SettingsActivity.class);
        playMusic=new Intent(MainMenuActivity.this,PlayMusicService.class);
//        Typeface typeface=Typeface.createFromAsset(getAssets(),"Chunkfive.otf");
//        tvName.setTypeface(typeface);
        tvName.setText("BATMAN\nGIẢI ĐỐ");
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2949508366818582~7558872153");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
//                writeRecord("0");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // write code for saving data to the file
        }


        startService(playMusic);
        btnChoimoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(choimoi);
                overridePendingTransition(R.anim.slidein,R.anim.slideout);

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                finish();
            }
        });
        btnDiemcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(diemcao);
                overridePendingTransition(R.anim.slidein,R.anim.slideout);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(settings);
                overridePendingTransition(R.anim.slidein,R.anim.slideout);
            }
        });
        btnHuongdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(huongdan);
                overridePendingTransition(R.anim.slidein,R.anim.slideout);
            }
        });

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
    public void onBackPressed() {
        super.onBackPressed();
        stopService(playMusic);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(playMusic);

    }

    @Override
    protected void onPause() {
        super.onPause();
        ///// bat su kien nut HOME = trick
        ///// onPause duoc goi khi bam BACK hoac HOME hoac chuyen doi giua cac Activity ???
        ///// hom_btncheck check xem nguoi dung bam HOME thi se goi stopService
        ///// activity_check check nguoi dung chuyen activity
        ///// back_check check nguoi dung bam BACK
        /////if(back_check ==0 && activity_check==0)stopService(playMusic);
        //stopService(playMusic);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(playMusic);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //startService(playMusic);


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        startService(playMusic);
    }
}
