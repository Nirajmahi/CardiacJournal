package com.example.cardiacjournal;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button mButton,his;

    ImageView mImageView;
    TextView date,time,sys,dia,bpm,comment;
    ProgressBar mProgressBar;
    CardView mCardView;
    MyDBHelper myDB ;
    ArrayList<String> log_id,sys1,dia1,bpm1,date1,time1,comment1;
    Handler mHandler;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= findViewById(R.id.add_record_btn);
        mProgressBar=findViewById(R.id.progress_bar);
        mCardView=findViewById(R.id.cardView);
        date=findViewById(R.id.dateText);
        time=findViewById(R.id.timeText);
        sys=findViewById(R.id.sysText);
        dia=findViewById(R.id.diaText);
        comment=findViewById(R.id.comText);
        bpm=findViewById(R.id.text_view_progress);
        his=findViewById(R.id.history);
        mImageView=findViewById(R.id.imageView4);
        myDB=new MyDBHelper(MainActivity.this);
        log_id=new ArrayList<>();
        sys1= new ArrayList<>();
        dia1=new ArrayList<>();
        date1=new ArrayList<>();
        time1=new ArrayList<>();
        bpm1=new ArrayList<>();
        comment1=new ArrayList<>();
        storeDataInArrays();
        mButton.setOnClickListener(view -> {
            DialogFragment dialogFragment =new DialogFragment();
            dialogFragment.show(getFragmentManager(),"MyFragment");
    });
    his.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ab= new Intent(MainActivity.this,LogActivity.class);
            startActivity(ab);
            finish();

        }
    });







        int position = log_id.size();

        int sysval=Integer.parseInt(sys1.get(position-1));
        int diaval=Integer.parseInt(dia1.get(position-1));
        if((sysval>=90 && sysval<140) && (diaval>=60 && diaval<=90 )){
            mCardView.setBackgroundResource(R.drawable.greenbgsq);

        }
        else if((sysval>=140 && sysval<180) || (diaval>90 && diaval<120 )){
            // com="High Blood Pressure";
            mCardView.setBackgroundResource(R.drawable.yellowbgsq);

        }
        else if(sysval>=180 || diaval>=120 ){
            //com="Hypertensive Crisis,Consult Doctor";
            mCardView.setBackgroundResource(R.drawable.redbgsq);
            sys.setTextColor(getResources().getColor(R.color.white));
            sys.setTextColor(getResources().getColor(R.color.white));

        }
        else if(sysval<90 || diaval<60){
            //com="Low Blood Pressure";
            mCardView.setBackgroundResource(R.drawable.yellowbgsq);
        }

            sys.setText(String.valueOf(sys1.get(position - 1)));
            dia.setText(String.valueOf(dia1.get(position - 1)));
            date.setText(String.valueOf(date1.get(position - 1)));
            time.setText(String.valueOf(time1.get(position - 1)));
            bpm.setText(String.valueOf(bpm1.get(position - 1)));
            comment.setText(String.valueOf(comment1.get(position - 1)));
            mProgressBar.setProgress(Integer.parseInt(bpm1.get(position - 1)));



        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(MainActivity.this,LogActivity.class);
                startActivity(b);
                finish();
            }
        });
    }

    public void storeDataInArrays(){

        Cursor cursor=myDB.readAllData();

        if(cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else{

            while(cursor.moveToNext()){
                log_id.add(cursor.getString(0));
                sys1.add(cursor.getString(1));
                dia1.add(cursor.getString(2));
                bpm1.add(cursor.getString(3));
                date1.add(cursor.getString(4));
                time1.add(cursor.getString(5));
                comment1.add(cursor.getString(6));
            }


        }



    }


    @Override
    protected void onResume() {
        super.onResume();
        myDB=new MyDBHelper(MainActivity.this);
        log_id=new ArrayList<>();
        sys1= new ArrayList<>();
        dia1=new ArrayList<>();
        date1=new ArrayList<>();
        time1=new ArrayList<>();
        bpm1=new ArrayList<>();
        comment1=new ArrayList<>();
        storeDataInArrays();
        int position = log_id.size();

        int sysval=Integer.parseInt(sys1.get(position-1));
        int diaval=Integer.parseInt(dia1.get(position-1));
        if((sysval>=90 && sysval<140) && (diaval>=60 && diaval<=90 )){
            mCardView.setBackgroundResource(R.drawable.greenbgsq);

        }
        else if((sysval>=140 && sysval<180) || (diaval>90 && diaval<120 )){
            // com="High Blood Pressure";
            mCardView.setBackgroundResource(R.drawable.yellowbgsq);

        }
        else if(sysval>=180 || diaval>=120 ){
            //com="Hypertensive Crisis,Consult Doctor";
            mCardView.setBackgroundResource(R.drawable.redbgsq);


        }
        else if(sysval<90 || diaval<60){
            //com="Low Blood Pressure";
            mCardView.setBackgroundResource(R.drawable.yellowbgsq);
        }

            sys.setText(String.valueOf(sys1.get(position - 1)));
            dia.setText(String.valueOf(dia1.get(position - 1)));
            date.setText(String.valueOf(date1.get(position - 1)));
            time.setText(String.valueOf(time1.get(position - 1)));
            bpm.setText(String.valueOf(bpm1.get(position - 1)));
            comment.setText(String.valueOf(comment1.get(position - 1)));
            mProgressBar.setProgress(Integer.parseInt(bpm1.get(position - 1)));


        }

    private final Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            MainActivity.this.mHandler.postDelayed(mRunnable,2000);
        }
    };

}
