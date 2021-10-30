/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bill;
import model.BillDetail;
import model.BillDetailDTO;
import model.Customer;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BillServlet", urlPatterns = {"/BillServlet"})
public class BillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOBill DAOBill;
    private DAOBillDetail daoBillDetail = new DAOBillDetail();
    private DAOProduct daop = new DAOProduct();

    public void init() {
        DAOBill = new DAOBill();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        System.out.println(action);

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertBill(request, response);
                    break;
                case "delete":
                    deleteBill(request, response);
                    break;
//                case "edit":
//                    showEditForm(request, response);
//                    break;
                case "update":
                    updateBill(request, response);
                    break;
//                case "view":
//                    viewBill(request, response);
//                    break;
                case "reject":
                    rejectBill(request, response);
                    break;
                case "viewDetail":
                    viewBillDetail(request, response);
                    break;
                case "pagingBill":
                    pagingBill(request,response);
                    break;
//                default:
//                    listBill(request, response);
//                    break;
                case "searchBill":
                    searchBill(request, response);
                    break;
                 default:
                    pagingBill(request, response);
                    break;    
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        List<Bill> listBill = DAOBill.getAll();
//        request.setAttribute("listBill", listBill);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/bill/bill-list.jsp");
//        dispatcher.forward(request, response);
    }
    
     private void pagingBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
         String indexPage = request.getParameter("index");
         int index =0;
         if(indexPage == null){
             index = 1;
         }else {
           index = Integer.parseInt(indexPage);
         }
         int countTotal = DAOBill.countBill();
         
         int lastPage = countTotal/6;
         if(countTotal % 6 !=0  ){
             lastPage++;
         }
         List<Bill> list = DAOBill.pagingBill(index);
         System.out.println("countTotal: "+countTotal);
         System.out.println("lastPage: "+lastPage);
         System.out.println("index: "+index);
         for (Bill bill : list) {
             System.out.println("bill is: "+bill.getCname());
         }
         
        
         
         request.setAttribute("list", list);
        request.setAttribute("countTotal", countTotal);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("index", index);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage-bill.jsp");
        dispatcher.forward(request, response);
    }
     
     String isAdmin ="";
     String Cid ="";
      
    private void searchBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String key = request.getParameter("txt");
        
        
        System.out.println("Isadminaaaaaaaaaa: "+isAdmin);
        System.out.println("CIdisaaaaaaaaa: "+Cid);
        System.out.println("Keyaaaaaaaaa: "+key);
        List<Bill> list = new ArrayList<>();
//        phuong thuc equals ap dung cho string nen phai dung dau ""
        if (isAdmin.equals("0")) {
            
            list = DAOBill.searchBillByCname(key, Integer.parseInt(Cid),isAdmin);
        } else {
            
            list = DAOBill.searchBillByCname(key, -1,isAdmin);
        }

        for (Bill bill : list) {
            System.out.println("<h1>" + bill.getCname() + "</h1>");
        }
        PrintWriter out = response.getWriter();
        System.out.println("davaosearch");
        out.println("<div class=\"col-lg-12 p-5 bg-white rounded shadow-sm mb-5\">\n"
                + "                                    <div class=\"table-responsive\">\n"
                + "                                        <table class=\"table\">\n"
                + "                                            <thead>\n"
                + "                                                <tr>\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "                                                        <div class=\"p-2 px-3 text-uppercase\">Customer</div>\n"
                + "                                                    </th>\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "                                                        <div class=\"py-2 text-uppercase\">Date</div>\n"
                + "                                                    </th>\n"
                + "\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "                                                        <div class=\"py-2 text-uppercase\">Total</div>\n"
                + "                                                    </th>\n"
                + "\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "                                                        <div class=\"py-2 text-uppercase\">Phone</div>\n"
                + "                                                    </th>\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "                                                        <div class=\"py-2 text-uppercase\">Address</div>\n"
                + "                                                    </th>\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "                                                        <div class=\"py-2 text-uppercase\">Status</div>\n"
                + "                                                    </th>\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "                                                        <div class=\"py-2 text-uppercase\">Actions</div>\n"
                + "                                                    </th>\n"
                + "                                                    <th scope=\"col\" class=\"border-0 bg-light\">\n"
                + "\n"
                + "                                                    </th>\n"
                + "                                                </tr>\n"
                + "                                            </thead>\n"
                + "                                            <tbody>");
        for (Bill b : list) {
            out.println("<tr>\n"
                    + "                                                        <td class=\"align-middle\"><strong>" + b.getCname() + "</strong></td>\n"
                    + "                                                        <td class=\"align-middle\"><strong>" + b.getDateCreate() + "</strong></td>\n"
                    + "                                                        <td class=\"align-middle\"><strong>" + b.getTotal() + "</strong></td>\n"
                    + "\n"
                    + "\n"
                    + "                                                        <td class=\"align-middle\"><strong>" + b.getCphone() + "</strong></td>\n"
                    + "                                                        <td class=\"align-middle\"><strong>" + b.getcAddress() + "</strong></td>");
            if (b.getStatus() == 1) {
                out.println("<td class=\"align-middle\"><strong>Processing</strong></td>\n"
                        + "                                                            <td class=\"align-middle\"><a href=\"BillServlet?action=reject&id=${b.oID}\" class=\"text-dark\">\n"
                        + "                                                                    <button type=\"button\" class=\"btn btn-danger\">Reject</button>\n"
                        + "                                                                </a>\n"
                        + "                                                            </td>");
            }
            if (b.getStatus() == 0) {
                out.println("<td class=\"align-middle\"><strong>Cancelled </strong></td>");
            }
            if (b.getStatus() == 2) {
                out.println("<td class=\"align-middle\"><strong>Successful  </strong></td>");
            }
            out.println("<td class=\"align-middle\"><a href=\"BillServlet?action=viewDetail&id=" + b.getoID() + "\" class=\"text-dark\">\n"
                    + "                                                                <button type=\"button\" class=\"btn btn-danger\">View detail</button>\n"
                    + "                                                            </a>\n"
                    + "                                                        </td>\n"
                    + "                                                    </tr> ");
        }

        out.println("     </tbody>\n"
                + "                                        </table>\n"
                + "                                    </div>\n"
                + "\n"
                + "                                </div>");
    }

    

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bill/bill-form.jsp");
        dispatcher.forward(request, response);
    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        String id = request.getParameter("id");
