/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author NTL
 */
public class DBConnect {

    private static String dbURL="jdbc:mysql://localhost:3306/danhgiakienthuc";
    private static String dbUser="root";
    private static String dbPassword="admin";
    
    public static Connection getConnecttion() {
        Connection cons = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cons = DriverManager.getConnection(dbURL,dbUser,dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }
 
    public static void main(String[] args) {
    }    
}
