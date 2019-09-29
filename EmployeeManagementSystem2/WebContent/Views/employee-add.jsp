<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<body>
  <div class="container">
   <h1>Add/Edit Employee Details</h1>
   <hr/>
   <div class="row">
	     <div class="col-md-4">
		     <form action="${pageContext.request.contextPath}/EmployeeController" method="POST">
			       <div class="form-group">
			         <input type="text" name="firstname" placeholder="Enter your Name" value="${employee.name}" class="form-control"/> <br/>
			       </div>
				    <div class="form-group">
				      <input type="date" name="dob" value="${employee.dob}" class="form-control"/> <br/>
				    </div>
				    <div class="form-group">
		              <input type="text" name="department" placeholder="Enter your Department" value="${employee.department}" class="form-control"/> <br/>		       
		 		    </div>
				    <input type="hidden" name="id" value="${employee.id}"/>
				    <button class="btn btn-primary" type="submit">Save Employee</button>
		     </form>
	     </div>
   </div>
  </div> 
</body>
</html>