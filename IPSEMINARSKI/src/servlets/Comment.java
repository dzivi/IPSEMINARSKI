package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOclasses.CommentDAO;
import DAOclasses.LikeDAO;
import classes.Validate;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
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
		
		String userid = request.getParameter("userid");
		String postid = request.getParameter("postid");
		String text = request.getParameter("text");
		
		
		if(Validate.check(userid) && Validate.check(postid) && Validate.check(text)){
			CommentDAO comment = new CommentDAO();
			
			try{
				int uid=Integer.parseInt(userid);
				int pid=Integer.parseInt(postid);
				comment.insertComment(pid, uid, text);
				
				response.sendRedirect("home.jsp");
				
			} catch(Exception e){
				
				request.getRequestDispatcher("errorpage.jsp").forward(request, response);
				
			}
			
		} else {
			
			request.setAttribute("msg", "Comment is not set, you must enter text comments...");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		
	}

}
