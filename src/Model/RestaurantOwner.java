/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class RestaurantOwner extends ActorManagement{
    private int restOwnerNo;
    private String restUserName;
    private String restPassword;

    public int getRestOwnerNo() {
        return restOwnerNo;
    }

    public void setRestOwnerNo(int restOwnerNo) {
        this.restOwnerNo = restOwnerNo;
    }

    public String getRestUserName() {
        return restUserName;
    }

    public void setRestUserName(String restUserName) {
        this.restUserName = restUserName;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }
    
    public static RestaurantOwner getRestaurantOwner(int restOwnerNo){
        RestaurantOwner ro = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Branch WHERE restOwnerNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, restOwnerNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ro = new RestaurantOwner();
                orm(rs,ro);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return ro;
    }
    
    private static void orm(ResultSet rs,RestaurantOwner ro) throws SQLException, SQLException, SQLException, SQLException, SQLException {
        ro.setRestOwnerNo(rs.getInt("restOwnerNo"));
        ro.setRestUserName(rs.getString("restUserName"));
        //password ไม่เซต
    }
    
    public static RestaurantOwner signIn(String username,String password){
        RestaurantOwner ro = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM RestaurantOwner WHERE LOWER(restUserName) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(ActorManagement.decryptPassword(rs.getString("restPassword")).equals(password)){
                    ro = new RestaurantOwner();
                    orm(rs,ro);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }        
        return ro;
    }

    public static RestaurantOwner signInForCookie(String cookieVal){
        RestaurantOwner ro = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM RestaurantOwner WHERE LOWER(restUserName) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cookieVal.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ro = new RestaurantOwner();
                orm(rs,ro);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return ro;
    }
}
