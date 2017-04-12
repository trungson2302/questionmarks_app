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
    int index=0,socau=10,diem=0;
     //CountDownTimer timer;

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
                        //timer=null;
                        //timer.start();
                        //startTimer(timer);
                    }else
                    {
                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }
                else {
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
                        //timer=null;
                        //timer.start();
                        //startTimer(timer);
                    }else
                    {
                        btnYes.setClickable(false);
                        btnNo.setClickable(false);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Ban da thua", Toast.LENGTH_SHORT).show();
                    btnYes.setClickable(false);
                    btnNo.setClickable(false);
                }

            }
        });

    }
//    public void startTimer(CountDownTimer _timer)
//    {
//
//        _timer=new CountDownTimer(4000,1000) {
//            int a=3;
//            @Override
//            public void onTick(long millisUntilFinished) {
//                check=a-1;
//                tvTime.setText((a-1)+"s");
//                a--;
//                if(check==0){
//                    // cancel();
//                    Toast.makeText(MainActivity.this, "Ban thua", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFinish() {
////            tvTime.setText(a+"");
////            Toast.makeText(MainActivity.this, "Ban thua", Toast.LENGTH_SHORT).show();
//
//
//            }
//        };
//        _timer.start();
//    }
    public void inCauhoi(int n){
        tvCauhoi.setText(ds_cauhoi.get(n).getCauhoi());
        if(index<socau){
        index++;}
    }
}
