import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/Signup")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Signup extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		pw.println("<div class='9u'><section><header><h2>Sign Up</h2></header>");
		pw.println("<form method='post' action='Login'>");
		pw.println("<table style='width:100%'><tr><td><h3>Username</h3></td><td>");
		pw.println("<input type='text' name='username' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td><h3>Password</h3></td><td>");
		pw.println("<input type='password' name='password' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td><h3>Re-Password</h3></td><td>");
		pw.println("<input type='password' name='repassword' value='' class='input'required></input></td></tr>");
		pw.println("<tr><td><h3>E-mail</h3></td><td>");
		pw.println("<input type='email' name='email' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td><h3>address</h3></td><td>");
		pw.println("<input type='text' name='address' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td><h3>User Type</h3></td><td>");
		pw.println("<select name='usertype' class='input'>");
		pw.println("<option value='customer' selected>Customer</option>");
		pw.println("<option value='hospital'>Hospital</option>");
		pw.println("<option value='doctor'>Doctor</option>");
		pw.println("<option value='insuranceCompany'>Insurance Company</option>");
		pw.println("<option value='admin'>Admin</option></select></td></tr>");
		pw.println("</table>");
		pw.println("<input type='submit' class='btnbuy' name='user' value='Create'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
		pw.println("</form></section></div>");


		utility.printHtml("Footer.html");

	}

}
