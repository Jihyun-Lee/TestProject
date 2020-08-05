package com.technicolor.constraintlayouttest;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClipboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.relative_main);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);


        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        ContentResolver cr = getContentResolver();

        ClipData clip = clipboard.getPrimaryClip();
        if ( clip != null){

            String text = null;
            String title = null;
            ClipData.Item item = clip.getItemAt(0);


        }
        

        clipboard.setPrimaryClip(clip);
        String str = "test";
        CharSequence cs =str;
        str= cs.toString();

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
    }
}
