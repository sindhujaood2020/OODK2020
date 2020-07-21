<html>
<head>

<title>Patient Enrollment Form</title>

<style type="text/css">
	h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:SlateBlue;
	text-align: center; text-decoration: underline }
	table{font-family: Calibri; color:white; font-size: 11pt; font-style: normal;width: 50%;
	text-align:; background-color: SlateBlue; border-collapse: collapse; border: 2px solid navy}
	table.inner{border: 0px}
</style>

<script language="javascript" type="text/javascript" src="./js/datetimepicker.js"></script>

</head>
<body>
<h3>Patient Enrollment Form</h3>
<form name="patientForm" action="addPatient" method="POST" id="patientForm">
<table align="center" cellpadding = "10">
<tr>
<td>First Name</td>
<td><input type="text" name="firstName" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>
<tr>
<td>Last Name</td>
<td><input type="text" name="lastName" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>
<tr>
<td>Email</td>
<td><input type="text" name="email" maxlength="100" /></td>
</tr>
<tr>
<td>Date of Birth</td>
<td>
<input readonly name="dob" id="demo1" type="text" size="25"><a href="javascript:NewCal('demo1','ddmmyyyy')"><img src="./images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
</td>
</tr>
<tr>
<td>Gender</td>
<td>
<input type="radio" id="male" name="gender" id="gender" value="male">
<label for="male">Male</label><br>
<input type="radio" id="female" name="gender" value="female">
<label for="female">Female</label><br>
<input type="radio" id="other" name="gender" value="other">
<label for="other">Other</label> 
</td>
</tr>
<tr>
<td>Contact number</td>
<td><input type="text" name="contactNumber" maxlength="100" /></td>
</tr>
<tr>
<td>Address</td>
<td><textarea name="address" id="address" form="patientForm" placeholder="Enter address here..."/></textarea>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</td>
</tr>
</table>
</form>
</body>
</html>