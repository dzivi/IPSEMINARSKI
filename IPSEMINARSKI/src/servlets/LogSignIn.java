package servlets;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOclasses.UserDAO;
import classes.User;
import classes.Validate;

@WebServlet("/LogSignIn")
public class LogSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogSignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userID");
		try {
			int	uID = Integer.parseInt(userID);
			UserDAO ud = new UserDAO();
			ud.updateOnlineStatus("f", uID);
			ud.updateLastActivity(uID);
			HttpSession sesion = request.getSession();
			
			
			sesion.invalidate();
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		String msg = null;

		// action for sign up
		if (Validate.check(type, "sign")) {

			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String reenteremail = request.getParameter("reenteremail");
			String newpassword = request.getParameter("newpassword");
			String sex = request.getParameter("sex");
			String birdthday = request.getParameter("birdthday");

			if (Validate.check(firstname) && Validate.check(lastname)
					&& Validate.check(email) && Validate.check(reenteremail)
					&& Validate.check(newpassword) && Validate.check(sex)
					&& Validate.check(birdthday)) {
				if (Validate.check(email, reenteremail)) {

					UserDAO ud = new UserDAO();
					int exist = ud.existUser(email, newpassword); 
					//check if user exists
					if (exist == 0) {
						try {
							
							Date date = new Date();
							SimpleDateFormat dateformat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							String createdDate =dateformat.format(date);
							
							//
							String dateForNick = createdDate.replace("/", "_");
							dateForNick = dateForNick.replace(" ", "_");
							dateForNick = dateForNick.replace(":", "_");
							String nickname=firstname+"_"+dateForNick;
							
							String root=getServletContext().getRealPath("/");
							System.out.println("IPSEMINARSKI/WebContent/images/users/"+nickname);
							
						File file = new File("../workspace/IPSEMINARSKI/WebContent/images/users/"+nickname);
						File fileProfile = new File("../workspace/IPSEMINARSKI/WebContent/images/users/"+nickname+"/profile");
						File fileAlbum = new File("../workspace/IPSEMINARSKI/WebContent/images/users/"+nickname+"/album");
							
							
							if (!file.exists()) {
								if (file.mkdirs()) {
									fileProfile.mkdir();
									fileAlbum.mkdir();
									System.out.println("Directories are created!");
								} else {
									System.out.println("Failed to create directories!");
								}
							}
							
							
						ud.insertUser(firstname, lastname, birdthday, sex, email, newpassword, createdDate, nickname);
							
							
							
							msg = "Registration was successful!";
						} catch (Exception e) {
							msg = "Date error!";
						}

					} else {
						msg = "The user exist!";
					}
				} else {
					msg = "The email addresses do not match!";
				}
			} else {
				msg = "Sign Up error! You must fill in all fields.";
			}
			request.setAttribute("msgsign", msg);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);

			// action for log in
		} else if (Validate.check(type, "log")) {

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (Validate.check(email) && Validate.check(password)) {

				UserDAO ud = new UserDAO();
				int exist = ud.existUser(email, password);
				if (exist==1) {
					// msg = "Sve ok!";
					User u = new User();
					
					u=ud.getUser(email, password);
					ud.updateOnlineStatus("t", u.getUserID());
					HttpSession sesion = request.getSession();
					sesion.setMaxInactiveInterval(600);
					
					sesion.setAttribute("logged", u);
					request.getRequestDispatcher("home.jsp").forward(request, response);
				} else {
					msg = "Invalid username or password!";
				}

			} else {
				msg = " Log in error! Enter username and password!";
			}
			request.setAttribute("msglog", msg);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
			
		} else {
			request.setAttribute("msglog", "Log In error!");
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		}

	}

}
