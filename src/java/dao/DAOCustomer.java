/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Admin;
import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Admin
 */
public class DAOCustomer {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customer" + "  (cname,cphone,cAddress,username,password,status,isAdmin) VALUES "
            + " (?, ?, ?, ?, ?, ?,?);";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from Customer where cid =?";
    private static final String LOGIN_CUSTOMER = "select * from Customer where username =? and password = ?";
    private static final String SELECT_ALL_CUSTOMER = "select * from Customer ORDER BY cid ASC";
    private static final String DELETE_CUSTOMER_SQL = "delete from Customer where cid = ?;";
    private static final String UPDATE_CUSTOMER_SQL = "update Customer set cname = ?,cphone= ?, cAddress=?,username=?,password=?,status=? , isAdmin=? where cid = ?;";
    private static final String SEARCH_CUSTOMER_BY_NAME = "select * from Customer where [cname] like ?;";
    public void insertCustomer(Customer cus) {

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(INSERT_CUSTOMER_SQL);
            ps.setString(1, cus.getCname());
            ps.setString(2, cus.getCphone());
            ps.setString(3, cus.getcAddress());
            ps.setString(4, cus.getUsername());
            ps.setString(5, cus.getPassword());
            ps.setInt(6, cus.getStatus());
            ps.setString(7, cus.getIsAdmin());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Customer> searchCusByName(String name){
        ArrayList<Customer> arr = new ArrayList<>();
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SEARCH_CUSTOMER_BY_NAME);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
             while (rs.next()) {
                 int id = rs.getInt(1);
                String  cname = rs.getString(2); 
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                 int status = rs.getInt(7);
                 String isAdmin = rs.getString(8);
                Customer cus = new Customer(id,cname, cphone, cAddress, username, password,status,isAdmin);
                arr.add(cus);

            }
          return arr;
            
        }catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Customer loginCustomer(String username , String password){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(LOGIN_CUSTOMER);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String  cname = rs.getString(2); // "pname"
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username1 = rs.getString(5);
                String password1 = rs.getString(6);
                int status = rs.getInt(7);
                String isAdmin = rs.getString(8);
                
                Customer cus = new Customer(Integer.parseInt(id),cname, cphone, cAddress, username, password,status,isAdmin);
                return cus;

            }
            

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteCustomer(int pid) throws SQLException {

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(DELETE_CUSTOMER_SQL);
            ps.setInt(1, pid);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateCustomer(Customer cus) throws SQLException {
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(UPDATE_CUSTOMER_SQL);

            ps.setString(1, cus.getCname());
            ps.setString(2, cus.getCphone());
            ps.setString(3, cus.getcAddress());
            ps.setString(4, cus.getUsername());
            ps.setString(5, cus.getPassword());
            ps.setInt(6, cus.getStatus());
            ps.setString(7, cus.getIsAdmin());
            ps.setInt(8, cus.getCid());
            
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerById(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_CUSTOMER_BY_ID);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                String  cname = rs.getString(2); // "pname"
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                int status = rs.getInt(7);
                String isAdmin = rs.getString(8);
                
                Customer cus = new Customer(Integer.parseInt(id),cname, cphone, cAddress, username, password,status,isAdmin);
                return cus;

            }
            

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Customer> getAll() {
        ArrayList<Customer> arr = new ArrayList<Customer>();
        String sql = "select * from Customer";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                 int id = rs.getInt(1);
                String  cname = rs.getString(2); // "pname"
                String cphone = rs.getString(3);
                String cAddress = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                 int status = rs.getInt(7);
                 String isAdmin = rs.getString(8);
                Customer cus = new Customer(id,cname, cphone, cAddress, username, password,status,isAdmin);
                arr.add(cus);

            }
          return arr;

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {

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
