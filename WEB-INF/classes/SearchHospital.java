import java.io.IOException;
import java.io.PrintWriter;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/SearchHospital")



public class SearchHospital extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);


		ArrayList<Hospital> hospitals;

		if (request.getParameter("searchName") != null) {
			hospitals = MySqlDataStoreUtilities.viewPartialHospital( request.getParameter("searchName"));
		} else {
			hospitals = MySqlDataStoreUtilities.viewHospital();
		}


		utility.printHtml("Header.html");
		// pw.println("<script type='text/javascript ' src ='javascript.js'></script>");

		pw.println("<div class='9u'><section><a href=SearchDoctor>Search Doctor</a><br><a href=SearchInsurance>Search Insurance</a><br>");
		if (!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			System.out.println("LogIn: Not");
		} else {
			HttpSession session = request.getSession();
			User user = utility.getUser();
			if (user.getUsertype().equals("hospital company")) {
				pw.println("<a href='PostHospital'><button type='button' class='btn btn-primary' style='background-color: #4CAF50;  border: none;color: white;padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;'>Post information</button></a>");
			}
			// System.out.println("User|" + user.getUserName() + " | " + user.getPassword());
		}
		pw.println("<section><article id='hospitalsearch'><hr style='width: 95%'><h2 style='font-size: 25px;'> Search Hospital</h2><hr style='width: 95%'>");

		pw.println("<form  method='Get' action='SearchHospital' >");
		pw.println("<input type='text' class='input	' id='searchId' placeholder='Search Name or location' size='50' name='searchName'  value='' >");
		pw.println("<div id='auto-row'><table id='complete-table' class='gridtable' style='width: 315px;'></table></div>");
		pw.println("<button class='btnbuy'>Submit</button>		");
		pw.println("</form>		");
		pw.println("<table id='table1' style='width:80%'>		");
		pw.println("<tr><td>&nbsp;</td><td><b>HospitalName </b></td><td><b>Location </b></td><td><b>DeatailPage</b></td></tr>		");
		
		for (Hospital hospital : hospitals) {
			pw.println("<tr><td>&nbsp;</td>		");
			pw.println("<td>"+hospital.getHospitalName()+"</td>");
			pw.println("<td>"+  hospital.getLocation() + "</td>");
			pw.println("<td>");
			pw.println("<a href=DetailedHospital?postId="+  hospital.getPostId()  +" >Detail Page</a> ");
			pw.println("</td>");
			pw.println("</tr>");
		}



		pw.println("</table><article></section></div>");

		utility.printHtml("Footer.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("hospital").equals("Create")) {
			String hospitalId = request.getParameter("hospitalId");
			String realName = request.getParameter("realName");
			// String department = request.getParameter("department");
			String address = request.getParameter("address");
			String lat="";
			String longt="";
			ArrayList<String> res = ApiUtilities.getLatLongPositions(address);
			lat = res.get(0);
			longt = res.get(1);


			String location = request.getParameter("location");
			String openTime = request.getParameter("openTime");
			String closeTime = request.getParameter("closeTime");
			String postTime = request.getParameter("postTime");

			MySqlDataStoreUtilities.insertHospital(Integer.parseInt(hospitalId) , realName, address, lat, longt, location, openTime, closeTime, postTime);
		} 
		

		doGet(request, response);
	}

}
