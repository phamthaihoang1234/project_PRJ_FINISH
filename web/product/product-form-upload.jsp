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
                    <h2>Manage <b>Product</b></h2>
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
                    <c:if test="${pro != null}">
                        <form action="uploadFileServlet?action=update" method="post" enctype="multipart/form-data">
                        </c:if>
                        <c:if test="${pro == null}">
                            <form action="uploadFileServlet?action=insert" method="post" enctype="multipart/form-data">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${pro != null}">
                                        Edit Product
                                    </c:if>
                                    <c:if test="${pro == null}">
                                        Add New Product
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${pro != null}">
                                <input type="hidden" name="id" value="<c:out value='${pro.pid}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Name</label> <input type="text"
                                                           value="<c:out value='${pro.pname}' />" class="form-control"
                                                           name="pname" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Quantity</label> <input type="text"
                                                               value="<c:out value='${pro.quantity}' />" class="form-control"
                                                               name="quantity" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Price</label> <input type="text"
                                                            value="<c:out value='${pro.price}' />" class="form-control"
                                                            name="price" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Image</label> 
                                <c:if test="${pro.image != null}">
                                    <img src="images/${pro.image}" width="80" height="70" />

                                </c:if>
                                <input type="file"
                                       class="form-control"
                                       name="image" required="required">

                            </fieldset>
                            <fieldset class="form-group">
                                <label>Description</label> <input type="text"
                                                                  value="<c:out value='${pro.description}' />" class="form-control"
                                                                  name="des" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Status</label> <input type="text"
                                                             value="<c:out value='${pro.status}' />" class="form-control"
                                                             name="status" required="required">
                            </fieldset>


                            <fieldset class="form-group">
                                <label>Category</label> 
                                <select name="categ">

                                    <c:forEach var="item" items="${cate}">
                                        <option  value="${item.cateID}" ${item.cateID == pro.cateID ? 'selected="selected"' : ''} >${item.cateName}</option>
                                    </c:forEach>

                                </select>
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
