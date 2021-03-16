package com.hotelreservationsystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class HotelReservationSystemTestClass {
    private HotelReservationSystem hotelReservationSystem;
    @Before
    public void init(){
        int lakeWoodWeekday[] = new int[2];
        lakeWoodWeekday[Hotel.Customer_Type.REGULAR_TYPE.ordinal()] = 110;
        lakeWoodWeekday[Hotel.Customer_Type.REWARD_TYPE.ordinal()] = 80;
        int lakeWoodWeekendDay[] = new int[2];
        lakeWoodWeekendDay[Hotel.Customer_Type.REGULAR_TYPE.ordinal()] = 90;
        lakeWoodWeekendDay[Hotel.Customer_Type.REWARD_TYPE.ordinal()] = 80;

        int bridgeWoodWeekday[] = new int[2];
        bridgeWoodWeekday[Hotel.Customer_Type.REGULAR_TYPE.ordinal()] = 150;
        bridgeWoodWeekday[Hotel.Customer_Type.REWARD_TYPE.ordinal()] = 110;
        int bridgeWoodWeekendDay[] = new int[2];
        bridgeWoodWeekendDay[Hotel.Customer_Type.REGULAR_TYPE.ordinal()] = 50;
        bridgeWoodWeekendDay[Hotel.Customer_Type.REWARD_TYPE.ordinal()] = 50;

        int ridgeWoodWeekday[] = new int[2];
        ridgeWoodWeekday[Hotel.Customer_Type.REGULAR_TYPE.ordinal()] = 220;
        ridgeWoodWeekday[Hotel.Customer_Type.REWARD_TYPE.ordinal()] = 100;
        int ridgeWoodWeekendDay[] = new int[2];
        ridgeWoodWeekendDay[Hotel.Customer_Type.REGULAR_TYPE.ordinal()] = 150;
        ridgeWoodWeekendDay[Hotel.Customer_Type.REWARD_TYPE.ordinal()] = 40;





        Hotel lakewood = new Hotel("Lakewood", "Miami", lakeWoodWeekday, lakeWoodWeekendDay, 3);
        Hotel bridewood = new Hotel("Bridgewood", "Miami", bridgeWoodWeekday, bridgeWoodWeekendDay, 4);
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
    public void givenHotelsInTheSystem_ShouldReturnTheCheapestHotelList_WithinGivenDates()
            throws InvalidDateExceptions, HotelException{
        String date1 = "10Sep2020";
        String date2 = "11Sep2020";
        Map<Hotel, Integer> map = hotelReservationSystem.getCheapestHotels(Hotel.Customer_Type.REGULAR_TYPE, date1, date2);
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
        String hotelListRates = hotelReservationSystem.printHotelsDetails();
        String expected = "Lakewood, Miami: Weekday and Weekend regular rate: 110, 90 Weekday and Weekend reward rates: 80, 80 Rating : 3\n" +
                "Bridgewood, Miami: Weekday and Weekend regular rate: 150, 50 Weekday and Weekend reward rates: 110, 50 Rating : 4\n" +
                "Ridgewood, Miami: Weekday and Weekend regular rate: 220, 150 Weekday and Weekend reward rates: 40, 0 Rating : 5\n";
        System.out.println(hotelListRates);
        Assert.assertEquals(expected, hotelListRates);
    }

    @Test
    public void givenHotelsInTheSystem_ShouldReturnTheCheapestHotelsInTheSystem_WithinGivenRangeOfDate()
            throws InvalidDateExceptions, HotelException{
        String date1 = "11Sep2020";
        String date2 = "12Sep2020";
        Map<Hotel, Integer> map = hotelReservationSystem.getCheapestHotels(Hotel.Customer_Type.REGULAR_TYPE, date1, date2);
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

    @Test
    public void givenHotelsInTheSystem_ShouldReturnTheBestCheapestHotelsInTheSystem_WithinGivenRangeOfDate()
            throws InvalidDateExceptions, HotelException{
        String date1 = "11Sep2020";
        String date2 = "12Sep2020";
        Map<Hotel, Integer> map = hotelReservationSystem.getBestRatedCheapestHotels(Hotel.Customer_Type.REGULAR_TYPE, date1, date2);
        String expected = "Bridgewood, Rating: 4 and Total Rates: 200";
        String result="";
        for( Map.Entry<Hotel, Integer> entry : map.entrySet()){
            result = entry.getKey().getName() + ", Rating: " + entry.getKey().rating()
                    + " and Total Rates: " + entry.getValue();
        }
        Assert.assertEquals(expected, result);
    }

    @Test
    public void givenHotelsInTheSystem_ShouldReturnTheBestRatedHotelsInTheSystem_WithinGivenRangeOfDate_TestCase7()
            throws InvalidDateExceptions, HotelException{
        String date1 = "11Sep2020";
        String date2 = "12Sep2020";
        Map<Hotel, Integer> map = hotelReservationSystem.getBestRatedHotels(Hotel.Customer_Type.REGULAR_TYPE, date1, date2);
        String expected = "Ridgewood, Rating: 5 and Total Rates: 370";
        String result="";
        for( Map.Entry<Hotel, Integer> entry : map.entrySet()){
            result = entry.getKey().getName() + ", Rating: " + entry.getKey().rating()
                    + " and Total Rates: " + entry.getValue();
        }
        Assert.assertEquals(expected, result);
    }

    @Test
    public void givenHotelsInTheSystem_ShouldReturnTheBestCheapRatedHotels_ForRewardCustomerType_WithinGivenRangeOfDate_TestCase8()
    throws InvalidDateExceptions, HotelException{
        String date1 = "11Sep2020";
        String date2 = "12Sep2020";
        String cutomer_type = "Reward_type";
        Map<Hotel, Integer> map = hotelReservationSystem.getBestRatedCheapestHotels(Hotel.Customer_Type.valueOf(cutomer_type.toUpperCase()),
                date1, date2);
        String expected = "Ridgewood, Rating: 5 and Total Rates: 140";
        String result="";
        for( Map.Entry<Hotel, Integer> entry : map.entrySet()){
            result = entry.getKey().getName() + ", Rating: " + entry.getKey().rating()
                    + " and Total Rates: " + entry.getValue();
        }
        Assert.assertEquals(expected, result);
    }
}
