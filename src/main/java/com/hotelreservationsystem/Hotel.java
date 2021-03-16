package com.hotelreservationsystem;

public class Hotel {
    private String name;
    private String location;
    private int weekdayRate;

    public Hotel(String name, String location, int weekdayRate){
        this.name = name;
        this.location = location;
        this.weekdayRate = weekdayRate;
    }

    public String getName(){ return this.name; }
    public String getLocation() { return this.location; }
    public int getWeekdayRate(){ return weekdayRate; }

    public int calculateRate(Date d1, Date d2) throws InvalidDateExceptions{
        Date currentDate = new Date(d1.getDay(), d1.getMonth(), d1.getYear());
        int calculatedRate = 0;
        while(currentDate.isInRange(d1, d2)){
            calculatedRate += this.weekdayRate;
            currentDate.addOneDay();
        }
        return calculatedRate;
    }
}
