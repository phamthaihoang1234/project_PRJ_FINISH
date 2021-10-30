
import dao.DAOBill;
import java.util.List;
import model.Bill;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class tesst {
    public static void main(String[] args) {
     
          DAOBill dao = new DAOBill();
         List<Bill> list = dao.pagingBill(3);
         
         for (Bill bill : list) {
             System.out.println(bill.getCname());
        }
    }
}
