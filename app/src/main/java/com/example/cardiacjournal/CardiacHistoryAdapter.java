package com.example.cardiacjournal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CardiacHistoryAdapter extends RecyclerView.Adapter<CardiacHistoryAdapter.viewHolder>{

    Context context;
    Updatefragment updatefragment;
    ArrayList log_id,sys,dia,bpm,date,time,comment;


    public AdapterView.OnItemClickListener listener;

    public CardiacHistoryAdapter(Context context,
                            ArrayList log_id,
                            ArrayList sys,
                            ArrayList dia,
                            ArrayList bpm,
                            ArrayList date,
                            ArrayList time,
                            ArrayList comment){
        this.context=context;
        this.log_id=log_id;
        this.dia=dia;
        this.time=time;
        this.bpm=bpm;
        this.sys=sys;
        this.comment=comment;
        this.date=date;
    }






    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_log,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        holder.mdate.setText(String.valueOf(date.get(position))+" "+String.valueOf(time.get(position)));

        int sysval=Integer.parseInt(String.valueOf(sys.get(position)));
        int diaval=Integer.parseInt(String.valueOf(dia.get(position)));
        holder.msys.setText(String.valueOf(sys.get(position)));
        holder.mdia.setText(String.valueOf(dia.get(position)));
        holder.mbpm.setText(String.valueOf(bpm.get(position)));
        holder.mcomment.setText(String.valueOf(comment.get(position)));


        if((sysval>=90 && sysval<140) && (diaval>=60 && diaval<=90 )){
            holder.mcomment.setBackgroundResource(R.drawable.greenbg);

        }
        else if((sysval>=140 && sysval<180) || (diaval>90 && diaval<120 )){
            // com="High Blood Pressure";
            holder.mcomment.setBackgroundResource(R.drawable.yellowbg);

        }
        else if(sysval>=180 || diaval>=120 ){
            //com="Hypertensive Crisis,Consult Doctor";
            holder.mcomment.setBackgroundResource(R.drawable.red_bg);


        }
        else if(sysval<90 || diaval<60){
            //com="Low Blood Pressure";
            holder.mcomment.setBackgroundResource(R.drawable.yellowbg);
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity=(AppCompatActivity)view.getContext();
                Updatefragment updatefragment=new Updatefragment();
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(log_id.get(position)));
                bundle.putString("sysval",String.valueOf(sys.get(position)));
                bundle.putString("diaval",String.valueOf(dia.get(position)));
                bundle.putString("bpm",String.valueOf(bpm.get(position)));
                bundle.putString("comment",String.valueOf(comment.get(position)));
                bundle.putString("date",String.valueOf(date.get(position)));
                bundle.putString("time",String.valueOf(time.get(position)));
                updatefragment.setArguments(bundle);
                updatefragment.show(appCompatActivity.getFragmentManager(),"myfrag");


            }
        });



    }



    @Override
    public int getItemCount() {
        return log_id.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        TextView mdate,msys,mdia,mbpm,mcomment,mtime;
        ImageButton medit,mdelete;
        ConstraintLayout mainLayout;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            mdate= itemView.findViewById(R.id.date);
            msys= itemView.findViewById(R.id.sys_m);
            mdia= itemView.findViewById(R.id.dia_m);
            mbpm= itemView.findViewById(R.id.bpm_m);
            mcomment= itemView.findViewById(R.id.comment_m);
            mainLayout=itemView.findViewById(R.id.layoutmain);


        }
    }

}

