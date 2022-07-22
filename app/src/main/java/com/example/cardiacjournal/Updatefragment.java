package com.example.cardiacjournal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

//import com.app.c.MyDBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Updatefragment extends android.app.DialogFragment {
    private static final String TAG = "UpdateFragment";
    public Context thiscontext;

    public interface OnInputListener {
        void sendInput(int sysval, int diaval, int bpm, String comment);
    }

    public OnInputListener mOnInputListener;

    EditText sysInput, diaInput, bpmInput, commentInput, Date, Time;
    Button mButton, dButton;
    TextView comIn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        thiscontext = this.getContext();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View v = inflater.inflate(R.layout.updatefragment, container, false);
        sysInput = v.findViewById(R.id.systolic1);
        diaInput = v.findViewById(R.id.diastolic1);
        bpmInput = v.findViewById(R.id.bpm_in1);
        commentInput = v.findViewById(R.id.comment_in1);
        mButton = v.findViewById(R.id.update_btn);
        Date = v.findViewById(R.id.date1);
        Time = v.findViewById(R.id.time1);
        comIn = v.findViewById(R.id.comment_in1);
        dButton = v.findViewById(R.id.deletee_btn);

        Date.setInputType(InputType.TYPE_NULL);
        Time.setInputType(InputType.TYPE_NULL);

        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        String sys = bundle.getString("sysval");
        String dia = bundle.getString("diaval");
        String bpm = bundle.getString("bpm");
        String comment = bundle.getString("comment");
        String dateval = bundle.getString("date");
        String timeval = bundle.getString("time");
        sysInput.setText(sys);
        diaInput.setText(dia);
        bpmInput.setText(bpm);
        commentInput.setText(comment);
        Date.setText(dateval);
        Time.setText(timeval);


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


        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper mydb = new MyDBHelper(thiscontext);
                mydb.deleteOneRow(id);
                getDialog().dismiss();
            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(sysInput.getText().toString().trim()) || TextUtils.isEmpty(diaInput.getText().toString().trim()) || TextUtils.isEmpty(bpmInput.getText().toString().trim()) || TextUtils.isEmpty(Date.getText().toString().trim()) || TextUtils.isEmpty(Time.getText().toString().trim())) {
                    if (TextUtils.isEmpty(sysInput.getText().toString().trim())) {
                        Toast.makeText(thiscontext, "Systolic Pressure Field is Required", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(diaInput.getText().toString().trim())) {
                        Toast.makeText(thiscontext, "Diastolic Pressure Field is Required", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(bpmInput.getText().toString().trim())) {
                        Toast.makeText(thiscontext, "Beats Per Minute Field is Required", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(Date.getText().toString().trim())) {
                        Toast.makeText(thiscontext, "Date Field is Required", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(Time.getText().toString().trim())) {
                        Toast.makeText(thiscontext, "Time Field is Required", Toast.LENGTH_LONG).show();
                    }
                } else {
                    int sysval = Integer.parseInt(sysInput.getText().toString());
                    int diaval = Integer.parseInt(diaInput.getText().toString());
                    int bpmval = Integer.parseInt(bpmInput.getText().toString());
                    String com = null;
                    String datevall = Date.getText().toString();
                    String timevall = Time.getText().toString();
                    if (TextUtils.isEmpty(commentInput.getText().toString().trim())) {

                        if ((sysval >= 90 && sysval < 140) && (diaval >= 60 && diaval <= 90)) {
                            com = "Normal";

                        } else if ((sysval >= 140 && sysval < 180) || (diaval > 90 && diaval < 120)) {
                            com = "High Blood Pressure";

                        } else if (sysval >= 180 || diaval >= 120) {
                            com = "Hypertensive Crisis,Consult Doctor";

                        } else if (sysval < 90 || diaval < 60) {
                            com = "Low Blood Pressure";

                        }

                    } else {
                        com = commentInput.getText().toString();
                    }

                    String syss = sysInput.getText().toString().trim();
                    String dias = diaInput.getText().toString().trim();
                    String bpmm = bpmInput.getText().toString().trim();
                    String comm = commentInput.getText().toString().trim();
                    String datevalll = Date.getText().toString().trim();
                    String timevalll = Time.getText().toString();
                    MyDBHelper mydb = new MyDBHelper(thiscontext);
                    mydb.updatedata(id, syss, dias, bpmm, comm, datevalll, timevall);
                    //mOnInputListener.sendInput(sysval,diaval,com);
                    getDialog().dismiss();
                }


            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showTimeDialog(EditText time) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourofDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                Time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(thiscontext, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }


    private void showDateDialog(EditText date) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
                Date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(thiscontext, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}