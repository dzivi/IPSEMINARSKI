package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOclasses.LikeDAO;
import classes.Validate;

/**
 * Servlet implementation class Like
 */
@WebServlet("/Like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Like() {
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
		// TODO Auto-generated method stub
		
		String postID= request.getParameter("postID");
		String userID= request.getParameter("userID");
		
		if(Validate.check(postID) && Validate.check(userID)){
			LikeDAO like= new LikeDAO();
			try{
				int pid=Integer.parseInt(postID);
				int uid=Integer.parseInt(userID);
				like.insertLike(pid, uid);
				request.setAttribute("src", "ok"); 
				response.getWriter().print("ok");
			} catch(Exception e){
				request.setAttribute("src", "not"); 
				response.getWriter().print("ok");
				
			}
			
		}
	}

}
