/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nghiepnlt.util.DBHelper;

/**
 *
 * @author tanng
 */
public class ProductDAO implements Serializable{
    private List<ProductDTO> list;
    
    public List<ProductDTO> getProductList(){
        return this.list;
    }
    
    public void getAllProductList() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "select sku, name, description, unitPrice, quantity, status "
                        + "from Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double unitPrice = rs.getDouble("unitPrice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    ProductDTO dto = new ProductDTO(sku, name, description, unitPrice, quantity, status);
                    if (this.list == null){
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        } finally{
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
    }
    
    public ProductDTO getProductById(String id) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "select sku,name,description,unitPrice,quantity,status "
                        + "from Product "
                        + "where sku=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()){
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double unitPrice = rs.getDouble("unitPrice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    ProductDTO productDto = new ProductDTO(sku, name, description, unitPrice, quantity, status);
                    return productDto;
                }
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return null;
    }
}
