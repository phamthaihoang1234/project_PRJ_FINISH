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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <%--<jsp:include page="Menu.jsp"></jsp:include>--%>
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Bill</b></h2>
                    </div>

                    <div class="col-sm-6">

                        <form action="uploadFileServlet?action=searchBill" method="post" class="form-inline my-2 my-lg-0">
                            <div class="input-group input-group-sm">
                                <input style="padding-top: 10px;padding-right: 154px;color: black;" class="search" oninput="searchByName(this)" value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">

                            </div>


                        </form>



                    </div>
                </div>
            </div>
        </div>
        <div class="shopping-cart">
            <div class="px-4 px-lg-0">

                <div class="pb-5">
                    <div class="container">
                        <div class="row">
                            <div id="content">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Customer</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Date</div>
                                                    </th>

                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Total</div>
                                                    </th>

                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Phone</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Address</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Status</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Actions</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">

                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${bill}" var="b">
                                                    <tr>
                                                        <td class="align-middle"><strong>${b.cname}</strong></td>
                                                        <td class="align-middle"><strong>${b.dateCreate}</strong></td>
                                                        <td class="align-middle"><strong>${b.total}</strong></td>


                                                        <td class="align-middle"><strong>${b.cphone}</strong></td>
                                                        <td class="align-middle"><strong>${b.cAddress}</strong></td>
                                                                <c:if test="${b.status == 1}">
                                                            <td class="align-middle"><strong>Đang xử lí ...</strong></td>
                                                            <td class="align-middle"><a href="BillServlet?action=reject&id=${b.oID}" class="text-dark">
                                                                    <button type="button" class="btn btn-danger">Reject</button>
                                                                </a>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${b.status == 0}">
                                                            <td class="align-middle"><strong>Đã hủy </strong></td>
                                                        </c:if>
                                                        <c:if test="${b.status == 2}">
                                                            <td class="align-middle"><strong>Đã giao hàng </strong></td>
                                                        </c:if>

                                                        <td class="align-middle"><a href="BillServlet?action=viewDetail&id=${b.oID}" class="text-dark">
                                                                <button type="button" class="btn btn-danger">View detail</button>
                                                            </a>
                                                        </td>
                                                    </tr> 
                                                </c:forEach>

                                            </tbody>

                                        </table>


                                    </div>
                                    <div class="clearfix">
                                        <div class="hint-text">Showing <b>6</b> out of <b>${countTotal}</b> entries</div>
                                        <ul class="pagination">
                                             <li class="page-item ${index == 1?"disabled":""}"><a href="BillServlet?action=pagingBill&index=${index-1}">Previous</a></li>
                                             <c:forEach begin="1" end="${lastPage}" var="i">
                                                <li class="page-item ${index == i?"active":""}"><a href="BillServlet?action=pagingBill&index=${i}" class="page-link">${i}</a></li>
                                            </c:forEach>
                                           
                                           
                                           <li class="page-item ${index ==lastPage?"disabled":""}"><a href="BillServlet?action=pagingBill&index=${index+1}" class="page-link">Next</a></li>
                                        </ul>
                                    </div>


                                </div>
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">

                            <c:if test="${mess != null}">
                                <h1 style="color: green">${mess}</h1>
                            </c:if>
                            <c:if test="${sessionScope.cart != null}">
                                <div class="col-lg-6">
                                    <!--<div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>-->
                                    <div class="p-4">



                                    </div>
                                </div>
                            </c:if>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>-->
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>-->
        <script src="js/manager.js" type="text/javascript"></script>

        <script>
            function searchByName(param) {
                var txtSearch = param.value;
                $.ajax({
                    url: "/demo_jsp_serverlet_bootstrap/uploadFileServlet?action=searchBill",
                    type: "get", //send it through get method
                    data: {
                        txt: txtSearch
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                        console.log("loiconmeroi");
                    }
                });
            }
        </script>
    </body>

</html>

