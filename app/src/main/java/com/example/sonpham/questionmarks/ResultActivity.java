package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvResult,tvChu;
    Button btnShare,btnRestart,btnMainmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvResult=(TextView)findViewById(R.id.textView3);
        tvChu=(TextView)findViewById(R.id.textView6);
        btnShare=(Button)findViewById(R.id.button11);
        btnRestart=(Button)findViewById(R.id.button10);
        btnMainmenu=(Button)findViewById(R.id.button9);

        final MediaPlayer buttonSound = MediaPlayer.create(ResultActivity.this,R.raw.buttonsoundclick);

        overridePendingTransition(0,0);
        AlphaAnimation nhapnhay2=new AlphaAnimation(1,0);
        nhapnhay2.setRepeatCount(Animation.INFINITE);
        nhapnhay2.setDuration(1000);
        tvChu.startAnimation(nhapnhay2);
        String diem=getIntent().getExtras().getString("kq");
        int socau=Integer.parseInt(diem)/100;
        tvResult.setText("Điểm: "+diem+" Số câu đúng: "+socau);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnMainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                Intent intent=new Intent(ResultActivity.this,MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
                finish();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
            }
        });
    }
}