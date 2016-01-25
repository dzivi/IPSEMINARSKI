package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.User;

import DAOclasses.UserDAO;
import classes.Validate;
import classes.User;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String opt = request.getParameter("opt");
		
		if(opt.equals("friends")){
		if(Validate.check(userid)){
			try {
				
				int uid = Integer.parseInt(userid);
				UserDAO userProfileDAO = new UserDAO();
				User userProfile = new User();
				
				userProfile = userProfileDAO.getUserbyID(uid);
				
				request.setAttribute("userProfile", userProfile);
				request.getRequestDispatcher("friends.jsp").forward(request, response);
				
			} catch (Exception e) {
				// error page
			}
		}
		} if(opt.equals("profile")){
			if(Validate.check(userid)){
				try {
					
					int uid = Integer.parseInt(userid);
					UserDAO userProfileDAO = new UserDAO();
					User userProfile = new User();
					
					userProfile = userProfileDAO.getUserbyID(uid);
					
					request.setAttribute("userProfile", userProfile);
					request.getRequestDispatcher("profile.jsp").forward(request, response);
					
				} catch (Exception e) {
					// error page
				}
		}
		} if(opt.equals("pictures")){
			if(Validate.check(userid)){
				try {
					
					int uid = Integer.parseInt(userid);
					UserDAO userProfileDAO = new UserDAO();
					User userProfile = new User();
					
					userProfile = userProfileDAO.getUserbyID(uid);
					
					request.setAttribute("userProfile", userProfile);
					request.getRequestDispatcher("pictures.jsp").forward(request, response);
					
				} catch (Exception e) {
					// error page
				}
		}
		} if(opt.equals("message")){
			if(Validate.check(userid)){
				try {
					
					int uid = Integer.parseInt(userid);
					UserDAO userProfileDAO = new UserDAO();
					User userProfile = new User();
					
					userProfile = userProfileDAO.getUserbyID(uid);
					
					request.setAttribute("userProfile", userProfile);
					request.getRequestDispatcher("newmessage.jsp").forward(request, response);
					
				} catch (Exception e) {
					// error page
				}
		}
		}
		
		if(opt.equals("about")){
			if(Validate.check(userid)){
				try {
					
					int uid = Integer.parseInt(userid);
					UserDAO userProfileDAO = new UserDAO();
					User userProfile = new User();
					
					userProfile = userProfileDAO.getUserbyID(uid);
					
					request.setAttribute("userProfile", userProfile);
					request.getRequestDispatcher("about.jsp").forward(request, response);
					
				} catch (Exception e) {
					// error page
				}
		}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
