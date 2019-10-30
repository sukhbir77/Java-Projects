package item_manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.api.scripting.URLReader;
import sun.misc.URLClassPath;


@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	DB_Access db = new DB_Access();
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession sess = request.getSession(true);
		if(sess.getAttribute("uid") == null) {
			// user bypassed the login page. send the user back to loginpage
			response.sendRedirect("Login?msg=must login first");
		} else {
			int uid = (Integer) sess.getAttribute("uid");
			
			String msg = "";
			if(request.getParameter("msg") != null) {
				msg = request.getParameter("msg");
			}
			
		int id= Integer.parseInt(request.getParameter("iid"));
		out.println("<center>");
		out.println("<form method=post>");
		out.println("<h1>ARE YOU SURE YOU WANT TO DELETE THE ITEM WITH ITEM ID ="+id+"</h1><br>");
		out.print("<input type=submit name=yes value=YES>");
		out.print("<input type=submit name=no value=NO><br>");
		out.print("</form>");
		out.print("</center>");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("iid"));
		if(request.getParameter("yes")!=null) {
			db.DeleteItem(id);
			response.sendRedirect("Home?msg=Item Deleted");
		}
		else if (request.getParameter("no")!=null) {
			response.sendRedirect("Home?msg=You Canceled");
		}
	}

}
