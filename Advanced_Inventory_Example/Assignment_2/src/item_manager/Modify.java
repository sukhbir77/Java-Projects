package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession sess = request.getSession(true);
		if(sess.getAttribute("uid") == null) {
			// user bypassed the login page. send the user back to loginpage
			response.sendRedirect("Login?msg=must login first");
		} else {
			int uid = (Integer) sess.getAttribute("uid");
			DB_Access db = new DB_Access();
			String msg = "Please Enter Values to change. **NOTE- please enter both values or else you will get error**";
			if(request.getParameter("msg") != null) {
				msg = request.getParameter("msg");
			}

			out.println("<center>");
			out.println("<span style='color:red;'>"+msg+"</span>");
			out.println("<form method=post>");
			out.println("User Name: <input type=text name=uname1><br>");
			out.println("User Pass: <input type=text name=upass1><br>");
			out.println("<input type=submit value=Change_Values>");
			out.println("</form>");
			out.println("</center>");
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
boolean canProceed = true;
HttpSession sess = request.getSession(true);
		if(!Helper.isValValid(request.getParameter("uname1"))) {
			canProceed = false;
		}
		if(!Helper.isValValid(request.getParameter("upass1"))) {
			canProceed = false;
		}
		String uname1 = request.getParameter("uname1");
		String upass1 = request.getParameter("upass1");
		int uid= (Integer) sess.getAttribute("uid");
		int uid1 = Helper.changeUserVal(uname1, upass1, uid);
		
		if(canProceed && uid1==1) {
			sess.setAttribute("uid", uid);
			response.sendRedirect("Home");
		}
		else {
			// values are NOT valid
			response.sendRedirect("Login?msg=error, values are not valid");
		}
	}

}
