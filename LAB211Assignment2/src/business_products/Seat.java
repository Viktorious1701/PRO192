/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_products;

import java.io.Serializable;

/**
 *
 * @author Windows
 */
public class Seat implements Serializable{

    private String seatName;
    private boolean booked;
    private boolean occupied;

    public Seat(int size) {
        this.seatName = generateSeatName(size);
        this.booked = false;
        this.occupied = false;
    }

    public Seat(String seatName, boolean available, boolean occupied, int size) {
        this.seatName = generateSeatName(size);
        this.booked = available;
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return String.format(" Seat: %s ", seatName);
    }

    private String generateSeatName(int seatNumber) {
        return Integer.toString(seatNumber);
    }

}
