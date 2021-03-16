package com.hotelreservationsystem;

import java.util.ArrayList;

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
}
