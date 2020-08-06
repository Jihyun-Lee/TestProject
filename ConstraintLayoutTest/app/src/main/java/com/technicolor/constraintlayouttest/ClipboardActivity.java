package com.technicolor.constraintlayouttest;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClipboardActivity extends AppCompatActivity {
    private static final String TEST_BROADCAST = "com.test.br.INTENT";
    private ClipboardManager clipboard;
    private ClipData myClip;
    private String TAG = "test";

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
                //TODO : distinguish which type was copied...
                if (item.getText() != null)
                    Toast.makeText(getApplicationContext(), "clipboard text : " + item.getText().toString(), Toast.LENGTH_SHORT).show();
                else //intent
                    Toast.makeText(getApplicationContext(), "clipboard text : " + item.getIntent().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            //set intent to clipboard
            @Override
            public void onClick(View view) {
                Intent appIntent = new Intent( TEST_BROADCAST );
                ClipData clip = ClipData.newIntent("Intent", appIntent);
                clipboard.setPrimaryClip(clip);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            //get intent
            @Override
            public void onClick(View view) {
                Intent pasteIntent = clipboard.getPrimaryClip().getItemAt(0).getIntent();
                if( pasteIntent != null){
                    //handle
                    Log.d(TAG, "send broadcast");
                    Toast.makeText(getApplicationContext(), "send broadcast", Toast.LENGTH_SHORT).show();
                    sendBroadcast(pasteIntent);
                } else {
                    //ignore
                    Log.d(TAG, "no intent found");
                    Toast.makeText(getApplicationContext(), "no intent found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TEST_BROADCAST);
        this.registerReceiver( mReceiver, intentFilter);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "action : "+ intent.toString());
            Toast.makeText(getApplicationContext(), "action : "+ intent.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
