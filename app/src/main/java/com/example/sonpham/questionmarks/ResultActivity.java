package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.facebook.FacebookSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    LinearLayout lnl;
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
        lnl=(LinearLayout)findViewById(R.id.linearlayout);
        final MediaPlayer buttonSound = MediaPlayer.create(ResultActivity.this,R.raw.buttonsoundclick);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2949508366818582~7558872153");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        overridePendingTransition(0,0);
        AlphaAnimation nhapnhay2=new AlphaAnimation(1,0);
        nhapnhay2.setRepeatCount(Animation.INFINITE);
        nhapnhay2.setDuration(1000);
        tvChu.startAnimation(nhapnhay2);
        String diem=getIntent().getExtras().getString("kq");
        int socau=Integer.parseInt(diem)/100;
        tvResult.setText("Điểm: "+diem+"\nSố câu đúng: "+socau);
        lnl.setBackgroundResource(R.drawable.explosion);
        AnimationDrawable animationDrawable=(AnimationDrawable)lnl.getBackground();
        animationDrawable.setOneShot(true);
        animationDrawable.start();
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidedown,R.anim.slidedowndown);
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
                overridePendingTransition(R.anim.slidedown,R.anim.slidedowndown);
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
