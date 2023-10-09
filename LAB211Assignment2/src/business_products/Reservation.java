/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Reservation implements Serializable {
    private static int uniqueCode = 0;
    private String ReservationID;
    private Passenger bookedPassengers;
    private String FlightID;
    private String preserveSeatName = "N/A";
    public Reservation() {
    }

    public Reservation(Passenger bookedPassengers) {
        this.bookedPassengers = bookedPassengers;
    }

    public Reservation(String ReservationID,Passenger bookedPassengers) {
        this.ReservationID = ReservationID;
        this.bookedPassengers = bookedPassengers;
    }

    public static int getUniqueCode() {
        return uniqueCode;
    }

    public static void setUniqueCode(int uniqueCode) {
        Reservation.uniqueCode = uniqueCode;
    }

    public String getPreserveSeatName() {
        return preserveSeatName;
    }

    public void setPreserveSeatName(String preserveSeatName) {
        this.preserveSeatName = preserveSeatName;
    }
    
    public Passenger getBookedPassengers() {
        return bookedPassengers;
    }

    public void setBookedPassengers(Passenger bookedPassengers) {
        this.bookedPassengers = bookedPassengers;
    }

    public String getReservationID() {
        return ReservationID;
    }

    public void setReservationID(int size) {
         String result = String.format( "R%07d",size + 1); // take import code as 7 digit  0000001,..
       
        System.out.println("Initialize code as: " + result);
        this.ReservationID = result;
        
    }

    public String getFlightID() {
        return FlightID;
    }

    public void setFlightID(String FlightID) {
        this.FlightID = FlightID;
    }

    @Override
    public String toString() {
        return //"Reservation{" + "ReservationID=" + ReservationID + ", bookedPassengers=" + bookedPassengers + ", FlightID=" + FlightID + ", preserveSeatName=" + preserveSeatName + '}';
                String.format("|%-15s|%-15s"+bookedPassengers+"%-15s|",ReservationID,FlightID,preserveSeatName);
    }
    
    
 
   

}
