

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/RecommanderRate")

public class RecommanderRate extends HttpServlet {




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		DoctorRecommenderUtility doctorRecommenderUtility = new DoctorRecommenderUtility();

		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
        prodRecmMap = doctorRecommenderUtility.readOutputFile();
        ArrayList<String> productsList = null;
        
        for(String user: prodRecmMap.keySet())
		{
            User u = utility.getUser();

			if(user.equals(u.getId()+""))
			{
				String products = prodRecmMap.get(user);
				products=products.replace("[","");
				products=products.replace("]","");
				products=products.replace("\"", " ");
                productsList = new ArrayList<String>(Arrays.asList(products.split(",")));
            } 
        }





        utility.printHtml("Header.html");

		pw.println("<div class='9u'><section><br>");

		pw.println("<section><article id='doctorsearch'><hr style='width: 95%'><h2 style='font-size: 25px;'>RecommanderRate</h2><hr style='width: 95%'>");

		pw.println("<table id='table1' style='width:80%'>		");
		pw.println("<tr><td>&nbsp;</td><td><b>DoctorName </b></td><td><b>Department </b></td><td><b>Location </b></td><td><b>Rank </b></td><td><b>DeatailPage</b></td></tr>		");
        
        int i = 1;
        if (productsList != null) {
            for(String prod : productsList){
                Doctor doctor = DoctorRecommenderUtility.getDoctor(Integer.parseInt(prod.replaceAll("[^a-zA-Z0-9_-]", "")) );
                pw.println("<tr><td>&nbsp;</td>		");
                pw.println("<td>"+doctor.getRealName()+"</td>");
                pw.println("<td>"+  doctor.getDepartment()    +"</td>		");
                pw.println("<td>"+  doctor.getLocation() + "</td>");
                pw.println("<td>"+  i + "</td>");
                pw.println("<td>");
                pw.println("<a href=DetailedDoctor?postId="+  doctor.getPostId()  +" >Detail Page</a> ");
                pw.println("</td>");
                pw.println("</tr>");
                i++;            
    
            }
        }

        


		pw.println("</table><article></section></div>");

		utility.printHtml("Footer.html");








    }
}
