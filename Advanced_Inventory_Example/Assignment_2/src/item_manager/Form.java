package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DB_Access db= new DB_Access();
		String msg = "";
		if(request.getParameter("msg") != null) {
			msg = request.getParameter("msg");
		}
		
		out.println("<center>");
		out.println("<td align=right><a href=CreateAccount>Create Account</a><br>");
		out.println("<span style='color:red;'>"+msg+"</span>");
		out.println("<form method=post>");
		out.println("User Name: <input type=text name=uname><br>");
		out.println("User Pass: <input type=text name=upass><br>");
		out.println("<input type=submit value=LogIn>");
		out.println("</form>");
		out.println("</center>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean canProceed = true;
		
		if(!Helper.isValValid(request.getParameter("uname"))) {
			canProceed = false;
		}
		if(!Helper.isValValid(request.getParameter("upass"))) {
			canProceed = false;
		}
		if(canProceed) {
			// values are valid from the business logic standpoint
			String uname = request.getParameter("uname");
			String upass = request.getParameter("upass");
			
			int uid = Helper.checkUserLogin(uname, upass);
			if( uid != -1) {
				// correct, send user to Home page
				HttpSession sess = request.getSession();
				sess.setAttribute("uid", uid);
				response.sendRedirect("Home");
			}
			else {
				// incorrect, send user to the login form with error message
				response.sendRedirect("Login?msg=user not recognized");
			}
		}
		else {
			// values are NOT valid
			response.sendRedirect("Login?msg=error, values are not valid");
		}
	}

}







