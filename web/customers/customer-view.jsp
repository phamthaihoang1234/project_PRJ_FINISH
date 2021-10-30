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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            .col-md-5 {

                margin-left: 358px;
                margin-top: -9px;
            }
        </style>
    </head>
    <body>


        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>View <b>Customer</b></h2>
                </div>
                <!--                <div class="col-sm-6">
                                    <a href="uploadFileServlet?action=new"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                
                                </div>-->
            </div>
        </div>
        <br>
        <div class="container col-md-5" >
            <div class="card" >
                <div class="card-body">
                    <caption>
                        <h2>
                            <c:if test="${cus != null}">
                                View Customer
                            </c:if>

                        </h2>
                    </caption>

                    <c:if test="${cus != null}">
                        <input type="hidden" name="id" value="<c:out value='${cus.cid}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Name</label> <input type="text"
                                                   value="<c:out value='${cus.cname}' />" class="form-control"
                                                   name="cname" readonly="" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Phone</label> <input type="text"
                                                    value="<c:out value='${cus.cphone}' />" class="form-control"
                                                    name="cphone" readonly required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Address</label> <input type="text"
                                                      value="<c:out value='${cus.cAddress}' />" class="form-control"
                                                      name="cAddress" readonly required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Username</label> <input type="text"
                                                       value="<c:out value='${cus.username}' />" class="form-control"
                                                       name="username" readonly required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Password</label> <input type="text"
                                                       value="<c:out value='${cus.password}' />" class="form-control"
                                                       name="password" readonly required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Status</label> <input type="text"
                                                     value="<c:out value='${cus.status}' />" class="form-control"
                                                     name="status" readonly required="required">
                    </fieldset>
                    <a class="btn btn-success" href="CustomerServlet?action=manageAcc" >Back</a>

                </div>
            </div>
        </div>
    </body>
</html>
