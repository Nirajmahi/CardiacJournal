package com.example.cardiacjournal;

<<<<<<< HEAD
import android.content.Intent;
=======

>>>>>>> 6b4447b545325b53d4aaf71c2069f60a54b1fe99
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mButton;
    Button history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= findViewById(R.id.add_record_btn);
        mButton.setOnClickListener(view -> {
            DialogFragment dialogFragment =new DialogFragment();
            dialogFragment.show(getFragmentManager(),"MyFragment");
    });



        history=findViewById(R.id.historyy);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(MainActivity.this,LogActivity.class);
                startActivity(b);
                finish();
            }
        });
}
}