package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    TextView tvCauhoi,tvDiem,tvTime;
    Button btnYes,btnNo;
    LinearLayout lnl,lnl2;
    ImageView imv2;
    ArrayList<cauhoi> ds_cauhoi=new ArrayList<cauhoi>();
    int index=0,socau=40,diem=0,check_backbtn=0;
    Animation animation;


    //CountDownTimer timer;
//     CountDownTimer timer=new CountDownTimer(12000,1000) {
//         int a=10;
//         @Override
//         public void onTick(long millisUntilFinished) {
//
//             tvTime.setText((a)+"s");
//            if(a==0){
//                onFinish();
//                cancel();
//            }
//             a--;
//         }
//
//         @Override
//         public void onFinish() {
//
//            loseAction();
//         }
//     };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCauhoi=(TextView)findViewById(R.id.textView);
        btnYes =(Button)findViewById(R.id.button);
        btnNo=(Button) findViewById(R.id.button2);
        tvDiem=(TextView)findViewById( R.id.textView4);
        tvTime=(TextView)findViewById(R.id.textView5);
        lnl=(LinearLayout)findViewById(R.id.linearLayout);
        lnl2=(LinearLayout)findViewById(R.id.linearLayout2);
        imv2=(ImageView)findViewById(R.id.imageView2);

        overridePendingTransition(0,0);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Chunkfive.otf");
        tvCauhoi.setTypeface(typeface);
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


        //inCauhoi(index);
        startGame();
//        btnYes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //String a= btnYes.getText().toString();
//
//                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase("Đúng")){
//                    diem+=100;
//                    tvDiem.setText(""+diem);
//                    if(index<socau) {
//                        inCauhoi(index);
//                        //timer.cancel();
//                        //if(timer!=null)timer.cancel();
//
//                        //timer.start();
//                        //startTimer(timer);
//                    }else
//                    {
//
//                        btnYes.setClickable(false);
//                        btnNo.setClickable(false);
//                    }
//                }
//                else {
//                    loseAction();
//                }
//
//            }
//        });
//        btnNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                //String a=btnNo.getText().toString();
//                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase("Sai")){
//
//                    diem+=100;
//                    tvDiem.setText(""+diem);
//                    if(index<socau) {
//                        inCauhoi(index);
//                        //timer.cancel();
//                        //if(timer!=null)timer.cancel();
//                        //timer.start();
//                        //startTimer(timer);
//                    }else
//                    {
//                        btnYes.setClickable(false);
//                        btnNo.setClickable(false);
//                    }
//                }else{
//                    loseAction();
//                    }
//            }
//        });

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
//        ImageView imgv=new ImageView(MainActivity.this);
//        lnl.addView(imgv);
        runAnimation(imv2);
        MediaPlayer laugh=MediaPlayer.create(MainActivity.this,R.raw.trolllaugh);
        laugh.start();
        imv2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("kq",b);
                startActivityForResult(intent,1);
                finish();
            }
        },3000);

    }
    public void startGame(){
        Toast.makeText(this, "start new game", Toast.LENGTH_SHORT).show();
        inCauhoi();
        final MediaPlayer buttonSound = MediaPlayer.create(MainActivity.this,R.raw.truefalseclick);

        final CountDownTimer _timer=new CountDownTimer(12000,1001) {
            int a=3;

            @Override
            public void onTick(long millisUntilFinished) {

                MediaPlayer beepSound=MediaPlayer.create(MainActivity.this,R.raw.beep);
                tvTime.setText((a)+"s");
                if(a!=0)beepSound.start();
                if(a==0){

                    onFinish();
                    cancel();
                }
                a--;
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
                        startGame();
                    }else
                    {
                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }
                else {
                    loseAction();
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
                        startGame();
                    }else
                    {
                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }else{
                    loseAction();
                }
            }
        });
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
        imageView.setImageResource(R.drawable.troll_face);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity=Gravity.CENTER;
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=getIntent();
//                finish();
//                startActivity(intent);
//            }
//        });
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
//        stopService(new Intent(MainActivity.this, PlayMusic.class));
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        startService(new Intent(MainActivity.this,PlayMusic.class));
    }
    @Override
    public void onBackPressed() {
        check_backbtn=1;
        finish();
        super.onBackPressed();
    }
}

