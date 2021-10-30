/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAOAmin;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Admin;


/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOAmin daoAdmin;

    public void init() {
        daoAdmin = new DAOAmin();
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
                    insertAdmin(request, response);
                    break;
                case "delete":
                    deleteAdmin(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateAdmin(request, response);
                    break;
                case "view":
                    viewAdmin(request, response);
                    break;

                default:
                    listAdmin(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<Admin> listAdmin = daoAdmin.getAll();

        request.setAttribute("listAdmin", listAdmin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Admin existingAdmin = daoAdmin.getAdminById(id);
        request.setAttribute("admin", existingAdmin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-form.jsp");

        dispatcher.forward(request, response);

    }

    private void viewAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Admin existingAdmin = daoAdmin.getAdminById(id);
        request.setAttribute("admin", existingAdmin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin-view.jsp");

        dispatcher.forward(request, response);

    }

    private void insertAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter("id");
        out.print("<h1>" + id + "</h1>");
        out.print("<h1>deonhandcdata</h1>");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //  check data
        // convert
        // entity
        Admin admin = new Admin(username, password);
        daoAdmin.insertAdmin(admin);
        response.sendRedirect("AdminServlet");
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //  check data
        // convert
        // entity
        Admin admin = new Admin(Integer.parseInt(id), username, password);
        daoAdmin.updateAdmin(admin);
        response.sendRedirect("AdminServlet");
    }

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idDel = request.getParameter("id");

        daoAdmin.deleteAdmin(Integer.parseInt(idDel));
        response.sendRedirect("AdminServlet");

    }

}
