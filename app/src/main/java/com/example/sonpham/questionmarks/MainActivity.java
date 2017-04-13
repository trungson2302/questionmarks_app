package com.example.sonpham.questionmarks;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvCauhoi,tvDiem,tvTime;
    Button btnYes,btnNo;
    ArrayList<cauhoi> ds_cauhoi=new ArrayList<cauhoi>();
    int index=0,socau=10,diem=0,checktimer=1;
     CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCauhoi=(TextView)findViewById(R.id.textView);
        btnYes =(Button)findViewById(R.id.button);
        btnNo=(Button) findViewById(R.id.button2);
        tvDiem=(TextView)findViewById( R.id.textView4);
        tvTime=(TextView)findViewById(R.id.textView5);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"OpenSans-Regular.ttf");
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
        //timer.start();

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a= btnYes.getText().toString();
                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase(a)){

                    diem+=100;
                    tvDiem.setText("point: "+diem);
                    if(index<socau) {
                        inCauhoi(index);
                        //timer.cancel();
                        //if(checktimer!=0)cancleTimer(timer);

                        //timer.start();
                        startTimer(timer);
                    }else
                    {

                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }
                else {
                    // if(checktimer!=0)cancleTimer(timer);
                    Toast.makeText(MainActivity.this, "Ban da thua", Toast.LENGTH_SHORT).show();
                    btnYes.setClickable(false);
                    btnNo.setClickable(false);
                }

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a=btnNo.getText().toString();
                if(ds_cauhoi.get(index-1).getDap_an().equalsIgnoreCase(a)){

                    diem+=100;
                    tvDiem.setText("point: "+diem);
                    if(index<socau) {
                        inCauhoi(index);
                        //timer.cancel();
                        //if(checktimer!=0)cancleTimer(timer);
                        //timer.start();
                        startTimer(timer);
                    }else
                    {
                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }else{
                    //if(checktimer!=0)cancleTimer(timer);
                    Toast.makeText(MainActivity.this, "Ban da thua", Toast.LENGTH_SHORT).show();
                    btnYes.setClickable(false);
                    btnNo.setClickable(false);
                }

            }
        });

    }
    public void startTimer(CountDownTimer _timer)
    {

        _timer=new CountDownTimer(5000,1000) {
            int a=4;
            @Override
            public void onTick(long millisUntilFinished) {

                tvTime.setText((a-1)+"s");
                a--;

            }

            @Override
            public void onFinish() {

            tvTime.setText((a-1)+"");
            Toast.makeText(MainActivity.this, "Ban thua", Toast.LENGTH_SHORT).show();
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
}
