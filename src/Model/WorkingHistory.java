/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author USER
 */
public class WorkingHistory {
    private int workNo;
    private int fromTime;
    private int toTime;
    private Date fromDate;
    private Date toDate;
    private String empTypeName;
    private String positionName;
    private double workingPay;
    private int empNo;
    private int branchNo;
    private double hours;

    public int getWorkNo() {
        return workNo;
    }

    public void setWorkNo(int workNo) {
        this.workNo = workNo;
    }

    public int getFromTime() {
        return fromTime;
    }

    public void setFromTime(int fromTime) {
        this.fromTime = fromTime;
    }

    public int getToTime() {
        return toTime;
    }

    public void setToTime(int toTime) {
        this.toTime = toTime;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getEmpTypeName() {
        return empTypeName;
    }

    public void setEmpTypeName(String empTypeName) {
        this.empTypeName = empTypeName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public double getWorkingPay() {
        return workingPay;
    }

    public void setWorkingPay(double workingPay) {
        this.workingPay = workingPay;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }
    
    public String clockIn(String empTypeName,String positionName,int empNo,int branchNo){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "INSERT INTO WorkingHistory(fromTime,fromDate,empTypeName,positionName,empNo,branchNo) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            int fromTime = getTimeInt(new Date(System.currentTimeMillis()));
            Date fromDate = new Date(System.currentTimeMillis());
            ps.setInt(1, fromTime);
            ps.setDate(2, fromDate);
            ps.setString(3, empTypeName);
            ps.setString(4, positionName);
            ps.setInt(5, empNo);
            ps.setInt(6, branchNo);
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Clock In Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }
    
    private static int getTimeInt(Date time){
        String sdf = new SimpleDateFormat("HH:mm").format(time);
        int intTime = (Integer.parseInt(sdf.substring(0,2))*60) + Integer.parseInt(sdf.substring(3,5));
        return intTime;
    }
    
    public String clockOut(int workNo){
        String msg = null;
        try{
            Connection con = ConnectionBuilder.getConnection();
            String sql = "UPDATE WorkingHistory SET toTime = ?, toDate = ?, workingPay = ? WHERE workNo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            int toTime = getTimeInt(new Date(System.currentTimeMillis()));
            Date toDate = new Date(System.currentTimeMillis());
            double workingPay = calcWorkingPay(toTime,toDate,workNo,con);
            ps.setInt(1, toTime);
            ps.setDate(2, toDate);
            ps.setDouble(2, workingPay);
            ps.setInt(3, workNo);
            int status = ps.executeUpdate();
            if(status > 0){
                msg = "Clock Out Success";
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return msg;
    }
    
    private static double calcWorkingPay(int toTime,Date toDate,int workNo,Connection con){
        double workingPay = 0;
        try{
            String sql = "SELECT * FROM workingHistory w "
                    + "JOIN Employee e ON w.empNo = e.empNo "
                    + "JOIN EmploymentType et ON e.empTypeNo = et.empTypeNo "
                    + "JOIN Constraint c ON e.positionNo = w.positionNo "
                    + "WHERE w.workNo = ? AND w.positionNo = e.positionNo AND w.empTypeNo = e.empTypeNo ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,workNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int fromTime = rs.getInt("fromTime");
                Date fromDate = rs.getDate("fromDate");
                double hours = getDiffHours(fromDate,fromTime,toDate,toTime);
                Double specPay = rs.getDouble("specPay");
                String empTypeName = rs.getString("empTypeName");
                int hoursPerDay = rs.getInt("hoursPerDay");
                if(specPay != null){
                    // จ่ายตามบุคคล
                    if(empTypeName.indexOf("ชั่วโมง") != -1){
                        if(hours > hoursPerDay){
                            workingPay = hoursPerDay * specPay;
                        }else{
                            workingPay = hours * specPay;
                        }
                    }else if(empTypeName.indexOf("วัน") != -1){
                        workingPay = specPay;
                    }
                    // ถ้าเป็นรายเดือนจะไม่มีเก็บรายวันไว้ในตารางการทำงาน
                }else{
                    // จ่ายตามตำแหน่ง
                    double positionPay = rs.getDouble("pay");
                    if(empTypeName.indexOf("ชั่วโมง") != -1){
                        if(hours > hoursPerDay){
                            workingPay = hoursPerDay * positionPay;
                        }else{
                            workingPay = hours * positionPay;
                        }
                    }else if(empTypeName.indexOf("วัน") != -1){
                        workingPay = positionPay;
                    }
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return workingPay;
        // ยังไม่เสร็จ
    }
    
    private static double getDiffHours(Date fromDate,int fromTime,Date toDate,int toTime){
        double hours = 0;
        long diffDay = TimeUnit.DAYS.convert(toDate.getTime() - fromDate.getTime(), TimeUnit.MILLISECONDS);
        if(diffDay == 0){
            int intHours = Math.abs(toTime - fromTime);
            hours = intHours / 60d;
            System.out.println(hours);
        }else{
            if(diffDay == 1){
                hours = ((1440-fromTime) + toTime) / 60d;
            }else{
                hours = ((1440-fromTime) + toTime + ((24*60) * (diffDay - 1))) / 60d;
            }
        }
        return hours;
    }
}
