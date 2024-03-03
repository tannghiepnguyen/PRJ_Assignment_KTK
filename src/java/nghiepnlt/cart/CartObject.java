/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import nghiepnlt.product.ProductDAO;
import nghiepnlt.product.ProductDTO;

/**
 *
 * @author tanng
 */
public class CartObject implements Serializable{
    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }
    
    public boolean addItemToCart(String id) throws SQLException, NamingException{
        ProductDAO dao = new ProductDAO();
        boolean result = false;
        //1. check existed id
        if (id == null){
            return result ;
        }
        // id is valid
        if (id.trim().isEmpty()){
            return result ;
        }
        //2. check existed items
        if (this.items == null){
            this.items = new HashMap();
        }// items have not existed
        //3. check existed item -> existed ---> increate quantity, otherwise drop item
        int quantity = 1;
        if (this.items.containsKey(dao.getProductById(id))){
            quantity = this.items.get(dao.getProductById(id)) + 1;
        }
        this.items.put(dao.getProductById(id), quantity);
        result = true;
        return result;
    }
    
    public boolean removeItemFromCart(String id) throws SQLException, NamingException{
        boolean result = false;
        ProductDAO dao = new ProductDAO();
        //1. check existed items
        if (this.items != null){
            //2. check existed item -> existed --> remove
            if (this.items.containsKey(dao.getProductById(id))){
                this.items.remove(dao.getProductById(id));
                if (this.items.isEmpty()){
                    this.items = null;
                }
                result = true;
            }
        }
        return result;
    }
}
