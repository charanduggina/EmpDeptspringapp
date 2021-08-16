<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
      hi testing here ${obj.empid} , ${obj.firstname} ,${obj.lastname}
      <%-- using jstl here--%>
      <form action="addemployee">
          <label for="fname">emp id</label><br>
          <input type="text" name="empid"><br>
        <label for="fname">First name:</label><br>
        <input type="text"  name="firstname"><br>
        <label for="lname">Last name:</label><br>
        <input type="text" name="lastname">
         <input type="submit"><br>
      </form>
      <br>
       <form action="getemployee">
                <label for="fname">emp id</label><br>
                <input type="text" name="empid"><br>
               <input type="submit"><br>
            </form>


</body>
</html>