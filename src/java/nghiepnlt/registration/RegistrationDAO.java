/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiepnlt.registration;

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
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.makeConnection();
            if (con != null){
                //2. create SQL string
                String sql = "Select username "
                        + "from Registration "
                        + "where username = ? and password = ?";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()){
                    result = true;
                }// username and password is authenticated
            }// end connection has been available
        }
        finally{
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
        return result;
    }
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    
    public void searchLastname (String searchValue) throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. get connection
            con = DBHelper.makeConnection();
            if (con != null){
                //2. create SQL string
                String sql = "SELECT username,password,lastname,isAdmin "
                        + "From Registration "
                        + "Where lastname like ? ";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+searchValue+"%");
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()){
                    //5.1 get data from result set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    if (this.accounts == null){
                        this.accounts = new ArrayList<RegistrationDTO>();
                    }// end accounts have NOT existed
                    this.accounts.add(dto);
                }// username and password is authenticated, end account has existed
            }// end connection has been available
        }
        finally{
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
    
    public boolean deleteAccount(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.makeConnection();
            if (con != null){
                //2. create SQL string
                String sql = "Delete from Registration "
                        + "Where username = ?";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute query (Insert,Delete,Update - ExecuteUpdate ; Select - ExecuteQuery)
                int effectRows = stm.executeUpdate();
                //5. Process result
                if (effectRows > 0){
                    result = true;
                }
                // username and password is authenticated
            }// end connection has been available
        }
        finally{
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return result;
    }
    
    public boolean updateAccount(String username, String password, String admin) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.makeConnection();
            if (con != null){
                //2. create SQL string
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                //3. Create Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, admin != null);
                stm.setString(3, username);
                //4. Execute query (Insert,Delete,Update - ExecuteUpdate ; Select - ExecuteQuery)
                int effectRows = stm.executeUpdate();
                //5. Process result
                if (effectRows > 0){
                    result = true;
                }
                // username and password is authenticated
            }// end connection has been available
        }
        finally{
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return result;
    }
}
