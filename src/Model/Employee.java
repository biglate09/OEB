/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.*;

/**
 *
 * @author USER
 */
public class Employee {
    private int empNo;
    private String empName;
    private int empTypeNo;
    private String empTypeName;
    private int positionNo;
    private String positionName;
    private double specPay;
    private int branchNo;
    private String idCardNo;
    private String gender;
    private String telNo;
    
    
    public double getSpecPay() {
        return specPay;
    }

    public void setSpecPay(double specPay) {
        this.specPay = specPay;
    }
    
    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpTypeNo() {
        return empTypeNo;
    }

    public void setEmpTypeNo(int empTypeNo) {
        this.empTypeNo = empTypeNo;
    }

    public String getEmpTypeName() {
        return empTypeName;
    }

    public void setEmpTypeName(String empTypeName) {
        this.empTypeName = empTypeName;
    }

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
    
    public String addEmployee(Employee e){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO Employee(empName,idCardNo,gender,telNo,empTypeNo,positionNo,branchNo) VALUES(?,?,?,?,?,?,?)";
            if(e.getSpecPay() != 0){
                // ถ้าให้เงินตามพิทสวาทค่อยตั้ง DB จะได้เป็น null
                sql = "INSERT INTO Employee(empName,idCardNo,gender,telNo,empTypeNo,positionNo,branchNo,specPay) VALUES(?,?,?,?,?,?,?,?)";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getEmpName());
            ps.setString(2, e.getIdCardNo());
            ps.setString(3, e.getGender());
            ps.setString(4, e.getTelNo());
            ps.setInt(5, e.getEmpTypeNo());
            ps.setInt(6, e.getPositionNo());
            ps.setInt(7, e.getBranchNo());
            if(e.getSpecPay() != 0){
                // ถ้าให้เงินตามพิทสวาทค่อยตั้ง DB จะได้เป็น null
                ps.setDouble(8, e.getSpecPay());
            }
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Insert Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }
    
    public  String updateEmployee(int empNo, Employee e){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE Employee SET empName = ?,idCardNo = ?,gender = ?,telNo = ?,empTypeNo = ?,positionNo = ?,branchNo = ?,specPay = ?) WHERE empNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getEmpName());
            ps.setString(2, e.getIdCardNo());
            ps.setString(3, e.getGender());
            ps.setString(4, e.getTelNo());
            ps.setInt(5, e.getEmpTypeNo());
            ps.setInt(6, e.getPositionNo());
            ps.setInt(7, e.getBranchNo());
            if(e.getSpecPay() == 0){
                ps.setNull(8, Types.DOUBLE);
            }else{
                ps.setDouble(8, e.getSpecPay());
            }
            ps.setInt(9, empNo);
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Update Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }
    
    public String deleteEmployee(int empNo){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "DELETE FROM Employee WHERE empNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empNo);
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Delete Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }

    public List<Employee> getEmps(int branchNo){
        List<Employee> emps = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e"
                    + " JOIN EmploymentType et ON e.branchNo = et.branchNo "
                    + " JOIN EmployeePosition ep ON e.employeePosition = ep.employeePosition "
                    + " WHERE e.branchNo = ? ORDER BY empName ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchNo);
            ResultSet rs = ps.executeQuery();
            emps = new ArrayList<Employee>();
            while(rs.next()){
                Employee e = new Employee();
                orm(rs,e);
                emps.add(e);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return emps;
    }
    
    public Employee getEmp(int empNo){
        Employee emp = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "SELECT * FROM Employee e "
                    + " JOIN EmploymentType et ON e.branchNo = et.branchNo "
                    + " JOIN EmployeePosition ep ON e.employeePosition = ep.employeePosition "
                    + " WHERE e.empNo = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                emp = new Employee();
                orm(rs,emp);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return emp;
    }
    
    private static void orm(ResultSet rs,Employee e) throws SQLException{
        e.setBranchNo(rs.getInt("branchNo"));
        e.setEmpName(rs.getString("empNo"));
        e.setEmpNo(rs.getInt("empNo"));
        e.setEmpTypeName(rs.getString("empTypeName"));
        e.setEmpTypeNo(rs.getInt("empTypeNo"));
        e.setPositionName(rs.getString("positionName"));
        e.setPositionNo(rs.getInt("positionNo"));
        e.setIdCardNo(rs.getString("idCardNo"));
        e.setGender(rs.getString("gender"));
        e.setTelNo(rs.getString("telNo"));
        e.setSpecPay(rs.getDouble("specPay"));
    }
}