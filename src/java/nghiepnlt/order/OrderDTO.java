/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.order;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author tanng
 */
public class OrderDTO implements Serializable{
    private String id;
    private Date date;
    private double total;

    public OrderDTO() {
    }

    public OrderDTO(String id, Date date, double total) {
        this.id = id;
        this.date = date;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
