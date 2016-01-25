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

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>

<!-- call css -->
<link rel="stylesheet" type="text/css" href="css/styletest.css">

<!-- call jQ google api -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> 

<!-- call functions.js jQuery functions -->
<script type="text/javascript" src="js/functions.js"></script>

<!-- Call favicon for page-->
<link rel="icon" href="images/ico/logo.png" type="image/x-icon" />

</head>

<%
HttpSession sesion = request.getSession();
User logged = (User) sesion.getAttribute("logged");
ArrayList<User> searchList = (ArrayList<User>) request.getAttribute("searchList");
// String searchMess = (String) request.getAttribute("searchMess");


%>
<body>
	<div id="maincontainer">
		<div id="headercontainer">
		
		<%if (logged==null){
			response.sendRedirect("index.jsp");
			} else {
			
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
		<div id="contentcontainer">
			<div id="content">
				<div id="leftsidebar">

					<div class="leftbox">
						<p>Menu...</p>
						<ul>
							<a href="Profile?opt=friends&userid=<%=logged.getUserID()%>"><li>My Friends</li></a>
							<a href="home.jsp"><li>Posts</li></a>
							<a href="messages.jsp"><li>Messages
								<%if(countMessag!=0){%><span class='countMess'>(<%=countMessag %>)</span><%}								%>
							</li></a>
							<a href="requests.jsp"><li>Friends requests
								<%if(countReq!=0){%><span>(<%=countReq %>)</span><%}%></li></a>
							<a href="Profile?opt=profile&userid=<%=logged.getUserID()%>"><li>My profile</li></a>
							<a href="editprofile.jsp"><li>Edit profile</li></a>
							<%if(logged.getLevelID()==2){ %>
							<a href="admin.jsp"><li>Admin page</li></a>
							<%} %>
						</ul>
					</div>
				
				</div>
				<div id="centercontent">
					 <div class="middlecontainer">
					
					<%//here starts the logic
					
					
					
					String helpString[];
					int i,j=0;
					
					HashMap<Integer, String[]> hom=new HashMap<Integer, String[]>();
					hom=messag.getMyMessages(logged.getUserID());
					if(hom!=null && hom.size()>0){
					for(j=0;j<hom.size();j++){
						helpString=(String[]) hom.get(j);
							if(helpString.length>0){
								%>
								<%=Box.allMessages(helpString, logged) %>
							<%}
					}
					}else{
						%>
						No new messages!
						<%
					}
						//end 
						
						
						
					
						%>				
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