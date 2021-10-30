<%-- 
    Document   : login-form
    Created on : Oct 14, 2021, 11:52:42 AM
    Author     : Admin
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
<!--        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <title>Login</title>
    </head>
    <body>
        <section class="vh-100" style="background-color: #9A616D;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="col-md-6 col-lg-5 d-none d-md-block">
                                    <img
                                        src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/img1.jpg"

                                        alt="login form"
                                        class="img-fluid" style="border-radius: 1rem 0 0 1rem;"
                                        />
                                </div>
                                <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black">

                                        <form action="LoginController?action=access" >
                                           <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Logo</span>
                                            </div>

                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your account</h5>

                                            <div class="form-outline mb-4">
                                                <input type="text" id="form2Example17" class="form-control form-control-lg" />
                                                <label class="form-label" for="form2Example17" name="username">Username</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="password" id="form2Example27" class="form-control form-control-lg" />
                                                <label class="form-label" for="form2Example27" name="password">Password</label>
                                            </div>
                                            

                                            <div class="pt-1 mb-4">
                                                <button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>
                                                <a class="btn btn-dark btn-lg btn-block" href="ProductServlet">Back</a>
                                            </div>
                                            

                                            <a class="small text-muted" href="#!">Forgot password?</a>
                                            <p class="mb-5 pb-lg-2" style="color: #393f81;">Don't have an account? <a href="register-form.jsp" style="color: #393f81;">Register here</a></p>
                                            <a href="#!" class="small text-muted">Terms of use.</a>
                                            <a href="#!" class="small text-muted">Privacy policy</a>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
