<%@page import="com.model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.model.CourseDAOImpl"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Course page</title>
<link rel=stylesheet href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel=stylesheet href='styles.css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#courseId").change(function(){
		var id=$(this).val();
		$.ajax({url: "findc?id="+id, success: function(result){
           var course=JSON.parse(result);
			$("#name").val(course.name);
            $("#duration").val(course.duration);
            
            switch(course.proficiency)
			{
			case "Beginner":
				$("#beginner").prop("checked",true);
				break;
			case "Intermediate":
				$("#intermediate").prop("checked",true);
				break;
			case "Advanced":
				$("#advanced").prop("checked",true);
				break;
			}
            $("#price").val(course.price);
            $("#description").val(course.description);
		   }});
		});
	});
		
</script>
</head>
<body>
<jsp:useBean id="cdao" class="com.model.CourseDAOImpl" />
<%
	List<Course> courses=cdao.read();
	session.setAttribute("courses", courses);
%>
<form action='course'>

<h2>Courses</h2>
<table>
<tr><td>Course Id:</td><td>
<select name="id" id="courseId" class="form-control"><option></option>
<c:forEach var="course" items="${courses }">
	<option 	<c:if test="${param.id eq course.id }"> selected </c:if> >${course.id }</option>
</c:forEach>
</select></td></tr>

<tr>
<td>Course Name</td>
<td><input type="text" class="form-control" name="name" id=name value="${param.name }" /></td>
</tr>

<tr>
<td>Level</td>
<td><input type="radio" name='level' id='beginner' /><label for=beginner>Beginner</label>
<input type="radio" name='level' id='intermediate' /><label for=intermediate>Intermediate</label>
<input type="radio" name='level' id='advanced' /><label for=advanced>Advanced</label></td>
</tr>

<tr>
<td>Duration</td>
<td><input type="number" class="form-control" name="duration" id='duration' value="${param.duration }" /></td>
</tr>

<tr>
<td>Price</td>
<td><input type="number" class="form-control" name="price" id='price' value="${param.price}" /></td>
</tr>

<tr>
<td>Description</td>
<td><textarea class="form-control" name="description" rows="3" cols="30" id='description' value="${param.description }" /></textarea></td>
</tr>

<tr>
<td>Logo</td>
<td><input type="file" class="form-control" name="logo" id='logo' /></td>
</tr>

<tr><td><input type=submit value="Add/Edit course" name='btn' /></td><td><input type=submit value="Delete" name="btn" /></td></tr>

</table>
</form><br/>

Displaying all courses in db
<table border="1">
<thead>
<th>Course ID</th><th>Name</th><th>Level</th><th>Duration</th><th>Price</th><th>Logo</th><th>Description</th><th></th><th></th>
</thead>
<c:forEach var="c" items="${courses }">
	<tr>
		<td>${c.id}</td>
		<td>${c.name }</td>
		<td>${c.proficiency }</td>
		<td>${c.duration }</td>
		<td>${c.price }</td>
		<td>${c.logo }</td>
		<td>${c.description }</td>
		
		<td><a href="Course.jsp?id=${s.id }
		&name=${c.name}&proficiency=${c.proficiency}&duration=${c.duration}&price=${c.price}&logo=${c.logo}&description={c.description}"
		>Edit</a></td>
		<td><a href="course?id=${c.id }">Delete</a></td>
	</tr>
</c:forEach>

</body>
</html>