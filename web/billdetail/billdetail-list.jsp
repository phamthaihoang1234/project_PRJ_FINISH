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
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
                            <h3 style="color: black"> Admin
                                Management admin  </h3>
			</div>

			<ul class="navbar-nav">
                                    <li><a href="AdminServlet"
                                           class="nav-link">Admin</a></li>
                                </ul>
		</nav>
	</header>
	<br>

	<div class="row">
<!--		 <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Admin</h3>
			<hr>
			<div class="container text-left">

				<a href="AdminServlet?action=new" class="btn btn-success">Add
					New Admin</a>
			</div>

			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Password</th>
						
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="admin" items="${listAdmin}">

						<tr>
							<td><c:out value="${admin.adminID}" /></td>
							<td><c:out value="${admin.username}" /></td>
							<td><c:out value="${admin.password}" /></td>
							
							<td><a href="AdminServlet?action=edit&id=<c:out value='${admin.adminID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="AdminServlet?action=delete&id=<c:out value='${admin.adminID}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
