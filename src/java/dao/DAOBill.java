/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static com.sun.xml.bind.util.CalendarConv.formatter;
import static java.lang.System.out;
import model.Admin;
import model.Bill;
import model.Category;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Admin
 */
public class DAOBill {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private static final String INSERT_BILL_SQL = "INSERT INTO Bill" + "  (dateCreate,cname,cphone,cAddress,total,status,cid,oID,checkbill) VALUES "
            + " (?,?, ?, ?, ?, ?, ?, ?,?);";
    private static final String SELECT_BILL_BY_CID = "select * from Bill where cid =?;";
    private static final String SELECT_BILL_BY_CHECKBILL = "select * from Bill where oID =?;";
    private static final String SELECT_BILLDETAIL_BY_OID = "select * from BillDetail where oID =?;";
    private static final String SELECT_ALL_BILL = "select * from Bill ORDER BY oID ASC";
    private static final String DELETE_BILL_SQL = "delete from Bill where oID = ?;";
    private static final String UPDATE_BILL_SQL = "update Bill set dateCreate = ?,cname= ?,cphone= ?,cAddress= ?,total= ?,status= ? ,checkbill=? Where oID=?;";
    private static final String SEARCH_BILL_BY_NAME = "select * from Bill where cid = ? AND [cname] like ?;";
    private static final String SEARCH_BILL_BY_NAMEC = "select * from Bill where [cname] like ?;";
    private static final String COUNT_TOTAL_BILL = "select count(*) from Bill;";
    private static final String PAGING_BILL = "select * from Bill order by oID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";

    public void insertBill(Bill bi) {

        try {

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(INSERT_BILL_SQL);
            ps.setString(1, bi.getDateCreate());
            ps.setString(2, bi.getCname());
            ps.setString(3, bi.getCphone());
            ps.setString(4, bi.getcAddress());
            ps.setDouble(5, bi.getTotal());
            ps.setInt(6, bi.getStatus());
            ps.setInt(7, bi.getCid());
            ps.setInt(8, bi.getoID());
            ps.setString(9, bi.getCeckbill());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int countBill(){
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(COUNT_TOTAL_BILL);
           rs = ps.executeQuery();
           while(rs.next()){
               return rs.getInt(1);
           }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Bill> pagingBill(int in){
        List<Bill> list = new ArrayList<>();
         try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(PAGING_BILL);
            ps.setInt(1, (in - 1)*6);
            rs = ps.executeQuery();
            while(rs.next()){
                int oID = rs.getInt(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                int total = rs.getInt(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                String checkbill = rs.getString(9);

                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid, checkbill);
                list.add(bill);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 

    public boolean deleteBill(int oid) throws SQLException {

        try {
            
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(DELETE_BILL_SQL);
            ps.setInt(1, oid);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateBill(Bill bi) throws SQLException {
        try {

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(UPDATE_BILL_SQL);

            ps.setString(1, bi.getDateCreate());
            ps.setString(2, bi.getCname());
            ps.setString(3, bi.getCphone());
            ps.setString(4, bi.getcAddress());
            ps.setDouble(5, bi.getTotal());
            ps.setInt(6, bi.getStatus());
            ps.setString(7, bi.getCeckbill());

            ps.setInt(8, bi.getoID());

            boolean a = ps.executeUpdate() > 0;
            out.print("<h1>" + a + "</h1>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bill> searchBillByCname(String name, int Cid, String isAdmin) {
        ArrayList<Bill> arr = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
         System.out.println("Isadmin: "+isAdmin);
        System.out.println("CIdis: "+Cid);
        System.out.println("Key: "+name);
        try {
            conn = new DBContext().getConnection();
            if (isAdmin.equals("0")) {
                System.out.println("vao1");
                ps = conn.prepareStatement(SEARCH_BILL_BY_NAME);
                ps.setInt(1, Cid);
                ps.setString(2, "%" + name + "%");
            } else {
                 System.out.println("Khongvao1");
                ps = conn.prepareStatement(SEARCH_BILL_BY_NAMEC);
                ps.setString(1, "%" + name + "%");
            }

            rs = ps.executeQuery();
            while (rs.next()) {

                int oID = rs.getInt(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                int total = rs.getInt(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                String checkbill = rs.getString(9);

                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid, checkbill);
                arr.add(bill);

            }
            return arr;

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Bill> getAllBillByCid(String id) {
        ArrayList<Bill> arr = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_BILL_BY_CID);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                int oID = rs.getInt(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                int total = rs.getInt(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                String checkbill = rs.getString(9);

                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid, checkbill);
                arr.add(bill);

            }
            return arr;

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Bill getBillDetailById(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_BILL_BY_CHECKBILL);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                int oID = rs.getInt(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                int total = rs.getInt(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                String checkbill = rs.getString(9);

                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid, checkbill);
                return bill;

            }

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Bill> getAll() {
        ArrayList<Bill> arr = new ArrayList<Bill>();
        String sql = "select * from Bill ";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                int oID = rs.getInt(1);
                String dateCreate = rs.getString(2);
                String cname = rs.getString(3);
                String cphone = rs.getString(4);
                String cAddress = rs.getString(5);
                int total = rs.getInt(6);
                int status = rs.getInt(7);
                int cid = rs.getInt(8);
                String checkbill = rs.getString(9);

                Bill bill = new Bill(oID, dateCreate, cname, cphone, cAddress, total, status, cid, checkbill);
                arr.add(bill);

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
        DAOBill dao = new DAOBill();

//         for(Bill bi: dao.getAll()){
//             System.out.println(bi.getCname());
//         }
//
//        try {
//            //          dao.insertBill(new Bill(2,"03/11/2000", "12345678","linhbeo","linhbeo",1,2,1));
////            dao.insertBill(new Bill(3,"03/11/2000", "12345678","linhbeo","linhbeo",1,2,1));
////              dao.insertBill(new Bill(4,"03/11/2000", "12345678","linhbeo","linhbeo",1,2,1));
////                dao.insertBill(new Bill(5,"03/11/2000", "12345678","linhbeo","linhbeo",1,2,1));
////                  dao.insertBill(new Bill(6,"03/11/2000", "12345678","linhbeo","linhbeo",1,2,1));
////                    dao.insertBill(new Bill(7,"03/11/2000", "12345678","linhbeo","linhbeo",1,2,1));
////                      dao.insertBill(new Bill(8,"03/11/2000", "12345678","linhbeo","linhbeo",1,2,1))
////            dao.deleteBill(1);
//            dao.updateBill(new Bill(2,"03/11/2000", "12345678","linhbeo1234","linhbeo",1,2,1));
//
////        try {
////            dao.updateBill(new Bill("linhbeo", "12345678","linhbeo","linhbeo",1,2,1));
////        } catch (SQLException ex) {
////            Logger.getLogger(DAOAmin.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
