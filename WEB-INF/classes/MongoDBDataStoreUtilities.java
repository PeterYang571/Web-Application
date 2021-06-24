import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;

import java.util.*;

public class MongoDBDataStoreUtilities {
    static DBCollection doctorReviews;
    static DBCollection hospitalReviews;
    static DBCollection insuranceReviews;

    public static void getConnection() {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("CustomerReviews");
        doctorReviews = db.getCollection("doctorReviews");
        hospitalReviews = db.getCollection("hospitalReviews");
        insuranceReviews = db.getCollection("insuranceReviews");
    }

    public static String insertDoctorReview(String doctorId, String userId, String reviewrating, String reviewdate,
            String reviewtext) {

        try {
            getConnection();

            BasicDBObject doc = new BasicDBObject("title", "doctorReviews").append("doctorId", doctorId)
                    .append("userId", userId).append("reviewrating", Integer.parseInt(reviewrating))
                    .append("reviewdate", reviewdate).append("reviewtext", reviewtext);
            doctorReviews.insert(doc);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }

    //////////////////////////////
    public static String insertHospitalReview(String hospitalId, String reviewrating, String reviewdate,
            String reviewtext) {

        try {
            getConnection();

            BasicDBObject hos = new BasicDBObject("title", "hospitalReviews").append("hospitalId", hospitalId)
                    .append("reviewrating", Integer.parseInt(reviewrating)).append("reviewdate", reviewdate)
                    .append("reviewtext", reviewtext);
            hospitalReviews.insert(hos);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }

    public static String insertInsuranceReview(String insuranceId, String reviewrating, String reviewdate,
            String reviewtext) {

        try {
            getConnection();

            BasicDBObject ins = new BasicDBObject("title", "insuranceReviews").append("insuranceId", insuranceId)
                    .append("reviewrating", Integer.parseInt(reviewrating)).append("reviewdate", reviewdate)
                    .append("reviewtext", reviewtext);
            insuranceReviews.insert(ins);
            return "Successfull";
        } catch (Exception e) {
            return "UnSuccessfull";
        }

    }
    ///////////////////////////////

    public static void insertDefaultDoctorReview() {
        insertDoctorReview("2", "1", "7", "2020-11-01 16:59:17.007", "review1");
        insertDoctorReview("2", "3", "4", "2020-11-01 16:59:17.007", "review7");
        insertDoctorReview("2", "4", "2", "2020-11-01 16:59:17.007", "review8");
        insertDoctorReview("5", "6", "4", "2020-11-01 16:59:17.007", "review2");
        insertDoctorReview("9", "7", "6", "2020-11-01 16:59:17.007", "review3");
        insertDoctorReview("7", "8", "8", "2020-11-02 23:34:10.917", "review4");
        insertDoctorReview("11", "9", "3", "2020-11-02 23:34:10.917", "review5");
        insertDoctorReview("12", "10", "9", "2020-11-02 23:34:10.917", "review6");

        insertDoctorReview("11", "11", "6", "2020-11-07 05:10:33.568", "review11");
        insertDoctorReview("12", "13", "8", "2020-11-08 19:55:02.403", "review12");
        insertDoctorReview("13", "14", "5", "2020-11-08 18:13:48.016", "review13");
        insertDoctorReview("14", "15", "8", "2020-11-09 02:38:30.743", "review14");
        insertDoctorReview("15", "16", "4", "2020-11-09 02:45:20.898", "review15");
        insertDoctorReview("16", "17", "10", "2020-11-10 23:34:10.917", "review16");
        insertDoctorReview("17", "18", "6", "2020-11-10 08:56:51.496", "review17");
        insertDoctorReview("18", "19", "9", "2020-11-11 23:40:53.423", "review18");
        insertDoctorReview("19", "20", "9", "2020-11-11 03:46:57.240", "review19");
        insertDoctorReview("20", "21", "9", "2020-11-11 22:33:31.979", "review20");
        insertDoctorReview("21", "22", "10", "2020-11-12 23:34:10.927", "review21");
        insertDoctorReview("22", "23", "9", "2020-11-12 05:02:18.606", "review22");
        insertDoctorReview("23", "24", "7", "2020-11-12 08:33:31.044", "review23");
        insertDoctorReview("24", "25", "6", "2020-11-13 06:15:03.549", "review24");
        insertDoctorReview("25", "26", "10", "2020-11-13 23:14:10.927", "review25");
        insertDoctorReview("26", "27", "4", "2020-11-14 18:54:33.156", "review26");
        insertDoctorReview("27", "28", "6", "2020-11-14 16:29:10.059", "review27");
        insertDoctorReview("28", "1", "6", "2020-11-14 10:26:20.667", "review28");
        insertDoctorReview("29", "2", "10", "2020-11-14 23:14:10.117", "review29");
        insertDoctorReview("30", "3", "7", "2020-11-15 08:17:01.875", "review30");
        insertDoctorReview("31", "4", "8", "2020-11-15 21:40:29.769", "review31");
        insertDoctorReview("32", "5", "6", "2020-11-15 23:46:14.771", "review32");
        insertDoctorReview("33", "6", "7", "2020-11-15 17:22:18.375", "review33");
        insertDoctorReview("34", "7", "6", "2020-11-15 23:47:29.520", "review34");
        insertDoctorReview("35", "8", "9", "2020-11-15 12:59:57.867", "review35");
        insertDoctorReview("36", "9", "9", "2020-11-15 11:40:03.984", "review36");

    }

    /////////////////////////////////////////////////
    public static void insertDefaultHospitalReview() {

        insertHospitalReview("61", "9", "2020-11-23 06:18:20.249", "review61");
        insertHospitalReview("62", "4", "2020-11-23 00:20:36.215", "review62");
        insertHospitalReview("63", "4", "2020-11-24 12:29:45.902", "review63");
        insertHospitalReview("64", "10", "2020-11-24 12:34:12.231", "review64");
        insertHospitalReview("65", "6", "2020-11-25 00:37:57.392", "review65");
        insertHospitalReview("66", "9", "2020-11-26 13:11:26.223", "review66");
        insertHospitalReview("67", "10", "2020-11-26 13:34:20.127", "review67");
        insertHospitalReview("68", "6", "2020-11-27 09:55:17.252", "review68");
        insertHospitalReview("69", "9", "2020-11-27 12:08:38.401", "review69");
        insertHospitalReview("70", "5", "2020-11-27 21:17:20.515", "review70");
        insertHospitalReview("71", "9", "2020-11-27 17:06:56.126", "review71");
        insertHospitalReview("72", "10", "2020-11-28 23:33:12.227", "review72");
        insertHospitalReview("73", "4", "2020-11-28 21:32:13.317", "review73");
        insertHospitalReview("74", "10", "2020-11-28 3:34:20.302", "review74");
        insertHospitalReview("75", "8", "2020-11-29 19:55:02.098", "review75");
        insertHospitalReview("76", "8", "2020-11-29 18:13:48.180", "review76");
        insertHospitalReview("77", "7", "2020-11-29 02:38:30.332", "review77");
        insertHospitalReview("78", "5", "2020-11-29 02:45:20.577", "review78");
        insertHospitalReview("79", "6", "2020-11-29 08:56:51.463", "review79");
        insertHospitalReview("80", "9", "2020-11-29 23:40:53.430", "review80");
        insertHospitalReview("81", "10", "2020-11-29 13:23:12.234", "review81");
        insertHospitalReview("82", "10", "2020-11-29 4:34:15.420", "review82");
        insertHospitalReview("83", "6", "2020-11-30 10:02:20.083", "review83");
        insertHospitalReview("84", "7", "2020-11-30 10:48:37.286", "review84");
        insertHospitalReview("85", "5", "2020-11-30 01:05:03.949", "review85");
        insertHospitalReview("86", "4", "2020-11-30 01:05:23.800", "review86");
        insertHospitalReview("87", "6", "2020-11-30 04:53:10.858", "review87");
        insertHospitalReview("88", "4", "2020-11-30 04:33:58.772", "review88");
        insertHospitalReview("89", "9", "2020-11-30 05:30:14.446", "review89");
        insertHospitalReview("90", "9", "2020-11-30 04:53:31.303", "review90");

    }

    public static void insertDefaultInsuranceReview() {
        insertInsuranceReview("91", "10", "2020-11-16 12:24:10.127", "review37");
        insertInsuranceReview("92", "9", "2020-11-17 15:49:52.673", "review38");
        insertInsuranceReview("93", "7", "2020-11-17 04:58:47.139", "review39");
        insertInsuranceReview("94", "4", "2020-11-17 20:42:54.319", "review40");
        insertInsuranceReview("95", "8", "2020-11-17 16:48:39.745", "review41");
        insertInsuranceReview("96", "6", "2020-11-18 00:19:47.886", "review42");
        insertInsuranceReview("97", "6", "2020-11-18 18:55:33.719", "review43");
        insertInsuranceReview("98", "6", "2020-11-18 01:22:57.874", "review44");
        insertInsuranceReview("99", "5", "2020-11-18 15:18:07.423", "review45");
        insertInsuranceReview("100", "6", "2020-11-18 16:51:42.574", "review45");
        insertInsuranceReview("101", "7", "2020-11-18 20:44:41.109", "review47");
        insertInsuranceReview("102", "5", "2020-11-18 11:19:22.342", "review48");
        insertInsuranceReview("103", "10", "2020-11-19 13:34:10.123", "review49");
        insertInsuranceReview("104", "6", "2020-11-20 07:30:28.990", "review50");
        insertInsuranceReview("105", "7", "2020-11-20 23:34:00.346", "review51");
        insertInsuranceReview("106", "7", "2020-11-20 10:05:43.722", "review52");
        insertInsuranceReview("107", "6", "2020-11-20 14:21:57.501", "review53");
        insertInsuranceReview("108", "10", "2020-11-21 22:33:12.227", "review54");
        insertInsuranceReview("109", "4", "2020-11-22 16:26:28.443", "review55");
        insertInsuranceReview("110", "9", "2020-11-22 02:53:54.363", "review56");
        insertInsuranceReview("111", "10", "2020-11-22 1:34:10.630", "review57");
        insertInsuranceReview("112", "5", "2020-11-23 16:40:46.959", "review58");
        insertInsuranceReview("113", "8", "2020-11-23 00:47:32.435", "review59");
        insertInsuranceReview("114", "9", "2020-11-23 10:35:28.747", "review60");
        insertInsuranceReview("115", "9", "2020-11-23 06:18:20.249", "review61");
        insertInsuranceReview("116", "4", "2020-11-23 00:20:36.215", "review62");
        insertInsuranceReview("117", "4", "2020-11-24 12:29:45.902", "review63");
        insertInsuranceReview("118", "10", "2020-11-24 12:34:12.231", "review64");
        insertInsuranceReview("119", "6", "2020-11-25 00:37:57.392", "review65");
        insertInsuranceReview("120", "9", "2020-11-26 13:11:26.223", "review66");

    }

    /////////////////////////////////////////////////
    public static HashMap<String, ArrayList<Review>> selectDoctorReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = doctorReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("doctorId"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("doctorId"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("doctorId"));
                Review review = new Review(obj.getString("doctorId"), obj.getString("reviewrating"),
                        obj.getString("reviewdate"), obj.getString("reviewtext"));
                // add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }

    }

    //////////////////////////////////////////////

    public static HashMap<String, ArrayList<Review>> selectHospitalReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = hospitalReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("hospitalId"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("hospitalId"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("hospitalId"));
                Review review = new Review(obj.getString("hospitalId"), obj.getString("reviewrating"),
                        obj.getString("reviewdate"), obj.getString("reviewtext"));
                // add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }

    }

    public static HashMap<String, ArrayList<Review>> selectInsuranceReview() {
        HashMap<String, ArrayList<Review>> reviews = null;

        try {

            getConnection();
            DBCursor cursor = insuranceReviews.find();
            reviews = new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext()) {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if (!reviews.containsKey(obj.getString("insuranceId"))) {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("insuranceId"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("insuranceId"));
                Review review = new Review(obj.getString("insuranceId"), obj.getString("reviewrating"),
                        obj.getString("reviewdate"), obj.getString("reviewtext"));
                // add to review hashmap
                listReview.add(review);

            }
            return reviews;
        } catch (Exception e) {
            reviews = null;
            return reviews;
        }

    }

    ////////////////////////////////////////////////

    // public static ArrayList<Bestrating> topProducts() {
    // ArrayList<Bestrating> Bestrate = new ArrayList<Bestrating>();
    // try {

    // getConnection();
    // int retlimit = 5;
    // DBObject sort = new BasicDBObject();
    // sort.put("reviewrating", -1);
    // DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
    // while (cursor.hasNext()) {
    // BasicDBObject obj = (BasicDBObject) cursor.next();

    // String prodcutnm = obj.get("productname").toString();
    // String rating = obj.get("reviewrating").toString();
    // Bestrating best = new Bestrating(prodcutnm, rating);
    // Bestrate.add(best);
    // }

    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return Bestrate;
    // }
}