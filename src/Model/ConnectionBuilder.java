package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;

/**
 *
 * @author USER
 */
public class ConnectionBuilder {
    private static Connection con;
    public static Connection getConnection(){
        String url = "jdbc:mysql://iambighead.com:3306/OEB";
        String username = "OrderEatBill";
        String password = "oebproject2017";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        }catch(Exception ex){
            System.out.println(ex);
        }
        return con;
    }
}
