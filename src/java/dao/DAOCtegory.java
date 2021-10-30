/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Admin;
import model.Bill;
import model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DAOCtegory {
     Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private static final String INSERT_CATEG_SQL = "INSERT INTO Category" + "  (cateName,status) VALUES "
            + " (?,?);";
    private static final String SELECT_CATEG_BY_ID = "select * from Category where cateID =?";
    private static final String SELECT_ALL_BILL = "select * from Category ORDER BY cateID ASC";
    private static final String DELETE_CATEG_SQL = "delete from Category where cateID = ?;";
    private static final String UPDATE_CATEG_SQL = "update Category set cateName = ?,status= ? Where cateID=?;";

    public void insertCate(Category ca) {

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(INSERT_CATEG_SQL);
            ps.setString(1, ca.getCateName());
           
            ps.setInt(2, ca.getStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public Category getCategoryById(String id) {
         
        
       ArrayList<Category> arr=new ArrayList<Category>();
       
        Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_CATEG_BY_ID);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                
                String cateName = rs.getString(2); // "pname"
                int idc = rs.getInt(1);
                int status = rs.getInt(3);
                
                Category ca=new Category(idc,cateName,status);
               return ca;

            }

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteCate(int caID) throws SQLException {

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(DELETE_CATEG_SQL);
            ps.setInt(1, caID);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateCateg(Category cate) throws SQLException {
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(UPDATE_CATEG_SQL);

            ps.setString(1, cate.getCateName());
            
            ps.setInt(2, cate.getStatus());
            ps.setInt(3, cate.getCateID());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Category> getAll() {
         ArrayList<Category> arr=new ArrayList<Category>();
        String sql = "select * from Category";

        Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                String cateName = rs.getString(2); // "pname"
                int id = rs.getInt(1);
                int status = rs.getInt(3);
                
                Category ca=new Category(id,cateName,status);
               arr.add(ca);
               

            }
            return arr;

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public ArrayList<Category> getAllByArray() {
         ArrayList<Category> arr=new ArrayList<Category>();
        String sql = "select * from Category";
//        ResultSet rs = getData(sql);
        Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                String cateName = rs.getString(2); // "pname"
                int id = rs.getInt(1);
                int status = rs.getInt(3);
                
                Category ca=new Category(id,cateName,status);
               arr.add(ca);

            }

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

//    private Connection getConnection() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public static void main(String[] args) {
        DAOCtegory dao = new DAOCtegory();
//        dao.insertCate(new Category("hoang",1));
//         try {
////             dao.deleteCate(2);
//             dao.updateCateg(new Category(1,"minh",2));
//         } catch (SQLException ex) {
//             Logger.getLogger(DAOCtegory.class.getName()).log(Level.SEVERE, null, ex);
//         }
//            for(Category ca: dao.getAll()){
//             System.out.println(ca.getCateName());
//         }


    }
    
}
