<%@ page import="com.project.consultant.model.Template" %>
<%@ page import="com.project.consultant.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Update</title>
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
            <div class="row">
                <div class="col-md-12 p-5 text-center" style="border-radius: 10px; border:solid #2e9ba3 2px;">
                    <div class="text-center">
                        <h5>Edit your profile</h5>
                    </div>
                    <form action="http://localhost:9096/student/update" method="post" class="my-2 my-lg-0" onSubmit="return validate()">
                        <%
                            Student student = (Student) request.getAttribute("student");
                        %>
                        <input type = "hidden" id="id" name="id" placeholder="id" value=<%=student.getId() %> class="form-control mr-sm-2 mt-2"/>
                        <input type = "hidden" id="username" name="username" placeholder="Username" value=<%=student.getUsername() %> class="form-control mr-sm-2 mt-2"/>
                        <input type = "text" id="txtusername" name="txtusername" placeholder="Username" value=<%=student.getUsername() %> class="form-control mr-sm-2 mt-2" disabled/>
                        <input type = "password" id="password" name="password" placeholder="Password" value=<%=student.getPassword() %> class="form-control mr-sm-2 mt-2"/>
                        <input type = "password" id="rpassword" name="rpassword" placeholder="Retype Password" value=<%=student.getPassword() %> class="form-control mr-sm-2 mt-2"/>
                        <input type = "email" id="email" name="email" placeholder="Email" value=<%=student.getEmail() %> class="form-control mr-sm-2 mt-2"/>
                        <input type = "text" id="contact" name="contact" placeholder="Phone Number" value=<%=student.getContact() %> class="form-control mr-sm-2 mt-2"/>
                        <br/>
                        <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Update</button>
                    </form>
                </div>
            </div>
        </div>
</body>
</html>


<script>
    function validate(){
        var password = document.getElementById("password").value;
        var rpassword = document.getElementById("rpassword").value;
        if(password == rpassword){
            return true;
        }
        else{
            alert("Error: Password doesn't match");
            return false;
        }
    }
</script>