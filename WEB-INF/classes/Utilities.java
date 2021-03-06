
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/*
 * Utilities class contains class variables of type HttpServletRequest,
 * PrintWriter,String and HttpSession.
 *
 * Utilities class has a constructor with HttpServletRequest, PrintWriter
 * variables.
 *
 */

public class Utilities extends HttpServlet {
    HttpServletRequest req;
    PrintWriter pw;
    String url;
    HttpSession session;

    public Utilities(HttpServletRequest req, PrintWriter pw) {
        this.req = req;
        this.pw = pw;
        this.url = this.getFullURL();
        this.session = req.getSession(true);

    }

    /*
     * Printhtml Function gets the html file name as function Argument, If the html
     * file name is Header.html then It gets Username from session variables.
     * Account ,Cart Information ang Logout Options are Displayed
     */

    public void printHtml(String file) {
        String result = HtmlToString(file);
        // to print the right navigation in header of username cart and logout etc
        if (file == "Header.html") {
            if (session.getAttribute("username") != null) {
                String username = session.getAttribute("username").toString();
                username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
                User user = getUser();
                if (user.getUsertype().equals("admin")) {
                    result = result + "<li><a href='InventoryReport'>Visualization</a></li>";
                    result = result + "<li><a href='DataAnalytics'>DataAnalytics</a></li>";
                    result = result + "<li><a href='Logout'>Logout</a></li>";
                } else {
                    result = result + "<li><a href='PersonalProfile'>Hello," + user.getUserName() + "</a></li>";
                    result = result
                            + "<li><a href='BookingCustomer'>Reservation</a></li><li><a href='Setting'>Setting</a></li><li><a href='Logout'>Logout</a></li>";
                }

            } else {
                result = result + "<li><a href='Login'>Login/Signup</a></li>";
            }
            result += HtmlToString("Header2.html");
            pw.print(result);

        } else
            pw.print(result);
    }

    /* getFullURL Function - Reconstructs the URL user request */

    public String getFullURL() {
        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/");
        return url.toString();
    }

    /*
     * HtmlToString - Gets the Html file and Converts into String and returns the
     * String.
     */
    public String HtmlToString(String file) {
        String result = null;
        try {
            String webPage = url + file;
            URL url = new URL(webPage);
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            result = sb.toString();
        } catch (Exception e) {
        }
        return result;
    }

    /*
     * logout Function removes the username , usertype attributes from the session
     * variable
     */

    public void logout() {
        session.removeAttribute("userid");
        session.removeAttribute("username");
        session.removeAttribute("usertype");
    }

    /* logout Function checks whether the user is loggedIn or Not */

    public boolean isLoggedin() {
        if (session.getAttribute("username") == null)
            return false;
        return true;
    }

    /* username Function returns the username from the session variable. */

    public String username() {
        if (session.getAttribute("username") != null)
            return session.getAttribute("username").toString();
        return null;
    }

    /* usertype Function returns the usertype from the session variable. */
    public String usertype() {
        if (session.getAttribute("usertype") != null)
            return session.getAttribute("usertype").toString();
        return null;
    }

    /*
     * getUser Function checks the user is a customer or retailer or manager and
     * returns the user class variable.
     */
    public User getUser() {
        String usertype = usertype();
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            hm = MySqlDataStoreUtilities.selectUser();
        } catch (Exception e) {
        }
        User user = hm.get(username());
        return user;
    }



}
