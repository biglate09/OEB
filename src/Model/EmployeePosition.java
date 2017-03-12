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
public class EmployeePosition {
    private int positionNo;
    private String positionName;
    private int branchNo;

    public int getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }
    
    public String addEmployeePosition(String positionName, int branchNo){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO EmployeePosition(positionName,branchNo) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, positionName);
            ps.setInt(2, branchNo);
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Insert Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }
    
    public  String updateEmployeePosition(int positionNo, String positionName){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE EmployeePosition SET empName = ? WHERE positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, positionName);
            ps.setInt(2, positionNo);
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Update Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }
    
    public String deleteEmployeePosition(int positionNo){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM EmployeePosition WHERE positionNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, positionNo);
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Delete Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }

    public List<EmployeePosition> getEmpPositionsByBranchNo(int branchNo){
        List<EmployeePosition> empPositions = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM EmployeePosition WHERE branchNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            empPositions = new ArrayList<EmployeePosition>();
            while(rs.next()){
                EmployeePosition e = new EmployeePosition();
                e.setBranchNo(rs.getInt("positionNo"));
                empPositions.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return empPositions;
    }
}
