package com.example.sonpham.questionmarks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvCauhoi,tvDiem,tvTime;
    Button btnYes,btnNo;
    ImageView imv2;
    ArrayList<cauhoi> ds_cauhoi=new ArrayList<cauhoi>();
    int index=0,socau=40,diem=0,check_backbtn=0,dem=10;
    Animation animation;
    boolean ready=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCauhoi=(TextView)findViewById(R.id.textView);
        btnYes =(Button)findViewById(R.id.button);
        btnNo=(Button) findViewById(R.id.button2);
        tvDiem=(TextView)findViewById( R.id.textView4);
        tvTime=(TextView)findViewById(R.id.textView5);
        imv2=(ImageView)findViewById(R.id.imageView2);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2949508366818582~7558872153");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        overridePendingTransition(0,0);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"GoodDog.otf");
        Typeface typefacenumber=Typeface.createFromAsset(getAssets(),"digital.ttf");
        //tvCauhoi.setTypeface(typeface);
        tvTime.setTypeface(typefacenumber);
        QuanLyCauHoi quanLyCauHoi=new QuanLyCauHoi(MainActivity.this);
        try {
            quanLyCauHoi.createDataBase();
        }catch (Exception e){
            e.printStackTrace();
        }
        quanLyCauHoi.close();
        quanLyCauHoi=new QuanLyCauHoi(MainActivity.this);
        ds_cauhoi=quanLyCauHoi.layNcaungaunhien(socau);
        quanLyCauHoi.close();
        CustomDialog dialog=new CustomDialog(MainActivity.this);

        //inCauhoi(index);
        startGame(dem);
//

    }

    public void startGame(int x){
        inCauhoi();

        final int s =x;
        final MediaPlayer buttonSound = MediaPlayer.create(MainActivity.this,R.raw.truefalseclick);
        final MediaPlayer buttonOK =MediaPlayer.create(MainActivity.this,R.raw.buttonsoundclick);

        final CountDownTimer _timer=new CountDownTimer(12000,1000) {
            int a=s;
            @Override
            public void onTick(long millisUntilFinished) {
                MediaPlayer beepSound= MediaPlayer.create(MainActivity.this,R.raw.beep);
                tvTime.setText("00:"+String.format("%02d",a));
                if(check_backbtn==1){
                    cancel();
                }else{
                if(a!=0){
                    beepSound.start();
                    //beepSound.reset();
                }
                if(a==0){

                    onFinish();
                    cancel();
                }
                a--;}
            }

            @Override
            public void onFinish() {
                //beepSound.start();
                if(check_backbtn==1){}else {

                    loseAction();
                }
            }
        };
        _timer.start();
        btnYes.setClickable(true);
        btnNo.setClickable(true);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonSound.start();
                _timer.cancel();
                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase("Đúng")){
                    diem+=100;
                    tvDiem.setText(""+diem);
                    if(index<socau) {
                        //inCauhoi();
                        startGame(dem);
                    }else
                    {
                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }
                else {
                    //loseAction();
                    _timer.cancel();
                    CustomDialog dialog=new CustomDialog(MainActivity.this);
                    dialog.show();
                }
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonSound.start();
                _timer.cancel();
                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase("Sai")){

                    diem+=100;
                    tvDiem.setText(""+diem);
                    if(index<socau) {
                        //inCauhoi();
                        startGame(dem);
                    }else
                    {
                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }else{
                    _timer.cancel();
                    CustomDialog dialog=new CustomDialog(MainActivity.this);
                    dialog.show();

                }
            }
        });
    }

    public void loseAction(){
        Toast.makeText(MainActivity.this, "Bạn đã thua", Toast.LENGTH_SHORT).show();
        MediaPlayer falseSound=MediaPlayer.create(MainActivity.this,R.raw.falsesound);


        falseSound.start();
        String c= getRecord();
        int a=0;
        if(c!=null)a=Integer.parseInt(c);
        final String b= tvDiem.getText().toString();
        if(a<Integer.parseInt(b))
            writeRecord(b);
        btnYes.setVisibility(View.INVISIBLE);
        btnNo.setVisibility(View.INVISIBLE);
        btnYes.setClickable(false);
        btnNo.setClickable(false);
        runAnimation(imv2);
        MediaPlayer laugh=MediaPlayer.create(MainActivity.this,R.raw.trolllaugh);
        laugh.start();
        imv2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("kq",b);
                startActivityForResult(intent,1);
                overridePendingTransition(R.anim.slidein,R.anim.slideout);
                finish();
            }
        },3000);

    }
    public void inCauhoi(){
        tvCauhoi.setText(ds_cauhoi.get(index).getCauhoi());
        if(index<socau){
        index++;}
    }

    public void runAnimation(ImageView imageView)
    {
        animation=AnimationUtils.loadAnimation(this,R.anim.troll_face);
        animation.setFillAfter(true);
        tvTime.setVisibility(View.GONE);
        btnNo.setVisibility(View.GONE);
        btnYes.setVisibility(View.GONE);
        tvCauhoi.setVisibility(View.GONE);
        imageView.setImageResource(R.drawable.jokerface);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity=Gravity.CENTER;
        imageView.setLayoutParams(layoutParams);
        imageView.startAnimation(animation);

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
    protected void onStop() {
        super.onStop();
        check_backbtn=1;

        finish();
        //stopService(new Intent(MainActivity.this, PlayMusicService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //startService(new Intent(MainActivity.this,PlayMusicService.class));
    }
    @Override
    public void onBackPressed() {
        check_backbtn=1;
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slidedown,R.anim.slidedowndown);
    }
    class CustomDialog extends AlertDialog{

            Button btnOK;
        MediaPlayer btnSoundd;
        protected CustomDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.custom_dialog);
            btnOK=(Button)findViewById(R.id.button12);

            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnSoundd=MediaPlayer.create(MainActivity.this,R.raw.buttonsoundclick);
                    btnSoundd.start();
                    dem--;
                    startGame(dem);
                    dismiss();
                }
            });
        }
    }
}

