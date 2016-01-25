package servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAOclasses.UserDAO;
import classes.User;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class UploadServlet extends HttpServlet {

	/**
	 * Name of the directory where uploaded files will be saved, relative to the
	 * web application directory.
	 */
	

	/**
	 * handles file upload
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets absolute path of the web application
		
		HttpSession sesion = request.getSession();
		User logged = (User) sesion.getAttribute("logged");
		
		
		
		String NickName = logged.getNickName();
		String SAVE_DIR = "profile";
		int UserID = logged.getUserID();
		String putanja;
		
		String appPath = "D:/JAVA/workspace/IPSEMINARSKI/WebContent/images/users";
		// constructs path of the directory to save uploaded file
		String savePath = appPath+"/"+NickName+"/"+SAVE_DIR;
		
		

		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
			putanja = savePath +"/"+ fileName;
			part.write(putanja);
			
			
			
			
					putanja = putanja.replace("D:/JAVA/workspace/IPSEMINARSKI/WebContent/", "");
					
					
					UserDAO user = new UserDAO();
					user.updateUserProfilePic(putanja, UserID);
			
		}
		
		request.setAttribute("msg", "Profile picture updated!");
		request.getRequestDispatcher("editprofile.jsp").forward(request, response);

		
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
