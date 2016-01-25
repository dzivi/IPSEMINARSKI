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
User userProfile = (User) request.getAttribute("userProfile");
FriendShipDAO fd = new FriendShipDAO();
%>


<!DOCTYPE html>
<html>
<head>
<%if (logged==null){
	response.sendRedirect("index.jsp");
	} else { %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=userProfile.getFirstName() %> <%=userProfile.getLastName() %></title>

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
						<img alt="" src="<%=userProfile.getProfilePicture()%>">
						 <p><%=userProfile.getFirstName()+" "+userProfile.getLastName() %></p>
						 <%if((logged.getUserID()!=userProfile.getUserID() && fd.weAreFriends(logged.getUserID(), userProfile.getUserID())!=0) || logged.getLevelID()==2){ %>
						<b><a href="Profile?opt=message&userid=<%=userProfile.getUserID()%>"><img alt="" src="images/message.png"></a></b>
						<%} %>
					</div>
				</div>
			</div>
		</div>
	
		<div id="contentcontainer">
			<div id="content">
				<div id="leftsidebar">

					<div class="leftbox">
					<% 
					if(fd.weAreFriends(logged.getUserID(), userProfile.getUserID())!=0 || logged.getUserID()==userProfile.getUserID() || logged.getLevelID()==2){%>
						<p>Menu...</p>
						<ul>
						
							<a href="Profile?opt=friends&userid=<%=userProfile.getUserID()%>"><li><%=userProfile.getFirstName()%>'s friends</li></a>
							<a href="Profile?opt=profile&userid=<%=userProfile.getUserID()%>"><li><%=userProfile.getFirstName()%>'s posts</li></a>
							<a href="Profile?opt=about&userid=<%=userProfile.getUserID()%>"><li>About <%=userProfile.getFirstName()%></li></a>
							<%if(userProfile.getUserID()==logged.getUserID()){ %>
							<a href="editprofile.jsp"><li>Edit profile</li></a>
							<%} %>
							<%} %>
						</ul>
					</div>
				
				</div>
				<div id="centercontent">
					 <div class="middlecontainer">
					
					<div class="postcontent">
				<h2>About <%=userProfile.getFirstName() %></h2>
				<h4>Name:</h4><%=userProfile.getFirstName() %> <%=userProfile.getLastName() %><br><br>
				<h4>Birdthday:</h4><%=userProfile.getBirdthday() %><br><br>
				<h4>Sex:</h4><%=userProfile.getSex() %>
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