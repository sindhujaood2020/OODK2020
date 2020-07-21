<html>
<head>

<title>Slot Enrollment Form</title>

<style type="text/css">
	h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:SlateBlue;
	text-align: center; text-decoration: underline }
	table{font-family: Calibri; color:white; font-size: 11pt; font-style: normal;width: 50%;
	text-align:; background-color: SlateBlue; border-collapse: collapse; border: 2px solid navy}
	table.inner{border: 0px}
</style>

<script type="text/javascript" src="availableSlots.json"></script>

<script>

	var ctx = '<%=request.getContextPath()%>';
	
	var userdata = JSON.parse(availableSlots); 

	function getSpecializations() 
	{
		var specDD = document.getElementById("specialization");
		
	    var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState == 4) {
	            var data = xhr.responseText;
	            
	            var specsList = JSON.parse(data);
	            
	            for (var i = 0; i < specsList.length; i++) {
	                var option = document.createElement("OPTION");
	 
	                option.innerHTML = specsList[i];
	                option.value = specsList[i];
	 
	                //Add the Option element to DropDownList.
	                specDD.options.add(option);
	            }
	        }
	    }
	    xhr.open('GET', ctx+"/getSpecializations", true);
	    xhr.send(null);
    }
	
	function findDoctors(specialization)
	{
		var doctorDD = document.getElementById("doctorName");
		for (var i=0; i<doctorDD.length; i++) {
		    if (doctorDD.options[i].innerHTML != 'Select...')
		    	doctorDD.remove(i);
		}
		
	    var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState == 4) {
	            var data = xhr.responseText;
	            
	            var docList = JSON.parse(data);
	            
	            for (var i = 0; i < docList.length; i++) {
	                var option = document.createElement("OPTION");
	 
	                option.innerHTML = docList[i].first_name+" "+docList[i].last_name;
	                option.value = docList[i].doctor_id;
	 
	                //Add the Option element to DropDownList.
	                doctorDD.options.add(option);
	            }
	        }
	    }
	    xhr.open('GET', ctx+"/getDoctors?specialization="+specialization+"", true);
	    xhr.send(null);
	}
	
	function setPatientName(obj){
		document.getElementById("patient_id").value = obj.value;
		document.getElementById("patientName").value = obj.title;
	}
	
	function deleteRows(id, toDeleteHeader) {
	      var obj = document.getElementById(id);
	      if(!obj && !obj.rows)
	        return;
	      if(typeof toDeleteHeader == 'undefined')
	        toDeleteHeader = false;
	      var limit = !!toDeleteHeader ? 0 : 1;
	      var rows = obj.rows; 
	      if(limit > rows.length)
	        return;
	      for(; rows.length > limit; ) {
	        obj.deleteRow(limit);
	      }
	    }
	
	function findPatients()
	{
		var firstName = document.getElementById("firstName").value;
		var lastName = document.getElementById("lastName").value;
		
		var patientsTable = document.getElementById("PatientsList");
		
		deleteRows("PatientsList", false);
		
	    var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() 
	    {
	        if (xhr.readyState == 4) {
	            var data = xhr.responseText;
	            
	            var patientsList = JSON.parse(data);
	            
	            for (var i = 0; i <patientsList.length; i++) 
	            {
	            	// Create an empty <tr> element and add it to the 1st position of the table:
	            	var row = document.createElement("tr");

	            	// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
	            	var cell1 = document.createElement("td");
	            	var cell2 = document.createElement("td");
	            	var cell3 = document.createElement("td");
	            	
	            	var patientId = patientsList[i].patient_id;
	            	var patientName = patientsList[i].first_name+" "+patientsList[i].last_name;

	                var radioInput = document.createElement("input");
	                radioInput.setAttribute("type", "radio");
	                radioInput.setAttribute("name", "patientSelect");
	                radioInput.setAttribute("value", patientId);
	                radioInput.setAttribute("title", patientName);
	                radioInput.setAttribute("id", patientId);
	                radioInput.setAttribute("onchange", "setPatientName(this)");
	                
	            	// Add some text to the new cells:
	            	cell1.appendChild(radioInput);
	            	cell2.appendChild(document.createTextNode(patientId));
	            	cell3.appendChild(document.createTextNode(patientName));
	            	
	            	row.appendChild(cell1);
	            	row.appendChild(cell2);
	            	row.appendChild(cell3);
	            	
	            	patientsTable.appendChild(row);
	            }
	        }
	    }
	    xhr.open('GET', ctx+"/findPatients?firstName="+firstName+"&lastName="+lastName, true);
	    xhr.send(null);
	}
	
	function readTextFile(file, callback) {
	    var rawFile = new XMLHttpRequest();
	    rawFile.overrideMimeType("application/json");
	    rawFile.open("GET", file, true);
	    rawFile.onreadystatechange = function() {
	        if (rawFile.readyState === 4 && rawFile.status == "200") {
	            callback(rawFile.responseText);
	        }
	    }
	    rawFile.send(null);
	}
	
	function getAvailability()
	{
		ctx = '<%=request.getContextPath()%>';
		
		var doctorObj = document.getElementById("doctorName");
		var doctorID = doctorObj.options[doctorObj.selectedIndex].value;
		var patientId = document.getElementById("patient_id").value;
		var date = document.getElementById("demo1").value;
		var time = document.getElementById("slot_time").value;
		var timeObj = document.getElementById("slot_time");
		var time = timeObj.options[timeObj.selectedIndex].value;
		
		var xhr1 = new XMLHttpRequest();
	    xhr1.onreadystatechange = function() 
	    {
	        if (xhr1.readyState == 4) 
	        {
	            var data = "";
	            data = xhr1.responseText;
	            alert("Slot availability : "+data);
	        }
	    }
	    xhr1.open('GET', ctx+"/getAvailability?doctorId="+doctorID+"&date="+date+"&time="+time+"&Action=GetAvailability", true);
	    xhr1.send(null);

	}

	
	
	function loadDefaults()
	{
		getSpecializations();

		readTextFile(ctx+"/availableSlots.json", function(text){
		    var data = JSON.parse(text);

		    var slotTimeDD = document.getElementById("slot_time");
		    
		    for (var i = 0; i < data.slots.length; i++) {
                var option = document.createElement("OPTION");
 
                option.innerHTML = data.slots[i];
                option.value = data.slots[i];
 
                //Add the Option element to DropDownList.
                slotTimeDD.options.add(option);
            }
		});
	}
