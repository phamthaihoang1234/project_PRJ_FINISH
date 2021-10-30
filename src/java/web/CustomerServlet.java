/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAOCustomer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOCustomer daoCus;

    public void init() {
        daoCus = new DAOCustomer();
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
                    insertCus(request, response);
                    break;
                case "delete":
                    deleteCus(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCus(request, response);
                    break;
                case "view":
                    viewCus(request, response);
                    break;
                case "unblock":
                    unblock(request, response);
                    break;
                case "manageAcc":
                    manageAcc(request, response);
                    break;
                case "searchCus":
                    searchCus(request, response);
                    break;
                default:
                    listCus(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Customer> listCus = daoCus.getAll();
        request.setAttribute("listCus", listCus);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/customer-list.jsp");
        dispatcher.forward(request, response);
    }

    private void searchCus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String key = request.getParameter("txt");
        List<Customer> list = daoCus.searchCusByName(key);
        for (Customer customer : list) {
            System.out.println(customer.getCname());
        }
        PrintWriter out = response.getWriter();
        out.println("<table class=\"table table-striped table-hover\">\n"
                + "                        <thead>\n"
                + "                            <tr>\n"
                + "                                <th>\n"
                + "                                    <span class=\"custom-checkbox\">\n"
                + "                                        <input type=\"checkbox\" id=\"selectAll\">\n"
                + "                                        <label for=\"selectAll\"></label>\n"
                + "                                    </span>\n"
                + "                                </th>\n"
                + "                               \n"
                + "                                <th>Cname</th>\n"
                + "                                <th>Cphone</th>\n"
                + "                                <th>CAddress</th>\n"
                + "                                <th>Username</th>\n"
                + "                                <th>Password</th>\n"
                + "                                <th>Status</th>\n"
                + "                                <th >Action</th>\n"
                + "                                <th ></th>\n"
                + "\n"
                + "                            </tr>\n"
                + "                        </thead>");
        for (Customer cus : list) {
            out.println("<tr>\n" +
"                                <td>\n" +
"                                    <span class=\"custom-checkbox\">\n" +
"                                        <input type=\"checkbox\" id=\"checkbox1\" name=\"options[]\" value=\"1\">\n" +
"                                        <label for=\"checkbox1\"></label>\n" +
"                                    </span>\n" +
"                                </td>\n" +
"                                <td hidden=\"\">"+cus.getCid()+"</td>\n" +
"                                <td>"+cus.getCname()+"</td>\n" +
"                                <td>"+cus.getCphone()+"</td>\n" +
"                                <td>"+cus.getcAddress()+"</td>\n" +
"                                <td>"+cus.getUsername()+"</td>\n" +
"                                <td>"+cus.getPassword()+"</td>");

            if (cus.getStatus() == 1) {
                out.println(" <td class=\"align-middle\"> <strong>Enable</strong> </td>");
            }

            if (cus.getStatus() != 1) {
                out.println("<td class=\"align-middle\"> <strong>Disnable</strong></td>");
            }

           out.println("<td>\n" +
"                                    <a href=\"CustomerServlet?action=edit&id="+cus.getCid()+"\"  class=\"edit\" data-toggle=\"modal\"><i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Edit\">&#xE254;</i></a>");


            if (cus.getStatus() == 1) {
                out.println("<a onclick=\"return confirm('Are you sure you want to block this user?')\" href=\"CustomerServlet?action=delete&id="+cus.getCid()+"\" class=\"delete\" data-toggle=\"modal\"><i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Block user\">&#xE872;</i></a>");
            }

            if (cus.getStatus() != 1) {
                out.println("<a onclick=\"return confirm('Are you sure you want to unblock this user?')\" href=\"CustomerServlet?action=unblock&id="+cus.getCid()+"\" class=\"delete\" data-toggle=\"modal\"><i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Unblock user\">&#xE872;</i></a>");
            }
            out.println("<a href=\"CustomerServlet?action=view&id="+cus.getCid()+"\"  class=\"edit\" data-toggle=\"modal\"><i class=\"fa fa-eye\" aria-hidden=\"true\" title=\"View\"></i></a>\n"
                    + "\n"
                    + "                                </td>\n"
                    + "                            </tr>");

        }
        out.println(" </table>");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");

        Customer existingCus = daoCus.getCustomerById(id);
        request.setAttribute("cus", existingCus);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/customer-form.jsp");

        dispatcher.forward(request, response);

    }

    private void viewCus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        String key = request.getParameter("key");
        
        Customer existingCus = daoCus.getCustomerById(id);
        request.setAttribute("cus", existingCus);
        
       
            RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/customer-view.jsp");
             dispatcher.forward(request, response);
        
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/customer-view.jsp");

//        dispatcher.forward(request, response);

    }

    private void manageAcc(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        ArrayList<Customer> arr = daoCus.getAll();
        request.setAttribute("listCus", arr);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customers/customer-manager.jsp");

        dispatcher.forward(request, response);

    }
    int number = 4;

    private int getId() {

        number = number + 1;
        return number;
    }

    private void insertCus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String name = request.getParameter("cname");
        String phone = request.getParameter("cphone");
        String address = request.getParameter("cAddress");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String status = request.getParameter("status");

        //  check data
        // convert
//        int sta = Integer.parseInt(status);

        // entity
        Customer cus = new Customer(name, phone, address, username, password, 1, "0");
        daoCus.insertCustomer(cus);
        response.sendRedirect("LoginController?action=login");
    }

    private void updateCus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("cname");
        String phone = request.getParameter("cphone");
        String address = request.getParameter("cAddress");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String status = request.getParameter("status");

        //  check data
        // convert
        int sta = Integer.parseInt(status);

        // entity
        Customer cus = new Customer(Integer.parseInt(id), name, phone, address, username, password, sta, "0");
        daoCus.updateCustomer(cus);
        response.sendRedirect("CustomerServlet");
    }

    private void deleteCus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idDel = request.getParameter("id");
        Customer cus = daoCus.getCustomerById(idDel);
        cus.setStatus(0);
        daoCus.updateCustomer(cus);
        response.sendRedirect("CustomerServlet?action=manageAcc");

    }

    private void unblock(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idDel = request.getParameter("id");
        Customer cus = daoCus.getCustomerById(idDel);
        cus.setStatus(1);
        daoCus.updateCustomer(cus);
        response.sendRedirect("CustomerServlet?action=manageAcc");

    }

}
