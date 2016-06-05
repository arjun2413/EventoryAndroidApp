package com.spuds.eventapp.Shared;

import android.util.Log;

import java.util.GregorianCalendar;

/**
 * Created by tina on 5/13/16.
 */
public class EventDate {

    GregorianCalendar gregorianCalendar;
    boolean valid = false;
    public int year,month,day,hour;
    String minute;
    private String[] months = {"January","February","March","April","May","June","July","August",
                               "September","October","November","December"};


    //ctor
    public EventDate(String date) {

        Log.e("EventDate" , "date: " + date);

        //Check for proper date form
        // TODO validate string not working
        if(validateString(date)) {

            //Use substring to parse each snippet of string.
            this.year = Integer.parseInt(date.substring(0,2));
            this.month = Integer.parseInt(date.substring(3,5));
            this.day = Integer.parseInt(date.substring(6,8));
            this.hour = Integer.parseInt(date.substring(11,13));
            this.minute = date.substring(14,16);

            //Check that dates are in reasonable range
            if(     this.year >= 2016 &&
                    this.month >= 1 && this.month <= 12 &&
                    this.day >= 1   && this.day <= 31 &&
                    this.hour >= 0 && this.hour <= 12 &&
                    Integer.parseInt(this.minute) >= 0 && Integer.parseInt(this.minute) <= 59) {
                gregorianCalendar = new GregorianCalendar(this.year,this.month,this.day,
                                                          this.hour,Integer.parseInt(this.minute));
            }

            if (String.valueOf(this.minute).length() == 1)
                this.minute += "0";

        }
        else {
            //("Improper", "string value for date");
        }
    }

    public String get24Time(){
        return this.hour + ":" + this.minute;
    }

    public String get12Time() {

        if(this.hour < 12 ){
            return this.hour + ":" + this.minute + " AM";
        }
        else{
            int a = this.hour - 12;
            return a + ":" + this.minute + " PM";
        }

    }

    public String get12TimeMinusAMPM() {
        if(this.hour < 12 ){
            return this.hour + ":" + this.minute;
        }
        else{
            int a = this.hour - 12;
            return a + ":" + this.minute;
        }
    }

    // TODO probably wrong
    public String getAMPM() {
        //("AMPM", String.valueOf(this.hour));
        if(this.hour < 12 ){
            return "AM";
        }
        else{
            return "PM";
        }
    }

    public String getDate() {
        int a = this.month;
        return months[a] + " " + this.day + ", " + this.year;
    }

    public String getDateLong(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    public String getMonth(boolean upperCase) {
        //return lower case month
        if (!upperCase) {
            return months[this.month - 1];
        }
        //return upper case month
        else {
            return months[this.month - 1].toUpperCase();
        }
    }

    public String getDay() {
        return Integer.toString(this.day);
    }

    private boolean validateString(String s){
        //("YEEZY", s);
        //use RegEx to ensure correct format. If it is in correct form, return true.
        if(s.matches("\\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2}")){
            return true;
        }
        //if bad form return false;
        return false;

    }
}
