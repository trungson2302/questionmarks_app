package com.example.sonpham.questionmarks;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Son Pham on 4/17/2017.
 */

public class CustomDialog extends Dialog  {

    public Activity c;
    public Dialog d;
    public Button ok;
    ImageView imv3,imv4;


    public CustomDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ok = (Button) findViewById(R.id.button12);
        imv3=(ImageView)findViewById(R.id.imageView3);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }


}
