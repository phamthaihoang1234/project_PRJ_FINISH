/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Admin;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author Admin
 */
public class DAOAmin {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private static final String INSERT_ADMIN_SQL = "INSERT INTO admin" + "  (username,password) VALUES "
            + " (?, ?);";
    private static final String SELECT_ADMIN_BY_ID = "select * from admin where cid =? ORDER BY cid ASC";
    private static final String SELECT_ALL_ADMIN = "select * from admin ORDER BY cid ASC";
    private static final String DELETE_ADMIN_SQL = "delete from admin where cid = ?;";
    private static final String UPDATE_ADMIN_SQL = "update admin set username = ?,password= ? where cid = ?;";
    private static final String LOGIN_ADMIN = "select * from admin where username =? and password =?;";
    public void insertAdmin(Admin ad) {

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(INSERT_ADMIN_SQL);
            ps.setString(1, ad.getUsername());
            ps.setString(2, ad.getPassword());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAdmin(int pid) throws SQLException {

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(DELETE_ADMIN_SQL);
            ps.setInt(1, pid);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public Admin loginAdmin(String username , String password){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(LOGIN_ADMIN);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                int adminID = rs.getInt(1);
                String usernam = rs.getString(2);
                String passwor = rs.getString(3);
                 String isAdmin = rs.getString(4);
                Admin ad=new Admin(adminID,usernam,passwor,isAdmin);
               
               return ad;


            }
            

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateAdmin(Admin ad) throws SQLException {
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(UPDATE_ADMIN_SQL);

            ps.setString(1, ad.getUsername());
            ps.setString(2, ad.getPassword());
            ps.setInt(3, ad.getAdminID());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Admin getAdminById(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_ADMIN_BY_ID);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                
                 
                int adminID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String isAdmin = rs.getString(4);
                Admin ad=new Admin(adminID,username,password,isAdmin);
               
               return ad;

            }

        } catch (Exception ex) {
            Logger.getLogger(DAOAmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Admin> getAll() {
        ArrayList<Admin> arr = new ArrayList<Admin>();
        String sql = "select * from admin";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_ALL_ADMIN);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                 
                int adminID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String isAdmin = rs.getString(4);
                Admin ad=new Admin(adminID,username,password,isAdmin);
                
               
               arr.add(ad);
               

            }
            return arr;

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    private Connection getConnection() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public static void main(String[] args) {
        DAOAmin dao = new DAOAmin();

//            dao.deleteAdmin(3);
//        try {
//            dao.updateAdmin(new Admin(6,"linhbeo", "12345678"));
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOAmin.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for(Admin a: dao.getAll()){
//             System.out.println(a.getUsername());
//         }
    }

}
