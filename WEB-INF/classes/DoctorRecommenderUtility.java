import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class DoctorRecommenderUtility{
	
	static Connection conn = null;
    static String message;
	
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database?autoReconnect=true&useSSL=false","root","root");
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}

	public HashMap<String,String> readOutputFile(){

		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
		String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\Project\\matrixFactorizationBasedRecommendations.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return prodRecmMap;

	}
	public static Doctor getDoctor(int doctorId)
	{	
		Doctor doctor = new Doctor();

		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Doctordetails where doctorId="+doctorId+" limit 1;";
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				doctor.setPostId(rs.getInt("postId"));
				doctor.setDoctorId(rs.getInt("doctorId"));
				doctor.setRealName(rs.getString("realName"));
				doctor.setDepartment(rs.getString("department"));
				doctor.setAddress(rs.getString("address"));
				doctor.setLat(rs.getString("locationLat"));
				doctor.setLongt(rs.getString("locationLong"));
				doctor.setLocation(rs.getString("location"));
				doctor.setOpenTime(rs.getString("OpenTime"));
				doctor.setCloseTime(rs.getString("closeTime"));
				doctor.setPostTime(rs.getString("postTime"));
			}

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return doctor;			
	}
	
	// public static Product getProduct(String product){
	// 	Product prodObj = new Product();
	// 	try 
	// 	{
	// 		String msg = getConnection();
	// 		String selectProd="select * from  Doctordetails where doctorId=?";
	// 		PreparedStatement pst = conn.prepareStatement(selectProd);
	// 		pst.setString(1,product);
	// 		ResultSet rs = pst.executeQuery();
		
	// 		while(rs.next())
	// 		{	
	// 			prodObj = new Product(rs.getString("ProductType"),rs.getString("productName"),rs.getDouble("productPrice"), rs.getString("productManufacturer"),rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getInt("productRebate"));
	// 		}
	// 		rs.close();
	// 		pst.close();
	// 		conn.close();
	// 	}
	// 	catch(Exception e)
	// 	{
	// 	}
	// 	return prodObj;	
	// }



}