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

@WebServlet("/RecommandationDoctor")



public class RecommandationDoctor extends HttpServlet {

	// private Comparator newComparator = new Comparator() {
	// 	PrintWriter pw = response.getWriter();

	// 	Utilities utility = new Utilities(request, pw);

	// 	User user = utility.getUser();

    //     public int compare (Doctor o1, Doctor o2) {
    //         return utility.getDistance(user.getLat(), user.getLongt(), o1.getLat(), o1.getLongt())  -  utility.getDistance(user.getLat(), user.getLongt(), o2.getLat(), o2.getLongt())   ;
    //     }
	// };
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to do actions");
			response.sendRedirect("Login");
			return;
		}

        final User user = utility.getUser();


        ArrayList<Doctor> doctors = MySqlDataStoreUtilities.viewDoctor();
        
        Comparator newComparator = new Comparator() {
            // PrintWriter pw = response.getWriter();
    
            // Utilities utility = new Utilities(request, pw);


    
    
            public int compare (Object o1, Object o2) {
                return (int)(getDistance(user.getLat(), user.getLongt(), ((Doctor)o1).getLat(), ((Doctor)o1).getLongt())  -  getDistance(user.getLat(), user.getLongt(), ((Doctor)o2).getLat(), ((Doctor)o2).getLongt())  )  ;
            }
        };

        Collections.sort(doctors,newComparator);
		
		


		utility.printHtml("Header.html");

		pw.println("<script>");
		pw.println("function initMap() {");
		pw.println("const myLatLng = { lat: "+ user.getLat()   +", lng: "+ user.getLongt() +" };");


		pw.println("const map = new google.maps.Map(document.getElementById('map'), {");
		pw.println("zoom: 3,");
		pw.println("center: myLatLng,});");

		//Top 5 Doctor's location base on distance
		for (int i = 0; i < 5; i++) {
			Doctor doctor = doctors.get(i);
			pw.println("new google.maps.Marker({");
			pw.println("position: { lat: "+ doctor.getLat()   +", lng: "+ doctor.getLongt() +" },");
			pw.println("map,");
			pw.println("title: 'Hello World!',");
			pw.println("});");
		}

		pw.println("}</script>");
		pw.println("<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyC1V_mk_-XXKc8e-N1W0N4g6zZRC_YZLMI&callback=initMap&libraries=&v=weekly' defer></script>");
		pw.println("<div class='9u'><section><header><div align='center'><section><h2>Recommandation:Doctor</h2></section></div><hr><table style='width:100% '>");
		pw.println("<tr><th>Name</th><th>Department</th><th>Location</th><th>Distance</th><th>Detailed Page</th></tr>");

		for (int i = 0; i < 5; i++) {
			Doctor doctor = doctors.get(i);
			pw.println("<tr>");
			pw.println("<td>"+ doctor.getRealName()  + "</td>");
			pw.println("<td>"+  doctor.getDepartment() +"</td>");
			pw.println("<td>"+ doctor.getLocation()  +"</td>");
			pw.println("<td>" +  Double.parseDouble(String.format("%.2f",  getDistance(user.getLat(), user.getLongt(), doctor.getLat()  ,  doctor.getLongt())    ))      +" miles</td>");
			pw.println("<td><a href=DetailedDoctor?postId="+  doctor.getPostId()  +" >Detail Page</a> </td>");
			pw.println("</tr>");			
		}		
		pw.println("</table>");
		pw.println("<span class='byline'></span> <br><div id='map' style='height:400px; width:100%'></div>");
		pw.println("</section></div>");


		utility.printHtml("Footer.html");

	}


	public double getDistance(String lat_1, String lon_1, String lat_2, String lon_2) {


        double lat1 = Double.parseDouble(lat_1);
        double lon1 = Double.parseDouble(lon_1);
        double lat2 = Double.parseDouble(lat_2);
        double lon2 = Double.parseDouble(lon_2);

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts decimal degrees to radians : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts radians to decimal degrees : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
	}
	




}
