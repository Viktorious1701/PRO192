/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

/**
 *
 * @author Windows
 */
interface IReservationManagement {
    public void makeReservation(FlightManagement flightManagement);
    public void checkin(FlightManagement flightManagement);
    public void showAll();
}
