<%-- 
    Document   : Cart
    Created on : Oct 31, 2020, 9:42:21 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Product</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Unit Prize</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Quantity</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Total Prize</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Action</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase"></div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.cart}" var="prod">
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="images/${prod.pro.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${prod.pro.pname}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${prod.pro.price}</strong></td>
                                                    <td class="align-middle"><strong>${prod.quan}</strong></td>
                                                    <td class="align-middle"><strong>${prod.pro.price * prod.quan}</strong></td>

                                                    <td class="align-middle">

                                                        <c:if test="${prod.quan > 0}">
                                                            <a href="uploadFileServlet?action=sub&id=<c:out value='${prod.pro.pid}' />"><button  class="btnSub">-</button></a> <strong>${prod.quan}</strong>
                                                        </c:if>
                                                        <c:if test="${prod.quan < 1}">
                                                            <a href="uploadFileServlet?action=sub&id=<c:out value='${prod.pro.pid}' />"><button disabled="" class="btnSub">-</button></a> <strong>${prod.quan}</strong>
                                                        </c:if>
                                                        <c:forEach items="${sessionScope.proChoose}" var="p">
                                                            <c:if test="${p.pid.equals(prod.pro.pid) && p.quantity >=1}">
                                                                <a href="uploadFileServlet?action=cart&id=<c:out value='${prod.pro.pid}' />"><button class="btnAdd">+</button></a>
                                                            </c:if>
                                                        </c:forEach>




                                                    </td>
                                                    <td class="align-middle"><a href="uploadFileServlet?action=removeCart&id=${prod.id}" class="text-dark">
                                                            <button type="button" class="btn btn-danger">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr> 

                                            </c:forEach>
                                            <tr>
                                        <a href="uploadFileServlet"><button type="button" class="btn btn-primary">Back to continue buy</button>
                                            </tr>
                                            </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">

                            <c:if test="${mess != null}">
                                <h1 style="color: green">${mess}</h1>
                            </c:if>
                            <c:if test="${sessionScope.cart != null}">
                                <div class="col-lg-6">
                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                                    <div class="p-4">
                                        <ul class="list-unstyled mb-4">
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong>${total}$</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Phí vận chuyển</strong><strong>Free ship</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>0 $</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng thanh toán</strong>
                                                <h5 class="font-weight-bold">${total} $</h5>
                                            </li>
                                        </ul>

                                        <c:if test="${sessionScope.auth == null && sessionScope.cart !=null}">
                                            <a   class="btn btn-dark rounded-pill py-2 btn-block" href="LoginController?action=login" >CheckOut</a>
                                        </c:if>
                                        <c:if test="${sessionScope.auth != null && sessionScope.cart !=null && total !=0}">
                                            <a  onclick="return confirm('Are you sure you want to checkout this item?')" class="btn btn-dark rounded-pill py-2 btn-block" href="uploadFileServlet?action=showform&cid=${sessionScope.auth.cid}" >CheckOut</a>
                                        </c:if>
                                        <c:if test="${sessionScope.cart ==null || total==0}">
                                            <button  class="btn btn-dark rounded-pill py-2 btn-block" disabled="" >CheckOut</button>
                                        </c:if>
                                    </div>
                                </div>
                            </c:if>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
</html>
