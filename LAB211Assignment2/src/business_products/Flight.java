/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_products;

import action_service.Menu;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Flight implements Serializable{
    private String flightCode;
    private String depCity;
    private String desCity;
    private Date depTime;
    private Date arrivalTime;
    private Seat[] totalSeats;
    private List<String> crewID;
    
    public Flight() {
        crewID = new ArrayList<>();
    }
    /**
     * int totalSeats to automatically generated seat number, and if it is available
     * @param flightCode
     * @param depCity
     * @param desCity
     * @param depTime
     * @param arrivalTime
     * @param totalSeats 
     */
    public Flight(String flightCode, String depCity, String desCity, Date depTime, Date arrivalTime, int totalSeats) {
        this.flightCode = flightCode;
        this.depCity = depCity;
        this.desCity = desCity;
        this.depTime = depTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = new Seat[totalSeats];
        initializeSeats(totalSeats);
        crewID = new ArrayList<>();
    
    }

    public Flight(Flight get) {
        this.flightCode = get.flightCode;
        this.depCity = get.depCity;
        this.desCity = get.desCity;
        this.depTime = get.depTime;
        this.arrivalTime = get.arrivalTime;
        this.totalSeats = get.totalSeats;
        this.crewID = get.getCrewID();
    }

    public List<String> getCrewID() {
        return crewID;
    }

    public void setCrewID(List<String> crewID) {
        this.crewID = crewID;
    }
    
    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getDepCity() {
        return depCity;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
    }

    public String getDesCity() {
        return desCity;
    }

    public void setDesCity(String desCity) {
        this.desCity = desCity;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Seat[] getTotalSeats() {
        return totalSeats;
    }
    public List getAvailableSeats(){
        List<Seat> availableList = new ArrayList<>();
        for (int i = 0; i < totalSeats.length; i++)
        {
            Seat currentSeat = totalSeats[i];
            if(currentSeat.isBooked() == false){
                availableList.add(currentSeat);
            }
        }
        return availableList;
    }

    public void setTotalSeats(Seat[] totalSeats) {
        this.totalSeats = totalSeats;
    }
    private void initializeSeats(int size){
        for (int i = 0; i < size; i++)
        {
         totalSeats[i] = new Seat(i+1);
        }
    }
    public void showFlightCrew(){
        Menu.showFlightCrew(crewID,flightCode);
    }
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return //"Flight{" + "flightCode=" + flightCode + ", depCity=" + depCity + ", desCity=" + desCity + ", depTime=" + depTime + ", arrivalTime=" + arrivalTime + ", totalSeats=" + totalSeats.length + '}';
        String.format("|%-10s|%-15s|%-16s|%-20s|%-20s|%12d|",flightCode,depCity,desCity,sdf.format(depTime),sdf.format(arrivalTime),totalSeats.length);
    }
    
}
