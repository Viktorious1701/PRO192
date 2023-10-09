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
public class Employee implements Serializable {
    private String empID;
    private String name;
    private String role;
    private Flight currentFlight;
    private String location;
    public Employee() {
    }

    public Employee(String empID, String name, String role, Flight currentFlight, String location) {
        this.empID = empID;
        this.name = name;
        this.role = role;
        this.currentFlight = currentFlight;
        this.location = location;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Flight getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(Flight currentFlight) {
        this.currentFlight = currentFlight;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        if(currentFlight == null)
            return String.format("|%-10s|%-20s|%-15s|     NO AVAILABLE FLIGHTS!| " , empID,name,role);
        return //"Employee{" + "empID=" + empID + ", name=" + name + ", role=" + role + ", currentFlight=" + currentFlight + ", location=" + location + '}';
                String.format("|%-10s|%-20s|%-15s" + currentFlight+ "%-15s|", empID,name,role,location);
    }
    
}
