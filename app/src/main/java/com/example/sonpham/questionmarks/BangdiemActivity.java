package com.example.sonpham.questionmarks;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BangdiemActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdiem);
        textView=(TextView)findViewById(R.id.textView2);

        textView.setText(getRecord());
        String text=textView.getText().toString();
        if(text==""){
            textView.setText("0");
            writeRecord("0");
            textView.setText(getRecord());
        }


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


}
