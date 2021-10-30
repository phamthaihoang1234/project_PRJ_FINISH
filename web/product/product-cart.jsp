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
            .checkout {
                padding-top: 76px;
                padding-left: 951px;

            }
        </style>
    </head>
    <body>
        <c:if test="${auth != null}">
            <h4 style="color:red;float:right;margin-right: 21px">Hello ${sessionScope.auth.username}</h4>
        </c:if>


        <br>

        <div class="row">
            <!--		 <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Cart</h3>
                <hr>


                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>

                            <th>PID</th>
                            <th>Product Name</th>

                            <th>Quantity</th>
                            <th>Unit price</th>
                            <th>Total price</th>



                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="prod" items="${sessionScope.cart}">

                            <tr>
                                <td><c:out value="${prod.pro.pid}" /></td>
                                <td><c:out value="${prod.pro.pname}" /></td>
                                <td><c:out value="${prod.quan}" /></td>
                                <td><c:out value="${prod.pro.price}" /></td>
                                <td><c:out value="${prod.quan * prod.pro.price }" /></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->

                    </tbody>

                </table>
                <c:if test="${mess != null}">
                    <h1 style="color: green">${mess}</h1>
                </c:if>

                <c:if test="${total != null}">
                    <h4 style="color: green;float:right" >Total:${total}</h4>
                </c:if>


                <div class="checkout">
                    <a class="btn btn-success" href="ProductServlet" >Back</a>

                    <c:if test="${sessionScope.auth == null && sessionScope.cart !=null}">
                        <a   class="btn btn-success" href="LoginController?action=login" >CheckOut</a>
                    </c:if>
                    <c:if test="${sessionScope.auth != null && sessionScope.cart !=null}">
                        <a  onclick="return confirm('Are you sure you want to checkout this item?')" class="btn btn-success" href="ProductServlet?action=checkout" >CheckOut</a>
                    </c:if>
                        <c:if test="${sessionScope.cart ==null}">
                            <button  class="btn btn-success" disabled="" >CheckOut</button>
                    </c:if>
                       




                </div>

            </div>

        </div>
    </body>
</html>
