/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bill;
import model.BillDetail;

/**
 *
 * @author Admin
 */
public class DAOBillDetail {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private static final String INSERT_BILLDETAIL_SQL = "INSERT INTO BillDetail" + "  (pid,oID,dateCreate,quantity,price,total) VALUES "
            + " (?,?, ?, ?, ?, ?);";
    private static final String SELECT_BILLDETAIL_BY_OID = "select * from BillDetail where oID =?;";
    private static final String SELECT_ALL_BILLDETAIL = "select * from BillDetail";
    private static final String DELETE_BILL_BILLDETAIL_BY_pID = "delete from BillDetail where pid = ?;";
    private static final String UPDATE_BILL_BILLDETAIL= "update BillDetail set pid = ?,quantity= ?,dateCreate= ? ,price= ?,total= ? Where oID=?;";
    
    public void insertBillDetail(BillDetail bi) {

        try {
           
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(INSERT_BILLDETAIL_SQL);
            ps.setInt(1, bi.getPid());
            ps.setString(2, bi.getoID());
            ps.setString(3, bi.getDateCreate());
            
            ps.setInt(4, bi.getQuantity());
            ps.setDouble(5, bi.getMoney());
            ps.setDouble(6, bi.getTotal());
           

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<BillDetail> getAllBillDetailByOId(int oid){
        ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_BILLDETAIL_BY_OID);
            ps.setInt(1, oid);
            rs = ps.executeQuery();
            while (rs.next()) {

                
                int pID = Integer.parseInt(rs.getString(1));
                String oID = rs.getString(2);
                String dateOfCreate = rs.getString(3);
                int quantity = Integer.parseInt(rs.getString(4));
                double price = Double.parseDouble(rs.getString(5));
                double total = Double.parseDouble(rs.getString(6));
               
                

                BillDetail billDetail = new BillDetail(pID,oID,dateOfCreate,quantity,price,total);
                arr.add(billDetail);

            }
            return arr;

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean deleteBillDetail(int oid) throws SQLException {

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(DELETE_BILL_BILLDETAIL_BY_pID);
            ps.setInt(1, oid);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    
}
