package com.hotelreservationsystem;

public class Hotel {
    enum CUSTOMER_TYPE{
        REGULAR_TYPE,
        REWARD_TYPE;
    };

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
}
