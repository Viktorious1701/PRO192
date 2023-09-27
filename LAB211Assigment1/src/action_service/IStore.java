/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_products.Warehouse;
import java.util.List;

/**
 *
 * @author Windows
 */
public interface IStore {
    public void addProduct();
    public void updateProductInformation(Warehouse warehouse);
    public void deleteProduct();
    public void ShowAllProducts();
}
