<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<div class="navbar navbar-inverse">
		
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/Profile2">Profile</a></li>
				 	
				
				</ul>
				<div class="nav navbar-nav navbar-right">
             
            </div>
			</div>
		</div>
</head>
<body>
<img src="${use.profileLink}" alt="Profile Page Image" width="250px" height="250px" /><h3>${use.details}</h3>

							<c:forEach var="post" items="${posts}">
							
							
							
			<a href="/select-post?id=${post.id }">				
				<img src="${post.imageurl}" alt="Profile Page Image" width="200px" height="200px" /></a>
																	
		
								
							</c:forEach>

</body>
</html>