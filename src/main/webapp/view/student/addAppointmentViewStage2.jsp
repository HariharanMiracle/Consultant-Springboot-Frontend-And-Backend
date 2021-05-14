<%@ page import="com.project.consultant.model.Template" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.project.consultant.model.Consulttime" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Details</title>
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
                <div class="col-md-12 p-5" style="border-radius: 10px; border:solid #2e9ba3 2px;">
                    <h3>Appointment Form</h3>
                    <form action="http://localhost:9096/appointment/makeAppointment" method="post" class="my-2 my-lg-0" onSubmit="return validate()">
                        <%
                            String error = (String) request.getAttribute("error");
                            int consultantid = (Integer) request.getAttribute("id");
                            if(error == null || error.equals("")){
                                error="";
                            }
                        %>
                        <p class="text-danger"><%=error %></p>
                        <input type = "hidden" id="consultantid" name="consultantid" placeholder="consultantid" value=<%=consultantid %> class="form-control mr-sm-2 mt-2"/>
                        <input type = "number" id="hh" name="hh" placeholder="Hour" class="form-control mr-sm-2 mt-2"/>
                        <input type = "number" id="mm" name="mm" placeholder="Minitues" class="form-control mr-sm-2 mt-2"/>
                        <input type = "number" id="hours" name="hours" placeholder="Time gap" class="form-control mr-sm-2 mt-2"/>
                        <input type = "date" id="date" name="date" class="form-control mr-sm-2 mt-2"/>
                        <br/>
                        <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Make appointment</button>
                    </form>
                </div>
            </div>
            <p>Click here to go to <a href="http://localhost:9096/student/home">home</a></p>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-12 p-5 text-center" style="border-radius: 10px; border:solid #2e9ba3 2px;">
                        <ul><%
                            List<Consulttime> consulttimeListView = (List<Consulttime>) request.getAttribute("consulttimeList");
                            for(Consulttime obj : consulttimeListView){
                                %>
                                    <li><h5> <span>Date: <%=obj.getDate() %></span> || <span>From Time: <%=obj.getFromtime() %></span> || <span>Hours: <%=obj.getHours() %></span> </h5></li>
                                <%
                            }
                        %></ul>
                </div>
            </div>
        </div>
</body>
</html>