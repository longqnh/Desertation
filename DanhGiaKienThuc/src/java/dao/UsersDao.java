/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

/**
 *
 * @author NTL
 */
public class UsersDao {
        
    public boolean checkUsername (String username) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT * FROM table_user WHERE username = '" + username + "';";
        PreparedStatement ps;
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                connection.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean checkEmail(String email) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT * FROM table_user WHERE email = '" + email + "';";
        PreparedStatement ps;
        try {
            ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                connection.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean InsertUser (Users user) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "INSERT INTO table_user VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Users login(String username, String password) {
	Connection con = DBConnect.getConnecttion();
	String sql = "select * from table_user where username='" + username + "' and password='" + password + "'";
	PreparedStatement ps;
	try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users u = new Users();
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                con.close();
                return u;
            }
	} catch (SQLException e) {
            e.printStackTrace();
	}
	return null;
    }
    
    public Users updateUser(Users user) {
	Connection con = DBConnect.getConnecttion();
	String sql = "select * from table_user where username='" + user.getUsername() + "';";
	PreparedStatement ps;
	try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users u = new Users();
                
                con.close();
                return u;
            }
	} catch (SQLException e) {
            e.printStackTrace();
	}
	return null;
    }
}
