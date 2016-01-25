package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOclasses.UserDAO;
import classes.Validate;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String option = request.getParameter("option");

		if (Validate.check(option)) {

			if (Validate.check(option, "about")) {
				String FirstName = request.getParameter("FirstName");
				String LastName = request.getParameter("LastName");
				String Email = request.getParameter("Email");
				String Sex = request.getParameter("Sex");
				String Password = request.getParameter("Password");
				String UserID = request.getParameter("UserID");
				String typedPassword = request.getParameter("typedPassword");

				if (Validate.check(FirstName) && Validate.check(LastName)
						&& Validate.check(Email) && Validate.check(Sex)
						&& Validate.check(Password) && Validate.check(UserID)
						&& Validate.check(typedPassword)) {

					if (Validate.check(Password, typedPassword)) {
						try {

							int uid = Integer.parseInt(UserID);
							UserDAO user = new UserDAO();

							user.updateUser(uid, FirstName, LastName, Sex,
									Email);

							request.setAttribute("msg", "User updated!");
							request.getRequestDispatcher("editprofile.jsp")
									.forward(request, response);

						} catch (Exception e) {

							System.out.println("An error has occured!");
						}

					} else {
						request.setAttribute("msg", "Wrong password!");
						request.getRequestDispatcher("editprofile.jsp")
								.forward(request, response);
					}
				} else {
					request.setAttribute("msg", "All fields must be filled!");
					request.getRequestDispatcher("editprofile.jsp").forward(
							request, response);
				}

			}

			if (Validate.check(option, "passchange")) {
				String Password = request.getParameter("Password");
				String UserID = request.getParameter("UserID");
				String newtypedPassword = request
						.getParameter("newtypedPassword");
				String newretypedPassword = request
						.getParameter("newretypedPassword");
				String typedPassword = request.getParameter("typedPassword");

				if (Validate.check(Password) && Validate.check(UserID)
						&& Validate.check(newtypedPassword)
						&& Validate.check(newretypedPassword)
						&& Validate.check(typedPassword)) {

					if (Validate.check(Password, typedPassword)) {
						if(Validate.check(newretypedPassword, newtypedPassword)){
						try {

							int uid = Integer.parseInt(UserID);
							UserDAO user = new UserDAO();

							user.updateUserPass(newtypedPassword, uid);

							request.setAttribute("msg", "Password updated!");
							request.getRequestDispatcher("editprofile.jsp")
									.forward(request, response);

						} catch (Exception e) {

							System.out.println("An error has occured!");
						}
						} else {
							
							request.setAttribute("msg", "New passwords do not match!");
							request.getRequestDispatcher("editprofile.jsp")
									.forward(request, response);
							
						}

					} else {
						request.setAttribute("msg", "Wrong password!");
						request.getRequestDispatcher("editprofile.jsp")
								.forward(request, response);
					}
				} else {
					request.setAttribute("msg", "All fields must be filled!");
					request.getRequestDispatcher("editprofile.jsp").forward(
							request, response);
				}
			}

		}

	}
}
