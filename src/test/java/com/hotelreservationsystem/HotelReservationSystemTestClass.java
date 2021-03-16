package com.hotelreservationsystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HotelReservationSystemTestClass {
    private HotelReservationSystem hotelReservationSystem;
    @Before
    public void init(){
        int lakeWoodWeekday = 110;
        int brideWoodWeekday = 160;
        int ridgeWoodWeekday = 220;


        Hotel lakewood = new Hotel("Lakewood", "Miami", 250);
        Hotel bridewood = new Hotel("Bridgewood", "Miami", 200);
        Hotel ridgewood = new Hotel("Ridgewood", "Miami", 130);

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
}
