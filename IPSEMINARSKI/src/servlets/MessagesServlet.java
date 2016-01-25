package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOclasses.FriendShipDAO;
import DAOclasses.MessageDAO;
import DAOclasses.NotificationDAO;
import classes.Message;
import classes.Validate;

/**
 * Servlet implementation class MessagesServlet
 */
@WebServlet("/MessagesServlet")
public class MessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String option= request.getParameter("option");
		
		if(Validate.check(option)){
			
			//update message when somebody replays on message
			if(Validate.check(option, "update")){
				String textToReplay= request.getParameter("textToReplay");
				String messageID= request.getParameter("messageID");
				String userID= request.getParameter("userID");
				String nottiID= request.getParameter("nottiID");
				if(Validate.check(textToReplay) && Validate.check(messageID) && Validate.check(userID) && Validate.check(nottiID)){
					
					MessageDAO mess= new MessageDAO();
					mess.updateMessage(textToReplay, Integer.parseInt( messageID));
					
					NotificationDAO nott=new NotificationDAO();
					nott.updateNotification(Integer.parseInt(userID), "t", Integer.parseInt(nottiID), Integer.parseInt(messageID));
					
					request.setAttribute("src", "ok"); 
					response.getWriter().print("ok");
				}else{
					request.setAttribute("src", "error"); 
					response.getWriter().print("error");
				}
				
			}
			//end
			
			//update notification 
			if(Validate.check(option, "updatenott")){
				String nottiID= request.getParameter("nottiID");
				String messageID= request.getParameter("messageID");
				String userID= request.getParameter("userID");
				if(Validate.check(nottiID) && Validate.check(messageID) && Validate.check(userID)){
					
					NotificationDAO not= new NotificationDAO();
					not.updateNotification(0, "f", Integer.parseInt(nottiID), Integer.parseInt(messageID));
					
					request.setAttribute("src", "ok"); 
					response.getWriter().print("ok");
				}else{
					request.setAttribute("src", "error"); 
					response.getWriter().print("error");
				}
				
			}
			
			if(Validate.check(option, "insert")){
				String UserReqID = request.getParameter("UserReqID");
				String UserRespID = request.getParameter("UserRespID");
				String Text = request.getParameter("Text");
				String FirstName = request.getParameter("FirstName");
				
				if(Validate.check(UserReqID) && Validate.check(UserRespID) && Validate.check(Text) && Validate.check(FirstName)){
				try {
					int reqID = Integer.parseInt(UserReqID);
					int respID = Integer.parseInt(UserRespID);
					FriendShipDAO fd = new FriendShipDAO();
					MessageDAO message = new MessageDAO();
					Message mess = new Message();
					NotificationDAO nott = new NotificationDAO();
					
					int fshipID = fd.getFriendShipIDByFriends(reqID, respID);
										
					message.insertMessage(fshipID, ">> "+FirstName+": "+Text+"\n");
					
					mess = message.lastSendMessage(fshipID);
					
					int messID = mess.getMessageID();
					
					nott.insertNotification(messID, respID);
					
					
					request.getRequestDispatcher("messages.jsp").forward(request, response);
					
					
				} catch (Exception e) {
					
					
					request.getRequestDispatcher("messages.jsp").forward(request, response);
				}
				
				} else {
					request.setAttribute("msg", "Message are not sent, you must enter text messages...");
					request.getRequestDispatcher("home.jsp").forward(request, response);
				}
				
				
			}
		}
		
		
		
	}

}
