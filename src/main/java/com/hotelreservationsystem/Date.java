package com.hotelreservationsystem;

import java.util.regex.*;

public class Date implements Comparable<Date>{
    enum Day{
        SUN,
        MON,
        TUE,
        WED,
        THU,
        FRI,
        SAT;
    }
    private static final int LOWER_BOUND_YEAR = 1800;
    private static final int UPPER_BOUND_YEAR = 9999;
    private static final int TOTAL_MONTHS = 12;
    private static final int t[] = {0, 3, 2, 5, 0, 3, 5,
            1, 4, 6, 2, 4};


    private int day;
    private int month;
    private int year;

    private int[] months;

    public Date(int day, int month, int year) throws InvalidDateExceptions{
        this.day = day;
        this.month = month;
        this.year = year;
        months = new int[13];
        if(!validate(this))
            throw new InvalidDateExceptions(InvalidDateExceptions.ExceptionType.INVALID_DATE, "Date invalid");
        else {
            months[1] = months[3] = months[5] = months[7]
                    = months[8] = months[10] = months[12] = 31;
            months[4] = months[6] = months[9] = months[11] = 30;
            if(isLeap(year))
                months[2] = 29;
            else
                months[2] = 28;
        }
    }

    private static boolean isLeap(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    private static boolean validate(Date d) {
        boolean isLeap = false, isValid = true;

        if(d.year >= LOWER_BOUND_YEAR && d.year <= UPPER_BOUND_YEAR) {
            isLeap = isLeap(d.year);

            if(d.month >=1 && d.month <= TOTAL_MONTHS ) {
                if(d.month == 2) {
                    if(isLeap && d.day >=1 && d.day <= 29) {
                        isValid = true;
                    }
                    else if(d.day >=1 && d.day<= 28) {
                        isValid = true;
                    }
                    else
                        isValid = false;
                }
                else if (d.month == 4 || d.month == 6 || d.month == 9 || d.month == 11) {
                    if(d.day>=1 && d.day<=30)
                        isValid = true;
                    else
                        isValid = false;
                }
                else if( d.day >= 1 && d.day <= 31)
                    isValid = true;
                else
                    isValid = false;
            }
            else
                isValid = false;
        }
        else
            isValid = false;

        return isValid;
    }

    public static Date extractDate(String date) throws InvalidDateExceptions {
        Pattern pattern = Pattern.compile("(^[0-9][0-9]{1})(\\/|\\-|\\.)?(([a-zA-Z]{3})|([1-9][1-9]))(\\/|\\-|\\.)?([1-9]{1}[0-9]{3}$)");
        Matcher m = pattern.matcher(date);
        if(m.find()) {
            String day = m.group(1);
            String month = m.group(3);
            String year = m.group(7);
            int d = Integer.parseInt(day);
            int mon;
            try {
                mon = Integer.parseInt(month);
            }catch(NumberFormatException e) {
                mon = getMonth(month.toLowerCase());
            }
            int y = Integer.parseInt(year);
            return new Date(d, mon, y);
        }
        else
           throw new InvalidDateExceptions(InvalidDateExceptions.ExceptionType.INVALID_PATTERN, "Not a valid input pattern for date");
    }

    private static int getMonth(String month) {
        if(month.compareTo("jan") == 0)
            return 1;
        if(month.compareTo("feb") == 0)
            return 2;
        if(month.compareTo("mar") == 0)
            return 3;
        if(month.compareTo("apr") == 0)
            return 4;
        if(month.compareTo("may") == 0)
            return 5;
        if(month.compareTo("jun") == 0)
            return 6;
        if(month.compareTo("jul") == 0)
            return 7;
        if(month.compareTo("aug") == 0)
            return 8;
        if(month.compareTo("sep") == 0)
            return 9;
        if(month.compareTo("oct") == 0)
            return 10;
        if(month.compareTo("nov") == 0)
            return 11;
        if(month.compareTo("dec") == 0)
            return 12;

        return -1;
    }

    @Override
    public String toString() {
        return this.day + "/" + this.month + "/" + this.year;
    }

    public boolean isInRange(Date d1, Date d2) {
        if(this.compareTo(d1) < 0)
            return false;
        if(this.compareTo(d2) > 0)
            return false;
        return true;
    }

    @Override
    public int compareTo(Date o) {
        if(this.day == o.day && this.month == o.month && this.year == o.year)
            return 0;
        else if(this.year > o.year || this.year == o.year && this.month > o.month ||
                this.year == o.year && this.month == o.month && this.day > o.day)
            return 1;
        else
            return -1;
    }

    private void increaseOneYear() {
        if(this.year + 1 < UPPER_BOUND_YEAR) {
            this.year++;
            if(isLeap(this.year)) {
                months[2] = 29;
            }
        }
        else {
            System.out.println("Throw Exception");
        }

    }

    private void increaseOneMonth() {
        if(this.month + 1 < TOTAL_MONTHS)
            this.month++;
        else {
            this.month = 1;
            increaseOneYear();
        }
    }

    public void addOneDay() {
        if(this.day + 1 < this.months[month])
            this.day++;
        else {
            this.day = 1;
            increaseOneMonth();
        }
    }

    public Day calculateDay() {
        int y = this.year;
        y -= (this.month < 3) ? 1 : 0;
        int d = ( y + y/4 - y/100 + y/400 + t[this.month-1] + this.day) % 7;
        switch(d){
            case 0	:	return Day.SUN;
            case 1	: 	return Day.MON;
            case 2	: 	return Day.TUE;
            case 3	:	return Day.WED;
            case 4	:	return Day.THU;
            case 5	:	return Day.FRI;
            case 6	:	return Day.SAT;
            default	:	return null;
        }
    }

    public int getDay() { return this.day; }
    public int getMonth() { return this.month; }
    public int getYear() { return this.year; }
}