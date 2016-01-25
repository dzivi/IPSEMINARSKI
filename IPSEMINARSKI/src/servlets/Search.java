package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOclasses.UserDAO;
import classes.User;
import classes.Validate;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchname = request.getParameter("searchname");
		searchname = searchname.replace(" ", "");
		
		
	if(Validate.check(searchname)){
			UserDAO ud = new UserDAO();
			ArrayList<User> searchList= new ArrayList<User>();
			searchList = ud.searchUser(searchname);
			request.setAttribute("searchList", searchList);		
			request.getRequestDispatcher("search.jsp").forward(request, response);
			
			
	} else {
		
		request.setAttribute("msg", "Please enter a query in the search box above...");
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
