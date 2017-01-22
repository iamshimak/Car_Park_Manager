package root;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ShimaK on 11-Nov-16.
 */
class DateTime implements Serializable{

    final static int serialVersionUID = 1586736234;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    /*public DateTime(int year) {
        this(year,1);
    }

    public DateTime(int year, int month) {
        this(year,month,1);
    }

    public DateTime(int year, int month, int day) {
        this(year,month,day,0);
    }

    public DateTime(int year, int month, int day, int hour) {
        this(year,month,day,hour,0);
    }

    public DateTime(int year, int month, int day, int hour, int minute) {
        this(year,month,day,hour,minute,0);
    }

    public DateTime(int year, int month, int day, int hour, int minute, int seconds) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }*/

    public boolean setDateTime (int year, int month, int day, int hour, int minute, int second) {
        if(year > 1899 && year < 2100 && month > 0 && month < 13){
            if(month == 2 && year % 4 == 0) {
                if(!(day < 30)) {
                    return false;
                }
            } else if(month == 2 && year % 4 > 0) {
                if(!(day < 29)) {
                    return false;
                }
            } else {

                if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                        && day < 32) {

                } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day < 31) {

                } else {
                    return false;
                }
            }

            if(hour > -1 && hour < 25 && minute > -1 && minute < 61 && second > -1 && second < 61) {
                this.year = year;
                this.month = month;
                this.day = day;
                this.hour = hour;
                this.minute = minute;
                this.second = second;

                return true;
            }
        }
        return false;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconds() {
        return second;
    }

    public int differenceInHours(DateTime date) {
        /*int totHours = (date.getYear() - year)  * 8760;
        totHours += (date.getMonth() - month) * 730;
        totHours += (date.getDay() - day) * 24;
        totHours += date.getHour() - hour;
        return  totHours;*/
        return this.differenceInHours(date,this);
    }

    public int differenceInHours(DateTime d1 , DateTime d2){
        int totHours = (d1.getYear() - d2.getYear())  * 8760;
        totHours += (d1.getMonth() - d2.getMonth()) * 730;
        totHours += (d1.getDay() - d2.getDay()) * 24;
        totHours += d1.getHour() - d2.getHour();
        return  totHours;
    }

    public int differenceInMinutes(DateTime date) {
        int totMinutes = ((date.getYear()) - year) * 525600;
        totMinutes += ((date.getMonth()) - month) * 43800;
        totMinutes += ((date.getDay()) - day) * 1500;
        totMinutes += (date.getHour() - hour) * 60;
        totMinutes += date.getMinute() - minute;
        return  totMinutes;
        //return this.differenceInMinutes(date,this);
    }

    public int differenceInMinutes(DateTime d1 , DateTime d2) {
        int totMinutes = (d1.getYear() - d2.getYear())  * 8760 * 60;
        totMinutes += (d1.getMonth() - d2.getMonth()) * 730 * 60;
        totMinutes += (d1.getDay() - d2.getDay()) * 24 * 60;
        totMinutes += d1.getHour() - d2.getHour() * 60;
        totMinutes += d1.getMinute() - d2.getMinute();
        return  totMinutes;
    }

    public String toString(){
        return hour+":"+minute+" "+day+"/"+month+"/"+year;
    }
}
