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
        </style>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
                            <h3 style="color: black"> 
                                Management category  </h3>
			</div>

			<ul class="navbar-nav">
                                    <li><a href="CategoryServlet"
                                           class="nav-link">Category</a></li>
                                </ul>
		</nav>
	</header>
	<br>

	<div class="row">
<!--		 <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of category</h3>
			<hr>
			<div class="container text-left">

				<a href="CategoryServlet?action=new" class="btn btn-success">Add
					New category</a>
			</div>

			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Status</th>
						
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="cate" items="${listCateg}">

						<tr>
							<td><c:out value="${cate.cateID}" /></td>
							<td><c:out value="${cate.cateName}" /></td>
							<td><c:out value="${cate.status}" /></td>
							
							<td><a href="CategoryServlet?action=edit&id=<c:out value='${cate.cateID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a onclick="return confirm('Are you sure you want to delete this item?')"
								href="CategoryServlet?action=delete&id=<c:out value='${cate.cateID}' />">Delete</a>
                                                        &nbsp;&nbsp;&nbsp;&nbsp; <a
								href="CategoryServlet?action=view&id=<c:out value='${cate.cateID}' />">View</a>
                                                        </td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
