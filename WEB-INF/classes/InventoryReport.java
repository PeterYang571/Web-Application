import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InventoryReport")

public class InventoryReport extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter pw = response.getWriter();






		/* Header, Left Navigation Bar are Printed.

		All the soundSystem and soundSystem information are dispalyed in the Content Section

		and then Footer is Printed*/

        Utilities utility = new Utilities(request,pw);
        utility.printHtml("Header.html");


		pw.println("<div class='6u'>");

        pw.print("<h2>Top provider</h2>");
        pw.print("<table>");
        pw.print("<tr>");
        pw.print("<td>UserName:</td>");
        pw.print("<td>Count:</td>");

        pw.print("<tr>");	
        for (List<String> list : MySqlDataStoreUtilities.viewTopBookProvider()) {

            pw.print("<td>"+ list.get(0)  +"</td><td>"+ list.get(1)+"</td>");

            pw.print("</tr>");

        }
        pw.print("</table>");
        pw.print("<br>");
        pw.print("<br>");
        pw.print("<br>");
        pw.print("<h2>Top date</h2>");
        pw.print("<table>");
        pw.print("<tr>");
        pw.print("<td>Date:</td>");
        pw.print("<td>Count:</td>");

        pw.print("<tr>");	
        for (List<String> list : MySqlDataStoreUtilities.viewTopBookDate()) {

            pw.print("<td>"+ list.get(0)  +"</td><td>"+ list.get(1)+"</td>");

            pw.print("</tr>");

        }
        pw.print("</table>");

        pw.print("<br>");
        pw.print("<br>");
        pw.print("<br>");

        pw.print("<h2>Bar Chart:</h2>");
        pw.print("<table  class='gridtable'>");


        pw.print("<h3><button id='btnGetChartData'>View Chart</h3>");
        pw.println("<div id='chart_div'></div>");
        pw.println("</div></div></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='InventoryReport.js'></script>");

        pw.print("</table>");


		pw.println("</div>");



        utility.printHtml("Footer.html");

    }

    class ProductSales {
        String product;
        String sales;
        public ProductSales(String product, String sales) {
            this.product = product;
            this.sales = sales;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {


            ArrayList<ProductSales> productSales = new ArrayList<>();

            for (List<String> list : MySqlDataStoreUtilities.viewTopBookDate()) {
                productSales.add(new ProductSales(list.get(0), list.get(1)));
            }
            
            
            String reviewJson = new Gson().toJson(productSales);
            System.out.println(reviewJson);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
