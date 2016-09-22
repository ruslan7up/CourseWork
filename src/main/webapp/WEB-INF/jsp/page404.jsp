<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.09.2016
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="error-page">
<head>
    <title>Ошибка 404</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link rel="stylesheet" media="screen" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="/resources/css/bootstrap-theme.min.css">

    <!-- Bootstrap Admin Theme -->
    <link rel="stylesheet" media="screen" href="/resources/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" media="screen" href="/resources/css/bootstrap-admin-theme-change-size.css">

    <!-- Bootstrap Error Page -->
    <link rel="stylesheet" media="screen" href="/resources/css/bootstrap-error-page.css">
</head>
<body>

<!-- content -->
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-lg-12">
            <div class="centering text-center error-container">
                <div class="text-center">
                    <h2 class="without-margin">Ошибка <span class="text-warning"><big>404</big></span></h2>
                    <h4 class="text-warning">Page not found</h4>
                </div>
                <div class="text-center">
                    <h3><small>Choose an option below</small></h3>
                </div>
                <hr>
                <ul class="pager">
                    <li><a href="about.html">&larr; About</a></li>
                    <li><a href="dashboard.html">Dashboard</a></li>
                    <li><a href="ui-and-interface.html">UI & Interface</a></li>
                    <li><a href="error-pages.html">Other error pages &rarr;</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<div class="navbar navbar-footer navbar-fixed-bottom">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <footer role="contentinfo">
                    <p class="left">Bootstrap 3.x Admin Theme</p>
                    <p class="right">&copy; 2013 <a href="http://www.meritoo.pl" target="_blank">Meritoo.pl</a></p>
                </footer>
            </div>
        </div>
    </div>
</div>

</body>
</html>