//
//        Bill existingBill = DAOBill.getBillDetailById(id);
//        request.setAttribute("bill", existingBill);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/bill/bill-form.jsp");
//
//        dispatcher.forward(request, response);
//
//    }
//    private void viewBill(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        String id = request.getParameter("id");
//
//        Bill existingBill = DAOBill.getBillDetailById(id);
//        request.setAttribute("bill", existingBill);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/bill/bill-view.jsp");
//
//        dispatcher.forward(request, response);
//
//    }
    private int getId() {
        int number = 0;
        if (!DAOBill.getAll().isEmpty()) {
            for (Bill bill : DAOBill.getAll()) {
                number = bill.getoID();
            }
        }

        number = number + 1;
        return number;
    }

    private void insertBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String dateCreate = request.getParameter("dateCreate");
        String cname = request.getParameter("cname");
        String cphone = request.getParameter("cphone");
        String cAddress = request.getParameter("cAddress");
        String total = request.getParameter("total");
        String status = request.getParameter("status");
        String cid = request.getParameter("cid");
        //  check data
        // convert

        int tota = Integer.parseInt(total);

        int sta = Integer.parseInt(status);
        int ci = Integer.parseInt(cid);

        // entity
//        Bill bill = new Bill(getId(), dateCreate, cname, cphone, cAddress, tota, sta, ci);
//        DAOBill.insertBill(bill);
        response.sendRedirect("BillServlet");
    }

    private void updateBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String id = request.getParameter("id");

        String dateCreate = request.getParameter("dateCreate");

        String cname = request.getParameter("cname");

        String cphone = request.getParameter("cphone");

        String cAddress = request.getParameter("cAddress");

        String total = request.getParameter("total");

        String status = request.getParameter("status");

        String cid = request.getParameter("cid");

        //  check data
        // convert
        double tota = Double.parseDouble(total);

        int sta = Integer.parseInt(status);
        int ci = Integer.parseInt(cid);

        // entity
//        Bill bill = new Bill(Integer.parseInt(id), dateCreate, cname, cphone, cAddress, tota, sta, ci);
//        DAOBill.updateBill(bill);
        response.sendRedirect("BillServlet");
    }

    private void rejectBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String idDel = request.getParameter("id");
        Bill b = DAOBill.getBillDetailById(idDel);
        b.setStatus(0);
        DAOBill.updateBill(b);

        response.sendRedirect("uploadFileServlet?action=manageBill");
    }

    private void viewBillDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        List<BillDetailDTO> billDetailDTO = new ArrayList<>();
        String oid = request.getParameter("id");
        List<BillDetail> billDetail = daoBillDetail.getAllBillDetailByOId(Integer.parseInt(oid));
        for (BillDetail billDetail1 : billDetail) {
            System.out.println(billDetail1.getMoney());
        }
        for (BillDetail bd : billDetail) {
            BillDetailDTO billdro = new BillDetailDTO(daop.getProductById(String.valueOf(bd.getPid())), bd.getDateCreate(), bd.getQuantity(), bd.getMoney(), bd.getTotal());
            billDetailDTO.add(billdro);
        }
        request.setAttribute("billDetailDTO", billDetailDTO);

        RequestDispatcher dispatcher = request.getRequestDispatcher("viewDetailBill.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BillServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idDel = request.getParameter("id");

        DAOBill.deleteBill(Integer.parseInt(idDel));
        response.sendRedirect("BillServlet");

    }
}
