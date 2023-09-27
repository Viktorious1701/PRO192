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
public class DailyProduct  extends Product implements Serializable{
    private String type;

    public DailyProduct(String Type) {
        this.type = Type;
    }

    public DailyProduct(String Type, String productCode, String productName, Date manufacturingDate, Date expirationDate, int quantity, int price, boolean importState, boolean exportState) {
        super(productCode, productName, manufacturingDate, expirationDate, quantity, price, importState, exportState);
        this.type = Type;
    }

    public DailyProduct() {
        super();
        type = "daily";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + " "+type+"    |";
    }
    
}
