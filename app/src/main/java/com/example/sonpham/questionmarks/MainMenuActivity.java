package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;

public class MainMenuActivity extends AppCompatActivity {
    Button btnChoimoi,btnDiemcao, btnSettings,btnThoat,btnHuongdan;
    Intent playMusic;
    int back_check =0,activity_check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnChoimoi=(Button)findViewById(R.id.button3);
        btnDiemcao=(Button)findViewById(R.id.button4);
        btnHuongdan=(Button)findViewById(R.id.button5);
        btnSettings =(Button)findViewById(R.id.button6);
        btnThoat=(Button)findViewById(R.id.button7);

        final MediaPlayer buttonSound = MediaPlayer.create(this,R.raw.buttonsoundclick);
        final Intent choimoi=new Intent(MainMenuActivity.this,MainActivity.class);
        final Intent diemcao=new Intent(MainMenuActivity.this,BangdiemActivity.class);
        final Intent huongdan =new Intent(MainMenuActivity.this,HuongdanActivity.class);
        final Intent settings=new Intent(MainMenuActivity.this, SettingsActivity.class);
        playMusic=new Intent(MainMenuActivity.this,PlayMusicService.class);

        overridePendingTransition(0,0);
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
                activity_check=1;
                //onStop();

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
                activity_check=1;
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(settings);
                /// neu co chuyen activity thi activity_check=1;
            }
        });
        btnHuongdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                startActivity(huongdan);
                /// neu co chuyen activity thi activity_check=1;
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
        back_check =1;
        super.onBackPressed();
        stopService(playMusic);
        finish();
        System.exit(0);
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
        startService(playMusic);

    }

    @Override
    protected void onRestart() {
        activity_check=0;
        super.onRestart();
        startService(playMusic);
    }
}
