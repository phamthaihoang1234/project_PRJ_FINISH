<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<style>
    div#navbarsExampleDefault {
   
    margin-left: -294px;
}
</style>-->
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="uploadFileServlet">Shop computer</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
<!--                <li class="nav-item">
                        <a class="nav-link" href="uploadFileServlet?action=manageBill">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="uploadFileServlet?action=manageBill">Product</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="uploadFileServlet?action=manageBill">Sale</a>
                    </li>-->
                <c:if test="${sessionScope.auth == null}">
                    <!--                    <li class="nav-item">
                                            <a class="nav-link" href="#">Manager Account</a>
                                        </li>-->
                </c:if>
                <c:if test="${sessionScope.auth.isAdmin ==1}">
                    <li class="nav-item">
                        <a class="nav-link" href="CustomerServlet?action=manageAcc">Manager Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="uploadFileServlet?action=manageProduct">Manager Product</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="uploadFileServlet?action=manageBill&isAdmin=1">Manager Bill</a>
                    </li>
<!--                    <li class="nav-item">
                        <a class="nav-link" href="BillServlet?action=pagingBill&isAdmin=1">Manager Bill</a>
                    </li>-->
                </c:if>
                    <c:if test="${sessionScope.auth != null &&sessionScope.auth.isAdmin ==0}">
                    
                    <li class="nav-item">
                        <a class="nav-link" href="uploadFileServlet?action=manageBill&cid=${sessionScope.auth.cid}&isAdmin=0">Bill</a>
                    </li>
                </c:if>


            </ul>

            <form action="uploadFileServlet?action=searchPro" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input oninput="searchByName(this)" value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="uploadFileServlet?action=viewCart">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light">${sessionScope.cart.size()}</span>
                </a>
                   
            </form>
                    <div class="account" style="margin-top: -19px;">
                          <c:if test="${sessionScope.auth != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Hello ${sessionScope.auth.username}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LoginController?action=logout">Logout</a>
                    </li> 
                </c:if>
                <c:if test="${sessionScope.auth == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="LoginController?action=login&home=1">Login</a>
                    </li>
                </c:if>
                    </div>
            
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Shop phụ kiện laptop chính hãng  </h1>
        <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phẩm công nghệ chính hãng nhập khẩu Mỹ</p>
    </div>
<!--<img class="card-img-top" src="https://tse3.mm.bing.net/th?id=OIP._0za5Bax7VfjurTz_jTJeQHaEc&pid=Api&P=0&w=255&h=154" alt="Card image cap" width="120" height="300" >-->
</section>
<!--end of menu-->
