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
                    <h3 style="color: black"> 
                        Management customer  </h3>
                </div>

                <ul class="navbar-nav">
                    <li><a href="CustomerServlet"
                           class="nav-link">Customer</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!--		 <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of customer</h3>
                <hr>
                <div class="container text-left">

                    <a href="CustomerServlet?action=new" class="btn btn-success">Add
                        New Customer</a>
                </div>

                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>CID</th>
                            <th>Cname</th>
                            <th>Cphone</th>
                            <th>CAddress</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Status</th>
                            <th >Action</th>


                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="cus" items="${listCus}">

                            <tr>
                                <td><c:out value="${cus.cid}" /></td>
                                <td><c:out value="${cus.cname}" /></td>
                                <td><c:out value="${cus.cphone}" /></td>
                                <td><c:out value="${cus.cAddress}" /></td>
                                <td><c:out value="${cus.username}" /></td>
                                <td><c:out value="${cus.password}" /></td>
                                <td><c:out value="${cus.status}" /></td>

                                <td><a href="CustomerServlet?action=edit&id=<c:out value='${cus.cid}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a onclick="return confirm('Are you sure you want to delete this item?')"
                                        href="CustomerServlet?action=delete&id=<c:out value='${cus.cid}' />">Delete</a>
                                &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="CustomerServlet?action=view&id=<c:out value='${cus.cid}' />">View</a>
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
