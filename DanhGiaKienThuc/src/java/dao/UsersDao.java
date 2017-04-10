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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;
import tools.MD5;

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
    
    public void InsertUser (Users user) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "INSERT INTO table_user VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, MD5.encryption(user.getPassword()));
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getLop());
            ps.setString(6, user.getRole());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql_kyvong="", sql_phuongsai="";
        switch (user.getLop()) {
            case 12:
                sql_kyvong = "INSERT INTO table_kyvong12 VALUES(?,?,?,?,?,?,?)";
                sql_phuongsai = "INSERT INTO table_phuongsai12 VALUES(?,?,?,?,?,?,?)";                
                break;
            case 11:
                sql_kyvong = "INSERT INTO table_kyvong11 VALUES(?,?,?,?,?,?,?)";
                sql_phuongsai = "INSERT INTO table_phuongsai11 VALUES(?,?,?,?,?,?,?)";                                
                break;
            case 10:
                sql_kyvong = "INSERT INTO table_kyvong10 VALUES(?,?,?,?,?,?,?)";
                sql_phuongsai = "INSERT INTO table_phuongsai10 VALUES(?,?,?,?,?,?,?)";                                
                break;
        }

        try {
            PreparedStatement ps = connection.prepareCall(sql_kyvong);
            ps.setString(1, user.getUsername());
            ps.setDouble(2, 1);
            ps.setDouble(3, 1);
            ps.setDouble(4, 1);
            ps.setDouble(5, 1);
            ps.setDouble(6, 1);
            ps.setDouble(7, 1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        try {
            PreparedStatement ps = connection.prepareCall(sql_phuongsai);
            ps.setString(1, user.getUsername());
            ps.setString(2, "0");
            ps.setString(3, "0");
            ps.setString(4, "0");
            ps.setString(5, "0");
            ps.setString(6, "0");
            ps.setString(7, "0");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }             
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
                u.setLop(rs.getInt("lop"));
                u.setRole(rs.getString("role"));
                con.close();
                return u;
            }
	} catch (SQLException e) {
            e.printStackTrace();
	}
	return null;
    }
    
    public void deleteUser(String username) {
        Connection connection = DBConnect.getConnecttion();

        String sql = "DELETE FROM table_user WHERE username='" + username + "'";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.execute(sql);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(Users user) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "UPDATE table_user SET name='" + user.getName() + "', email='" + user.getEmail() +  
                "', lop='" + user.getLop() + "', role='" + user.getRole() + "' WHERE username='" + user.getUsername() + "'";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.execute(sql);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changePassword(Users user) {
        Connection connection = DBConnect.getConnecttion();
        String sql = "UPDATE table_user SET name='" + user.getName() + "', email='" + user.getEmail() +  
                "', password='" + MD5.encryption(user.getPassword()) + "', lop='" + user.getLop() + "', role='" + user.getRole() + "' WHERE username='" + user.getUsername() + "'";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.execute(sql);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Users> getAllUsers() {
        Connection connection = DBConnect.getConnecttion();
        List<Users> list = new ArrayList();

        String sql = "SELECT * FROM table_user";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String username =rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int lop = rs.getInt("lop");
                String role = rs.getString("role");
                
                Users u = new Users(username, password, name, email, role, lop);
                list.add(u);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List<Users> getAllUsers(String search_username, int startPageIndex, int recordsPerPage) {
        Connection connection = DBConnect.getConnecttion();
        List<Users> list = new ArrayList();

        String sql = "SELECT * FROM table_user WHERE username LIKE '%" + search_username + "' LIMIT " + startPageIndex + "," + recordsPerPage;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String username =rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int lop = rs.getInt("lop");
                String role = rs.getString("role");
                
                Users u = new Users(username, password, name, email, role, lop);
                list.add(u);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }    
    
    public int getUserCount() {
        Connection connection = DBConnect.getConnecttion();
        String sql = "SELECT COUNT(*) AS COUNT FROM table_user";
        
	int count=0;
	try 
	{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();	
            while (rs.next()) 
            {
                count=rs.getInt("COUNT");
            }
	} 
	catch (SQLException e) 
	{
            System.err.println(e.getMessage());
	}
	return count;
    }    
}
