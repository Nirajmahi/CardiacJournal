package com.example.cardiacjournal;

import java.util.Date;

public class CardiacHistory {
    /**
     * this class is for setdata
     */

     Date mDate;

        Number Sys,Dia,Bpm;
        String Comment;

        public CardiacHistory(Date date, Number sys, Number dia, Number bpm, String comment) {
            mDate = date;

            Sys = sys;
            Dia = dia;
            Bpm = bpm;
            Comment = comment;
        }

        public CardiacHistory(Date date,  Number sys, Number dia, Number bpm) {
            mDate = date;

            Sys = sys;
            Dia = dia;
            Bpm = bpm;
        }


        public Date getDate() {
            return mDate;
        }

        public void setDate(Date date) {
            mDate = date;
        }





        public Number getSys() {
            return Sys;
        }

        public void setSys(Number sys) {
            Sys = sys;
        }

        public Number getDia() {
            return Dia;
        }

        public void setDia(Number dia) {
            Dia = dia;
        }

        public Number getBpm() {
            return Bpm;
        }

        public void setBpm(Number bpm) {
            Bpm = bpm;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String comment) {
            Comment = comment;
        }
    }



