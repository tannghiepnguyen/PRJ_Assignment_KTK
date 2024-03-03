/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.product;

import java.io.Serializable;

/**
 *
 * @author tanng
 */
public class ProductDTO implements Serializable{
    private String sku;
    private String name;
    private String description;
    private double unitPrice;
    private int quantity;
    private boolean status;

    public ProductDTO(String sku, String name, String description, double unitPrice, int quantity, boolean status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductDTO)){
            return false;
        }
        ProductDTO dto = (ProductDTO)obj;
        return this.sku.equals(dto.getSku());
    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }
    
    
    public ProductDTO() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
