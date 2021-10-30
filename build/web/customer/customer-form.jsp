<%-- 
    Document   : user-form
    Created on : Oct 4, 2021, 1:32:59 PM
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
    </head>
    <body>

        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <h3> Customer
                        Management  </h3>
                </div>

                <ul class="navbar-nav">
                    <li><a href="CustomerServlet"
                           class="nav-link">Customer</a></li>
                </ul>

            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${cus != null}">
                        <form action="CustomerServlet?action=update" method="post">
                        </c:if>
                        <c:if test="${cus == null}">
                            <form action="CustomerServlet?action=insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${cus != null}">
                                        Edit Customer
                                    </c:if>
                                    <c:if test="${cus == null}">
                                        Add New Customer
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${cus != null}">
                                <input type="hidden" name="id" value="<c:out value='${cus.cid}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Name</label> <input type="text"
                                                               value="<c:out value='${cus.cname}' />" class="form-control"
                                                               name="cname" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Phone</label> <input type="text"
                                                               value="<c:out value='${cus.cphone}' />" class="form-control"
                                                               name="cphone" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Address</label> <input type="text"
                                                               value="<c:out value='${cus.cAddress}' />" class="form-control"
                                                               name="cAddress" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Username</label> <input type="text"
                                                               value="<c:out value='${cus.username}' />" class="form-control"
                                                               name="username" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Password</label> <input type="text"
                                                               value="<c:out value='${cus.password}' />" class="form-control"
                                                               name="password" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Status</label> <input type="text"
                                                               value="<c:out value='${cus.status}' />" class="form-control"
                                                               name="status" required="required">
                            </fieldset>






                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
