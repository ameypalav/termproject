<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
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
<div role="navigation">
		<div class="navbar navbar-inverse">
		
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="Record">Profile</a></li>
				 	<li><a href="/show-users">Show Users Post</a></li> 
				 	
				
				</ul>
				<div class="nav navbar-nav navbar-right">
               <a href="/logout" class="navbar-brand"><strong>Logout</strong></a>
            </div>
			</div>
		</div>
	</div>
</head>
<body>
	<div class="container text-center" id="tasksDiv">
			
				<hr>
				<div class="table-responsive">
				<h3> All Posts</h3>
				
						
					
							<c:forEach var="pt" items="${post}">
							
							
							
			<a href="/select2-post?id=${pt.id }">					<img src="${pt.imageurl}" alt="Profile Page Image" width="300px" height="300px" /></a>
																	
							 		
									
			</a>
							<!--  <td> <a href="/edit-user?id=${event.eventid }">Edit</a></td> -->
								
							</c:forEach>
					
					
				</div>
			</div>
</body>
</html>