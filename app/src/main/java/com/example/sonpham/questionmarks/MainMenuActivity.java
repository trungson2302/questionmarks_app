package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    Button btnChoimoi,btnDiemcao,btnGioithieu,btnThoat,btnHuongdan;
    Intent playMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnChoimoi=(Button)findViewById(R.id.button3);
        btnDiemcao=(Button)findViewById(R.id.button4);
        btnHuongdan=(Button)findViewById(R.id.button5);
        btnGioithieu=(Button)findViewById(R.id.button6);
        btnThoat=(Button)findViewById(R.id.button7);

        final Intent choimoi=new Intent(MainMenuActivity.this,MainActivity.class);
        playMusic=new Intent(MainMenuActivity.this,PlayMusic.class);
        startService(playMusic);
        btnChoimoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(choimoi);
                //onStop();

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
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
        //stopService(playMusic);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(playMusic);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startService(playMusic);
    }
}
