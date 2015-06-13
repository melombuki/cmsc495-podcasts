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

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="Tom, Ben, Josh">

    <title>Podcasts</title>

    <style>
        .image-rounded {
            width: 140px;
            height: 140px;
        }
    </style>

</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="#">Add a new subscription</a></li>
                <li><a href="#">Delete a subscription</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-md-10 main">
            <h1 class="page-header">Subscriptions</h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Title</th>
                        <th>Author</th>
                        <th style="width:50%;">Description</th>
                        <th>Latest Episode</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${podcasts}" var="podcast">
                        <tr>
                            <td>
                                <%-- TODO: add link to episode list for this podcast when episode list screen exists --%>
                                <g:fieldValue bean="${podcast}" field="title" /> <br/>
                                <small><g:fieldValue bean="${podcast}" field="subtitle" /></small>
                            </td>
                            <td> <g:fieldValue bean="${podcast}" field="author" /> </td>
                            <td>
                                <g:fieldValue bean="${podcast}" field="description" />
                            </td>
                            <td>
                                <g:set var="episode" value="${podcast.newestEntry}"/>
                                <%--TODO: add link to play screen for this entry when the play screen exists --%>
                                <g:fieldValue bean="${episode}" field="title" />
                            </td>
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