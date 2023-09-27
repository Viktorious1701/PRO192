/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_products.Product;
import business_products.Warehouse;
import java.util.List;

/**
 *
 * @author Windows
 */
public interface IReport {
    public void showExpiredProduct(List<Product> products);
    public void showSellingProduct(List<Product> products);
    public void showProductRunningOutOfStock(List<Product> products);
    public void importExportReceipt(List<Product> products, Warehouse warehouse);
}
