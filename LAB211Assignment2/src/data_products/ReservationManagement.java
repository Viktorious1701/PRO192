/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import action_service.Input;
import action_service.Menu;
import business_products.Flight;
import business_products.Passenger;
import business_products.Reservation;
import business_products.Seat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class ReservationManagement implements IReservationManagement, Serializable {

    private List<Reservation> reservation;

    public ReservationManagement() {
        this.reservation = new ArrayList<>();
    }

    public ReservationManagement(List reservation) {
        this.reservation = reservation;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    @Override
    public void makeReservation(FlightManagement flightManagement) {
        // check if there is at least a flight
        if (!flightManagement.isEmpty())
        {
            flightManagement.showAllFlights();
        } else
        {
            System.out.println("There are currently no flights!");
            return;
        }
        List<Flight> filteredList = null;
        // selected seats to make reservation
        int selectedSeats;
        try
        {
            int count = 1;
            filteredList = flightManagement.searchFlight();
            if (!filteredList.isEmpty())
            {
                // show table of flight
                Menu.showAllFLightAvailable(filteredList);
            } else
            {
                System.out.println("There are no flights available yet!");
                return;
            }
            String choice;
            do
            {
                choice = Input.inputNonBlankStr("Please select the flight you want to choose:");

            } while (Integer.parseInt(choice) <= 0);

            // create a temporary flight
            Flight selectedFlight = filteredList.get(Integer.parseInt(choice) - 1);
            int bookedSeatsIndex = 0;

            // check if there is still available seats
            if (selectedFlight.getTotalSeats().length != bookedSeatsIndex)
            {

                String continueAdd = null;
                do
                {
                    // isBooked returns true if a seat is booked, false if it's not book
                    // if it's not booked, then set the state to true
                    while (selectedFlight.getTotalSeats()[bookedSeatsIndex].isBooked())
                    {
                        bookedSeatsIndex++;
                    }
                    //set the next available seats booked state to true
                    selectedFlight.getTotalSeats()[bookedSeatsIndex].setBooked(true);
                    // Add personal information of customer
                    Reservation newReservation = new Reservation();
                    //set Reservation ID
                    newReservation.setReservationID();
                    //set Flight ID
                    newReservation.setFlightID(selectedFlight.getFlightCode());

                    // passenger information
                    System.out.println("Passenger Information:");

                    Passenger newPassenger = new Passenger();
                    String name = Input.inputNonBlankStr("Please enter the name:");
                    newPassenger.setName(name);
                    String contact = Input.inputNonBlankStr("Enter Contact Number: ");
                    newPassenger.setContactNums(contact);
                    // add passenger to the current reservation

                    newReservation.setBookedPassengers(newPassenger);
                    reservation.add(newReservation);
                    continueAdd = Input.inputNonBlankStr("do you want to continue booking? (y/n): ");

                } while (continueAdd.equalsIgnoreCase("y"));

                // set the flight available seats back
                flightManagement.removeByKey(selectedFlight.getFlightCode());

                flightManagement.put(selectedFlight.getFlightCode(), selectedFlight);
                System.out.println("Added reservation successfully");
            } else
            {
                System.out.println("The flight is already fullly occupied");
            }

        } catch (NumberFormatException e)
        {
            System.out.println("Not a valid choice ");
            System.out.println(e);
        }
    }

    @Override
    public void checkin(FlightManagement flightManagement) {
        int target = 0;
        String reserveID = Input.inputNonBlankStr("Please enter your Reservation ID: ");
        Reservation selectedReservation = null;
        // search through the reservation list
        boolean foundFlag = false;
        if (!this.reservation.isEmpty())
        {
            for (Reservation i : this.reservation)
            {
                if (i.getReservationID().equals(reserveID))
                {
                    // check if the reservation ID already has a seat number
                    if (i.getPreserveSeatName().equalsIgnoreCase("N/A"))
                    {
                        selectedReservation = i;
                        System.out.println("Found Reservation!");

                        foundFlag = true;
                    } // return to main menu if the seat has already checked-in
                    else
                    {
                        System.out.println("the reservation has already checked-in!");
                        return;
                    }
                } else
                {
                    // check index of the selected reservation
                    target++;
                    continue;
                }
                if (!foundFlag)
                {
                    System.out.println("Reservation not found!");
                }
                target = 0;
            }
        }System.out.println("You have no reservations yet!");
        // look for the flight with the same ID
        Flight selectedFlight = null;
        if (flightManagement.containsKey(selectedReservation.getFlightID()))
        {
            selectedFlight = flightManagement.get(selectedReservation.getFlightID());
        }
        boolean flag = false;
        // changing the seats attribute in the selected flight
        for (Seat i : selectedFlight.getTotalSeats())
        {
            if (!i.isOccupied() && !flag)
            {
                // set occupied to true, also get the seat number for the guest
                i.setOccupied(true);
                //menu table here

                System.out.println("Allocating Guest Seat Number as : " + i.getSeatName());
                // get the seat name of the seleted flight
                selectedReservation.setPreserveSeatName(i.getSeatName());
                flag = true;
            }
        }
        flightManagement.remove(selectedReservation.getFlightID());
        flightManagement.put(selectedFlight.getFlightCode(), selectedFlight);
        System.out.println("Added successfully!");

        Menu.showAvailableSeat(selectedFlight.getTotalSeats());
    }

    @Override
    public void showAll() {
        
        Menu.showAllReservations(reservation);
    }

}
