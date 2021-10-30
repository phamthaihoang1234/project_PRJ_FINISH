
import dao.DAOAmin;
import dao.DAOBillDetail;
import dao.DAOCustomer;
import dao.DAOProduct;
import java.util.List;
import model.Admin;
import model.BillDetail;
import model.Customer;
import model.Product;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
         DAOAmin daoAdmin = new DAOAmin();
         DAOProduct pro = new DAOProduct();
          DAOBillDetail daoBillDetail = new DAOBillDetail();
         
          Admin ad = daoAdmin.loginAdmin("admin", "12345678");
          System.out.println(ad.getAdminID()+ad.getUsername()+ad.getPassword()+ad.getIsAdmin());
        
//         List<Product> list = pro.searchProductByName("Mac");
//         for (Product p : list) {
//             System.out.println(p.getPname());
//        }
    }
}
