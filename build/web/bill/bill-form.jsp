

<%@page import="java.text.ParseException"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
                    <h4> Bill
                        Management  </h4>
                </div>

                <ul class="navbar-nav">
                    <li><a href="BillServlet"
                           class="nav-link">Bill</a></li>
                </ul>

            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${bill != null}">
                        <form action="BillServlet?action=update" method="post">
                        </c:if>
                        <c:if test="${bill == null}">
                            <form action="BillServlet?action=insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${bill != null}">
                                        Edit Bill
                                    </c:if>
                                    <c:if test="${bill == null}">
                                        Add New Bill
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${bill != null}">
                                <input type="hidden" name="id" value="<c:out value='${bill.oID}' />" />
                            </c:if>
                            

                            <fieldset class="form-group">
                                <label>dateCreate</label> <input type="date" value="<c:out value='${bill.dateCreate}' />"
                                                                  class="form-control" 
                                                                 name="dateCreate" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>name</label> <input type="text" required="required"
                                                           value="<c:out value='${bill.cname}' />" class="form-control"
                                                           name="cname">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>phone</label> <input type="text"
                                                            value="<c:out value='${bill.cphone}' />" class="form-control"
                                                            name="cphone" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Address</label> <input type="text"
                                                              value="<c:out value='${bill.cAddress}' />" class="form-control"
                                                              name="cAddress" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Total</label> <input type="text"
                                                            value="<c:out value='${bill.total}' />" class="form-control"
                                                            name="total" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Status</label> <input type="text"
                                                             value="<c:out value='${bill.status}' />" class="form-control"
                                                             name="status" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Cid</label> <input type="text"
                                                          value="<c:out value='${bill.cid}' />" class="form-control"
                                                          name="cid" required="required">
                            </fieldset>





                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
