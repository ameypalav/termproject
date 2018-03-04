<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function required()
{
var empt = document.forms["file1"]["file"].value;
if (empt == ""  || ept==null)
{
alert("Please input a Value");
return false;
}
else 
{
alert('Code has accepted : you can try another');
return true; 
}
}
</script> 
</head>
<body>
<h3>Upload an Image</h3>
<form action="/upload" method="POST" name="file1" enctype="multipart/form-data">

<input type="file" name="file" id="uploadImage"/>
 <input type="submit" id="upload" />
</form  >

</body>
</html>