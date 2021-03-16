package com.hotelreservationsystem;

public class Hotel {
    private String name;
    private String location;
    private int weekdayRate;
    private int weekendDayRate;
    private int rating;

    public Hotel(String name, String location, int weekdayRate, int weekendDayRate, int rating){
        this.name = name;
        this.location = location;
        this.weekdayRate = weekdayRate;
        this.weekendDayRate = weekendDayRate;
        this.rating = rating;
    }

    public String getName(){ return this.name; }
    public String getLocation() { return this.location; }
    public int getWeekdayRate(){ return this.weekdayRate; }
    public int getWeekendDayRate(){ return this.weekendDayRate; }
    public int rating(){ return this.rating; }

    public int calculateRate(Date d1, Date d2) throws InvalidDateExceptions{
        Date currentDate = new Date(d1.getDay(), d1.getMonth(), d1.getYear());
        int calculatedRate = 0;
        while(currentDate.isInRange(d1, d2)){
            if(currentDate.calculateDay().equals(Date.Day.SAT) || currentDate.calculateDay().equals(Date.Day.SUN))
                calculatedRate += this.weekendDayRate;
            else
                calculatedRate += this.weekdayRate;
            currentDate.addOneDay();
        }
        return calculatedRate;
    }
}
