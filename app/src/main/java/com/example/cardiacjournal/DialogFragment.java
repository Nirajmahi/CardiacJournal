package com.example.cardiacjournal;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DialogFragment extends android.app.DialogFragment {
    private static final String TAG = "DialogFragment";
    public  Context thiscontext;

    public interface OnInputListener {
        void sendInput(int sysval,int diaval,int bpm,String comment);
    }
    public OnInputListener mOnInputListener;

    EditText sysInput,diaInput,bpmInput,commentInput,Date,Time;
    Button mButton;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        thiscontext= this.getContext();


        View v= inflater.inflate(R.layout.input_fragement,container,false);
        sysInput=v.findViewById(R.id.systolic);
        diaInput=v.findViewById(R.id.diastolic);
        bpmInput=v.findViewById(R.id.bpm_in);
        commentInput=v.findViewById(R.id.comment_in);
        mButton=v.findViewById(R.id.add_btn);
        Date=v.findViewById(R.id.date);
        Time=v.findViewById(R.id.time);


        Date.setInputType(InputType.TYPE_NULL);
        Time.setInputType(InputType.TYPE_NULL);

        Bundle bundle =new Bundle();
        bundle.putInt("sysval",sysInput.getInputType());
        bundle.putInt("diaval",diaInput.getInputType());
        bundle.putInt("bpmval",bpmInput.getInputType());
        bundle.putString("commentval",commentInput.getText().toString());


        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(Date);
            }


        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(Time);
            }
        });




        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sysval = sysInput.getInputType();
                int diaval = diaInput.getInputType();
                int bpm = bpmInput.getInputType();
                String com=commentInput.getText().toString();
                mOnInputListener.sendInput(sysval,diaval,bpm,com);
                getDialog().dismiss();
            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showTimeDialog(EditText time) {
        Calendar calendar= Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourofDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                Time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(thiscontext,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }


    private void showDateDialog(EditText date) {
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd");
                Date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(thiscontext,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }




}

