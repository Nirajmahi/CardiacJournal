package com.example.cardiacjournal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.annotation.IntRange;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@RunWith(RobolectricTestRunner.class)
public class databasetest {
    /**
     * this method test if a value is inserted in database properly
     */
    @Test
    public void testInsertData()
    {
        MyDBHelper myDatabaseHelper = new MyDBHelper((RuntimeEnvironment.application));

        Integer sys = 120;
        Integer dias = 60;
        Integer pul = 65;

        Calendar calendar = Calendar.getInstance();

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String time = dateFormat.format(calendar.getTime());

        long i=myDatabaseHelper.addRecord(sys,dias,pul,date,time,"sitting");
        assertTrue(myDatabaseHelper.checkDataExistsOrNot(i));

        myDatabaseHelper.close();


    }

    /**
     * this method is to test to test the if the record is updated properly
     */
    @Test
    public void testUpdateData() {
        MyDBHelper myDatabaseHelper = new MyDBHelper(RuntimeEnvironment.application);

        Integer sys = 120;
        Integer dias = 60;
        Integer pul = 65;
        Calendar calendar = Calendar.getInstance();

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String time = dateFormat.format(calendar.getTime());
        long i = myDatabaseHelper.addRecord(sys,dias,pul,date,time,"sitting");


        myDatabaseHelper.updatedata(Long.toString(myDatabaseHelper.addRecord(sys,dias,pul,date,time,"sitting")),"100","65","65",date,time,"lying");

        assertTrue(myDatabaseHelper.checkContent(Long.toString(myDatabaseHelper.addRecord(sys,dias,pul,date,time,"sitting")),"100","65",date,time,"lying"));

        myDatabaseHelper.close();
    }
    /**
     * this method is to test to test the if the record is deleted properly from database
     */
    @Test
    public void DeleteData()
    {
        MyDBHelper myDatabaseHelper = new MyDBHelper(RuntimeEnvironment.application);

        Integer sys = 120;
        Integer dias = 60;

        Integer pul = 65;

        Calendar calendar = Calendar.getInstance();

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String time = dateFormat.format(calendar.getTime());
        long i = myDatabaseHelper.addRecord(sys,dias,pul,date,time,"sitting");

        myDatabaseHelper.deleteOneRow(Long.toString(i));
        assertFalse(myDatabaseHelper.checkDataExistsOrNot(i));
        myDatabaseHelper.close();
    }



}








