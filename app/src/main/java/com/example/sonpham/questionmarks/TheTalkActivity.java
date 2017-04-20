package com.example.sonpham.questionmarks;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.GONE;

public class TheTalkActivity extends AppCompatActivity {
    ImageView imgBat,imgJoker;
    TextView tvBat,tvJoker;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_talk);
        imgBat=(ImageView)findViewById(R.id.imageView5);
        imgJoker=(ImageView)findViewById(R.id.imageView4);
        tvBat=(TextView)findViewById(R.id.textView11);
        tvJoker=(TextView)findViewById(R.id.textView10);
        final Intent intent=new Intent(TheTalkActivity.this,MainActivity.class);


        tvBat.setText("Đang tắm sữa tươi thì lại có tin báo thằng Giốc Cơ đi gây rối");
        tvJoker.setText("Chào chú dơi, rảnh quá không có gì làm nên anh đã đặt 1 quả bom lên tường nhà chú rồi");
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                tvBat.setText("Mặc dù không muốn đi nhưng lỡ mang danh công lý nên phải vác đít đi vậy");
                tvBat.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvBat.setVisibility(GONE);
                        tvJoker.setVisibility(View.VISIBLE);
                        imgBat.setVisibility(GONE);
                        imgJoker.setVisibility(View.VISIBLE);
                        tvBat.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tvJoker.setText("Qủa bom sẽ nổ sau 10 giây nếu như chú không trả lời đúng 1 câu hỏi... cho nên chúc chú may mắn nhé ahihi ");
                                tvBat.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(intent);
                                        finish();
                                    }
                                },4000);
                            }
                        },4000);
                    }
                },4000);
            }
        };
        handler.postDelayed(runnable,3000);
        tvBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                startActivity(intent);
                finish();
            }
        });
        tvJoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                startActivity(intent);
                finish();
            }
        });

    }
}
