import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/PostHospital")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class PostHospital extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
		Utilities utility = new Utilities(request,pw);

		User user = utility.getUser();
		utility.printHtml("Header.html");
		pw.println("<div class='9u'><section><header><h2>Post Hospital</h2></header>");
		pw.println("<form method='post' action='SearchHospital'>");
		pw.println("<table style='width:100%'><tr><td><h3>Real Name</h3></td><td>");

		pw.println("<input type='text' name='realName' value='' class='input' required></input></td></tr>");
		// pw.println("<tr><td><h3>Department</h3></td><td>");
		// pw.println("<input type='text' name='department' value='' class='input' required></input></td></tr>");
		pw.println("<tr><td><h3>Address</h3></td><td>");
		pw.println("<input type='text' name='address' value='' class='input'required></input></td></tr>");
		pw.println("<tr><td><h3>location</h3></td><td>");
		pw.println("<input type='text' name='location' value='' class='input' required></input></td></tr>");

		pw.println("<tr><td><h3>Open Time</h3></td><td>");
		pw.println("<select name='openTime' class='input'>");
		pw.println("<option value='8:00' selected>8:00</option>");
		pw.println("<option value='9:00' selected>9:00</option>");
		pw.println("<option value='10:00' selected>10:00</option>");
		pw.println("<option value='11:00' selected>11:00</option>");
		pw.println("<option value='12:00' selected>12:00</option>");
		pw.println("<option value='13:00' selected>13:00</option>");
		pw.println("<option value='14:00' selected>14:00</option></select></td></tr>");



		pw.println("<tr><td><h3>Close Time</h3></td><td>");
		pw.println("<select name='closeTime' class='input'>");
		pw.println("<option value='13:00' selected>13:00</option>");
		pw.println("<option value='14:00' selected>14:00</option>");
		pw.println("<option value='15:00' selected>15:00</option>");
		pw.println("<option value='16:00' selected>16:00</option>");
		pw.println("<option value='17:00' selected>17:00</option>");
		pw.println("<option value='18:00' selected>18:00</option>");
		pw.println("<option value='19:00' selected>19:00</option>");
		pw.println("<option value='20:00' selected>20:00</option>");
		pw.println("<option value='21:00' selected>21:00</option>");
		pw.println("<option value='22:00' selected>22:00</option>");
		pw.println("<option value='23:00' selected>23:00</option>");
		pw.println("<option value='24:00' selected>24:00</option></select></td></tr>");



		pw.println("</table>");

		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());


		pw.println("<input type='hidden' name='postTime' value='"+ formatter.format(date) +"'></input>");
		pw.println("<input type='hidden' name='hospitalId' value='"+ user.getId() +"'></input>");
		pw.println("<input type='submit' class='btnbuy' name='hospital' value='Create'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
		pw.println("</form></section></div>");


		utility.printHtml("Footer.html");

	}

}
