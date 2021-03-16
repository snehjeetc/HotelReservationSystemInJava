package com.hotelreservationsystem;

public class Hotel {
    enum Customer_Type{
        REGULAR_TYPE,
        REWARD_TYPE;
    }

    private String name;
    private String location;
    private int weekdayRate[];
    private int weekendDayRate[];
    private int rating;

    public Hotel(String name, String location, int weekdayRate[], int weekendDayRate[], int rating){
        this.name = name;
        this.location = location;
        this.weekdayRate = weekdayRate;
        this.weekendDayRate = weekendDayRate;
        this.rating = rating;
    }

    public String getName(){ return this.name; }
    public String getLocation() { return this.location; }
    public int[] getWeekdayRate(){ return this.weekdayRate; }
    public int[] getWeekendDayRate(){ return this.weekendDayRate; }
    public int rating(){ return this.rating; }

    public int calculateRate(Customer_Type type, Date d1, Date d2) throws InvalidDateExceptions, HotelException{
        if(!type.equals(Customer_Type.REGULAR_TYPE) && !type.equals(Customer_Type.REWARD_TYPE))
            throw new HotelException("Invalid customer type");
        Date currentDate = new Date(d1.getDay(), d1.getMonth(), d1.getYear());
        int calculatedRate = 0;
        while(currentDate.isInRange(d1, d2)){
            if(currentDate.calculateDay().equals(Date.Day.SAT) || currentDate.calculateDay().equals(Date.Day.SUN))
                calculatedRate += this.weekendDayRate[type.ordinal()];
            else
                calculatedRate += this.weekdayRate[type.ordinal()];
            currentDate.addOneDay();
        }
        return calculatedRate;
    }

    @Override
    public String toString(){
        return this.name +", " + this.location + ": Weekday and Weekend regular rate: " + weekdayRate[Customer_Type.REGULAR_TYPE.ordinal()] +
                           ", " + weekendDayRate[Customer_Type.REGULAR_TYPE.ordinal()] +
                " Weekday and Weekend reward rates: " + weekdayRate[Customer_Type.REWARD_TYPE.ordinal()] + ", " +
                weekendDayRate[Customer_Type.REWARD_TYPE.ordinal()] + " Rating : " + this.rating;
    }
}
