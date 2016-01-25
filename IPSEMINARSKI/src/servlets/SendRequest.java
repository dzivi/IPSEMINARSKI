package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOclasses.FriendShipDAO;
import classes.Validate;

/**
 * Servlet implementation class SendRequest
 */
@WebServlet("/SendRequest")
public class SendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendRequest() {
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
		String UserReqID = request.getParameter("UserReqID");
		String UserRespID = request.getParameter("UserRespID");
		String msg = "";
		if(Validate.check(UserReqID) && Validate.check(UserRespID)){
			FriendShipDAO f = new FriendShipDAO();
			
			try {
			int	reqID = Integer.parseInt(UserReqID);
			int respID = Integer.parseInt(UserRespID);
			int exist = 0;
			exist = f.existFriendship(reqID, respID);
				if(exist == 0){
			
			f.insertFriendShip(reqID, respID);
			
				msg = "Your request is sent!";
				} else {
					msg = "This friendship now exist!";
				}
			} catch (Exception e) {
				msg = "Add friend error!";
			} 
			
			
			
		} else {
			msg = "Add friend error!";
			
		}
		request.setAttribute("UserReqID", "");
		request.setAttribute("UserRespID", "");
		request.setAttribute("msgadd", msg);
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
		
	}

}
