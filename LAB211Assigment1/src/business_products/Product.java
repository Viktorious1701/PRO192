/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_products;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Windows
 */
public abstract class Product implements Serializable{
    private String productCode;
    private String productName;
    private Date manufacturingDate;
    private Date expirationDate;
    private int quantity;
    private int price;
    private boolean importState;
    private boolean exportState;
    
    /**
     * default constructor
     * manufacturingDate is 1/1/1
     * expirationDate is 1/1/1
     */
    public Product() {
        this.productCode = "";
        this.productName = "";
        this.manufacturingDate = new Date(1,1,1);
        this.expirationDate = new Date(1,1,1);
    } 
    /**
     * create a given product by user
     * @param productCode
     * @param productName
     * @param manufacturingDate
     * @param expirationDate
     * @param quantity
     * @param price 
     * 
     */
    public Product(String productCode, String productName, Date manufacturingDate, Date expirationDate, int quantity, int price) {    
        this.productCode = productCode;
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.price = price;
        this.importState = false;
        this.exportState = false;
    }
    /**
     * create a full detailed product
     * @param productCode
     * @param productName
     * @param manufacturingDate
     * @param expirationDate
     * @param quantity
     * @param price
     * @param importState
     * @param exportState 
     */
    public Product(String productCode, String productName, Date manufacturingDate, Date expirationDate, int quantity, int price, boolean importState, boolean exportState) {
        this.productCode = productCode;
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.price = price;
        this.importState = importState;
        this.exportState = exportState;
    }
    
    /**
     * get the current number of products available
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * set the number of product
     * @param quantity 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * get price of product
     * @return price
     */
    public int getPrice() {
        return price;
    }
    /**
     * set price
     * @param price 
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * get a product code
     * @return productCode
     */
    public String getProductCode() {
        return productCode;
    }
    /**
     * set a product code
     * @param productCode 
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    /**
     * get a product name
     * @return productName
     */
    public String getProductName() {
        return productName;
    }
    /**
     * set a product name
     * @param productName 
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * get a product's manufacturing date
     * @return manufacturingDate
     */
    public Date getManufacturingDate() {
        return manufacturingDate;
    }
    /**
     * set a manufacturing date for a product
     * @param manufacturingDate 
     */
    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }
    /**
     * get expiration date
     * @return expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }
    /**
     * set new expiration date
     * @param expirationDate 
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    /**
     * return the state true if product is imported
     * @return 
     */
    public boolean isImportState() {
        return importState;
    }
    /**
     * set the state of import
     * @param importState 
     */
    public void setImportState(boolean importState) {
        this.importState = importState;
    }
    /**
     * return the state true if product is exported
     * @return 
     */
    public boolean isExportState() {
        return exportState;
    }
    /**
     * set the state of export
     * @param exportState 
     */
    public void setExportState(boolean exportState) {
        this.exportState = exportState;
    }
    
    
    
    @Override
    public String toString() {
        return String.format("|%-7s|%-25s|%-25s|%-25s|%10d|%10d|%12s|%12s|", this.productCode,this.productName,this.manufacturingDate, this.expirationDate,this.price,this.quantity,
                this.isImportState()? "Import" : "Not Import",
                this.isExportState()? "Export" : "Not Export");
    }
    
}
