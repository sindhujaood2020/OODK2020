<html>
<head>
<title>Smart Doc Service</title>
<link rel="stylesheet" type="text/css" href="./css/basestyles.css" />
</head>
<body onload="document.getElementById('id01').style.display='block'">
<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'"
class="close" title="Close Modal">&times;</span>

  <!-- Modal Content -->
  <form class="modal-content animate" name="loginForm" method="post" action="userLogin">
    <div class="imgcontainer">
      <img src="./images/SmartDoc_log.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
      <label for="userId"><b>User Name</b></label>
      <input type="text" placeholder="Enter Username" name="userId" required>

      <label for="password"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" required>

      <button type="submit">Login</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">New User ? <a href="userenrollment.jsp">Click here</a> to register</span>
    </div>
  </form>
</div>
</body>
</html>