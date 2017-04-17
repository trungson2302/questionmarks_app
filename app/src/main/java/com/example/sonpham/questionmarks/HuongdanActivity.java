package com.example.sonpham.questionmarks;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HuongdanActivity extends AppCompatActivity {

    Button btnOK;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huongdan);
        btnOK=(Button)findViewById(R.id.button13);
        textView=(TextView)findViewById(R.id.textView8);
        final MediaPlayer buttonSound = MediaPlayer.create(HuongdanActivity.this,R.raw.truefalseclick);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                finish();
            }
        });
    }
}
