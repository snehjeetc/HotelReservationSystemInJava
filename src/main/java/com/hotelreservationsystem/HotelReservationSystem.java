package com.hotelreservationsystem;

import java.util.*;

public class HotelReservationSystem {
    private ArrayList<Hotel> hotelList;

    public HotelReservationSystem(){
        hotelList = new ArrayList<>();
    }

    public void add(Hotel h){
        hotelList.add(h);
    }

    public void printWelcomeMessage(){
        System.out.println("Welcome to hotel reservation program.");
    }

    public String getHotelNames(){
        StringBuilder names = new StringBuilder();
        for(Hotel h : hotelList)
            names.append(h.getName() + " ");
        return names.toString();
    }

    public Map<Hotel, Integer> getCheapestHotels (String from_date, String to_date) throws InvalidDateExceptions{
        Date d1 = Date.extractDate(from_date);
        Date d2 = Date.extractDate(to_date);
        Map<Hotel, Integer> cheapHotels_andRatesMap = new HashMap<>();
        Integer cheapestRate = Integer.MAX_VALUE;
        for(Hotel hotel : hotelList){
            Integer total_period_rate = hotel.calculateRate(d1, d2);
            if(total_period_rate < cheapestRate){
                cheapHotels_andRatesMap.clear();
                cheapestRate = total_period_rate;
                cheapHotels_andRatesMap.put(hotel, cheapestRate);
            }
            else if(total_period_rate == cheapestRate){
                cheapHotels_andRatesMap.put(hotel, total_period_rate);
            }
        }
        return cheapHotels_andRatesMap;
    }

    public String printNameRates(){
        StringBuilder hotelListString = new StringBuilder();
        for(Hotel hotel : hotelList){
            hotelListString.append(hotel.getName() + " Weekday Rate: " + hotel.getWeekdayRate()
                            + " Weekend Day Rate: " + hotel.getWeekendDayRate() + "\n");
        }
        return hotelListString.toString();
    }
}
