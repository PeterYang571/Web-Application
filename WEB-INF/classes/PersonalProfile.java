import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/PersonalProfile")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class PersonalProfile extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();		
	
		Utilities utility = new Utilities(request,pw);
		User user = utility.getUser();


		utility.printHtml("Header.html");



		pw.println("<div class='9u'>");
		pw.println("<section>");
		pw.println("<header>");
		pw.println("<div align='center'><section><h2>Personal Information</h2></section></div><hr>");
		pw.println("<table style='width:100% '>");
		pw.println("<tr><th>userName</th><th>email</th></tr>");
		//Real value
		pw.println("<tr><td>"+ user.getUserName() +"</td><td>"+ user.getEmail()  +"</td></tr>");

		pw.println("<tr><th>usertype</th><th>Location</th></tr>");
		//Real value
		pw.println("<tr><td>"+  user.getUsertype()+"</td><td>"+ user.getLocation() +"</td></tr>");


		pw.println("<tr><th>Latitude</th><th>Longitude</th></tr>");
		//Real value
		pw.println("<tr><td>"+ user.getLat()  +"</td><td>"+  user.getLongt()   +"</td></tr> ");


		pw.println("<tr><th>Address</th></tr>");
		//Real value
		pw.println("<tr><td>"+  user.getAddress() +"</td></tr>");




		pw.println("</table></section></div>");


		utility.printHtml("Footer.html");

	}


}
