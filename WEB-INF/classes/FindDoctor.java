

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


@WebServlet("/FindDoctor")

public class FindDoctor extends HttpServlet {
    class ProductAvg{
        String productName;
        int count;
        double rate;
        public ProductAvg (String productName,int count, double rate) {
            this.productName = productName;
            this.count = count;
            this.rate = rate;
        }
    }


    private static final Comparator newComparator = new Comparator() {
        public int compare (Object o1, Object o2) {
            return (int)(((ProductAvg)o2).rate/((ProductAvg)o2).count  - ((ProductAvg)o1).rate/ ((ProductAvg)o1).count );
        }
    };



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);


        HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectDoctorReview();

        HashMap<String, ProductAvg> productRate = new HashMap<>();
       
        if (hm == null) {
            pw.println("<h2>Mongo Db server is not up and running</h2>");
        } else {
            for (Map.Entry<String, ArrayList<Review>> reviews : hm.entrySet()) {
                for (Review r : reviews.getValue()) {
                    if (!productRate.containsKey(r.getId())) {
                        ProductAvg productAvg = new ProductAvg(r.getId(),1, Double.parseDouble(r.getReviewrating()) );
                        productRate.put(r.getId(), productAvg);
                    } else {
                        ProductAvg val = productRate.get(r.getId());
                        productRate.put(r.getId(), new ProductAvg(r.getId(),val.count+1, val.rate + Double.parseDouble(r.getReviewrating())   ));
                    }
                }
            }
        }


        LinkedList<ProductAvg> ans =  new LinkedList<>();
        for (Map.Entry<String, ProductAvg> productAvg: productRate.entrySet()) {
            ans.add(productAvg.getValue());
        }
        Collections.sort(ans,newComparator);

        int reviewrating = Integer.parseInt(request.getParameter("reviewRating"));
        String compareRating = request.getParameter("compareRating");


        utility.printHtml("Header.html");

		pw.println("<div class='6u'><section><br>");

		pw.println("<section><article id='doctorsearch'><hr style='width: 95%'><h2 style='font-size: 25px;'>Find Doctor based on review</h2><hr style='width: 95%'>");

		pw.println("<table id='table1' style='width:80%'>		");
		pw.println("<tr><td>&nbsp;</td><td><b>DoctorName </b></td><td><b>Department </b></td><td><b>Location </b></td><td><b>Rate </b></td><td><b>DeatailPage</b></td></tr>		");
        
        
        for (ProductAvg ProductAvg: ans) {  
            for (Doctor doctor: MySqlDataStoreUtilities.viewDoctor()) {
                if (doctor.getDoctorId()== Integer.parseInt(ProductAvg.productName) ) {
                    if (compareRating.equals("EQUALS_TO")) {
                        if (ProductAvg.rate/ProductAvg.count == reviewrating) {
                            pw.println("<tr><td>&nbsp;</td>		");
                            pw.println("<td>"+doctor.getRealName()+"</td>");
                            pw.println("<td>"+  doctor.getDepartment()    +"</td>		");
                            pw.println("<td>"+  doctor.getLocation() + "</td>");
                            pw.println("<td>"+  Double.parseDouble(String.format("%.2f", ProductAvg.rate/ProductAvg.count)) + "</td>");
                            pw.println("<td>");
                            pw.println("<a href=DetailedDoctor?postId="+  doctor.getPostId()  +" >Detail Page</a> ");
                            pw.println("</td>");
                            pw.println("</tr>");
                        }
                    } else {
                        if (ProductAvg.rate/ProductAvg.count > reviewrating) {
                            pw.println("<tr><td>&nbsp;</td>		");
                            pw.println("<td>"+doctor.getRealName()+"</td>");
                            pw.println("<td>"+  doctor.getDepartment()    +"</td>		");
                            pw.println("<td>"+  doctor.getLocation() + "</td>");
                            pw.println("<td>"+  Double.parseDouble(String.format("%.2f", ProductAvg.rate/ProductAvg.count)) + "</td>");
                            pw.println("<td>");
                            pw.println("<a href=DetailedDoctor?postId="+  doctor.getPostId()  +" >Detail Page</a> ");
                            pw.println("</td>");
                            pw.println("</tr>");
                        }

                    }

                }
            }


        }

		pw.println("</table><article></section></div>");

		utility.printHtml("Footer.html");








    }
}
