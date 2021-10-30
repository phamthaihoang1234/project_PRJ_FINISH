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

        
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">


                    <form action="LoginController?action=access" method="POST" >
                        <fieldset class="form-group">
                            <label>Username</label> <input type="text"
                                                           class="form-control"
                                                           name="username" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Password</label> <input type="text"
                                                           class="form-control"
                                                           name="password" required="required">
                        </fieldset>
                        <button type="submit" class="btn btn-success">Login</button>
                    </form>
                    <c:if test="${message != null}">
                        <h4 style="color:red;float:right;margin-right: 21px">${message}</h4>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
