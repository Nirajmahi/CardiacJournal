package com.example.cardiacjournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private  Context context;
    private static  final String DATABASE_NAME="CardioLog.db"; //giving our database a name
    private static final int DATABASE_VERSION=1;
    private static  final String TABLE_NAME ="my_record";
    private static  final String COLUMN_ID="id";
    private static  final String COLUMN_SYS="systolic";
    private static  final String COLUMN_DIA="diastolic";
    private static  final String COLUMN_BPM="bpm";
    private static  final String COLUMN_DATE="date";
    private static  final String COLUMN_TIME="time";
    private static  final String COLUMN_COMMENT="comment";

    public MyDBHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SYS + " INTEGER, " +
                COLUMN_DIA + " INTEGER, " +
                COLUMN_BPM + " INTEGER, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_COMMENT + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }

    public void addRecord(int sys,int dia,int bpm , String date ,String time , String comment){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SYS,sys);
        cv.put(COLUMN_DIA,dia);
        cv.put(COLUMN_BPM,bpm);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_TIME,time);
        cv.put(COLUMN_COMMENT,comment);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result==-1){
            Toast.makeText(context,"Failed to add",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }





    }

 Cursor readAllData(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
 }

 void updatedata(String row_id,String sys, String dia, String bpm, String comment, String date, String time)
 {SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv= new ContentValues();

        cv.put(COLUMN_SYS,sys);
        cv.put(COLUMN_DIA,dia);
        cv.put(COLUMN_BPM,bpm);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_TIME,time);
        cv.put(COLUMN_COMMENT,comment);
        long result= db.update(TABLE_NAME,cv,"id=?",new String[]{row_id});
     if(result==-1){
         Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show();
     }
     else{
         Toast.makeText(context,"Updated Successfully",Toast.LENGTH_SHORT).show();
     }
 }

 void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME,"id=?", new String[]{row_id});
     if(result==-1){
         Toast.makeText(context,"Failed to delete",Toast.LENGTH_SHORT).show();
     }
     else{
         Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
     }
    }

}
