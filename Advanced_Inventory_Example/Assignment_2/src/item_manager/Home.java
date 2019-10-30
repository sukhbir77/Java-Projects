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
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
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
			DB_Access db = new DB_Access();
			String uname = db.getUserName(uid);
			
			out.println("<center><table width='80%'>");
			out.println("<tr>");
			out.println("<td><h1>Welcome " + uname + "</h1></td>");
			out.println("<td align=right><a href=Modify>Modify Account</a>");
			out.println("&nbsp;&nbsp;<a href=Logout>LogOut</a></td>");
			out.println("</tr>");
			out.println("<tr><td colspan=2>");
			
			out.println("<center><h2>All User Items</h2>");
			// display all user items
			ArrayList<Item> all = db.getAllUserItems(uid);
			out.println("<table>");
			out.println("<tr><th>ACTIONS</th><th>ITEM ID </th><th>NAME</th><th>QUANTITY</th></tr>");
			for(Item i : all) {
				out.println("<tr>");
				out.println("<td><a href=ViewItem?iid="+i.getIid()+">View</a>");
				out.println("    <a href=Edit?iid="+i.getIid()+">Edit</a> ");
				out.println("    <a href=DeleteItem?iid="+i.getIid()+">Delete</a></td>");
				out.println("<td>"+i.getIid()+"</td>");
				out.println("<td>"+i.getItemName()+"</td>");
				out.println("<td>"+i.getQty()+"</td>");
				out.println("</tr>");
				
			} 
			out.println("</table>");
			
			out.println("<h2>Add a New Item</h2>");
			String msg = "";
			if(request.getParameter("msg") != null) 
				msg = request.getParameter("msg");
			out.println("<h3 style='color:red;'>"+msg+"</h3>");
			out.println("<form method=post action=CreateItem>");
			out.println("Item Name: <input type=text name=iname><br>");
			out.println("Item Quantity: <input type=text name=iqty><br>");
			out.println("<input type=submit value=Create>");
			out.println("</form>");
			
			out.println("</center>");
			
			out.println("</td></tr>");
			out.println("</table></center>");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}







