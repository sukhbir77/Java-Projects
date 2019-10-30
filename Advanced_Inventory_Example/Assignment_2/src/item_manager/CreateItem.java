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
 * Servlet implementation class CreateItem
 */
@WebServlet("/CreateItem")
public class CreateItem extends HttpServlet {
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
			
			String msg = "";
			if(request.getParameter("msg") != null) {
				msg = request.getParameter("msg");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(Helper.isItemNameValid(request.getParameter("iname")) &&
				Helper.isItemQtyValid(request.getParameter("iqty"))) {
			String itemName = request.getParameter("iname");
			int iQty = Integer.parseInt(request.getParameter("iqty"));
			DB_Access db = new DB_Access();
			HttpSession sess = request.getSession(true);
			int uid = (Integer) sess.getAttribute("uid");
			db.addNewItem(itemName, iQty, uid);
			response.sendRedirect("Home");
		}
		else {
			response.sendRedirect("Home?msg=values are wrong, try again");
		}
	}

}
