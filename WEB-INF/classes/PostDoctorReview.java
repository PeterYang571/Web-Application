import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@WebServlet("/PostDoctorReview")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class PostDoctorReview extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");

		String doctorId = request.getParameter("doctorId");
		String postId = request.getParameter("postId");


		pw.println("<div class='9u'><section><header><h2>Add Review</h2></header>");
		pw.println("<form method='POST' action='PostDoctorReview'>");
		pw.println("<table style='width:100%'>");
		pw.println("<tr><td><h3>Review</h3></td><td>");
		pw.println("<textarea name='review' rows='4' cols='50' class='input' required></textarea></td></tr>");


		pw.println("<tr><td><h3>Rate</h3></td><td>");
		pw.println("<select name='rate' class='input'>");
		pw.println("<option value='0' selected>0</option>");
		pw.println("<option value='1'>1</option>");
		pw.println("<option value='2'>2</option>");
		pw.println("<option value='3'>3</option>");
		pw.println("<option value='4'>4</option>");
		pw.println("<option value='5'>5</option>");
		pw.println("<option value='6'>6</option>");
		pw.println("<option value='7'>7</option>");
		pw.println("<option value='8'>8</option>");
		pw.println("<option value='9'>9</option>");
		pw.println("<option value='10'>10</option>");

		pw.println("</select></td></tr></table>");
		User user = utility.getUser();
		pw.println("<input type='hidden' name='customerId' value='"+ user.getId()  +"'></input>");
		pw.println("<input type='hidden' name='doctorId' value='"+ doctorId  +  "'></input>");
		pw.println("<input type='hidden' name='postId' value='"+ postId  +  "'></input>");

		pw.println("<input type='submit' class='btnbuy' name='action' value='AddReview'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
		pw.println("</form></section></div>");


		utility.printHtml("Footer.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String doctorId = request.getParameter("doctorId");
		String postId = request.getParameter("postId");
		String customerId = request.getParameter("customerId");

		String review = request.getParameter("review");
		String rate = request.getParameter("rate");
		System.out.println("AddReview : " + review + ":" + rate);
		System.out.println("doctorId : " + doctorId + " postId:" + postId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		MongoDBDataStoreUtilities.insertDoctorReview(doctorId,customerId, rate, timestamp+"", review);


		response.sendRedirect("DetailedDoctor?postId="+postId);
	}

}
