<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.model.StudentDAOImpl"%>
<%@page import="com.model.DepartmentDAOImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Department"%>
<%@page import="com.model.Student"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel=stylesheet href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#studentId").change(function(){
		var id=$(this).val();
		$.ajax({url: "finds?id="+id, success: function(result){
           // $("#div1").html(result);
           //alert(result);
           //i am getting a json string as result. to convert json string into js object
           //JSON.stringify()		JSON.parse()
           var student=JSON.parse(result);
//populate the values to the ui
			$("#name").val(student.name);
			$("#dob").val(student.dob);
			switch(student.gender)
			{
			case "Female":
				$("#female").prop("checked",true);
				break;
			case "Male":
				$("#male").prop("checked",true);
				break;
			case "Others":
				$("#others").prop("checked",true);
				break;
			}
			//department  <option selected>
			$("#departmentId").val(student.departmentId);
        }});
	});
});
</script>

</head>
<body>
<jsp:useBean id="sdao" class="com.model.StudentDAOImpl" />
<jsp:useBean id="ddao" class="com.model.DepartmentDAOImpl" />
<%
	List<Student> students=sdao.read();
	session.setAttribute("students", students);
	List<Department> departments= ddao.read();
	session.setAttribute("departments", departments);
%>

<form action="student">
<table>
<tr><td>Student Id:</td><td>
<select name="id" id="studentId"><option></option>
<c:forEach var="student" items="${students }">
	<option 	<c:if test="${param.id eq student.id }"> selected </c:if> >${student.id }</option>
</c:forEach>
</select></td></tr>

<tr><td>Name:</td><td><input type=text name=name id=name value="${param.name }" /></td></tr>
<tr><td>Date Of Birth:</td><td><input type=date name=dob id=dob value="${param.dob }" /></td></tr>
<tr><td>Gender:</td><td>
<input type=radio value="Female" name=gender id=female 	<c:if test="${param.gender eq 'Female' }"> checked </c:if> /><label for=female>Female</label>
<input type=radio value="Male" name=gender id=male <c:if test="${param.gender eq 'Male' }"> checked </c:if> /><label for=male>Male</label>
<input type=radio value="Others" name=gender id=others <c:if test="${param.gender eq 'Others' }"> checked </c:if> /><label for=others>Others</label>
</td></tr>
<tr><td>Department:</td><td>
<select name="departmentId" id="departmentId">
	<option>Choose...</option>
<c:forEach var="department" items="${departments }">
	<option value="${department.id }" <c:if test="${param.departmentId eq department.id }"> selected </c:if> >${department.name }</option>
</c:forEach>
</select>
</td></tr>
<tr><td><input type=submit value="Add/Update" name="btn" /></td><td><input type=submit value="Delete" name="btn" /></td></tr>
</table>
</form>
Displaying all students in db
<table border="1">
<thead>
<th>Student ID</th><th>Name</th><th>Date of Birth</th><th>Gender</th><th>Department</th><th></th><th></th>
</thead>
<c:forEach var="s" items="${students }">
	<tr>
		<td>${s.id}</td>
		<td>${s.name }</td>
		<td>${s.dob1 }</td>
		<td>${s.gender }</td>
		<td>${s.departmentId }</td>
		<td><a href="Student.jsp?id=${s.id }
		&name=${s.name}&dob=${s.dob1}&departmentId=${s.departmentId}&gender=${s.gender}"
		>Edit</a></td>
		<td><a href="student?id=${s.id }">Delete</a></td>
	</tr>
</c:forEach>

</body>
</html>