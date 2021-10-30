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

        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <h3 style="color: black"> Bill
                        Management bill  </h3>
                </div>

                <ul class="navbar-nav">
                    <li><a href="BillServlet"
                           class="nav-link">Admin</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!--		 <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Bill</h3>
                <hr>
                <div class="container text-left">

                    <a href="BillServlet?action=new" class="btn btn-success">Add
                        New Bill</a>
                </div>

                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>oID</th>
                            <th>dateCreate</th>
                            <th>cname</th>
                            <th>cphone</th>
                            <th>cAddress</th>
                            <th>total</th>
                            <th>Status</th>
                            <th>cid</th>
                            <th colspan="2" style="display: center;">Action</th>
                            
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="bill" items="${listBill}">

                            <tr>
                                <td><c:out value="${bill.oID}" /></td>
                                <td><c:out value="${bill.dateCreate}"/></td>
                                <td><c:out value="${bill.cname}" /></td>
                                <td><c:out value="${bill.cphone}" /></td>
                                <td><c:out value="${bill.cAddress}" /></td>
                                <td><c:out value="${bill.total}" /></td>
                                <td><c:out value="${bill.status}" /></td>
                                <td><c:out value="${bill.cid}" /></td>

                                <td><a href="BillServlet?action=edit&id=<c:out value='${bill.oID}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a onclick="return confirm('Are you sure you want to delete this item?')"
                                        href="BillServlet?action=delete&id=<c:out value='${bill.oID}' />">Delete</a>
                                &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a
                                        href="BillServlet?action=view&id=<c:out value='${bill.oID}' />">View</a>
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
