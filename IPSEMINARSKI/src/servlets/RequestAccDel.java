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
 * Servlet implementation class RequestAccDel
 */
@WebServlet("/RequestAccDel")
public class RequestAccDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestAccDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqid = request.getParameter("reqid");
		String myid = request.getParameter("myid");
		String submit = request.getParameter("submit");
		
		if(Validate.check(reqid) && Validate.check(myid) && Validate.check(submit)){
			if(Validate.check(submit, "Accept")){
		try {
			int rid = Integer.parseInt(reqid);
			int mid = Integer.parseInt(myid);
			
			FriendShipDAO f = new FriendShipDAO();
			
			
			f.acceptFriendReq(rid, mid);
			
			
			
			request.getRequestDispatcher("requests.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		}
		} else if(Validate.check(submit, "Delete")){
			try {
				int rid = Integer.parseInt(reqid);
				int mid = Integer.parseInt(myid);
				FriendShipDAO f = new FriendShipDAO();
				f.deleteRequest(rid, mid);
				
				
				
				request.getRequestDispatcher("requests.jsp").forward(request, response);
				
			} catch (Exception e) {
				
			}
		}
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ActionToDo = request.getParameter("ActionToDo");
		String friendId = request.getParameter("friendId");
		String myID = request.getParameter("myID");
		
		if (Validate.check(ActionToDo) && Validate.check(friendId) && Validate.check(myID)) {
			FriendShipDAO f = new FriendShipDAO();
			if(ActionToDo.equals("Add")){
				f.acceptFriendReq(Integer.parseInt(friendId),Integer.parseInt(myID));
			}else{
				f.deleteRequest(Integer.parseInt(friendId),Integer.parseInt(myID));
			}
			
			request.setAttribute("src", "ok"); 
			response.getWriter().print("ok");
		}

}
		
	

}
