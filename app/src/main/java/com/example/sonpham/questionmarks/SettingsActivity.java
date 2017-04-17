package com.example.sonpham.questionmarks;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity {

    SeekBar seekBar;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //initControls();

    }
//    private void initControls()
//    {
//        try
//        {
//            seekBar = (SeekBar)findViewById(R.id.seekBar2);
//            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(100,20);
//            seekBar.setLayoutParams(params);
//            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            seekBar.setMax(audioManager
//                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
//            seekBar.setProgress(audioManager
//                    .getStreamVolume(AudioManager.STREAM_MUSIC));
//
//
//            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//            {
//                @Override
//                public void onStopTrackingTouch(SeekBar arg0)
//                {
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar arg0)
//                {
//                }
//
//                @Override
//                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
//                {
//                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//                            progress, 0);
//                }
//            });
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
