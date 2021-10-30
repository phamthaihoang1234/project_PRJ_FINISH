<%-- 
    Document   : admin-view
    Created on : Oct 9, 2021, 10:03:49 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                    <h3> Admin
                        Management  </h3>
                </div>

                <ul class="navbar-nav">
                    <li><a href="AdminServlet"
                           class="nav-link">Admin</a></li>
                </ul>

            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">


                    <caption>
                        <h2>
                            <c:if test="${admin != null}">
                                View Admin Detail
                            </c:if>

                        </h2>
                    </caption>

                    <c:if test="${admin != null}">
                        <input type="hidden" name="id" value="<c:out value='${admin.getAdminID()}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Username</label> <input type="text"
                                                       value="<c:out value='${admin.getUsername()}' />" class="form-control"
                                                       name="username" readonly="" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="text"
                                                       value="<c:out value='${admin.password}' />" readonly="" class="form-control"
                                                       name="password">
                    </fieldset>
                    <a class="btn btn-success" href="AdminServlet" >Back</a>




                </div>
            </div>
        </div>
    </body>
</html>
