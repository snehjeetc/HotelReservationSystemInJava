package com.hotelreservationsystem;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    public Map<Hotel, Integer> getCheapestHotels (Hotel.Customer_Type type, String from_date, String to_date)
            throws InvalidDateExceptions, HotelException{
        Date d1 = Date.extractDate(from_date);
        Date d2 = Date.extractDate(to_date);
        Map<Hotel, Integer> cheapHotels_andRatesMap = new HashMap<>();
        Integer cheapestRate = Integer.MAX_VALUE;
        for(Hotel hotel : hotelList){
            Integer total_period_rate = hotel.calculateRate(type, d1, d2);
            int compare = total_period_rate.compareTo(cheapestRate);
            if( compare < 0){
                cheapHotels_andRatesMap.clear();
                cheapestRate = total_period_rate;
                cheapHotels_andRatesMap.put(hotel, cheapestRate);
            }
            else if(compare == 0){
                cheapHotels_andRatesMap.put(hotel, total_period_rate);
            }
        }
        return cheapHotels_andRatesMap;
    }

    public String printHotelsDetails(){
        StringBuilder hotelListString = new StringBuilder();
        for(Hotel hotel : hotelList){
            hotelListString.append(hotel + "\n");
        }
        return hotelListString.toString();
    }

    public String printNamesAndRating(){
        StringBuilder hotelListString = new StringBuilder();
        for(Hotel hotel : hotelList)
            hotelListString.append(hotel.getName() + " Rating: " + hotel.rating() + "\n");
        return hotelListString.toString();
    }

    public Map<Hotel, Integer> getBestRatedCheapestHotels(Hotel.Customer_Type type, String from_date, String to_date)
            throws InvalidDateExceptions, HotelException {
        Date d1 = Date.extractDate(from_date);
        Date d2 = Date.extractDate(to_date);
        Map<Hotel, Integer> cheapHotels_andRatesMap = new HashMap<>();
        Integer cheapestRate = Integer.MAX_VALUE;
        Hotel ref_cheapestHotel = null;
        for(Hotel hotel : hotelList){
            Integer total_period_rate = hotel.calculateRate(type, d1, d2);
            int compare = total_period_rate.compareTo(cheapestRate);
            if( compare < 0){
                cheapHotels_andRatesMap.clear();
                cheapestRate = total_period_rate;
                ref_cheapestHotel = hotel;
                cheapHotels_andRatesMap.put(hotel, cheapestRate);
            }
            else if(compare == 0){
                if(hotel.rating() > ref_cheapestHotel.rating()) {
                    cheapHotels_andRatesMap.clear();
                    cheapHotels_andRatesMap.put(hotel, total_period_rate);
                }
                else if(hotel.rating() == ref_cheapestHotel.rating()){
                    cheapHotels_andRatesMap.put(hotel, total_period_rate);
                }
            }
        }
        return cheapHotels_andRatesMap;
    }

    public Map<Hotel, Integer> getBestRatedHotels(Hotel.Customer_Type type, String from_date, String to_date)
            throws InvalidDateExceptions, HotelException{
        Date d1 = Date.extractDate(from_date);
        Date d2 = Date.extractDate(to_date);
        Map<Hotel, Integer> bestRatedHotels_AndTheirRates = new HashMap<>();
        int rating = -1;
        for(Hotel hotel : hotelList){
            if(hotel.rating() > rating){
                bestRatedHotels_AndTheirRates.clear();
                rating = hotel.rating();
                bestRatedHotels_AndTheirRates.put(hotel, hotel.calculateRate(type, d1, d2));
            }
            else if(hotel.rating() == rating){
                bestRatedHotels_AndTheirRates.put(hotel, hotel.calculateRate(type, d1, d2));
            }
        }
        return bestRatedHotels_AndTheirRates;
    }

    public List<Hotel> getCheapAndBestRatedHotelList(Hotel.Customer_Type valueOf, String date1, String date2)
    throws InvalidDateExceptions, HotelException{
        Date d1 = Date.extractDate(date1);
        Date d2 = Date.extractDate(date2);
        LocalDate localDate1 = LocalDate.of(d1.getYear(), d1.getMonth(), d1.getDay());
        LocalDate localDate2 = LocalDate.of(d2.getYear(), d2.getMonth(), d2.getDay());
        Hotel min = hotelList.stream()
                            .min((a, b) ->{
                                int a_val = 0;
                                int b_val = 0;
                                a_val = a.calculateRate_LocalDates(valueOf, localDate1, localDate2);
                                b_val = b.calculateRate_LocalDates(valueOf, localDate1, localDate2);
                                if(a_val - b_val == 0)
                                    return a.rating() - b.rating();
                                else if(a_val - b_val > 0)
                                    return a_val;
                                else
                                    return b_val;
                            })
                           .get();
        if(min == null)
            throw new HotelException("Invalid customer type");
        int minimum = min.calculateRate_LocalDates(valueOf, localDate1, localDate2);
        return  hotelList.stream()
                .filter(e -> {
                    boolean result = false;
                    result =  e.calculateRate_LocalDates(valueOf, localDate1, localDate2) == minimum;
                    if(result == true)
                        return true;
                    else
                        return false;
                }).collect(Collectors.toList());
    }
}
