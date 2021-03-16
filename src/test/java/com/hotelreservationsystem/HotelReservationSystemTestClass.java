package com.hotelreservationsystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class HotelReservationSystemTestClass {
    private HotelReservationSystem hotelReservationSystem;
    @Before
    public void init(){
        int lakeWoodWeekday = 110;
        int brideWoodWeekday = 160;
        int ridgeWoodWeekday = 220;


        Hotel lakewood = new Hotel("Lakewood", "Miami", lakeWoodWeekday);
        Hotel bridewood = new Hotel("Bridgewood", "Miami", brideWoodWeekday);
        Hotel ridgewood = new Hotel("Ridgewood", "Miami", ridgeWoodWeekday);

        hotelReservationSystem = new HotelReservationSystem();
        hotelReservationSystem.printWelcomeMessage();
        hotelReservationSystem.add(lakewood);
        hotelReservationSystem.add(bridewood);
        hotelReservationSystem.add(ridgewood);
    }

    @Test
    public void givenHotelsInTheSystem_ShowAllTheHotelNames_ShouldReturnProperHotel(){
        String names = hotelReservationSystem.getHotelNames();
        String expectedNames = "Lakewood Bridgewood Ridgewood ";
        Assert.assertEquals(expectedNames, names);
    }

    @Test
    public void givenHotelsInTheSystem_ShouldReturnTheCheapestHotelList_WithinGivenDates() throws InvalidDateExceptions{
        String date1 = "10Sep2020";
        String date2 = "11Sep2020";
        Map<Hotel, Integer> map = hotelReservationSystem.getCheapestHotels(date1, date2);
        boolean result = false;
        for( Map.Entry<Hotel, Integer> entry : map.entrySet()){
            if(entry.getKey().getName().equals("Lakewood") && entry.getValue().equals(Integer.valueOf(220)))
                result = true;
            System.out.println("Hotel: " + entry.getKey().getName() + "Rate: " + entry.getValue());
        }
        Assert.assertTrue(result);
    }
}
