/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAOBill;
import dao.DAOCtegory;
import dao.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bill;
import model.BillProductCart;
import model.Customer;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOProduct daoPro;
    private DAOCtegory daoCate = new DAOCtegory();
    private DAOBill DAOBill = new DAOBill();
    public void init() {
        daoPro = new DAOProduct();
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

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertProd(request, response);
                    break;
                case "delete":
                    deleteProd(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateProd(request, response);
                    break;
                case "view":
                    viewProd(request, response);
                    break;
                case "cart":
                    cartProd(request, response);
                    break;
                case "checkout":
                    checkOut(request, response);
                    break;
                default:
                    listProd(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void viewProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");

        Product existingProd = daoPro.getProductById(id);
        request.setAttribute("pro", existingProd);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-view.jsp");

        dispatcher.forward(request, response);
    }
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
    int nu =0;
    private int getBillId(){
        nu = nu +1;
        return nu;
    }

    private void checkOut(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        int quan =0;
        Customer cus = (Customer) session.getAttribute("auth");
        ArrayList<BillProductCart> billCart = (ArrayList<BillProductCart>) session.getAttribute("cart");
        for (BillProductCart b : billCart) {
            quan += b.getQuan();
        }
        out.print("<h1:soluongcart>"+billCart.size()+"</h1>");
        out.print("<h1:soluongcart>"+quan+"</h1>");
        Bill bill = new Bill(getId(), String.valueOf(java.time.LocalDateTime.now()), cus.getCname(), cus.getCphone(), cus.getcAddress(), quan, cus.getStatus(), cus.getCid(),String.valueOf(getBillId()));
        DAOBill.insertBill(bill);
        request.setAttribute("mess", "Checkout successfully");
        
        session.removeAttribute("cart");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-cart.jsp");

        dispatcher.forward(request, response);
    }

    private void cartProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");
        float total = 0;
        Product existingProd = daoPro.getProductById(id);
        HttpSession session = request.getSession();

        ArrayList<BillProductCart> cartList = new ArrayList<>();
        ArrayList<BillProductCart> cart_List = (ArrayList<BillProductCart>) session.getAttribute("cart");
        BillProductCart procart = new BillProductCart(existingProd, 1);
        if (cart_List == null) {
            cartList.add(procart);
            out.print("<h1>lan1size" + cartList.size() + "</h1>");
            session.setAttribute("cart", cartList);
        } else {
            boolean exist = false;
            out.print("<h1>lan2size" + cartList.size() + "</h1>");
            cartList = cart_List;
            for (BillProductCart billProductCart : cart_List) {
                if (billProductCart.getPro().getPid().equals(id)) {
                    exist = true;
                    billProductCart.setQuan(billProductCart.getQuan() + 1);
                }
            }
            if (!exist) {
                cartList.add(procart);
            }
           
            session.setAttribute("cart", cartList);
        }
         for (BillProductCart bill : cartList) {
                total += bill.getQuan() * bill.getPro().getPrice();
            }
         request.setAttribute("total", total);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-cart.jsp");

        dispatcher.forward(request, response);
    }

    private void listProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProd = daoPro.getAllByArray();
        request.setAttribute("listProd", listProd);
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("auth");
        request.setAttribute("cus", cus);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cate", daoCate.getAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("cate", daoCate.getAll());
        Product existingProd = daoPro.getProductById(id);
        request.setAttribute("pro", existingProd);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-form.jsp");

        dispatcher.forward(request, response);

    }

    private int getIdPro() {
        int id = 0;
        for (Product product : daoPro.getAllByArray()) {
            id = Integer.parseInt(product.getPid());
        }
        id = id +1;
        return id;
    }

    private void insertProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String name = request.getParameter("pname");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String image = request.getParameter("image");
        String des = request.getParameter("des");
        String status = request.getParameter("status");
        String cate = request.getParameter("categ");

        //  check data
        // convert
        int quan = Integer.parseInt(quantity);
        double pri = Double.parseDouble(price);
        int sta = Integer.parseInt(status);
        int cateID = Integer.parseInt(cate);

        // entity
        Product pro = new Product(String.valueOf(getIdPro()), name, quan, pri, image, des, sta, cateID);
        daoPro.insertProduct(pro);
        response.sendRedirect("ProductServlet");
    }

    private void updateProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("pname");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String image = request.getParameter("image");
        String des = request.getParameter("des");
        String status = request.getParameter("status");
        String cate = request.getParameter("categ");
        //  check data
        // convert
        int quan = Integer.parseInt(quantity);
        double pri = Double.parseDouble(price);
        int sta = Integer.parseInt(status);
        int cateID = Integer.parseInt(cate);
        // entity
        Product pro = new Product(id, name, quan, pri, image, des, sta, cateID);
        daoPro.updateProduct(pro);
        response.sendRedirect("ProductServlet");
    }

    private void deleteProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idDel = request.getParameter("id");

        daoPro.deleteProduct(idDel);
        response.sendRedirect("ProductServlet");

    }

}
