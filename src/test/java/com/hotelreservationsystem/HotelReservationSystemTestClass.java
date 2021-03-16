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
        int lakeWoodWeekendDay = 90;

        int brideWoodWeekday = 150;
        int bridWoodWeekendDay = 50;

        int ridgeWoodWeekday = 220;
        int ridgeWoodWeekendDay = 150;



        Hotel lakewood = new Hotel("Lakewood", "Miami", lakeWoodWeekday, lakeWoodWeekendDay, 3);
        Hotel bridewood = new Hotel("Bridgewood", "Miami", brideWoodWeekday, bridWoodWeekendDay, 4);
        Hotel ridgewood = new Hotel("Ridgewood", "Miami", ridgeWoodWeekday, ridgeWoodWeekendDay, 5);

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

    @Test
    public void givenHotelsInTheSystem_ShouldReturnItsWeekDayAndWeekendDayRate(){
        String hotelListRates = hotelReservationSystem.printNameWeekDayAndWeekendRates();
        String expected = "Lakewood Weekday Rate: 110 Weekend Day Rate: 90\n" +
                "Bridgewood Weekday Rate: 160 Weekend Day Rate: 90\n" +
                "Ridgewood Weekday Rate: 220 Weekend Day Rate: 150\n";
        System.out.println(hotelListRates);
        Assert.assertEquals(expected, hotelListRates);
    }

    @Test
    public void givenHotelsInTheSystem_ShouldReturnTheCheapestHotelsInTheSystem_WithinGivenRangeOfDate()
            throws InvalidDateExceptions{
        String date1 = "11Sep2020";
        String date2 = "12Sep2020";
        Map<Hotel, Integer> map = hotelReservationSystem.getCheapestHotels(date1, date2);
        boolean result1_lakewood = false, result2_bridgewood = false;
        boolean rates_lakewood = false, rates_bridgewood = false;
        boolean tot_size = false;
        int size = 0;
        for(Map.Entry<Hotel, Integer> entry : map.entrySet()){
            if(entry.getKey().getName().equals("Lakewood") && entry.getValue().equals(Integer.valueOf(200))){
                result1_lakewood = true;
                rates_lakewood = true;
            }
            if(entry.getKey().getName().equals("Bridgewood") && entry.getValue().equals(Integer.valueOf(200))){
                result2_bridgewood = true;
                rates_bridgewood = true;
            }
            System.out.println("Name : " + entry.getKey().getName() + " Rate: " + entry.getValue());
            size++;
        }
        tot_size = (size == 2);
        Assert.assertTrue(result1_lakewood && rates_lakewood
                && result2_bridgewood && rates_bridgewood && tot_size);
    }

    @Test
    public void givenHotelsInTheSystem_ShouldReturnProperRatingsAsAssigned(){
        String result = hotelReservationSystem.printNamesAndRating();
        String expectedResult = "Lakewood Rating: 3\n" +
                "Bridgewood Rating: 4\n" +
                "Ridgewood Rating: 5\n";
        System.out.println(result);
        Assert.assertEquals(expectedResult, result);
    }
}
