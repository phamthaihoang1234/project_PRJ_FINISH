

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


                    <caption>
                        <h2>
                            <c:if test="${bill != null}">
                                View Bill Detail
                            </c:if>

                        </h2>
                    </caption>

                    <c:if test="${bill != null}">
                        <input type="hidden" name="id" value="<c:out value='${bill.oID}' />" />
                    </c:if>


                    <fieldset class="form-group">
                        <label>DateCreate</label> <input type="text" value="<c:out value='${bill.dateCreate}' />"
                                                         class="form-control" 
                                                         name="dateCreate" readonly="" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>name</label> <input type="text"
                                                   value="<c:out value='${bill.cname}' />" readonly="" class="form-control"
                                                   name="cname">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>phone</label> <input type="text"
                                                    value="<c:out value='${bill.cphone}' />" class="form-control"
                                                    name="cphone" readonly  required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Address</label> <input type="text"
                                                      value="<c:out value='${bill.cAddress}' />" class="form-control"
                                                      name="cAddress" readonly required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Total</label> <input type="text"
                                                    value="<c:out value='${bill.total}' />" class="form-control"
                                                    name="total" readonly required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Status</label> <input type="text"
                                                     value="<c:out value='${bill.status}' />" class="form-control"
                                                     name="status" readonly required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Cid</label> <input type="text"
                                                  value="<c:out value='${bill.cid}' />" class="form-control"
                                                  name="cid" readonly required="required">
                    </fieldset>





                    <a class="btn btn-success" href="BillServlet" >Back</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

