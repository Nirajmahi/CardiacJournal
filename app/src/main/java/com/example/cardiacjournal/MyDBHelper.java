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

    public long addRecord(int sys,int dia,int bpm , String date ,String time , String comment){

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

    return result;


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

    public boolean checkDataExistsOrNot(Long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where " + id + " = " + Long.toString(id);
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean checkContent( String sys, String dias, String pulse, String date, String time, String comments) {
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        String[] columns = {MyDBHelper.COLUMN_SYS, MyDBHelper.COLUMN_DIA, MyDBHelper.COLUMN_BPM, MyDBHelper.COLUMN_DATE, MyDBHelper.COLUMN_TIME, MyDBHelper.COLUMN_COMMENT};
        Cursor cursor = sqLiteDatabase.query(MyDBHelper.TABLE_NAME, columns, MyDBHelper.COLUMN_ID+" = '"+"id"+"'", null, null, null, null);
        while (cursor.moveToNext()) {
            String i1 = String.valueOf(cursor.getColumnIndex(MyDBHelper.COLUMN_SYS));
            String i2 = String.valueOf(cursor.getColumnIndex(MyDBHelper.COLUMN_DIA));
            String i3 = String.valueOf(cursor.getColumnIndex(MyDBHelper.COLUMN_BPM));
            String i4 = String.valueOf(cursor.getColumnIndex(MyDBHelper.COLUMN_DATE));
            String i5 = String.valueOf(cursor.getColumnIndex(MyDBHelper.COLUMN_TIME));
            String i6 = String.valueOf(cursor.getColumnIndex(MyDBHelper.COLUMN_COMMENT));

            String sys1 = cursor.getString(Integer.parseInt(i1));
            String dia1 = cursor.getString(Integer.parseInt(i2));
            String pulse1 = cursor.getString(Integer.parseInt(i3));
            String date1 = cursor.getString(Integer.parseInt(i4));
            String time1 = cursor.getString(Integer.parseInt(i5));
            String comm1 = cursor.getString(Integer.parseInt(i6));

            if (sys != sys1 || dias != dia1  || pulse != pulse1  || date != date1 || time1 != time || comments != comm1) {
                cursor.close();
                return false;
            }
        }
        cursor.close();
        return true;
    }

}
