<%@page import="classes.User"%>
<%@page import="DAOclasses.UserDAO"%>
<%@page import="classes.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAOclasses.CommentDAO"%>
<%@page import="classes.Post"%>
<%@page import="java.io.File"%>
<%@page import="classes.Validate"%>
<%@page import="DAOclasses.LikeDAO"%>
<%@ page language="java" %>
<%!
//function to read images from folder 

//function to write posts

public String Posts(Post p, int id, String slika, String imePrezime, int loggedID){
	
	String htmlString="<div class=\"post\">"
	+"<div class=\"postheader\">"
	+"<img src=\""+slika+"\">"
	+"<p><b>"+imePrezime+"</b></p>"
	+"</div>"
	+"<div class=\"postcontent\">";
	//check if text exist
	if(Validate.check(p.getText())){
		htmlString+="<p>"+p.getText()+"</p>";
	}
	
	
	//chesk if link exist
	if(Validate.check(p.getLink())){
		htmlString+="<p><a href=\""+p.getLink()+"\" target=\"_blank\">"+p.getLink()+"</a></p>";
	}
		
	//for likes
	LikeDAO like=new LikeDAO();
	//for comments
	CommentDAO commentDAO = new CommentDAO();
			UserDAO userDAO = new UserDAO();
			User user = new User();
			ArrayList<Comment> comments = new ArrayList<Comment>();
			comments = commentDAO.getComments(p.getPostID());
			
	htmlString+="</div><input type='hidden' value="+ p.getPostID() +" class='postID'>"
			+"<div class=\"likescoments\">"
				+"<div>"
					+"<span class=\"like\">Like ("+ like.countLikes(p.getPostID()) +")</span>"
					+"<span class=\"comment\">Comment</span>"
				+"</div>"
				+"<div class=\"comments\">"
				+"<!-- single comment -->"
				+"<p>Add new comment</p>"
				+"<div class=\"singlecomment\">"
				+"<form action=\"Comment\" method=\"post\">"
				+"<textarea placeholder='...insert comment...' name='text'></textarea>"
				+"<input type=\"hidden\" name=\"userid\" value="+loggedID+">"
				+"<input type=\"hidden\" name=\"postid\" value="+p.getPostID()+">"
				+"</div><input type=\"submit\" value='Add comment' id='addcommbutton' >"
			+"</form></div>"
				+"<div class=\"comments\">"
					+"<!-- single comment -->";
					for(Comment pom : comments){
						user = userDAO.getUserbyID(pom.getUserID());
					htmlString+="<div class=\"singlecomment\">"
					+"<img src="+user.getProfilePicture()+">"
					+"<p>"+user.getFirstName()+" "+user.getLastName()+"</p>"
					+"<p>"+pom.getText()+"</p>"
				+"</div>";
					}
				htmlString+="</div></div></div>";
				
	return htmlString;
}


public String insertPost(){
	String htmlString="<div class='post'>"
							+"<div id='addHeader'><p>Add new post</p><input type='button' class='addInput' value='Open'></div>"
							+"<div id='addPostText'>"
								+"<textarea placeholder='...insert text...' name='postText' id='postText'></textarea>"
								+"<input type='text'  placeholder='...insert URL address here...' name='postLink'  id='postLink'>"
								+"<input type='file' name='postPicture' id='postPicture'>"
								+"<input type='button' value='Add' class='addInput' >"
							+"</div>"
						+"</div>";
	return htmlString;
}



%>