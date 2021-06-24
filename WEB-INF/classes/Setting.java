import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/Setting")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Setting extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		pw.println("<div class='9u'><section><header><h2>Change information</h2></header>");
		pw.println("<form method='post' action='Home'>");
		pw.println("<table style='width:100%'>");
		pw.println("<tr><td><h3>Password</h3></td><td>");
		pw.println("<input type='password' name='password' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td><h3>Address</h3></td><td>");
		pw.println("<input type='text' name='address' value='' class='input' required></input></td></tr></table>");
		pw.println("<input type='submit' class='btnbuy' name='action' value='Modify'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
		pw.println("</form></section></div>");



		utility.printHtml("Footer.html");

	}

}
