<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>

<style>


h3 {
    color: blue;
}
</style>
</head>
<body>
<div role="navigation">
		<div class="navbar navbar-inverse">
		
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/Profile2">Profile</a></li>
				 	<li><a href="#">Create Post</a></li> 
				 		<li><a href="/Post2">See Post</a></li>
				
				</ul>
				<div class="nav navbar-nav navbar-right">
            
            </div>
			</div>
		</div>
	</div>

<img src="${post.imageurl}" alt="Profile Page Image" width="300px" height="300px" />
<audio autoplay>
<source src="${post.audiourl}" type="audio/webm" />


</audio>
<h3>${post.descpt}</h3>
<c:forEach var="comment" items="${comment}">
							
							
							
	<h3>${ comment.commentuser}</h3>						<h4>	${comment.comment} </h4>
							
								
							</c:forEach>

<form action="/comment" method = "GET">


<input type="hidden"  name="postid" value="${post.id}">


<input type="text" name="comment" />
 <input type="submit" id="submit" />
</form >

</body>
</html>