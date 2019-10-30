package item_manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Edit")
public class Edit extends HttpServlet {
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
			String msg = "Please Enter Values to change. **NOTE- please enter all values or else you will get error**";
			if(request.getParameter("msg") != null) {
				msg = request.getParameter("msg");
			}

			out.println("<center>");
			out.println("<span style='color:red;'>"+msg+"</span>");
			out.println("<form method=post>");
			out.println("Item Name: <input type=text name=iname><br>");
			out.println("Quantity : <input type=text name=iqty><br>");
			out.println("<input type=submit value=Change_Values>");
			out.println("</form>");
			out.println("</center>");
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		if(Helper.isItemNameValid(request.getParameter("iname")) &&
				Helper.isItemQtyValid(request.getParameter("iqty"))) {
			String itemName = request.getParameter("iname");
			int iQty = Integer.parseInt(request.getParameter("iqty"));
			DB_Access db = new DB_Access();
			int iid = Integer.parseInt(request.getParameter("iid"));
			db.EditItem(itemName, iQty, iid);
			response.sendRedirect("Home");
		}
		else {
			response.sendRedirect("Home?msg=values are wrong, try again");
		}
			}
	}


