/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOProduct extends DBContext {

    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO Product" + "  (pid,pname,quantity,price,image,description,status,cateID,paths) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?,?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from Product where pid =?";
    private static final String SELECT_PRODUCT_BY_PNAME = "select * from Product where pname LIKE ? ";
    private static final String SELECT_PRODUCT_BY_PNAME_AND_SORT_ASC = "select * from Product where pname LIKE ? ORDER BY price asc  ";
    private static final String SELECT_PRODUCT_BY_SORT_ASC = "select * from Product ORDER BY price asc ";
    private static final String SELECT_PRODUCT_BY_PNAME_AND_SORT_DESC = "select * from Product where pname LIKE ? ORDER BY price desc ";
    private static final String SELECT_PRODUCT_BY_SORT_DEC = "select * from Product ORDER BY price desc ";
    private static final String SELECT_ALL_PRODUCTS = "select * from Product ORDER BY pid ASC";
    private static final String DELETE_PRODUCT_SQL = "delete from Product where pid = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update Product set pname = ?,quantity= ?, price =? , image = ?, description = ?, status = ? , cateID = ?, paths = ?   where pid = ?;";
    private static final String SEARCH_PRODUCT_BY_NAME = "select * from Product where [pname] like ?";
    public int insertProduct(Product pro) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int n = 0;
        String sql = "insert into Product(pid,pname,quantity,price,image,description,status,cateID,paths) "
                + "values('" + pro.getPid() + "','" + pro.getPname() + "'," + pro.getQuantity()
                + "," + pro.getPrice() + ",'" + pro.getImage() + "','" + pro.getDescription() + "'," + pro.getStatus() + ","
                + pro.getCateID() + ")";
        // System.out.println(sql);
        try {
            //Statemetn
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(INSERT_PRODUCTS_SQL);
            ps.setString(1, pro.getPid());
            ps.setString(2, pro.getPname());
            ps.setInt(3, pro.getQuantity());
            ps.setDouble(4, pro.getPrice());
            ps.setString(5, pro.getImage());
            ps.setString(6, pro.getDescription());
            ps.setInt(7, pro.getStatus());
            ps.setInt(8, pro.getCateID());
            ps.setString(9, pro.getPath());

            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Product searchProById(List<Product> list , String id){
        for (Product p : list) {
            if(p.getPid().equals(id)){
                return p;
            }
        }
        return null;
    }

    public int deleteProduct(String pid) throws SQLException {
        int rowDeleted;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {

            statement.setString(1, pid);
            rowDeleted = statement.executeUpdate();
            return rowDeleted;
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

//    public void displayAll() {
//        String sql = "select * from Product";
//        ResultSet rs = getData(sql);
//        try {
//            while (rs.next()) {
//                String pid = rs.getString("pid"), pname = rs.getString(2); // "pname"
//                int quantity = rs.getInt("quantity");
//                double price = rs.getDouble(4);
//                String image = rs.getString(5), description = rs.getString(6);
//                int status = rs.getInt(7), cateID = rs.getInt(8);
//                Product pro = new Product(pid, pname, quantity, price, image, description, cateID);
//                System.out.println(pro);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public ArrayList<Product> getPrice(double from, double to) {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product where price between " + from + " and " + to;

        return arr;
    }

    public Product getProductById(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_ID);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)
                       
                ));
            }

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Product> searchProductByName(String txt) {
        ArrayList<Product> arr = new ArrayList<Product>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SEARCH_PRODUCT_BY_NAME);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            while (rs.next()) {

                String pid = rs.getString(1), pname = rs.getString(2); // "pname"
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                arr.add(pro);

            }
            return arr;

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet getProductByPname(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_PNAME);
            ps.setString(1, "%" + id + "%");
            rs = ps.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getProductByPnameAndSortAsc(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_PNAME_AND_SORT_ASC);
            ps.setString(1, "%" + id + "%");
            rs = ps.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getProductByPnameAndSortDesc(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_PNAME_AND_SORT_DESC);
            ps.setString(1, "%" + id + "%");
            rs = ps.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getProductBySortAsc(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_SORT_ASC);

            rs = ps.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getProductByDesc(String id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_PNAME_AND_SORT_DESC);

            rs = ps.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

//    
//     public ArrayList<Product> getAllByPname(String pname) {
//        ArrayList<Product> arr1 = getAllByArray();
//        ArrayList<Product> arr = new ArrayList<Product>();
//         for (Product product : arr) {
//             if(product.getPname().contains(pname)){
//                 arr.add(product);
//             }
//         }
//        
//        return arr;
//    }
    public ArrayList<Product> getAllByArray() {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                String pid = rs.getString(1), pname = rs.getString(2); // "pname"
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                arr.add(pro);

            }
            return arr;
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public ArrayList<Product> getAllByCateId(String id) {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product where cateID=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                String pid = rs.getString(1), pname = rs.getString(2); // "pname"
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5), description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                arr.add(pro);

            }
            return arr;
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ResultSet getAll() {
        ArrayList<Product> arr = new ArrayList<Product>();
        String sql = "select * from Product";
//        ResultSet rs = getData(sql);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
//            while (rs.next()) {
//                
//                String pid = rs.getString(1), pname = rs.getString(2); // "pname"
//                int quantity = rs.getInt(3);
//                double price = rs.getDouble(4);
//                String image= rs.getString(5), description= rs.getString(6);
//                int status= rs.getInt(7), cateID= rs.getInt(8);
////                Product pro=new Product(pid, pname, quantity, price, image, description, cateID);
////               arr.add(pro);
//
//            }
//
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void updateProduct(Product pro) throws SQLException {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try  {
             conn = new DBContext().getConnection();
            ps = conn.prepareStatement(UPDATE_PRODUCT_SQL);
            System.out.println(pro.getPid());
            System.out.println(pro.getPname());
            System.out.println(pro.getQuantity());
            System.out.println(pro.getPrice());
            System.out.println(pro.getImage());
            System.out.println(pro.getDescription());
            System.out.println(pro.getStatus());
            System.out.println(pro.getCateID());
            System.out.println(pro.getPath());
//            exxcutr
            ps.setString(1, pro.getPname());
            ps.setInt(2, pro.getQuantity());
            ps.setDouble(3, pro.getPrice());
            ps.setString(4, pro.getImage());
            ps.setString(5, pro.getDescription());
            ps.setInt(6, pro.getStatus());
            ps.setInt(7, pro.getCateID());
            ps.setString(8, "haha");
            ps.setString(9, pro.getPid());
            ps.executeUpdate();
           
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        int n = dao.insertProduct(new Product("P20", "HP G6", 2, 500, "no image", "second hand", 1, 3));
        int n1 = dao.insertProduct(new Product("P21", "HP G6", 2, 500, "no image", "second hand", 1, 1));
        int n2 = dao.insertProduct(new Product("P22", "HP G6", 2, 500, "no image", "second hand", 1, 4));
        int n3 = dao.insertProduct(new Product("P23", "HP G6", 2, 500, "no image", "second hand", 1, 8));
//        if (n > 0) {
//            System.out.println("Inserted");
//        }

//        try {
//            int m = dao.deleteProduct("P03");
//            if(m > 0){
//                System.out.println("Delete success");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for(Product p: dao.getAll()){
//             System.out.println(p.getPname());
//         }
//        try {
//            int u = dao.updateProduct(new Product("P01", "asd", 6, 123, "yes_image", "autication", 1, 1));
//            if (u > 0) {
//                System.out.println(" Update success");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
