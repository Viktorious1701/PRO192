/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;


import business_products.Product;
import java.util.List;

/**
 *
 * @author Windows
 */
public interface IWareHouse {
    public void createImportReceipt(List<Product> products);
    public void createExportReceipt(List<Product> products);
}
