package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvCauhoi,tvDiem,tvTime;
    Button btnYes,btnNo;
    LinearLayout lnl,lnl2;
    ArrayList<cauhoi> ds_cauhoi=new ArrayList<cauhoi>();
    int index=0,socau=30,diem=0,checktimer=1;
    Animation animation;
    //CountDownTimer timer;
     CountDownTimer timer=new CountDownTimer(12000,1000) {
         int a=10;
         @Override
         public void onTick(long millisUntilFinished) {

             tvTime.setText((a)+"s");
            if(a==0){
                onFinish();
                cancel();
            }
             a--;
         }

         @Override
         public void onFinish() {

            loseAction();
         }
     };


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
        lnl2=(LinearLayout)findViewById(R.id.linerLayout2);

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
        inCauhoi(index);
        //startTimer(timer);
        timer.start();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String a= btnYes.getText().toString();

                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase("Đúng")){
                    diem+=100;
                    tvDiem.setText(""+diem);
                    if(index<socau) {
                        inCauhoi(index);
                        //timer.cancel();
                        //if(timer!=null)timer.cancel();

                        //timer.start();
                        //startTimer(timer);
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


                //String a=btnNo.getText().toString();
                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase("Sai")){

                    diem+=100;
                    tvDiem.setText(""+diem);
                    if(index<socau) {
                        inCauhoi(index);
                        //timer.cancel();
                        //if(timer!=null)timer.cancel();
                        //timer.start();
                        //startTimer(timer);
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

    public void loseAction(){
        if(timer!=null)timer.cancel();
        Toast.makeText(MainActivity.this, "Ban da thua", Toast.LENGTH_SHORT).show();
        String c= getRecord();
        int a=Integer.parseInt(c);
        String b= tvDiem.getText().toString();
        if(a<Integer.parseInt(b))
            writeRecord(b);
        btnYes.setClickable(false);
        btnNo.setClickable(false);
        ImageView imgv=new ImageView(MainActivity.this);
        //((ViewManager)tvCauhoi.getParent()).removeView(tvCauhoi);
        //lnl.removeView(tvCauhoi);
        lnl.addView(imgv);

        runAnimation(imgv);
    }
    public void startTimer(CountDownTimer _timer)
    {
        _timer=new CountDownTimer(12000,1000) {
            int a=10;
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText((a)+"s");
                if(a==0){
                    onFinish();
                    cancel();
                }
                a--;
            }

            @Override
            public void onFinish() {

                Toast.makeText(MainActivity.this, "Het gio", Toast.LENGTH_SHORT).show();
                btnYes.setClickable(false);
                btnNo.setClickable(false);
                ImageView imgv=new ImageView(MainActivity.this);
                //lnl.removeView(tvCauhoi);
                lnl.addView(imgv);
                runAnimation(imgv);
                checktimer=0;
            }
        };
        _timer.start();
    }
    public void cancleTimer(CountDownTimer _timer){
        _timer.cancel();
    }
    public void inCauhoi(int n){
        tvCauhoi.setText(ds_cauhoi.get(n).getCauhoi());
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                finish();
                startActivity(intent);
            }
        });
        imageView.setLayoutParams(layoutParams);
        imageView.startAnimation(animation);

    }
    public String getRecord(){
        String chuoi="";
        File file = new File("record.txt");
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
        File file = new File("record.txt");

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
        timer.cancel();
        finish();

    }
}

