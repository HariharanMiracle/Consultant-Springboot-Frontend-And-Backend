<%@ page import="com.project.consultant.model.Template" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<% Template template = Template.getObject(); %>
        <nav class="navbar navbar-dark bg-info">
            <a class="navbar-brand" href="#"><%=template.getLogo() %></a>
            <div class="container text-center">
                <h3 class="text-white"><%=template.getHeading() %></h3>
                <div class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-white my-2 my-sm-0" type="submit">Search</button>
                </div>
            </div>
            <a href="http://localhost:9096/logout" class="btn btn-dark">Logout</a>
        </nav>

        <br/>
        <br/>
        <br/>

        <div class="container">
            <h1>Functions</h1>
            <div class="ml-5">
                <ul>
                    <li><a href = "http://localhost:9096/student/details">Details</a></li>
                    <li><a href = "http://localhost:9096/student/update">Update</a></li>
                    <li><button onclick="deleteFunction()" class="btn btn-link">Delete</button></li>
                    <li><a href = "http://localhost:9096/student/appointment/stage1">Make Appointment</a></li>
                    <li><a href = "http://localhost:9096/student/appointment/myappointment">View My Appointments</a></li>
                </ul>
            </div>
        </div>
</body>
</html>

<script>
    function deleteFunction() {
      var r = confirm("Confirm delete!");
      if (r == true) {
        window.location.href = "http://localhost:9096/student/delete";
      }
    }
</script>
