package com.example.cardiacjournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * this class mainly implements the ui of historyactivity
 * this class also work as an interface between database and history page
 */

public class LogActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<CardiacHistory>loglist;
    MyDBHelper myDB ;
    CardiacHistoryAdapter mAdapter;
    ArrayList<String> log_id,sys,dia,bpm,date,time,comment;
    Button back;

    /**
     * this is to create the log activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        mRecyclerView = findViewById(R.id.recyclerView);
        ArrayList<CardiacHistory> loglist = new ArrayList<>();
        myDB=new MyDBHelper(LogActivity.this);
        log_id=new ArrayList<>();
        sys= new ArrayList<>();
        dia=new ArrayList<>();
        date=new ArrayList<>();
        time=new ArrayList<>();
        bpm=new ArrayList<>();
        comment=new ArrayList<>();
        storeDataInArrays();
        mAdapter= new CardiacHistoryAdapter(LogActivity.this,log_id,sys,dia,bpm,date,time,comment);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void storeDataInArrays(){
        /**
         * this  method is  used for action if we store data in arrays
         */

        Cursor cursor=myDB.readAllData();

        if(cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else{

            while(cursor.moveToNext()){
                log_id.add(cursor.getString(0));
                sys.add(cursor.getString(1));
                dia.add(cursor.getString(2));
                bpm.add(cursor.getString(3));
                date.add(cursor.getString(4));
                time.add(cursor.getString(5));
                comment.add(cursor.getString(6));
            }


        }




        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            /**
             * this is for going back to main Activity after updation or deletion
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent a= new Intent(LogActivity.this,MainActivity.class);
                startActivity(a);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed( ){
        /**
         * this is for going back to main Activity
         */
        Intent intent = new Intent(LogActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}