</script> 

<script language="javascript" type="text/javascript" src="./js/datetimepicker.js"></script>
      
</head>
<body onload='loadDefaults()'>
<h3>Slot Enrollment Form</h3>
<form name="slotEnrollForm" action="addSlot" method="POST" id="slotEnrollForm">


<table align="center" cellpadding = "10">
<tr>
<td>Enter Patient Name</td>
<td><input type="text" name="firstName" id="firstName" placeholder="first name"><input placeholder="last name"type="text" name="lastName" id="lastName"></td>
</tr>
<tr>
<td></td>
<td><input type="button" name="findPatient" id="findPatient" value="Find Patient" title="findPatient" onclick="findPatients()"></td>
</tr>
</table>

<table align="center" cellpadding = "10" id="PatientsList">
<tr><td>Select</td><td>Patient Id</td><td>Patient Name</td></tr>
</table>


<table align="center" cellpadding = "10">
<tr>
<td>Patient Name</td>
<td><input type="text" name="patientName" id="patientName" readonly><input type="hidden" name="patient_id" id="patient_id" value=""></td>
</tr>
<tr>
<td>Specialization</td>
<td><Select name="specialization" id="specialization" onchange="findDoctors(this.value)" >
<option value="">Select...</option>
</Select></td>
</tr>
<tr>
<td>Doctor Name</td>
<td>
<Select name="doctor_id" id="doctorName">
<option value="">Select...</option>
</Select>
</td>
</tr>
<tr>
<td>Slot Date</td>
<td><input readonly name="slot_date" id="demo1" type="text" size="25"><a href="javascript:NewCal('demo1','ddmmyyyy')"><img src="./images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td>
</tr>
<tr>
<td>Slot Time</td>
<td><select name="slot_time" id="slot_time" >
<option value="Select..">Select...</option>
</Select>
</td>
</tr>
<tr>
<td></td>
<td><input type="button" name="GetAvailability" id="GetAvailability" title="Get Availability" value="Get Availability" onclick="javascript:getAvailability();"/></td>
</tr>

<tr>
<td colspan="2" align="center">
<input type="submit" value="Submit" id="SubmitSlot">
<input type="reset" value="Reset">
</td>
</tr>
</table>
</form>
</body>
</html>