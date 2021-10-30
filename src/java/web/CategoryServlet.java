/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DAOCtegory;
import dao.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOCtegory daoCate;
    private DAOProduct daoPro = new DAOProduct();
    public void init() {
        daoCate = new DAOCtegory();
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
                    insertCateg(request, response);
                    break;
                case "delete":
                    deleteCateg(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCateg(request, response);
                    break;
                case "view":
                    viewCateg(request, response);
                    break;
                case "viewProduct":
                    viewProduct(request, response);
                    break;    
                default:
                    listCateg(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCateg(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> listCateg = daoCate.getAll();
        request.setAttribute("listCateg", listCateg);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/category-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void viewProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("cid");
        
        List<Product> listPro = daoPro.getAllByCateId(id);
        request.setAttribute("listProd", listPro);
        request.setAttribute("cate", daoCate.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-home.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/category-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");

        Category existingCateg = daoCate.getCategoryById(id);
        request.setAttribute("cate", existingCateg);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/category-form.jsp");

        dispatcher.forward(request, response);

    }
    private void viewCateg(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");

        Category existingCateg = daoCate.getCategoryById(id);
        request.setAttribute("cate", existingCateg);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/category-view.jsp");

        dispatcher.forward(request, response);

    }
    int number = 4;

    private int getId() {

        number = number + 1;
        return number;
    }

    private void insertCateg(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String id = request.getParameter("id");
        String categoryname = request.getParameter("categoryname");
        String status = request.getParameter("Status");

        //  check data
        // convert
        // entity
        Category cate = new Category(getId(), categoryname, Integer.parseInt(status));
        daoCate.insertCate(cate);
        response.sendRedirect("CategoryServlet");
    }

    private void updateCateg(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String id = request.getParameter("id");
        String categoryname = request.getParameter("categoryname");
        String status = request.getParameter("Status");

        //  check data
        // convert
        // entity
        Category cate = new Category(Integer.parseInt(id), categoryname, Integer.parseInt(status));
        daoCate.updateCateg(cate);
        response.sendRedirect("CategoryServlet");
    }

    private void deleteCateg(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idDel = request.getParameter("id");

        daoCate.deleteCate(Integer.parseInt(idDel));
        response.sendRedirect("CategoryServlet");

    }

}
