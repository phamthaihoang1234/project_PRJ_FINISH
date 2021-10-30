<%-- 
    Document   : product-view
    Created on : Oct 9, 2021, 10:04:54 PM
    Author     : Admin
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <title>User Management Application</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <h3> Product
                        Management  </h3>
                </div>

                <ul class="navbar-nav">
                    <li><a href="ProductServlet"
                           class="nav-link">Product</a></li>
                </ul>

            </nav>
        </header>
        <br>
       <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    
                    <form>
                            <c:if test="${pro != null}">
                                <input type="hidden" name="id" value="<c:out value='${pro.pid}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Name</label> <input type="text"
                                                       value="<c:out value='${pro.pname}' />" class="form-control"
                                                       name="pname" readonly>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Quantity</label> <input type="text"
                                                       value="<c:out value='${pro.quantity}' />" class="form-control"
                                                       name="quantity" required="required" readonly>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Price</label> <input type="text"
                                                       value="<c:out value='${pro.price}' />" class="form-control"
                                                       name="price" required="required" readonly>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Image</label> <input type="text"
                                                       value="<c:out value='${pro.image}' />" class="form-control"
                                                       name="image" required="required" readonly>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Description</label> <input type="text"
                                                       value="<c:out value='${pro.description}' />" class="form-control"
                                                       name="des" required="required" readonly>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Status</label> <input type="text"
                                                       value="<c:out value='${pro.status}' />" class="form-control"
                                                       name="status" required="required" readonly>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Category</label> <input type="text"
                                                       value="<c:out value='${pro.cateID}' />" class="form-control"
                                                       name="cate" required="required" readonly>
                            </fieldset>
                                                       
                                                       <a class="btn btn-success" href="ProductServlet" >Back</a>





                    </form>      
                        
                </div>
            </div>
        </div>
    </body>
</html>
