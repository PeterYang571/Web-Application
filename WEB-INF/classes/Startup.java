import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Startup")

/*  
startup servlet Intializes HashMap in SaxParserDataStore
*/

public class Startup extends HttpServlet
{

	public void init() throws ServletException
    {

        if (MySqlDataStoreUtilities.viewBook().size() < 2) {
            System.out.println("Create Database");
            
            MySqlDataStoreUtilities.createTable();
            System.out.println("Insert Default data");
            MongoDBDataStoreUtilities.insertDefaultDoctorReview();
            MongoDBDataStoreUtilities.insertDefaultHospitalReview();
            MongoDBDataStoreUtilities.insertDefaultInsuranceReview();

            MySqlDataStoreUtilities.insertDefaultData();
        }



        MySqlDataStoreUtilities.updateMessage(1,"Check my eye 1st");
        MySqlDataStoreUtilities.updateMessage(5,"Check my eye 2nd");


    }
}
