<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
var el = document.getElementById('click');
el.onclick = showFoo;


function showFoo() {
  alert('I am link click!');
  return false;
}
	function fn(frm)
	{
		var username=document.getElementById("username").value;
		var otp=document.getElementById("otp").value;
		var secret=document.getElementById("secret").value;

//		alert(username);
		if(username=="" || otp=="" || secret=="")
		{
	//		alert('user name is blank')
			return false;
		}else
		{
			frm.submit();			
		}
//		alert("form is submitting");

	
	}
	function generateOtp()
	{
		//call generate servlet
		var email=document.getElementById("email").value;
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		      //document.getElementById("demo").innerHTML =
		      //alert(this.responseText);
		      document.getElementById("secret").value=this.responseText;
		      document.getElementById("otpMsg").innerHTML="<font color=green>OTP sent to your email address</font>";
		      document.getElementById("otp").focus();
		    }
		  };
		  xhttp.open("GET", "generate?email="+email, true);
		  xhttp.send();
	}
</script>
</head>
<body>
<form method="POST" action="student" onsubmit="event.preventDefault(); return fn(this)">
User Name: <input type=text id=username name=username /><br/>
Email: <input type=email id=email name=email /><br/>
Enter otp: <input type=text id=otp name=otp />
<input type=button value="Generate OTP" id=gotp onclick="generateOtp()" />
<!-- <a href="" id='click' onclick="generateOtp()">Click here</a> -->
<div id="otpMsg"></div><br/>
<input type=hidden id=secret name=secret />
<input type=submit />
</form>
</body>
</html>