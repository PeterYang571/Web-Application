import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.*;
import javax.servlet.http.HttpSession;

@WebServlet("/DataAnalytics")

public class DataAnalytics extends HttpServlet {
	static DBCollection myReviews;
	/*
	 * Trending Page Displays all the Consoles and their Information in Game Speed
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		// check if the user is logged in
		if (!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to View Reviews");
			response.sendRedirect("Login");
			return;
		}

		utility.printHtml("Header.html");
		pw.println("<div class='9u'><section><br>");
		pw.println("<section><article id='doctorsearch'><hr style='width: 95%'><h2 style='font-size: 25px;'>DataAnalytics</h2><hr style='width: 95%'>");

		pw.print("<table id='bestseller'>");
		pw.print("<form method='Get' action='FindDoctor'>");

		pw.print("<table id='bestseller'>");

		pw.print("<tr><td> <input type='checkbox' name='queryCheckBox' value='reviewrating'> Select </td>");
		pw.print(" <td> Review Rating: </td>");
		pw.print(" <td>");
		pw.print(" <select name='reviewRating'>");
		pw.print(" <option value='1' selected>1</option>");
		pw.print(" <option value='2'>2</option>");
		pw.print(" <option value='3'>3</option>");
		pw.print("   <option value='4'>4</option>");
		pw.print("  <option value='5'>5</option>");
		pw.print(" <option value='6'>6</option>");
		pw.print(" <option value='7'>7</option>");
		pw.print("   <option value='8'>8</option>");
		pw.print("  <option value='9'>9</option>");
		pw.print(" <option value='10'>10</option>");

		pw.print("</td>");
		pw.print("<td>");
		pw.print("<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>");
		pw.print("<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than");
		pw.print("</td></tr>");

		

		pw.print("<tr>");
		pw.print("<td colspan = '4'> <input type='submit' value='Find Data' class='btnbuy' /> </td>");
		pw.print("</tr>");

		pw.println("</table><article></section></div>");
		utility.printHtml("Footer.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
