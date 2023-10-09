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
public class Passenger implements Serializable {
    private String name;
    private String contactNums;
    
    public Passenger() {
    }

    public Passenger(String name, String contactNums) {
        this.name = name;
        this.contactNums = contactNums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNums() {
        return contactNums;
    }

    public void setContactNums(String contactNums) {
        this.contactNums = contactNums;
    }

    @Override
    public String toString() {
        return //"Passenger{" + "name=" + name + ", contactNums=" + contactNums + '}';
            String.format("|%-15s|%-15s|", name,contactNums);
    }
    
    
}
