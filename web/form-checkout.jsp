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
                    <h2>Form <b>Checkout</b></h2>
                </div>
               
            </div>
        </div>
        <br>
        <div class="container col-md-5" >
            <div class="card" >
                <div class="card-body">


                    <form action="uploadFileServlet?action=checkout" method="post" >


                        <caption>

                            Add receiver's information

                        </caption>

                        <c:if test="${cus != null}">
                            <input type="hidden" name="cid" value="<c:out value='${cus.cid}' />" />
                        </c:if>

                        <fieldset class="form-group">
                             <input type="hidden"
                                                       value="<c:out value='${oid}' />" class="form-control"
                                                       name="oid" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Date</label> <input type="text"
                                                           value="<c:out value='${date}' />" class="form-control"
                                                           name="date" required="required" readonly="">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Customer name</label> <input type="text"
                                                        value="<c:out value='${cus.cname}' />" class="form-control"
                                                        name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Phone</label> <input type="text"
                                                              value="<c:out value='${cus.cphone}' />" class="form-control"
                                                              name="phone" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Address</label> <input type="text"
                                                         value="<c:out value='${cus.cAddress}' />" class="form-control"
                                                         name="address" required="required">
                        </fieldset>


                        <fieldset class="form-group">
                            <label>Total</label> <input type="text"
                                                       value="<c:out value='${total}' />" class="form-control"
                                                       name="total" required="required" readonly="">
                        </fieldset>
                        <fieldset class="form-group">
                            <input type="hidden"
                                                       value="<c:out value='1' />" class="form-control"
                                                       name="status" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                             <input type="hidden"
                                                       value="<c:out value='1' />" class="form-control"
                                                       name="hidden" required="required">
                        </fieldset>
                       







                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
