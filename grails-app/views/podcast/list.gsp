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
    <meta name="description" content="">
    <meta name="author" content="Tom, Ben, Josh">

    <title>Podcasts</title>

    <style>
        .image-rounded {
            width: 140px;
            height: 140px;
        }
    </style>

    <script type="application/javascript">
        function toggleDelete() {
            $('#editButton').toggle();
            $('#doneButton').toggle();

            $('#deleteColumnHeader').toggle();
            $('.deleteField').toggle();
        }
    </script>

</head>

<body>

<g:if test="${flash.error}">
    <div class="alert alert-danger">
        ${flash.error}
    </div>
</g:if>

<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
            <li>
                <g:form action="add" method="post" class="form-signin">
                    <h5 class="form-signin-heading">Add a subscription</h5>
                    <input type="podcastUrl" name="podcastUrl" id="podcastUrl" class="form-control" placeholder="Enter a Podcast URL" required autofocus>
                    <button class="btn btn-sm btn-primary btn-block" type="submit">Subscribe</button>
                </g:form>
            </li>
        </ul>
    </div>
    <div class="col-sm-9 col-md-10 main">
        <h1 class="page-header">Subscriptions</h1>
        <div>
            <button id="editButton" class="btn btn-default" onclick="toggleDelete();">Edit</button>
            <button id="doneButton" style="display:none;" class="btn btn-default" onclick="toggleDelete();">Done</button>
        </div>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th id="deleteColumnHeader" style="display:none;"></th>
                        <th></th>
                        <th>Title</th>
                        <th>Author</th>
                        <th style="width:50%;">Description</th>
                        <th>Latest Episode</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${subscriptions}" var="subscription">
                        <tr>
                            <td class="deleteField" style="display:none;">
                                <g:link action="delete" id="${subscription.id}" onclick="return confirm('Are you sure you want to delete your subscription to ${subscription.podcast.title}?');"><asset:image src="sign-delete-icon.png" width="48" height="48" /></g:link>
                            </td>
                            <td> <img src="<g:fieldValue bean="${subscription.podcast}" field="image" />" alt="cover" class="image-rounded"> </td>
                            <td>
                                <g:link action="show" id="${subscription.id}"><g:fieldValue bean="${subscription.podcast}" field="title" /></g:link>
                            </td>
                            <td> <g:fieldValue bean="${subscription.podcast}" field="author" /> </td>
                            <td>
                                <g:fieldValue bean="${subscription.podcast}" field="description" />
                            </td>
                            <td>
                                <g:set var="episode" value="${subscription.podcast.newestEntry}"/>
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

</body>
</html>