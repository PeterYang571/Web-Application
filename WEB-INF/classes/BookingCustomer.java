import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/BookingCustomer")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class BookingCustomer extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// List<Book> bookList = MySqlDataStoreUtilities.viewCustomerBook(1);	
		// List<Book> bookList = MySqlDataStoreUtilities.viewBook();		
	
		Utilities utility = new Utilities(request,pw);
		User user=utility.getUser();
		List<Book> bookList = MySqlDataStoreUtilities.viewCustomerBook(user.getId());	

		utility.printHtml("Header.html");


		pw.println("<div class='6u'><section><header><h2>Review Booking</h2><span class='byline'></span> <br><hr><header></section>");
		pw.println("<div><table><tr><th>BookId</th><th>Customer Name</th><th>Provider Name</th><th>Provider Type</th><th>Date</th><th>Time</th><th>Note</th><th>AddNote</th><th>Cancel</th></tr>");
		for (Book book : bookList) {
			pw.println("<form method='POST' action='BookingCustomer'><tr>");
			pw.println("<td>"+ book.getId() + "</td>");
			pw.println("<td>"+ book.getCustomerName() + "</td>");
			pw.println("<td>"+ book.getProvideName() + "</td>");
			pw.println("<td>"+ book.getProviderType() + "</td>");
			pw.println("<td>"+ book.getAppointmentDate()+ "</td>");
			pw.println("<td>"+ book.getTime()+ "</td>");
			pw.println("<td>"+ book.getNote() + "</td>");
			pw.println("<td><a href=AddNote?id="+book.getId()+">Add note</td>");
			pw.println("<td><input type='submit' class='btnbuy' name='cancel' value='"+book.getId()+"'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input></td></tr></form>");

		}
		pw.println("</table></div></div>");

		utility.printHtml("Footer.html");

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("addNote")!= null) {
			String id = request.getParameter("id");
			String note = request.getParameter("note");
			MySqlDataStoreUtilities.updateMessage(Integer.parseInt(id),note);
		} else {
			String cancelId = request.getParameter("cancel");
			MySqlDataStoreUtilities.removeBook(Integer.parseInt(cancelId) );
		}

		doGet(request, response);
	}





}
