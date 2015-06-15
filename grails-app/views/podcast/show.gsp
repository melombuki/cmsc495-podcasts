<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 6/15/15
  Time: 10:54 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Podcasts</title>

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="Tom, Ben, Josh">

    <title>${subscription.podcast.title}</title>

    <style>
        .image-rounded {
            width: 256px;
            height: 256px;
        }

        h1 {
            text-align: center;
        }
    </style>

</head>

<body>
    <h1>
        <g:fieldValue bean="${subscription.podcast}" field="title" />
    </h1>
    <div style="text-align: center;">
        <img src="<g:fieldValue bean="${subscription.podcast}" field="image" />" alt="cover" class="image-rounded">
    </div>


    <h2 class="page-header">Episodes</h2>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Title</th>
                <th>Summary</th>
                <th>Duration</th>
                <th>Published</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${subscription.podcast.entries.sort { -it.publishedDate.time }}" var="entry">
                <tr>
                    <td>
                        <%-- TODO: add link to play episode for this podcast when play episode screen exists --%>
                        <g:fieldValue bean="${entry}" field="title" /> <br/>
                    </td>
                    <td> <g:fieldValue bean="${entry}" field="summary" /> </td>
                    <td>
                        <podcast:duration entry="${entry}" />
                    </td>
                    <td>
                        <g:formatDate date="${entry.publishedDate}" format="MM/dd/yyyy" />
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>



</body>
</html>