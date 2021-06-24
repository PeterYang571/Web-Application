import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/Home")

/*
 * Home class uses the printHtml Function of Utilities class and prints the
 * Header,LeftNavigationBar, Content,Footer of Game Speed Application.
 * 
 */

public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		if (!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			System.out.println("LogIn: Not");
		} else {
			HttpSession session = request.getSession();
			User user = utility.getUser();
			// System.out.println("User|" + user.getUserName() + " | " + user.getPassword());
		}



		utility.printHtml("Header.html");
		ArrayList<News> newsList = ApiUtilities.getHealthNews();
		// Head news



		String headNews = newsList.get(0).getTitle();
		pw.print("<div class='6u'><section><header>");
		pw.print("<h2>" + newsList.get(0).getTitle() + "</h2>");
		pw.print("<span class='byline'>" + newsList.get(0).getPublishedAt() + ". By " + newsList.get(0).getAuthor()
				+ "</span></header>");
		pw.print("<p>" + newsList.get(0).getDescription() + "</p>");
		pw.print("<p><a href='DetailedNews?Title=" + newsList.get(0).getTitle().replaceAll("[^a-zA-Z0-9_-]", "") + "'><img src="
				+ newsList.get(0).getUrlToImage() + " 	alt='' width='550' height='300'></a></p>");
		pw.print("<a href='DetailedNews?Title=" + newsList.get(0).getTitle().replaceAll("[^a-zA-Z0-9_-]", "")
				+ "' class='button'>More Details</a></section></div>");
		// Other news
		pw.print("<div class='3u'><section class='sidebar'><header><h2>Other NEWS</h2></header>");
		pw.print("<ul class='style2'>");
		int i = 0;
		for (News news : newsList) {
			if (!news.getTitle().equals(headNews) && i < 3) {
				pw.print("<li><a href='DetailedNews?Title=" + news.getTitle().replaceAll("[^a-zA-Z0-9_-]", "") + "'><img src=" + news.getUrlToImage()
						+ " alt='' width='80' height='80'><p>" + news.getTitle() + "</p></a></li>");
				i++;
			}

		}




		pw.print("</ul></section></div>");

		utility.printHtml("Footer.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equals("Login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// System.out.println("Login : " + username + ":" + password);

			HashMap<String, User> hm = new HashMap<String, User>();
			// user details are validated using a file
			// if the file containts username and passoword user entered user will be
			// directed to home page
			// else error message will be shown
			try {
				hm = MySqlDataStoreUtilities.selectUser();
			} catch (Exception e) {

			}

			User user = hm.get(username);
			if (user != null) {
				String user_password = user.getPassword();
				if (password.equals(user_password)) {
					// System.out.println("Correct account!");
					HttpSession session = request.getSession(true);
					session.setAttribute("userid", user.getId());

					session.setAttribute("username", user.getUserName());
					session.setAttribute("usertype", user.getUsertype());
					response.sendRedirect("Home");
					return;
				} 
			}
			response.sendRedirect("Login?correct=false");

			

		} else if (request.getParameter("action").equals("AddReview")) {
			// String review = request.getParameter("review");
			// String rate = request.getParameter("rate");
			// // System.out.println("AddReview : " + review + ":" + rate);
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			// Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			// MongoDBDataStoreUtilities.insertDoctorReview("1", rate, timestamp+"", review);
			// HashMap<String, ArrayList<Review>> hm =MongoDBDataStoreUtilities.selectDoctorReview();
			// for (Map.Entry<String, ArrayList<Review>> entry: hm.entrySet() ){
			// 	for (Review r :entry.getValue()  ) {
			// 		System.out.println(r.getId() + ":" + r.getReviewdate() + ":" + r.getReviewtext() + ":" + r.getReviewrating());
			// 	}
			// }

		} else if ( request.getParameter("action").equals("Modify")) {
			PrintWriter pw = response.getWriter();

			Utilities utility = new Utilities(request, pw);
			User user = utility.getUser();
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String lat="";
			String longt="";
			ArrayList<String> res = ApiUtilities.getLatLongPositions(address);
			lat = res.get(0);
			longt = res.get(1);
			System.out.println("Update : " + password +":" +address);
			MySqlDataStoreUtilities.updateUser(user.getId(), password, address, lat, longt, address);

		}
		doGet(request, response);
	}

}
