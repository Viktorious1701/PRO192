package data_products;


import business_products.Employee;
import business_products.Flight;
import business_products.Reservation;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ProductData implements Serializable {

    private Map<String, Flight> flightManagement;
    private List<Employee> crewList;
    private List<Reservation> reservations;

    public ProductData(Map<String, Flight> flightManagement, List<Employee> crewList, List<Reservation> reservations) {
        this.flightManagement = flightManagement;
        this.crewList = crewList;
        this.reservations = reservations;
    }

    public Map<String, Flight> getFlightManagement() {
        return flightManagement;
    }

    public List<Employee> getCrewList() {
        return crewList;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
