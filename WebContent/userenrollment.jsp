<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit],input[type=reset] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit],input[type=reset]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>

<h3>User Enrollment Form</h3>

<% 
	String errorMessage = "";
	if(request.getAttribute("message")!=null){
		errorMessage = (String)request.getAttribute("message");
	}
%>

<div class="container">
  <form action="userEnrollment" name="enrollmentForm" method="POST">
  
  	<h3><%=errorMessage%></h3>
  
    <label for="firstName">First Name</label>
    <input type="text" id="fname" name="firstName" placeholder="Your first name..">

    <label for="lastName">Last Name</label>
    <input type="text" id="lname" name="lastName" placeholder="Your last name..">

    <label for="email">Email</label>
    <input type="text" name="email" maxlength="100"  placeholder="Your email address..">
    
    <label for="dob">Date of Birth</label>
    <input type="text" type="text" placeholder="dd/mm/yyyy" id="dob" name="dob">
    
    <label for="userId">User ID</label>
    <input type="text" type="text" name="userId" maxlength="100"  placeholder="Desired User Id..">

    <label for="password">Password</label>
    <input type="text" type="password" name="password" maxlength="100"  placeholder="password..">

    <label for="confPassword">Re-type Password</label>
    <input type="text" type="password" name="confPassword" maxlength="100"  placeholder="re-type password..">

    <input type="submit" name="ActionButton" value="Submit">
    <input type="reset" name="ActionButton" value="Reset" onclick='document.getElementById("enrollmentForm").reset();'>
    <input type="submit" name="ActionButton" value="Cancel" title="Back to Home">
  </form>
</div>

</body>
</html>
