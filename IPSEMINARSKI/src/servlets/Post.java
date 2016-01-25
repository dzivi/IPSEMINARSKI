package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOclasses.PostDAO;
import classes.Validate;

/**
 * Servlet implementation class Post
 */
@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String UserID = request.getParameter("UserID");
		String Text = request.getParameter("Text");
		String Link = request.getParameter("Link");
		
		if(Validate.check(UserID)){
			if(Validate.check(Text) || Validate.check(Link)){
				try {
					
					int uid = Integer.parseInt(UserID);
					PostDAO post = new PostDAO();
					post.insertPost(uid, Text, Link);
					
					request.setAttribute("msg", "Post is set!"); 
					request.getRequestDispatcher("home.jsp").forward(request, response);
					
				} catch (Exception e) {
					request.setAttribute("msg", "Post not set!"); 
					request.getRequestDispatcher("home.jsp").forward(request, response);
				}
			} else {
					request.setAttribute("msg", "You must fill in some of the fields!"); 
					request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}
	}

}
