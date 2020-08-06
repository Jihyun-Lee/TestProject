package com.technicolor.constraintlayouttest;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClipboardActivity extends AppCompatActivity {
    private ClipboardManager clipboard;
    private ClipData myClip;
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


        clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        //ContentResolver cr = getContentResolver();

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //set text to clipboard
                String text = "test message";
                myClip = ClipData.newPlainText("text", text);
                clipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(), "Text copied", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //get clipboard text
                ClipData clip = clipboard.getPrimaryClip();

                int count = clip.getItemCount();

                ClipData.Item item = clip.getItemAt(0);
                Log.d("test", "count : "+ count);
                Toast.makeText(getApplicationContext(), "clipboard text : " + item.getText().toString(), Toast.LENGTH_SHORT).show();
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
