<%--
  Created by IntelliJ IDEA.
  User: melombuki
  Date: 6/10/15
  Time: 11:57 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Podcasts</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="Tom, Ben, Josh">

    <title>Podcasts</title>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <br />
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Add a new subscription</a></li>
                <li><a href="#">Delete a subscription</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-md-10 main">
            <br />
            <h1 class="page-header">Subscriptions</h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${podcasts}">
                        <tr>
                            <td> ${it.title} </td>
                            <td> ${it.author} </td>
                            <td> ${it.subtitle} </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>