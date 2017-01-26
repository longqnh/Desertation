/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NTL
 */
public class DBConnect {

    public static Connection getConnecttion() {
        Connection cons = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cons = DriverManager.getConnection("jdbc:mysql://localhost:3306/danhgiakienthuc", "root", "admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }
 
    public static void main(String[] args) {
    }    
}
