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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Branch {
    private int branchNo;
    private String branchName;
    private String branchDesc;
    private int restNo;
    private Restaurant restaurant;
    private int addressNo;
    private Address address;
    private int restOwnerNo;
    private RestaurantOwner restaurantOwner;

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }

    public int getRestNo() {
        return restNo;
    }

    public void setRestNo(int restNo) {
        this.restNo = restNo;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(int addressNo) {
        this.addressNo = addressNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getRestOwnerNo() {
        return restOwnerNo;
    }

    public void setRestOwnerNo(int restOwnerNo) {
        this.restOwnerNo = restOwnerNo;
    }

    public RestaurantOwner getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }
    
    public Branch getBranch(int branchNo){
        Branch b = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Branch WHERE branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                b = new Branch();
                orm(rs,b);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return b;
    }
    
    public List<Branch> getBranches(int restNo){
        List<Branch> branches = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Branch WHERE restNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                branches = new ArrayList<Branch>();
                Branch b = new Branch();
                orm(rs,b);
                branches.add(b);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return branches;
    }
    
    private static void orm(ResultSet rs,Branch b) throws SQLException{
        b.setAddress(Address.getAddress(rs.getInt("branchNo")));
        b.setAddressNo(rs.getInt("addressNo"));
        b.setBranchDesc(rs.getString("branchDesc"));
        b.setBranchName(rs.getString("branchName"));
        b.setBranchNo(rs.getInt("branchNo"));
        b.setRestNo(rs.getInt("restNo"));
        b.setRestOwnerNo(rs.getInt("restOwnerNo"));
        b.setRestaurant(Restaurant.getRestaurant(rs.getInt("restNo")));
        b.setRestaurantOwner(RestaurantOwner.getRestaurantOwner(rs.getInt("restOwnerNo")));
    }
}