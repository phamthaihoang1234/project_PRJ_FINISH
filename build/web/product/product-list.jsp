<%-- 
    Document   : user-list
    Created on : Oct 4, 2021, 1:33:34 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>User Management Application</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <style>
            .table-bordered {
                text-align: center;
            }
        </style>
    </head>
    <body>

        <c:if test="${cus != null}">

            <a  class="btn btn-success" style="float:right;" href="LoginController?action=logout" >Logout</a>
            <h4 style="color:red;float:right;margin-right: 27px">Hello ${cus.cname}</h4>
        </c:if>
        <c:if test="${cus == null}">

            <a  class="btn btn-success" style="float:right;margin-right: 27px" href="LoginController?action=login" >Login</a>
        </c:if>


        <br>

        <div class="row">
            <!--		 <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Product</h3>
                <hr>
                <div class="container text-left">

                    <a href="ProductServlet?action=new" class="btn btn-success">Add
                        New Product</a>
                </div>

                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>

                            <th>PID</th>
                            <th>Product Name</th>
                            <th>Image</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>CateName</th>
                            <th>Action</th>


                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="prod" items="${listProd}">

                            <tr>
                                <td><c:out value="${prod.pid}" /></td>
                                <td><c:out value="${prod.pname}" /></td>
                                <td><c:out value="${prod.quantity}" /></td>
                                <td><c:out value="${prod.price}" /></td>
                                <td><c:out value="${prod.image}" /></td>
                                <td><c:out value="${prod.description}" /></td>
                                <td><c:out value="${prod.status}" /></td>
                                <td><c:out value="${prod.cateID}" /></td>


                                <td><a href="ProductServlet?action=edit&id=<c:out value='${prod.pid}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a onclick="return confirm('Are you sure you want to delete this item?')"
                                                                href="ProductServlet?action=delete&id=<c:out value='${prod.pid}' />">Delete</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="ProductServlet?action=view&id=<c:out value='${prod.pid}' />">View</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a  href="ProductServlet?action=cart&id=<c:out value='${prod.pid}' />">Add to cart</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>
