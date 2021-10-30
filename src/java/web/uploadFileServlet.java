/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOCtegory;
import dao.DAOCustomer;
import dao.DAOProduct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Bill;
import model.BillDetail;
import model.BillProductCart;
import model.Customer;
import model.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class uploadFileServlet extends HttpServlet {

    public static final String UPLOAD_DIR = "images";

    private static final long serialVersionUID = 1L;
    private DAOProduct daoPro = new DAOProduct(); ;
    private DAOCtegory daoCate = new DAOCtegory();
    private DAOCustomer daoCus = new DAOCustomer();
    private DAOBill DAOBill = new DAOBill();
    private DAOBillDetail daoBillDetail = new DAOBillDetail();
    private HashMap<String, BillProductCart> carts = new HashMap<>();
     List<Product> listProTemp = daoPro.getAllByArray();
//    public void init() {
//        daoPro = new DAOProduct();
//        
//    }

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
                case "sub":
                    subCart(request, response);
                    break;
                case "checkout":
                    checkOut(request, response);
                    break;
                case "showform":
                    showForm(request, response);
                    break;
                case "manageProduct":
                    manageProduct(request, response);
                    break;
                case "manageBill":
                    manageBill(request, response);
                    break;
                case "removeCart":
                    removeCartProd(request, response);
                    break;
                case "viewCart":
                    viewCartProd(request, response);
                    break;
                case "searchPro":
                    searchProAjax(request, response);
                    break;
                case "searchForMaPro":
                    searchForMaPro(request, response);
                    break;
                case "searchBill":
                    searchBill(request, response);
                    break;
                    
                default:
                    listProd(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
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

    private void searchProAjax(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String key = request.getParameter("txt");
        List<Product> list = daoPro.searchProductByName(key);
        PrintWriter out = response.getWriter();
        for (Product o : list) {
            out.println("<div class=\"product col-12 col-md-6 col-lg-4\">\n"
                    + "                                <div class=\"card\">\n"
                    + "                                    <img class=\"card-img-top\" src=\"images/" + o.getImage() + "\" alt=\"Card image cap\" width=\"80\" height=\"120\" >\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <p class=\"card-text show_txt\">" + o.getPname() + "</p>\n"
                    + "                                        <div class=\"row\">\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <p class=\"btn btn-danger btn-block\">" + o.getPrice() + " $</p>\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <a href=\"uploadFileServlet?action=cart&id=" + o.getPid() + " \" class=\"btn btn-success btn-block\">Add to cart</a>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>");
        }

    }

    private void searchForMaPro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String key = request.getParameter("txt");
        List<Product> list = daoPro.searchProductByName(key);
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
                + "                                <th>ID</th>\n"
                + "                                <th>Name</th>\n"
                + "                                <th>Image</th>\n"
                + "                                <th>Price</th>\n"
                + "                                <th>Quantity</th>\n"
                + "                                <th>Actions</th>\n"
                + "                            </tr>\n"
                + "                        </thead>");
        for (Product prod : list) {
            out.println("<tr>\n"
                    + "                                <td>\n"
                    + "                                    <span class=\"custom-checkbox\">\n"
                    + "                                        <input type=\"checkbox\" id=\"checkbox1\" name=\"options[]\" value=\"1\">\n"
                    + "                                        <label for=\"checkbox1\"></label>\n"
                    + "                                    </span>\n"
                    + "                                </td>\n"
                    + "                                <td>" + prod.getPid() + "</td>\n"
                    + "                                <td>" + prod.getPname() + "</td>\n"
                    + "                                <td>\n"
                    + "                                    <img src=\"images/" + prod.getImage() + "\">\n"
                    + "                                </td>\n"
                    + "                                <td>" + prod.getPrice() + "</td>\n"
                    + "                                <td>" + prod.getQuantity() + "</td>\n"
                    + "                                <td>\n"
                    + "                                    <a href=\"uploadFileServlet?action=edit&id=" + prod.getPid() + "\"  class=\"edit\" data-toggle=\"modal\"><i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Edit\">&#xE254;</i></a>\n"
                    + "                                    <a onclick=\"return confirm('Are you sure you want to delete this item?')\" href=\"uploadFileServlet?action=delete&id=<c:out value='" + prod.getPid() + "' />\" class=\"delete\" data-toggle=\"modal\"><i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Delete\">&#xE872;</i></a>\n"
                    + "                                </td>\n"
                    + "                            </tr>");
        }
        out.println("</table>");

    }

    int nu = 0;

    private int getBillId() {
        nu = nu + 1;
        return nu;
    }

    private void viewProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");

        Product existingProd = daoPro.getProductById(id);
        request.setAttribute("pro", existingProd);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-view.jsp");

        dispatcher.forward(request, response);
    }

    private void manageBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println(request.getParameter("cid"));
        isAdmin = request.getParameter("isAdmin");
      Cid = request.getParameter("cid");
        if (request.getParameter("cid") != null) {
//            request.setAttribute("bill", DAOBill.getBillDetailById(request.getParameter("cid")));
            request.setAttribute("bill", DAOBill.getAllBillByCid(request.getParameter("cid")));
        } else {
            request.setAttribute("bill", DAOBill.getAll());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("manage-bill.jsp");

        dispatcher.forward(request, response);
    }

    private void viewCartProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        double total = 0;
        ArrayList<BillProductCart> cart_List = (ArrayList<BillProductCart>) session.getAttribute("cart");
        if (cart_List != null) {
            for (BillProductCart bill : cart_List) {
                total += bill.getQuan() * bill.getPro().getPrice();
            }
            session.setAttribute("cart", cart_List);
            request.setAttribute("total", total);
        } else {
            if (request.getParameter("check") != null) {
                request.setAttribute("mess", "Checkout successfully! See you again");
            } else {
                request.setAttribute("mess", " ");
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(uploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("cid");
        Customer cus = daoCus.getCustomerById(id);
        HttpSession session = request.getSession();
        int quan = 0;
        double total1 = 0;

        ArrayList<BillProductCart> billCart = (ArrayList<BillProductCart>) session.getAttribute("cart");
        for (BillProductCart b : billCart) {
            quan += b.getQuan();
            total1 += b.getQuan() * b.getPro().getPrice();
        }

        request.setAttribute("cus", cus);
        request.setAttribute("oid", getId());
        request.setAttribute("total", total1);
        request.setAttribute("date", String.valueOf(java.time.LocalDateTime.now()));

        RequestDispatcher dispatcher = request.getRequestDispatcher("form-checkout.jsp");

        dispatcher.forward(request, response);
    }

    private void checkOut(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        int quan = 0;
        
        String cusName = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        String cid = request.getParameter("cid");
        String oid = request.getParameter("oid");
        String total2 = request.getParameter("total");
        double total1 = Double.parseDouble(total2);
        System.out.println(cusName);
        System.out.println(phone);
        System.out.println(address);
        System.out.println(status);
        System.out.println(cid);
        System.out.println(total1);
        ArrayList<BillProductCart> billCart = (ArrayList<BillProductCart>) session.getAttribute("cart");
//        for (BillProductCart b : billCart) {
//            quan += b.getQuan();
//            total1 += b.getQuan() * b.getPro().getPrice();
//        }
        Bill bill = new Bill(Integer.parseInt(oid), String.valueOf(java.time.LocalDateTime.now()), cusName, phone, address, total1, Integer.parseInt(status), Integer.parseInt(cid), String.valueOf(getId()));
        DAOBill.insertBill(bill);
        for (BillProductCart bi : billCart) {
            double total = bi.getQuan() * bi.getPro().getPrice();
            BillDetail b = new BillDetail(Integer.parseInt(bi.getPro().getPid()), String.valueOf(bill.getoID()), bill.getDateCreate(), bi.getQuan(), bi.getPro().getPrice(), total);
             Product existingProd = daoPro.getProductById(bi.getPro().getPid());
             existingProd.setQuantity(existingProd.getQuantity() - bi.getQuan());
            daoBillDetail.insertBillDetail(b);
            daoPro.updateProduct(existingProd);
        }
        
        listProTemp = daoPro.getAllByArray();
        request.setAttribute("mess", "Checkout successfully");

        session.removeAttribute("cart");
        session.removeAttribute("proChoose");

        response.sendRedirect("uploadFileServlet?action=viewCart&check=1");
    }

    private void subCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        float total = 0;
        Product existingProd = daoPro.getProductById(id);
        HttpSession session = request.getSession();
        List<Product> proChoo = (ArrayList<Product>)session.getAttribute("proChoose");
        for (Product p : proChoo) {
            if(p.getPid().equals(id)){
                 p.setQuantity(p.getQuantity() + 1);
            }
        }
       
        ArrayList<BillProductCart> cartList = new ArrayList<>();
        ArrayList<BillProductCart> cart_List = (ArrayList<BillProductCart>) session.getAttribute("cart");
        BillProductCart procart = new BillProductCart(existingProd, 1);

        boolean exist = false;

        cartList = cart_List;
        for (BillProductCart billProductCart : cart_List) {
            if (billProductCart.getPro().getPid().equals(id)) {
                exist = true;
                billProductCart.setQuan(billProductCart.getQuan() - 1);
            }
        }

        session.setAttribute("cart", cartList);
        session.setAttribute("proChoose", proChoo);

        for (BillProductCart bill : cartList) {
            total += bill.getQuan() * bill.getPro().getPrice();
        }
        request.setAttribute("total", total);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(uploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdCart(HttpServletRequest request, HttpServletResponse response) {
        int i = 1;
        HttpSession session = request.getSession();
        ArrayList<BillProductCart> cart_List = (ArrayList<BillProductCart>) session.getAttribute("cart");
        if (cart_List == null) {
            return 1;
        } else {
            for (BillProductCart billProductCart : cart_List) {
                i = billProductCart.getId() + 1;
            }

        }
        return i;
    }

    private void cartProds(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String id = request.getParameter("id");
        float total = 0;
        Product existingProd = daoPro.getProductById(id);
        HttpSession session = request.getSession();
        if (carts.containsKey(id)) {
            for (BillProductCart b : carts.values()) {
                if (b.getPro().getPid().equals(id)) {
                    b.setQuan(b.getQuan() + 1);
                }
            }
            session.setAttribute("cart", getItemCart(carts));

        } else {
            BillProductCart procart = new BillProductCart(getIdCart(request, response), existingProd, 1);
            carts.put(id, procart);
            session.setAttribute("cart", getItemCart(carts));
        }

        for (BillProductCart bill : getItemCart(carts)) {
            total += bill.getQuan() * bill.getPro().getPrice();
        }

        request.setAttribute("total", total);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
        dispatcher.forward(request, response);

    }

    private List<BillProductCart> getItemCart(HashMap<String, BillProductCart> map) {
        List<BillProductCart> arr = new ArrayList<>();
        for (BillProductCart c : carts.values()) {
            arr.add(c);
        }
        return arr;
    }

    private void removeCartProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        int total = 0;
        ArrayList<BillProductCart> cart_List = (ArrayList<BillProductCart>) session.getAttribute("cart");
        System.out.println(cart_List.size());
        Iterator<BillProductCart> iterator = cart_List.iterator();
        while (iterator.hasNext()) {
            BillProductCart language = iterator.next();
            if (language.getId() == (id)) {
                iterator.remove();
            }
        }

        for (BillProductCart bill : cart_List) {
            total += bill.getQuan() * bill.getPro().getPrice();
        }
        request.setAttribute("total", total);
        session.setAttribute("cart", cart_List);
         session.setAttribute("proChoose", daoPro.getAllByArray());
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");

        dispatcher.forward(request, response);
    }

    private void listProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProd = daoPro.getAllByArray();
        request.setAttribute("listProd", listProd);
        request.setAttribute("cate", daoCate.getAll());
//        HttpSession session = request.getSession();
//        Customer cus = (Customer) session.getAttribute("auth");
//        request.setAttribute("cus", cus);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-list-upload.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
        dispatcher.forward(request, response);
    }

    private void manageProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProd = daoPro.getAllByArray();
        request.setAttribute("listProd", listProd);
        request.setAttribute("cate", daoCate.getAll());
        HttpSession session = request.getSession();

        ArrayList<BillProductCart> cart_List = (ArrayList<BillProductCart>) session.getAttribute("cart");

//        HttpSession session = request.getSession();
//        Customer cus = (Customer) session.getAttribute("auth");
//        request.setAttribute("cus", cus);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-list-upload.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ManagerProduct.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cate", daoCate.getAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-form-upload.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("cate", daoCate.getAll());
        Product existingProd = daoPro.getProductById(id);
        request.setAttribute("pro", existingProd);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product-form-upload.jsp");

        dispatcher.forward(request, response);

    }
    public String dbFileName = "";

    private int getIdPro() {
        int id = 0;
        for (Product product : daoPro.getAllByArray()) {
            id = Integer.parseInt(product.getPid());
        }
        id = id + 1;
        return id;
    }

    private void insertProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("pname");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");

        String des = request.getParameter("des");
        String status = request.getParameter("status");
        String cate = request.getParameter("categ");

        String fileName = "";
        try {
            Part filePart = request.getPart("image");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        //  check data
        // convert
        int quan = Integer.parseInt(quantity);
        double pri = Double.parseDouble(price);
        int sta = Integer.parseInt(status);
        int cateID = Integer.parseInt(cate);
        out.print("<h1>dbFileName: " + dbFileName + "</h1>");
        // entity
        Product pro = new Product(String.valueOf(getIdPro()), name, quan, pri, fileName, des, sta, cateID, "haha");
        daoPro.insertProduct(pro);
        response.sendRedirect("uploadFileServlet?action=manageProduct");
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
    
    private void cartProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");
        float total = 0;
        System.out.println("vaocart");
//       Product proTemp = new Product(existingProd.getPid(),existingProd.getPname(),existingProd.getQuantity(),existingProd.getPrice(),existingProd.getImage(),existingProd.getDescription(),existingProd.getStatus(),existingProd.getCateID());
        Product existingProd = daoPro.getProductById(id);
        HttpSession session = request.getSession();
       
        
        for (Product p : listProTemp) {
            if(p.getPid().equals(existingProd.getPid())){
                p.setQuantity(p.getQuantity() - 1);
            }
        }
       
        
        System.out.println(daoPro.searchProById(listProTemp, id).toString());
        
//        session.setAttribute("proChoose", daoPro.searchProById(listProTemp, id));
        session.setAttribute("proChoose", listProTemp);
        
        ArrayList<BillProductCart> cartList = new ArrayList<>();
        ArrayList<BillProductCart> cart_List = (ArrayList<BillProductCart>) session.getAttribute("cart");
        BillProductCart procart = new BillProductCart(getIdCart(request, response), existingProd, 1);
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
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String name = request.getParameter("pname");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");

        String des = request.getParameter("des");
        String status = request.getParameter("status");
        String cate = request.getParameter("categ");

        String fileName = "";
        try {
            Part filePart = request.getPart("image");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        //  check data
        // convert
        int quan = Integer.parseInt(quantity);
        double pri = Double.parseDouble(price);
        int sta = Integer.parseInt(status);
        int cateID = Integer.parseInt(cate);

        // entity
        Product pro = new Product(String.valueOf(id), name, quan, pri, fileName, des, sta, cateID, "haha");
        daoPro.updateProduct(pro);
        response.sendRedirect("uploadFileServlet?action=manageProduct");
    }

    private void deleteProd(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idDel = request.getParameter("id");

        daoPro.deleteProduct(idDel);
        response.sendRedirect("uploadFileServlet?action=manageProduct");

    }

}
