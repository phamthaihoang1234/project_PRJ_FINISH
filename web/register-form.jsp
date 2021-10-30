<%-- 
    Document   : register-form
    Created on : Oct 14, 2021, 12:08:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <style>
            .card-registration .select-input.form-control[readonly]:not([disabled]) {
                font-size: 1rem;
                line-height: 2.15;
                padding-left: .75em;
                padding-right: .75em;
            }
            .card-registration .select-arrow {
                top: 13px;
            }
        </style>
    </head>
    <body>
        <section class="h-100 bg-dark">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card card-registration my-4">
                            <div class="row g-0">
                                <div class="col-xl-6 d-none d-xl-block">
                                    <img
                                        src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-registration/img4.jpg"
                                        alt="Sample photo"
                                        class="img-fluid"
                                        style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;"
                                        />
                                </div>
                                <div class="col-xl-6">
                                    <div class="card-body p-md-5 text-black">
                                        <h3 class="mb-5 text-uppercase">User registration form</h3>

                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="form3Example1m" class="form-control form-control-lg" />
                                                    <label class="form-label" for="form3Example1m">Name</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="form3Example1n" class="form-control form-control-lg" />
                                                    <label class="form-label" for="form3Example1n">Phone</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="form3Example1m1" class="form-control form-control-lg" />
                                                    <label class="form-label" for="form3Example1m1">Address</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="form3Example1n1" class="form-control form-control-lg" />
                                                    <label class="form-label" for="form3Example1n1">Username</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="text" id="form3Example8" class="form-control form-control-lg" />
                                            <label class="form-label" for="form3Example8">Password</label>
                                        </div>

                                       

                                        

                                        <div class="d-flex justify-content-end pt-3">
                                            <button type="button" class="btn btn-light btn-lg">Reset all</button>
                                            <button type="button" class="btn btn-warning btn-lg ms-2">Submit form</button>
                                        </div>

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
