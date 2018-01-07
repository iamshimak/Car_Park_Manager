package root;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * Created by ShimaK on 11-Nov-16.
 */
class DateTime implements Serializable {

    final static int serialVersionUID = 1586736234;
    private Date date;

    DateTime () {
        date = new Date();
    }

    DateTime (int year, int month, int day) {
        this(year, month, day , 0, 0);
    }

    DateTime (int year, int month, int day, int hour, int min) {
        try {
            LocalDate localDate = LocalDate.of(year, month, day);
            LocalTime localTime = LocalTime.of(hour, min);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            throw new InputMismatchException(e.getLocalizedMessage());
        }
    }

    public int getYear() {
        return localDateTime().getYear();
    }

    public int getMonth() {
        return localDateTime().getMonthValue();
    }

    public int getDay() {
        return localDateTime().getDayOfMonth();
    }

    public int getHour() {
        return localDateTime().getHour();
    }

    public int getMinute() {
        return localDateTime().getMinute();
    }

    public int getSeconds() {
        return localDateTime().getSecond();
    }

    private LocalDateTime localDateTime() {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
        int totMinutes = ((date.getYear()) - getYear()) * 525600;
        totMinutes += ((date.getMonth()) - getMonth()) * 43800;
        totMinutes += ((date.getDay()) - getDay()) * 1500;
        totMinutes += (date.getHour() - getHour()) * 60;
        totMinutes += date.getMinute() - getMinute();
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
        return getHour() + ":" + getMinute() + " " + getDay() + "/" + getMonth() + "/" + getYear();
    }
}
