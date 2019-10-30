package item_manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewItem
 */
@WebServlet("/ViewItem")
public class ViewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DB_Access db = new DB_Access();
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
		ArrayList<Item> all = db.specificUser(id);
		out.println("<table>");
		
		for(Item i : all) {
			
			out.print("<tr>");
			out.print("<td><h1>ITEM ID</h1>      "+i.getIid()+"</td></tr>");
			out.print("<tr>");
			out.print("<td><h1>ITEM NAME</h1>      "+i.getItemName()+"</td></tr>");
			out.print("<tr>");
			out.print("<td><h1>ITEM QUANTITY</h1>      "+i.getQty()+"</td></tr>");
			
			
		} 
		out.println("</table>");out.println();
		out.println("    <a href=Home>Back</a></td>");
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
