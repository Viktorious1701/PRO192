/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import action_service.Input;
import action_service.Menu;
import business_products.Flight;
import business_products.Seat;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows
 */
public class FlightManagement extends HashMap<String, Flight> implements IFlightManagement, Serializable {

    public FlightManagement() {
        super();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        try
//        {
//            Flight flight1 = new Flight("F0001", "City1", "City2", sdf.parse("01/01/2004 07:00:00"), sdf.parse("02/01/2004 09:00:00"), 10);
//            Flight flight2 = new Flight("F0002", "City2", "City3", sdf.parse("03/01/2004 20:00:00"), sdf.parse("04/01/2004 23:00:00"), 3);
//            Flight flight3 = new Flight("F0003", "City3", "City4", sdf.parse("05/01/2004 12:00:00"), sdf.parse("06/01/2004 19:00:00"), 20);
//            Flight flight4 = new Flight("F0004", "City4", "City5", sdf.parse("07/01/2004 22:00:00"), sdf.parse("08/01/2004 00:00:00"), 12);
//            this.put(flight1.getFlightCode(), flight1);
//
//            this.put(flight2.getFlightCode(), flight2);
//
//            this.put(flight3.getFlightCode(), flight3);
//
//            this.put(flight4.getFlightCode(), flight4);
//
//        } catch (ParseException ex)
//        {
//            Logger.getLogger(FlightManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public boolean isExist(String FlightCode) {
        if (this.containsKey(FlightCode))
        {
            System.out.println("Code already exist! ");
        }
        return this.containsKey(FlightCode);
    }

    public void removeByKey(String FlightCode) {
        if (this.containsKey(FlightCode))
        {
            this.remove(FlightCode);
        } else
        {
            System.out.println("Cannot find Flight Code provided! ");
        }
    }

    public List<Flight> toList() {
        return new ArrayList<Flight>(this.values());
    }

    @Override
    public void addAFlight() {

        String newCode, newDepCity, newDesCity;
        Date depTime, arrTime;
        // seat number must be in some sort of code right? 
        int seatNumbers;
        do
        {
            newCode = Input.inputValidCode();
        } while (this.isExist(newCode));

        newDepCity = Input.inputValidCity("Enter the departure city name: ");
        newDesCity = Input.inputValidCity("Enter the desinatoin city name: ");
        depTime = Input.inputTime();
        boolean flag = false;
        do
        {
            arrTime = Input.inputTime();
            if (arrTime.after(depTime))
            {
                flag = true;
            } else
            {
                System.out.println("The arrival time must be after the departure time! ");
                //default set
                if (arrTime.equals(depTime))
                {
                    String temp = Input.inputNonBlankStr("The date is on the same day, would you like to set to arrival time to 1 hour after departure time? (y/n):");
                    if (temp.equalsIgnoreCase("y"))
                    {
                        // set 1 hour longer
                        arrTime.setHours(arrTime.getHours() + 1);
                        flag = true;
                    } else
                    {
                        // do nothing
                    }
                }
            }
        } while (!flag);

        seatNumbers = Input.inputInt("Enter the total seats: ", 0);
        // set new flight details, seat will automatically generated
        Flight newFlight = new Flight(newCode, newDepCity, newDesCity, depTime, arrTime, seatNumbers);

        // add new flight to the collection
        this.put(newFlight.getFlightCode(), newFlight);
    }

    @Override
    public void showAllFlights() {
        List<Flight> flightList = this.toList();
        Menu.showAllFLightAvailable(flightList);
        String showChoice = Input.inputNonBlankStr("do you want to show details of every Flight crew? (Y/N): ");
        if (showChoice.equalsIgnoreCase("y"))
        {
            for (Flight i : flightList )
            {
                if(!i.getCrewID().isEmpty()){
                    Menu.showFlightCrew(i.getCrewID(),i.getFlightCode());
                }
            }
        }
    }

    public List<Flight> searchFlight() {
        SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        // set a new array list to filter the hashmap
        List<Flight> matchingFlights = new ArrayList<>();
        // take user choice
        int subChoice = Menu.showMenu(Menu.searchByType, 1);
        switch (subChoice)
        {
            //case 1 search flight by departure date
            case 1:
                String getDate = Input.inputNonBlankStr("Enter the Date of the Flight: ");
                Date searchDate = null;
                try
                {
                    searchDate = sdfDate.parse(getDate);
                } catch (ParseException ex)
                {
                    System.out.println("Must Enter in the format (dd/MM/yyyy)");
                    //Logger.getLogger(FlightManagement.class.getName()).log(Level.SEVERE, null, ex);
                }

                // only get the day month year, ignore all the hours, minute, seconds
                for (Flight i : this.values())
                {
                    Date reservationDate = null;
                    try
                    {
                        reservationDate = sdfDate.parse(sdfDate.format(i.getDepTime())); // Format reservation date as "dd/MM/yyyy"
                    } catch (ParseException ex)
                    {
                        System.out.println("Must Enter in the format (dd/MM/yyyy)");
                        //  Logger.getLogger(FlightManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (reservationDate.equals(searchDate))
                    {
                        matchingFlights.add(i);
                    }
                }
                break;
            // case 2 search flight by Departure city
            case 2:
                String searchDepCity = Input.inputNonBlankStr("Enter the Departure City Name: ");
                for (Flight i : this.values())
                {
                    if (i.getDepCity().equalsIgnoreCase(searchDepCity))
                    {
                        // add flight based on dep city name
                        matchingFlights.add(i);
                    }
                }
                break;
            case 3:
                String searchDesCity = Input.inputNonBlankStr("Enter the Departure City Name: ");
                for (Flight i : this.values())
                {
                    if (i.getDesCity().equalsIgnoreCase(searchDesCity))
                    {
                        // add flight based on des city name
                        matchingFlights.add(i);
                    }
                }
                break;
        }
        return matchingFlights;
    }
}
