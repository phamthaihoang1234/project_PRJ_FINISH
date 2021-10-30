<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }

        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Customer</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <form action="uploadFileServlet?action=searchPro" method="post" class="form-inline my-2 my-lg-0">
                                <div class="input-group input-group-sm">
                                    <input style="padding-top: 10px;padding-right: 154px;color: black;" class="search" oninput="searchByName(this)" value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">

                                </div>


                            </form>
                            <a href="CustomerServlet?action=new" style="margin-top: -31px;"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Customer</span></a>

                        </div>
                    </div>
                </div>

                <div id="content">                       
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                               
                                <th>Cname</th>
                                <th>Cphone</th>
                                <th>CAddress</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Status</th>
                                <th >Action</th>
                                <th ></th>

                            </tr>
                        </thead>


                        <c:forEach items="${listCus}" var="cus">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td hidden="">${cus.cid}</td>
                                <td>${cus.cname}</td>
                                <td>${cus.cphone}</td>
                                <td>${cus.cAddress}</td>
                                <td>${cus.username}</td>
                                <td>${cus.password}</td>
                                <c:if test="${cus.status == 1}">
                                    <td class="align-middle"> <strong>Enable</strong> </td>
                                </c:if>
                                <c:if test="${cus.status != 1}">
                                    <td class="align-middle"> <strong>Disnable</strong></td>
                                </c:if>





                                <td>
                                    <a href="CustomerServlet?action=edit&id=${cus.cid}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <c:if test="${cus.status == 1}">
                                        <a onclick="return confirm('Are you sure you want to block this user?')" href="CustomerServlet?action=delete&id=${cus.cid}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Block user">&#xE872;</i></a>
                                    </c:if>
                                    <c:if test="${cus.status != 1}">
                                        <a onclick="return confirm('Are you sure you want to unblock this user?')" href="CustomerServlet?action=unblock&id=${cus.cid}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Unblock user">&#xE872;</i></a>
                                    </c:if>
                                    <!--<a href="CustomerServlet?action=view&id=<c:out value='${cus.cid}' />"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="View">&#xE254;</i></a>-->
                                    <a href="CustomerServlet?action=view&id=<c:out value='${cus.cid}' />"  class="edit" data-toggle="modal"><i class="fa fa-eye" aria-hidden="true" title="View"></i></a>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
            <a href="uploadFileServlet"><button type="button" class="btn btn-primary">Back to home</button>

        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployee" class="modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="uploadFileServlet?action=insert" method="post" enctype="multipart/form-data">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>-->
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" value="${pro.pname}" class="form-control"
                                       name="pname" required="required">
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input type="file"
                                       class="form-control"
                                       name="image" required="required">
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input type="text"
                                       value="<c:out value='${pro.price}' />" class="form-control"
                                       name="price" required="required">
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <input type="text"
                                       value="<c:out value='${pro.status}' />" class="form-control"
                                       name="status" required="required">
                            </div>
                            <div class="form-group">
                                <label>Title</label>
                                <textarea name="title" class="form-control" required></textarea>
                            </div>

                            <div class="form-group">
                                <label>Description</label>
                                <textarea value="<c:out value='${pro.description}' />" class="form-control"
                                          name="des" required="required"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Category</label>

                                <select name="categ" class="form-select" aria-label="Default select example">

                                    <c:forEach var="item" items="${cate}">
                                        <option  value="${item.cateID}" ${item.cateID == pro.cateID ? 'selected="selected"' : ''} >${item.cateName}</option>
                                    </c:forEach>

                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <script src="js/manager.js" type="text/javascript"></script>
        <script>
                                            function searchByName(param) {
                                                var txtSearch = param.value;
                                                $.ajax({
                                                    url: "/demo_jsp_serverlet_bootstrap/CustomerServlet?action=searchCus",
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