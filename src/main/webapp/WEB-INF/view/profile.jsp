<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.FixedHeightContainer
{
  float:right;
  height: 150px;
  width:250px; 
  padding:3px; 
    
}
.Content
{
  height:224px;
   overflow:auto;
    background:#fff;
}

</style>
<script >


</script>
</head>
<body>
<script>
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response); 
    if (response.status === 'connected') {
      testAPI();
    } else {
    window.location.href = "./facebook";
     conlose.log("Please log into this app.");
    }
  }
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '352873048540541',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
    });
    
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
  function testAPI() {
    FB.api('/me?fields=id,name,email', function(response) {
      //console.log('Successful login for: ' + response.name);
      console.log(response);
      });
  }
</script>

<div role="navigation">
		<div class="navbar navbar-inverse">
		
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/Profile2">Profile</a></li>
				 	<li><a href="/Post">Create Post</a></li> 
				 		<li><a href="/Post2">Show Friends Post</a></li>
				 		<li><a href="/showfrnd">View Friends</a></li>
				<li><a href="/EditProfile">Edit Profile</a></li>
				
				
				</ul>
				
            </div>
			</div>
		</div>
	<div class="fb-login-button" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false" onclick="checkLoginState()"></div>
<h1>${use.username}</h1>

	



<img src="${use.profileLink}" alt="Profile Page Image" width="250px" height="250px" />
	<h3>About me</h3>
	<h4>${use.details}</h4>

<div  class="FixedHeightContainer">
<div class="Content">
<h3>Notification</h3>
<c:forEach var="notifications" items="${notification}">
							
							
						<a href="/select-post?id=${notifications.postid }"><h4>	${notifications.notifica} </h4></a>
						
							
								
							</c:forEach>
							
				
				
						
					
					
					
				

</div>
</div>

							<c:forEach var="post" items="${posts}">
							
							
							
			<a href="/select-post?id=${post.id }">				
				<img src="${post.imageurl}" alt="Profile Page Image" width="200px" height="200px" /></a>
																	
		
								
							</c:forEach>
							
</body>
</html>
