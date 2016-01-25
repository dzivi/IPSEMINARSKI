<%@page import="DAOclasses.PostDAO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.HashMap"%>
<%@page import="DAOclasses.UserDAO"%>
<%@page import="DAOclasses.FriendShipDAO"%>
<%@page import="classes.Box"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes.User"%>
<%@page import="classes.Validate"%>
<%@page import="DAOclasses.MessageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include file="functions.jsp" %>

<%
HttpSession sesion = request.getSession();
User logged = (User) sesion.getAttribute("logged");
ArrayList<User> searchList = (ArrayList<User>) request.getAttribute("searchList");

FriendShipDAO fd = new FriendShipDAO();
%>


<!DOCTYPE html>
<html>
<head>
<%if (logged==null){
	response.sendRedirect("index.jsp");
	} else { %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit profile</title>

<!-- Call favicon for page-->
<link rel="icon" href="images/ico/logo.png" type="image/x-icon" />


<!-- call css -->
<link rel="stylesheet" type="text/css" href="css/styletest.css">

<!-- call jQ google api -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> 

<!-- call functions.js jQuery functions -->
<script type="text/javascript" src="js/functions.js"></script>

</head>


<body>
	<div id="maincontainer">
		<div id="headercontainer">
		
		<%
			
			//generate friendship object
			//get number of request for friendship
			FriendShipDAO f = new FriendShipDAO();
			int countReq=0;
			countReq=f.numOfRequests(logged.getUserID());
			
					
			//generate object for messages 
			MessageDAO messag=new MessageDAO();
			int countMessag=0;
			countMessag=messag.countNotifications(logged.getUserID());
			
			
			%>
		
			<div id="header">
				<div id="leftheaderbox">
					<form action="Search" method="get">
						<input type="text" id="searchname" name="searchname"
							placeholder="Search..."> <input type="submit"
							id="searchbutton" name="searchbutton" value="Search">
					</form>
				</div>
				<div id="rightheaderbox">
					<div class="headersmallbox">
						<a href="Profile?opt=profile&userid=<%=logged.getUserID()%>"><%=logged.getFirstName() %></a>
						<input type="hidden" id="userID" value="<%=logged.getUserID()%>">
					</div>
					<div class="headersmallbox">
						<a href="home.jsp">Home</a>
					</div>
					<div class="headersmallbox">
						<a href="requests.jsp">Requests
							<%if(countReq!=0){%><span>(<%=countReq %>)</span><%}%>
						</a>
					</div>
					<div class="headersmallbox">
						<a href="messages.jsp">Messages
								<%if(countMessag!=0){%><span class='countMess'>(<%=countMessag %>)</span><%}%>			
						</a>
					</div>
					<div class="headersmallbox">
						<a href="LogSignIn?userID=<%=logged.getUserID()%>">LogOut</a>
					</div>
				</div>
			</div>
		</div>
	
		<!-- picture header -->
		<div id="contentcontainer">
			<div id="content">
				<div id="picturehead" style="background-image: url(images/cover.jpg)">
					<div>
						<br><br><br><br><br><br><br><br>
						<img alt="" src="<%=logged.getProfilePicture()%>">
						 <p><%=logged.getFirstName()+" "+logged.getLastName() %></p>
					</div>
				</div>
			</div>
		</div>
	
		<div id="contentcontainer">
			<div id="content">
				<div id="leftsidebar">

					<div class="leftbox">
					
					
						<ul>
						
							<a href="Profile?opt=friends&userid=<%=logged.getUserID()%>"><li><%=logged.getFirstName()%>'s friends</li></a>
							<a href="Profile?opt=profile&userid=<%=logged.getUserID()%>"><li><%=logged.getFirstName()%>'s posts</li></a>
							<a href="Profile?opt=about&userid=<%=logged.getUserID()%>"><li>About <%=logged.getFirstName()%></li></a>
							<a href="editprofile.jsp"><li>Edit profile</li></a>
							
							
						</ul>
					</div>
				
				</div>
				<div id="centercontent">
					 <div class="middlecontainer">
					
					<div class="postcontent">
					
				<form action="Edit" method="post">
						<h4><font color="red">${msg }</font></h4>
						<h2>Edit your profile</h2>
					<h4>First Name:</h4>	
					<input type="text" value="<%=logged.getFirstName() %>" class="signupfield" name="FirstName"><br>
					<h4>Last Name:</h4>
					<input type="text" value="<%=logged.getLastName() %>" class="signupfield" name="LastName"><br>
					<h4>Email:</h4>
					<input type="text" value="<%=logged.getEmail() %>"class="signupfield" name="Email"><br>
					<h4>Sex:</h4>
					<input type="text" value="<%=logged.getSex() %>"class="signupfield" name="Sex"><br><br>
					<input type="hidden" name="Password" value="<%=logged.getPassword()%>">
					<input type="hidden" name="UserID" value="<%=logged.getUserID()%>">
					<input type="hidden" name="option" value="about">
						<h4>You must enter the password to confirm the changes you made.</h4>
						<h4>Password:</h4>
					<input type="password" placeholder="Type password" class="signupfield" name="typedPassword"><br>
					<p align="center"><input type="submit" class="signupfield" value="Save the changes"></p>
				</form>
					
				<form action="Edit" method="post">
						<h2>Change your password</h2>
						<h4>Password:</h4>
					<input type="password" placeholder="Type password" class="signupfield" name="typedPassword"><br>
						<h4>New password:</h4>
					<input type="password" placeholder="Type new password" class="signupfield" name="newtypedPassword"><br>
					<h4>Re-type new password:</h4>
					<input type="password" placeholder="re-type new password" class="signupfield" name="newretypedPassword"><br><br>
					<input type="hidden" name="option" value="passchange">
					<input type="hidden" name="UserID" value="<%=logged.getUserID()%>">
					<input type="hidden" name="Password" value="<%=logged.getPassword()%>">
					
					<p align="center"><input type="submit" class="signupfield" value="Save the changes"></p>
				</form>
				
			<form method="post" action="UploadServlet" enctype="multipart/form-data">
				<h2>Change your profile picture</h2>
                      <h4>Select file to upload:</h4>
                      <input type="file" name="file" size="60"><br><br>
                      <p align="center"><input type="submit" value="Upload" class="signupfield"></p>
                </form>
                
                
            
					</div>
					
					
									
					 </div>
				</div>
				<div id="rightsidebar">			
				<%=Box.suggFriends(f.suggFriends(logged.getUserID()),logged.getUserID())	
						//write sugg friends
				%>
					${msgadd}
				<%=Box.onlineFriends(f.onlineFriends(logged.getUserID()))
						//write online friends		
				%>
				</div>
			</div>
		</div>
		<%} %>
	</div>

	
</body>
</html>