/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author NTL
 */
public class DBConnect {
    
    public static Connection getConnecttion() {
        Connection cons = null;
        try {
            Properties properties = new Properties();
            InputStream inputStream = DBConnect.class.getClassLoader().getResourceAsStream("config/DBConfig.properties");
            properties.load(inputStream);
            
            String driver = properties.getProperty("driver");
            String dbURL = properties.getProperty("url");
            String dbUser = properties.getProperty("user");
            String dbPassword = properties.getProperty("password");
            
            Class.forName(driver);
            cons = DriverManager.getConnection(dbURL,dbUser,dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }
 
    public static void main(String[] args) {
        System.out.println(DBConnect.getConnecttion());
    }    
}
