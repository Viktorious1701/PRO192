/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import business_products.User;

/**
 *
 * @author Windows
 */
interface ICrew {
    public void addCrewMember();
    public void deleteCrewMember();
    public void assignCrewMember(FlightManagement flightManagement);
    public void updateCrewMember();
    public void showAll();
    public void crewAssignMentAndAdminAcess(User user, FlightManagement flightManagement);
}
