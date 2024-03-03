/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import javax.naming.NamingException;
import nghiepnlt.product.ProductDAO;
import nghiepnlt.util.DBHelper;

/**
 *
 * @author tanng
 */
public class OrderDetailDAO implements Serializable{
    public boolean createOrderDetail(String name, double unitPrice, int quantity, double totalEachItem, String orderId) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into [OrderDetail](productId,unitPrice,quantity, total, orderId) "
                        + "values (?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setDouble(2, unitPrice);
                stm.setInt(3, quantity);
                stm.setDouble(4, totalEachItem);
                stm.setString(5, orderId);
                int rowsEffect = stm.executeUpdate();
                if (rowsEffect > 0){
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
