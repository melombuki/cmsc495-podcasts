<%--
  Created by IntelliJ IDEA.
  User: Vic
  Date: 6/14/2015
  Time: 5:40 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Help</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>

<!-- Nav tabs -->
<ul class="nav nav-tabs">
    <li class="active" data-toggle="tab">
        <a href="#podcasts" data-toggle="tab" onclick="this.blur()">
            Podcast Actions
        </a>
    </li>
    <li data-toggle="tab">
        <a href="#settings" data-toggle="tab" onclick="this.blur()">
            Settings
        </a>
    </li>
    <li data-toggle="tab">
        <a href="#logout" data-toggle="tab" onclick="this.blur()">
            Logging Out
        </a>
    </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
    <div class="tab-pane active" id="podcasts">
        <h3>Podcast Page</h3>
        <p>
        From the Podcast page, which is pictured above, you can view all of your current subscriptions, add new podcasts, delete old ones, and access all other pages within the Podcast App. Each of your current subscriptions will be listed in alphabetical order. For each subscription, the podcast image, title, author, a short description, and the latest episode are displayed on the page. Clicking on the title of a podcast subscription will take you to the Podcast Episodes page with all of the available episodes. From the Podcast page you can also navigate to the Settings page, Help page, or logout by clicking the corresponding tabs on the navigation bar.
        </p>

        <div class="panel-group" id="PodcastPageaccordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#PodcastPageaccordion" href="#PodcastPagecollapseOne">
                            Add a new subscription
                        </a>
                    </h4>
                </div>
                <div id="PodcastPagecollapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        New podcast subscriptions can be added using the “Add a subscription” form on the left side of the page. The Podcast URL must be a valid RSS/Atom URL for a podcast. Any podcast with a valid RSS/Atom URL can be added. To find podcast URLs, use any browser and search engine on a computer with internet access. To add a new podcast subscription:
                        <ol>
                            <li>Obtain a valid RSS/Atom podcast URL.</li>
                            <li>Copy and paste, or type the URL into the “Add a subscription” form as pictured to the right.</li>
                            <li>Click the “Subscribe” button below the podcast URL.</li>
                            <li>You should see the podcast added to your list of subscriptions.</li>
                        </ol>
                    </div>
                </div>
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#PodcastPageaccordion" href="#PodcastPagecollapseTwo">
                            Delete a subscription
                        </a>
                    </h4>
                </div>
                <div id="PodcastPagecollapseTwo" class="panel-collapse collapse">
                    <div class="panel-body">
                        You can remove any subscriptions to podcasts that you no longer want from the Podcast page. To delete a podcast subscription or multiple subscriptions at once from your account:
                        <ol>
                            <li>Click the “Edit” button above and to the left of the list of podcast subscriptions. Red delete icons, pictured to the right,  will appear to the left of each subscription's image.</li>
                            <li>Click the red delete icon next to the podcast subscription that you wish to remove.</li>
                            <li>Click the “Ok” button on the prompt to delete the podcast subscription. Click the “Cancel” button if you do not wish to delete the podcast subscription.</li>
                            <li>Repeat steps 2 and 3 for different podcast subscriptions if there you want to delete multiple podcasts at one time.</li>
                            <li>Click the “Done” button above and to the left of the list of podcast subscriptions when you are finished.</li>
                            <li>You have successfully deleted the podcast or podcasts. The podcast subscription(s) will be removed from the list of your podcasts.</li>
                        </ol>
                    </div>
                </div>
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#PodcastPageaccordion" href="#PodcastPagecollapseThree">
                            Play a Podcast Episode
                        </a>
                    </h4>
                </div>
                <div id="PodcastPagecollapseThree" class="panel-collapse collapse">
                    <div class="panel-body">
                        In order to play an episode from a podcast:
                        <ol>
                            <li>Scroll to the episode that you wish to listen to or watch.</li>
                            <li>Click on the title of the episode written in blue text on the left side of the episodes table.</li>
                            <li>You will be redirected to a page where you can listen to or watch the specific podcast episode.</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="tab-pane" id="settings">
        <h3>Settings Page</h3>
        <p>
        The Settings page allows you to change your registered email address and password. There are two different forms presented in the middle of the page to edit each setting respectively. Your currently registered email address will appear in the box under the heading “Update your email here.” Your password will not be displayed to ensure its security. The Settings page is shown below.
        </p>
        <div class="panel-group" id="Settingsaccordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#Settingsaccordion" href="#SettingscollapseOne">
                            Change Registered Email Address
                        </a>
                    </h4>
                </div>
                <div id="SettingscollapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        To change your registered email address:
                        <ol>
                            <li>Click in the box that displays your currently registered email address under the heading “Update your email here.”</li>
                            <li>Enter the email address that you wish to change to.</li>
                            <li>Click the “Update” button below the box or press enter on your keyboard.</li>
                            <li>If there are any errors, you will be notified before any changes take place. Fix any errors and repeat steps 2 and 3.</li>
                            <li>You have successfully updated your email address. Use this new email address on your next login.</li>
                        </ol>
                    </div>
                </div>
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#Settingsaccordion" href="#SettingscollapseTwo">
                            Change Login Password
                        </a>
                    </h4>
                </div>
                <div id="SettingscollapseTwo" class="panel-collapse collapse">
                    <div class="panel-body">
                        To change your login password:
                        <ol>
                            <li>Click in the first box under the heading “Update your password here.” You will the the word “Password” in the first box.</li>
                            <li>Enter your new password. The password must not be blank.</li>
                            <li>Once you have entered your new password, click in the second box, directly below the first with the words “Retype Password” in it.</li>
                            <li>Enter the same password that you typed in the first box. It must match exactly.</li>
                            <li>Click the “Update” button below the second box or press enter on your keyboard.</li>
                            <li>If there are any errors, you will be notified before any changes take place. Fix any errors and repeat steps 2 through 5.</li>
                            <li>You have successfully updated your email address. Use this new email address on your next login.</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="tab-pane" id="logout">
        <h3>Logging Out</h3>
        <p>
        You can logout from any page within the Podcast applicaiton. After logging out, you will be redirected to the Login page.
        </p>
        <div class="panel-group" id="Logoutaccordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#Logoutaccordion" href="#LogoutcollapseOne">
                            Logout
                        </a>
                    </h4>
                </div>
                <div id="LogoutcollapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        To logout of the Podcast application:
                        <ol>
                            <li>Click the “Logout” button at the top of the Podcast page.</li>
                            <li>You have successfully logged out of the Podcast applicaiton.</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>