package com.technicolor.constraintlayouttest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.technicolor.sqlitetest.FeedReaderContract;
import com.technicolor.sqlitetest.FeedReaderDbHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SqlTest extends AppCompatActivity {
    FeedReaderDbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_main);
        dbHelper = new FeedReaderDbHelper(getApplicationContext());
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);



        //insert
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String title = "example";
                String subtitle = "subtitle";
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

                long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                Toast.makeText(getApplicationContext(),"newRowId : "+ newRowId,Toast.LENGTH_SHORT).show();
                Log.d("easy_test", "newRowId : "+ newRowId);
            }
        });
        //read
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String[] projection = {
                        BaseColumns._ID,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
                };


                String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
                String[] selectionArgs = { "example" };


                String sortOrder;
                sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

                Cursor cursor = db.query(
                        FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                        projection,             // The array of columns to return (pass null to get all)
                        selection,              // The columns for the WHERE clause
                        selectionArgs,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        sortOrder               // The sort order
                );

                List itemIds = new ArrayList<>();
                while(cursor.moveToNext()) {
                    String title = cursor.getString(
                            cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
                            itemIds.add(title);
                    Toast.makeText(getApplicationContext(),"title : "+ title,Toast.LENGTH_SHORT).show();
                    Log.d("easy_test", "title : "+ title);
                }
                cursor.close();
            }
        });
        //delete
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
                String[] selectionArgs = { "example" };
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
                Toast.makeText(getApplicationContext(),"deletedRows : "+deletedRows,Toast.LENGTH_SHORT).show();
            }
        });

        //update
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String title = "update title";
                ContentValues values = new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
                String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
                String[] selectionArgs = { "example" };

                int count = db.update(
                        FeedReaderContract.FeedEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);

                Toast.makeText(getApplicationContext(),"update count : "+count,Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
