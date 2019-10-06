<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
<title>aMEY</title>
<style>


h3 {
    color: blue;
}
</style>
</head>
<body>
<a href="/delete2-post?id=${post.id }">	<h3>Delete</h3> </a>
<h3>${post.descpt }</h3>

<img src="${post.imageurl}" alt="Profile Page Image" width="300px" height="300px" />
<audio autoplay>
<source src="${post.audiourl}" type="audio/webm" />

</audio>
<c:forEach var="comment" items="${comment}">
							
							
							
	<h3>${ comment.commentuser}</h3>						<h4>	${comment.comment} </h4>
							
								
							</c:forEach>


</body>
</html>