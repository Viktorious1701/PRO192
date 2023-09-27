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
public class LongProduct extends Product implements Serializable{
    
    private String Type;

    public LongProduct(String Type) {
        this.Type = Type;
    }

    public LongProduct(String Type, String productCode, String productName, Date manufacturingDate, Date expirationDate, int quantity, int price) {
        super(productCode, productName, manufacturingDate, expirationDate, quantity, price);
        this.Type = Type;
    }

    public LongProduct(String Type, String productCode, String productName, Date manufacturingDate, Date expirationDate, int quantity, int price, boolean importState, boolean exportState) {
        super(productCode, productName, manufacturingDate, expirationDate, quantity, price, importState, exportState);
        this.Type = Type;
    }

    public LongProduct() {
        super();
        Type = "long";
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return super.toString() + " "+Type+"     |";
    }
    
}
