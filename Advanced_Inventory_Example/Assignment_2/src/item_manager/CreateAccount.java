package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession sess = request.getSession(true);
		
			DB_Access db = new DB_Access();
			String msg = "**NOTE- please enter both values or else you will get error**";
			if(request.getParameter("msg") != null) {
				msg = request.getParameter("msg");
			}
		out.println("<center>");
		out.println("<td align=center><h1>Create Account</h1>");
		out.println("<span style='color:red;'>"+msg+"</span>");
		out.println("<form method=post>");
		out.println("     Name: <input type=text name=name2><br>");
		out.println("User Name: <input type=text name=uname2><br>");
		out.println("User Pass: <input type=text name=upass2><br>");
		out.println("<input type=submit value=Create_Account>");
		out.println("</form>");
		out.println("</center>");
		}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean canProceed = true;
		HttpSession sess = request.getSession(true);
				if(!Helper.isValValid(request.getParameter("uname2"))) {
					canProceed = false;
				}
				if(!Helper.isValValid(request.getParameter("upass2"))) {
					canProceed = false;
				}
				String name2 = request.getParameter("name2");
				String uname2 = request.getParameter("uname2");
				String upass2 = request.getParameter("upass2");
				int uid1 = Helper.createUser(uname2,name2, upass2);
				
				if(canProceed && uid1==1) {
					response.sendRedirect("Login?msg=Account Created, Please login to proceed");
				}
				else {
					// values are NOT valid
					response.sendRedirect("CreateAccount?msg=error, values are not valid");
				}
	}

}
