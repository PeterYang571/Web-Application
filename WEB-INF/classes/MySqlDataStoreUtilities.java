import java.sql.*;
import java.util.*;

public class MySqlDataStoreUtilities {
    static Connection conn = null;

    public static void getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project_database?autoReconnect=true&useSSL=false", "root", "root");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable() {
        try {

            getConnection();
            conn.prepareStatement(
                    "create table Registration(userId integer NOT NULL AUTO_INCREMENT,userName varchar(100),password varchar(40),email varchar(60),usertype varchar(40),address varchar(100),locationLat varchar(20),locationLong varchar(20),location  varchar(30),Primary key(userId));")
					.execute();
					//change
            conn.prepareStatement(
                    "Create table Appointment(bookId integer NOT NULL AUTO_INCREMENT,providerId integer,customerId integer,appointmentDate date,appointmentTime time, primary key(bookId));")
                    .execute();
            conn.prepareStatement(
                    "Create table Doctordetails(postId integer NOT NULL AUTO_INCREMENT,doctorId integer,realName varchar(30),department varchar(40),address varchar(60),locationLat varchar(20),locationLong varchar(20),location  varchar(30),OpenTime varchar(20), closeTime varchar(20), postTime date,Primary key(postId));")
					.execute();
			conn.prepareStatement(
					"Create table Hospitaldetails(postId integer NOT NULL AUTO_INCREMENT,hospitalId integer,hospitalName varchar(100),address varchar(100),locationLat varchar(20),locationLong varchar(20),location  varchar(30),openTime varchar(20), closeTime varchar(20), postTime date,Primary key(postId));")
					.execute();
			conn.prepareStatement(
					"Create table Insurancedetails(postId integer NOT NULL AUTO_INCREMENT,insuranceId integer,insuranceName varchar(100),address varchar(100),locationLat varchar(20),locationLong varchar(20),location  varchar(30),openTime varchar(20), closeTime varchar(20), postTime date,Primary key(postId));")
					.execute();
            conn.prepareStatement(
                    "create table Message (messageId integer NOT NULL AUTO_INCREMENT, customerId integer, providerId integer, message varchar(1000), primary key(messageId));")
                    .execute();
            conn.prepareStatement(
                    "Create view  process as Select messageId, userName as 'CustomerName', providerId, message  from Message inner join Registration on Message.customerId = Registration.userId;")
                    .execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertDefaultData() {
        try {

            getConnection();
            conn.prepareStatement(
                    "INSERT INTO Registration (userId, userName, password, email, usertype, address, locationLat, locationLong, location)"
					+" VALUES (1,'Tom','tom','tom@gmail.com','customer','215 West Washington, W Washington St, Chicago, IL 60606','41.8828584','-87.6363466','Chicago, IL'),"
					+" (2,'Bill', 'bill','bill@gmail.com','doctor','3000 N Halsted St Ste 720, Chicago, IL 60657','41.9367044','-87.6519388','Chicago, IL'),"
					+" (3,'Lily','lily','lily@gmail.com','customer','255 King St, San Francisco, CA 94107','37.7495887','-122.4381021','San Francisco, CA'),"
					+" (4,'Jason','jason','jason@gmail.com','customer','1700 Newbury Park Dr, San Jose, CA 95133','37.3750217','-121.8650577','San Jose, CA'),"
					+" (5,'Ming','ming','ming@gmail.com','doctor','909 Hyde St #432, San Francisco, CA 94109','37.789371','-122.4194441','San Francisco, CA'),"
					+" (6,'Justin','justin','justin@gmail.com','customer','101 Colorado St, Austin, TX 78701','30.2641372','-97.7477867','Austin, TX'),"
					+" (7,'Brynna','brynna ','brynna@gmail.com','doctor','2911 Medical Arts St Building #10, Austin, TX 78705','30.2880745','-97.7295075','Austin, TX'),"
					+" (8,'Gina','gina','tina@gmail.com','customer','2829 27th St W, Williston, ND 58801','48.1700719','-103.6650649','Williston, ND'),"
					+" (9,'Ryan ','ryan ','ryan@gmail.com','doctor','1321 W Dakota Pkwy, Williston, ND 58801','48.1559559','-103.6530379','Williston, ND'),"
					+" (10,'David','david','david@gmail.com','customer','111 N Wabash Ave STE 1412, Chicago, IL 60602','41.8834746','-87.6279434','Chicago, IL'),"
					+" (11,'Milton','milton','milton@gmail.com','doctor','210 East 64th Street, 7th Floor, New York City, NY 10065','40.7640723','-73.9655038','New York City, NY'),"
					+" (12,'David','david','david@gmail.com','doctor','111 N Wabash Ave STE 1412, Chicago, IL 60602','41.8834746','-87.6279434','Chicago, IL'),"
					+" (13,'Shirish','shirish','shirish@gmail.com','doctor','5600 W Addison St Ste 400, Chicago, IL 60634','41.9460911','-87.7693311','Chicago, IL'),"
					+" (14,'Adrian','adrian','adrian@gmail.com','doctor','5200 Jose Figueres Ave #215, San Jose, CA 95116','37.3615718','-121.8524164','San Jose, CA'),"
					+" (15,'Russell','russell','russell@gmail.com','doctor','8875 Centre Park Dr suite d, Columbia, MD 21045','39.2289939','-76.8156736','Columbia, MD'),"
					+" (16,'March','march','march@gmail.com','doctor','131 Summer Pl Dr, West Columbia, SC 29169','34.0042347','-81.1231826','West Columbia, SC'),"
					+" (17,'Mark','mark','mark@gmail.com','doctor','11200 Gov Manly Way #309, Raleigh, NC 27614','35.9477509','-78.543784','Raleigh, NC'),"
					+" (18,'Jon','jon','jon@gmail.com','doctor','55 Fruit St, Boston, MA 02114','42.362706','-71.0708467','Boston, MA'),"
					+" (19,'Peter','peter','peter@gmail.com','doctor','330 Brookline Ave, Boston, MA 02215','42.3398798','-71.106747','Boston, MA'),"
					+" (20,'Robert','robert','robert@gmail.com','doctor','725 Albany St #3b, Boston, MA 02118','42.3348727','-71.0743435','Boston, MA'),"
					+" (21,'Edward','edward','edward@gmail.com','doctor','175 Remsen St 12th floor, Brooklyn, NY 11201','40.6934999','-73.9935536','Brooklyn, NY'),"
					+" (22,'Ken','ken','ken@gmail.com','doctor','110 E 40th St Floor 6, New York, NY 10016','40.7504117','73.980187','New York, NY'),"
					+" (23,'Wong','wong','wong@gmail.com','doctor','945 Hildebrand Ln NE, Bainbridge Island, WA 98110','47.6336187','-122.5199271','Bainbridge Island, WA'),"
					+" (24,'John','john','john@gmail.com','doctor','9155 SW Barnes Rd #430, Portland, OR 97225','45.510411','-122.772308','Portland, OR'),"
					+" (25,'Erin McCann','1234','erin@gmail.com','doctor','1658 N Milwaukee Ave, Chicago, IL 60647','41.911521','-87.6820203','Chicago, IL'),"
					+" (26,'Jennifer N. Ouchi','1234','jennifer@gmail.com','doctor','8635 W 3rd St, Los Angeles, CA 90048','34.0739596','-118.3831086','Los Angeles, CA'),"
					+" (27,'Nayantara','1234','Nayantara@gmail.com','doctor','2250 Hayes St Ste. 612, San Francisco, CA 94117','37.7731512','-122.4556097','San Francisco, CA'),"
					+" (28,'Liu','1234','liu@gmail.com','doctor','400 Parnassus Ave Suite A-550, San Francisco, CA 94143','37.7645542','-122.4594448','San Francisco, CA'),"
					+" (29,'Sujani','1234','Sujani@gmail.com','doctor','1 Gustave L. Levy Pl, New York, NY 10029','40.7900079','-73.9559261','New York, NY '),"
					+" (30,'Joseph','1234','Joseph@gmail.com','doctor','6835 Austin Center Blvd, Austin, TX 78731','30.3512728','-97.752607','Austin, TX'),"
					+" (31,'Rosen','1234','Rosen@gmail.com','doctor','3000 N Interstate 35 Frontage Rd, Austin, TX 78705','30.2884636','-97.7264813','Austin, TX'),"
					+" (32,'Ayman','1234','Ayman@gmail.com','doctor','4302 Alton Rd #400, Miami Beach, FL 33140','25.8140468','-80.1418685','Miami Beach, FL'),"
					+" (33,'Michael','1234','Michael@gmail.com','doctor','4308 Alton Rd, Miami Beach, FL 33140','25.8148808','-80.1426972','Miami Beach, FL'),"
					+" (34,'Gerald','1234','Gerald@gmail.com','doctor','321 N Kuakini St STE 802, Honolulu, HI 96817','21.3215492','-157.858117','Honolulu, HI'),"
					+" (35,'Kore','1234','Kore@gmail.com','doctor','1329 Lusitana St #307, Honolulu, HI 96813','21.3076091','-157.8542824','Honolulu, HI'),"
					+" (36,'Cho','1234','Cho@gmail.com','doctor','9155 SW Barnes Rd #430, Portland, OR 97225','45.510411','-122.7723133','Portland, OR'),"
					+" (37,'Paul','1234','Paul@gmail.com','customer','5100 Iroquois Dr, Ewa Beach, HI 96706','21.3250438','-157.9873356','Ewa Beach, HI'),"
					+" (38,'Steven','1234','Steven@gmail.com','customer','94 Waipahu St, Waipahu, HI 96797','21.3860078','-158.0089307','Waipahu, HI'),"
					+" (39,'Andrew','1234','Andrew@gmail.com','customer','761 Wakea St, Kapolei, HI 96707','21.3300714','-158.079894','Kapolei, HI'),"
					+" (40,'Kenneth','1234','Kenneth@gmail.com','customer','10599 Wilshire Blvd #110, Los Angeles, CA 90024','34.0623202','-118.4357091','Los Angeles, CA'),"
					+" (41,'Joshua','1234','Joshua@gmail.com','customer','232 E 2nd St, Los Angeles, CA 90012','34.049037','-118.244349','Los Angeles, CA'),"
					+" (42,'Kevin','1234','Kevin@gmail.com','customer','6201 Hollywood Blvd, Los Angeles, CA 90028','34.1020008','-118.3262434','Los Angeles, CA'),"
					+" (43,'Brian','1234','Brian@gmail.com','customer','1000 Abberly Village Cir, West Columbia, SC 29169','34.0122052','-81.1155177','West Columbia, SC'),"
					+" (44,'George','1234','George@gmail.com','customer','127 State St, West Columbia, SC 29169','33.9937528','-81.0579316','West Columbia, SC'),"
					+" (45,'Alan','1234','Alan@gmail.com','customer','800 State St, West Columbia, SC 29169','33.9866624','-81.0590445','West Columbia, SC'),"
					+" (46,'Juan','1234','Juan@gmail.com','customer','4 Lucy St, Boston, MA 02121','42.3242775','-71.062325','Boston, MA'),"
					+" (47,'Logan','1234','Logan@gmail.com','customer','55 Traveler St, Boston, MA 02118','42.3440127','-71.0625442','Boston, MA'),"
					+" (48,'Billy','1234','Billy@gmail.com','customer','101 S Huntington Ave, Boston, MA 02130','42.3294042','-71.1115223','Boston, MA'),"
					+" (49,'Dylan','1234','Dylan@gmail.com','customer','900 West Ave, Miami Beach, FL 33139','25.7794671','-80.1425595','Miami Beach, FL '),"
					+" (50,'Sean','1234','Sean@gmail.com','customer','3929 Meridian Ave, Miami Beach, FL 33140','25.812443','-80.1331626','Miami Beach, FL '),"
					+" (51,'Joe','1234','Joe@gmail.com','customer','727 Collins Ave, Miami Beach, FL 33139','25.7774202','-80.1321071','Miami Beach, FL '),"
					+" (52,'Jesse','1234','Jesse@gmail.com','customer','8800 N Interstate Hwy 35, Austin, TX 78753','30.3522149','-97.6928411','Austin, TX'),"
					+" (53,'Noah','1234','Noah@gmail.com','customer','10600 Brezza Ln, Austin, TX 78748','30.1508203','-97.7958844','Austin, TX'),"
					+" (54,'Lawrence','1234','Lawrence@gmail.com','customer','1720 Ala Moana Blvd, Honolulu, HI 96815','21.2860629','-157.8389005','Honolulu, HI'),"
					+" (55,'Carl','1234','Carl@gmail.com','customer','1300 Tribute Center Drive, Raleigh, NC 27612','35.8496689','-78.6908377','Raleigh, NC'),"
					+" (56,'Keith','1234','Keith@gmail.com','customer','141 Park at N Hills St STE 110, Raleigh, NC 27609','35.8496689','-78.6908377','Raleigh, NC'),"
					+" (57,'Gerald','1234','Gerald@gmail.com','customer','6350 Terra Verde Dr, Raleigh, NC 27609','35.8678324','-78.6398285','Raleigh, NC'),"
					+" (58,'Dennis','1234','Dennis@gmail.com','customer','541 Homestead Ln NE, Bainbridge Island, WA 98110','47.629461','-122.5189737','Bainbridge Island, WA'),"
					+" (59,'Alexander','1234','Alexander@gmail.com','customer','4779 Lynwood Center Rd NE, Bainbridge Island, WA 98110','47.6065271','-122.5479528','Bainbridge Island, WA'),"
					+" (60,'Patrick','1234','Patrick@gmail.com','customer','220 Parfitt Way SW # 220, Bainbridge Island, WA 98110','47.6232989','-122.522689','Bainbridge Island, WA'),"
					+" (61,'Chicago Pediatric and Neonatology','1234','xx@gmail.com','hospital company','7447 W Talcott Ave # 561, Chicago, IL 60631','41.9876675','-87.8145514','Chicago, IL'),"
					+" (62,'Donohoe Pediatrics','1234','xx@gmail.com','hospital company','3916 N Damen Ave., Chicago, IL 60618','41.9529772','-87.6790409','Chicago, IL'),"
					+" (63,'MIRACLE MILE PEDIATRICS','1234','xdx@gmail.com','hospital company','6221 Wilshire Blvd #215, Los Angeles, CA 90048','34.063501','-118.3640248','Los Angeles, CA'),"
					+" (64,'San Francisco Medical Center and Medical Offices','1234','xxxx@gmail.com','hospital company','2238 Geary Blvd 7th floor, San Francisco, CA 94115','37.7833688','-122.4406775','San Francisco, CA'),"
					+" (65,'Brooklyn Hospital Center-Hematology','1234','dc3@gmail.com','hospital company','121 Dekalb Ave, Brooklyn, NY 11201','40.7240049','-73.9789535','Brooklyn, NY'),"
					+" (66,'Lenox Hill Hospital','1234','xd9x@gmail.com','hospital company','100 E 77th St, New York, NY 10075','40.691265','-73.9777743','New York, NY'),"
					+" (67,'Mount Sinai Kravis Childrens Hospital','1234','xx3d@gmail.com','hospital company','1184 5th Ave, New York, NY 10029','40.7900579','-73.9538592','New York, NY'),"
					+" (68,'St. David`s South Austin Medical Center','1234','xxse@gmail.com','hospital company','901 W Ben White Blvd, Austin, TX 78704','30.2261149','-97.7745674','Austin, TX'),"
					+" (69,'Baylor Scott & White Medical Center','1234','wsw@gmail.com','hospital company','5245 W US Hwy 290 Service Rd, Austin, TX 78735','30.2358362','-97.8344989','Austin, TX'),"
					+" (70,'Shoal Creek Hospital','1234','eee@gmail.com','hospital company','3529 Mills Ave, Austin, TX 78731','30.3059555','-97.7484764','Austin, TX'),"
					+" (71,'Orlando Health Arnold Palmer Hospital for Children','1234','rrr@gmail.com','hospital company','92 W Miller St, Orlando, FL 32806','28.4590107','-81.4968755','Orlando, FL '),"
					+" (72,'Orlando Health Heart & Vascular Institute','1234','rg@gmail.com','hospital company','1222 S Orange Ave, Orlando, FL 32806','28.5233918','-81.3800672','Orlando, FL '),"
					+" (73,'South Miami Hospital','1234','re@gmail.com','hospital company','6200 SW 73rd St, Miami, FL 33143','25.7029961','-80.2943575','Miami, FL'),"
					+" (74,'Mercy Hospital','1234','fdsg@gmail.com','hospital company','3663 S Miami Ave, Miami, FL 33133','25.7400049','-80.213526','Miami, FL'),"
					+" (75,'Hawaii Cancer Care','1234','fgs@gmail.com','hospital company','1329 Lusitana St #307, Honolulu, HI 96813','21.3076091','-157.8520883','Honolulu, HI'),"
					+" (76,'Straub Outpatient Treatment Center','1234','rgl@gmail.com','hospital company','888 S King St, Honolulu, HI 96813','21.3025814','-157.8501322','Honolulu, HI'),"
					+" (77,'Howard County General Hospital','1234','fg@gmail.com','hospital company','5755 Cedar Ln, Columbia, MD 21044','39.2141113','-76.885968','Columbia, MD'),"
					+" (78,'Oncology/Hematology California Pacific Medical Center','1234','468d@gmail.com','hospital company','2238 Geary Blvd 7th floor, San Francisco, CA 94115','37.7833688','-122.4406775','San Francisco, CA'),"
					+" (79,'Pali Momi Medical Center','1234','juy@gmail.com','hospital company','98-1079 Moanalua Rd, Aiea, HI 96701','21.3835987','-157.9382973','Aiea, HI'),"
					+" (80,'Duke Raleigh Hospital','1234','uj@gmail.com','hospital company','3400 Wake Forest Rd, Raleigh, NC 27609','35.8284188','-78.6183679','Raleigh, NC'),"
					+" (81,'WakeMed North Hospital','1234','jm@gmail.com','hospital company','10000 Falls of Neuse Rd, Raleigh, NC 27614','35.9091252','-78.5977337','Raleigh, NC'),"
					+" (82,'Holly Hill Hospital','1234','ng@gmail.com','hospital company','3019 Falstaff Rd, Raleigh, NC 27610','35.782772','-78.5839164','Raleigh, NC'),"
					+" (83,'Lexington Medical Center','1234','gh@gmail.com','hospital company','2720 Sunset Blvd, West Columbia, SC 29169','34.0068841','-81.1137621','West Columbia, SC'),"
					+" (84,'St. Michael Medical Center','1234','ut@gmail.com','hospital company','2520 Cherry Ave, Bremerton, WA 98310','47.5829409','-122.6251543','Bainbridge Island, WA'),"
					+" (85,'Swedish Primary Care','1234','as@gmail.com','hospital company','945 Hildebrand Ln NE Suite 100, Bainbridge Island, WA 98110','47.6336543','-122.5177735','Bainbridge Island, WA'),"
					+" (86,'Virginia Mason Hospital and Seattle Medical Center','1234','dw@gmail.com','hospital company','1100 9th Ave, Seattle, WA 98101','47.6094886','-122.327968','Bainbridge Island, WA'),"
					+" (87,'OHSU Hospital','1234','xz@gmail.com','hospital company','3181 SW Sam Jackson Park Rd, Portland, OR 97239','45.4980686','-122.6850168','Portland, OR'),"
					+" (88,'Vibra Specialty Hospital of Portland','1234','cds@gmail.com','hospital company','10300 NE Hancock St, Portland, OR 97220','45.5356684','-122.5565101','Portland, OR'),"
					+" (89,'Providence Portland Medical Center','1234','cd@gmail.com','hospital company','4805 NE Glisan St, Portland, OR 97213','45.5281789','-122.6119391','Portland, OR'),"
					+" (90,'Legacy Emanuel Medical Center','1234','xs@gmail.com','hospital company','2801 N Gantenbein Ave, Portland, OR 97227','45.5436861','-122.6695411','Portland, OR'),"
					+" (91,'Cost-U-Less Insurance','1234','rte@gmail.com','insurance company','1152 N Capitol Ave, San Jose, CA 95132','37.3879017','-121.8599859','San Jose, CA'),"
					+" (92,'L V Insurance','1234','geg@gmail.com','insurance company','1625 Flickinger Ave, San Jose, CA 95131','37.3901792','-121.8791306','San Jose, CA'),"
					+" (93,'Fred Loya Insurance','1234','dfg@gmail.com','insurance company','154 S Jackson Ave, San Jose, CA 95116','37.3563654','-121.840821','San Jose, CA'),"
					+" (94,'Texas Mutual Insurance Company','1234','fsf@gmail.com','insurance company','2200 Aldrich St, Austin, TX 78723','30.3010368','-97.7035713','Austin, TX'),"
					+" (95,'Texas Life Insurance Co','1234','xcs@gmail.com','insurance company','229 Parkhill St, Huntsville, TX 77340','30.7061257','-95.5336161','Huntsville, TX'),"
					+" (96,'Texas Insurance Place','1234','hy@gmail.com','insurance company','4101 W Green Oaks Blvd ste 305-105, Arlington, TX 76016','32.6820499','-97.1948966','Arlington, TX'),"
					+" (97,'Island Insurance Hawaii','1234','bgf@gmail.com','insurance company','1022 Bethel St, Honolulu, HI 96813','21.3101916','-157.8623647','Honolulu, HI'),"
					+" (98,'First Insurance Company of Hawaii, LTD','1234','kyht@gmail.com','insurance company','1100 Ward Ave, Honolulu, HI 96814','21.3035122','-157.8498528','Honolulu, HI'),"
					+" (99,'Farmers Insurance Hawaii','1234','g54@gmail.com','insurance company','Six Waterfront Plaza, 500 Ala Moana Blvd 5th Floor, Honolulu, HI 96813','21.3018247','-157.8619678','Honolulu, HI'),"
					+" (100,'Insurance Company of Florida','1234','rge4@gmail.com','insurance company','3751 Maryweather Ln #102, Wesley Chapel, FL 33544','28.2118594','-82.356133','Wesley Chapel, FL'),"
					+" (101,'Great Florida Insurance','1234','ef3@gmail.com','insurance company','11362 Orange Blossom Trail, Orlando, FL 32837','28.400058','-81.4060582','Orlando, FL'),"
					+" (102,'Florida Peninsula Insurance Company','1234','yu6@gmail.com','insurance company','903 NW 65th St, Boca Raton, FL 33487','26.406303','-80.1047321','Boca Raton, FL'),"
					+" (103,'Florida Family Insurance Co','1234','j76@gmail.com','insurance company','27599 Riverview Center Blvd # 100, Bonita Springs, FL 34134','26.3397713','-81.8087294','Bonita Springs, FL '),"
					+" (104,'Westside Portland Insurance Company','1234','j767@gmail.com','insurance company','1615 SE, SE Tualatin Valley Hwy STE.1, Hillsboro, OR 97123','45.5092931','-122.9661441','Hillsboro, OR'),"
					+" (105,'Timmco Insurance, Inc.','1234','th6@gmail.com','insurance company','1615 NE Broadway, Portland, OR 97232','45.5352682','-122.6490668','Portland, OR'),"
					+" (106,'Chet Hill Insurance','1234','he3@gmail.com','insurance company','10402 NE Sandy Blvd, Portland, OR 97220','45.5587471','-122.5555748','Portland, OR'),"
					+" (107,'Vern Fonk Insurance','1234','jjg6@gmail.com','insurance company','9955 SE Washington St Ste 103, Portland, OR 97216','45.5186054','-122.560982','Portland, OR'),"
					+" (108,'WelPland Insurance','1234','hfgr5@gmail.com','insurance company','5845 SE 82nd Ave, Portland, OR 97266','45.4802632','-122.5792714','Portland, OR'),"
					+" (109,'Guardian Life Insurance Company of America','1234','rgrt3@gmail.com','insurance company','3435 Wilshire Blvd Suite 1550, Los Angeles, CA 90010','34.0622654','-118.2984956','Los Angeles, CA '),"
					+" (110,'Public Insurance Agency','1234','fgr5@gmail.com','insurance company','10941 W Pico Blvd, Los Angeles, CA 90064','34.039236','-118.431037','Los Angeles, CA'),"
					+" (111,'Greater New York Insurance Co','1234','hrt4@gmail.com','insurance company','200 Madison Ave #3, New York, NY 10016','40.749138','-73.9825933','New York, NY'),"
					+" (112,'New York Life Insurance Company','1234','gerf3@gmail.com','insurance company','1 MetroTech Center 17th floor, Brooklyn, NY 11201','40.6932748','-73.9866898','Brooklyn, NY'),"
					+" (113,'Rizzo Insurance Group, Inc.','1234','nrt4@gmail.com','insurance company','310 Broadway, Revere, MA 02151','42.4089016','-71.0136072','Revere, MA'),"
					+" (114,'Boston Mutual Life Insurance Company','1234','ger4@gmail.com','insurance company','120 Royall St, Canton, MA 02021','42.204151','-71.1291713','Canton, MA'),"
					+" (115,'John Hancock Life Insurance Co','1234','th6@gmail.com','insurance company','200 Berkeley St #1, Boston, MA 02116','42.3498585','-71.0727563','Boston, MA'),"
					+" (116,'Lordea Home Insurance','1234','regr3@gmail.com','insurance company','290 Bennington St, Boston, MA 02128','42.378997','-71.0285815','Boston, MA '),"
					+" (117,'Point Insurance','1234','gerg4@gmail.com','insurance company','1103 Commonwealth Avenue, Boston, MA 02215','42.3519424','-71.1254774','Boston, MA'),"
					+" (118,'Winmore Insurance Solutions LLC.','1234','fd4@gmail.com','insurance company','4152 W Armitage Ave, Chicago, IL 60639','41.917378','-87.731106','Chicago, IL'),"
					+" (119,'Pro Insurance Group','1234','gd3@gmail.com','insurance company','2521 Technology Dr Suite 201, Elgin, IL 60124','42.0898523','-88.3392642','Elgin, IL'),"
					+" (120,'Eagle Insurance','1234','fdh4@gmail.com','insurance company','2433 W 79th St, Chicago, IL 60652','41.7498883','-87.6842339','Chicago, IL'),"
					+" (121,'admin','admin','admin@gmail.com','admin','2433 W 79th St, Chicago, IL 60652','41.7498883','-87.6842339','Chicago, IL');")
                    .execute();

            conn.prepareStatement(
                    "INSERT INTO Doctordetails (postId, doctorId, realName, department, address, locationLat, locationLong, location, OpenTime,closeTime,postTime)"
					+" VALUES (1,2,'Dr. Sue Kafali-Nazarof','gynecologic & cosmetic surgeon','3000 N Halsted St Ste 720, Chicago, IL 60657','41.9367044','-87.6519388','Chicago, IL','09:00:00','17:00:00','2020-08-30'),"
					+" (2,5,'Dr. Ming Li Tsang','Family Medicine','909 Hyde St #432, San Francisco, CA 94109','37.789371','-122.4194441','San Francisco, CA','08:00:00','17:30:00','2020-09-03'),"
					+" (3,9,'Dr. Ryan M Siewert','Family Medicine','1321 W Dakota Pkwy, Williston, ND 58801','48.1559559','-103.6530379','Williston, ND','09:30:00','17:30:00','2020-09-05'),"
					+" (4,7,'Dr. Brynna Connor','Family Medicine','2911 Medical Arts St Building #10, Austin, TX 78705','30.2880745','-97.7295075','Austin, TX','08:00:00','17:30:00','2020-09-05'),"
					+" (5,11,'Dr. Milton Waner','hemangiomas and vascular malformations','210 East 64th Street, 7th Floor, New York City, NY 10065','40.7640723','-73.9655038','New York City, NY','08:15:00','16:30:00','2020-09-06'),"
					+" (6,12,'Dr. David J. Benditzson',' Primary Care','111 N Wabash Ave STE 1412, Chicago, IL 60602','41.8834746','-87.6279434','Chicago, IL','09:30:00','16:30:00','2020-09-12'),"
					+" (7,13,'Dr. Shirish N. Shah',' Cardiology ','5600 W Addison St Ste 400, Chicago, IL 60634','41.9460911','-87.7693311','Chicago, IL','09:00:00','17:30:00','2020-09-12'),"
					+" (8,14,'Dr. Adrian Ma',' Cardiovascular ','5200 Jose Figueres Ave #215, San Jose, CA 95116','37.3615718','-121.8524164','San Jose, CA','08:00:00','15:30:00','2020-09-15'),"
					+" (9,15,'Dr. Russell O. Schub','Gastroenterology','8875 Centre Park Dr suite d, Columbia, MD 21045','39.2289939','-76.8156736','Columbia, MD','10:00:00','16:00:00','2020-09-20'),"
					+" (10,16,'Dr. March E. Seabrook','Gastroenterology','131 Summer Pl Dr, West Columbia, SC 29169','34.0042347','-81.1231826','West Columbia, SC','08:00:00','17:00:00','2020-09-22'),"
					+" (11,17,'Mark W. Galland','Orthopedics','11200 Gov Manly Way #309, Raleigh, NC 27614','35.9477509','-78.543784','Raleigh, NC','09:00:00','17:30:00','2020-09-27'),"
					+" (12,18,'Dr. Jon J. Warner','Orthopedics','55 Fruit St, Boston, MA 02114','42.362706','-71.0708467','Boston, MA','08:30:00','16:30:00','2020-09-28'),"
					+" (13,19,'Dr. Peter Steinberg','Urology','330 Brookline Ave, Boston, MA 02215','42.3398798','-71.106747','Boston, MA','08:30:00','16:30:00','2020-10-08'),"
					+" (14,20,'Dr. Robert Oates','Urology','725 Albany St #3b, Boston, MA 02118','42.3348727','-71.0743435','Boston, MA','09:30:00','18:30:00','2020-10-09'),"
					+" (15,21,'Dr. Edward Zoltan','Urology','175 Remsen St 12th floor, Brooklyn, NY 11201','40.6934999','-73.9935536','Brooklyn, NY','08:30:00','18:00:00','2020-10-11'),"
					+" (16,22,'Dr. Ken Moadel','Ophthalmology','110 E 40th St Floor 6, New York, NY 10016','40.7504117','-73.980187','New York, NY','08:30:00','17:00:00','2020-10-12'),"
					+" (17,23,'Dr. Jacqueline Wong','Ophthalmology','945 Hildebrand Ln NE, Bainbridge Island, WA 98110','47.6336187','-122.5199271','Bainbridge Island, WA','09:00:00','17:00:00','2020-10-15'),"
					+" (18,24,'Dr. John H. Wilkins','Ophthalmology','9155 SW Barnes Rd #430, Portland, OR 97225','45.510411','-122.772308','Portland, OR','09:00:00','14:00:00','2020-10-16'),"
					+" (19,27,'Dr. Nayantara Seneviratne','Endocrinologists','2250 Hayes St Ste. 612, San Francisco, CA 94117','37.7731512','-122.4556097','San Francisco, CA','08:30:00','16:00:00','2020-10-18'),"
					+" (20,26,'Jennifer N. Ouchi','Endocrinologists','8635 W 3rd St, Los Angeles, CA 90048','34.0739596','-118.3831086','Los Angeles, CA','08:30:00','16:00:00','2020-10-19'),"
					+" (21,25,'Erin McCann','Endocrinologists','1658 N Milwaukee Ave, Chicago, IL 60647','41.911521','-87.6820203','Chicago, IL','09:30:00','16:00:00','2020-10-19'),"
					+" (22,28,'Dr. Chienying Liu, MD','Endocrinologists','400 Parnassus Ave Suite A-550, San Francisco, CA 94143','37.7645542','-122.4594448','San Francisco, CA','09:30:00','17:00:00','2020-10-20'),"
					+" (23,29,'Dr. Sujani G. Surakanti, MD','Hematologists','1 Gustave L. Levy Pl, New York, NY 10029','40.7900079','-73.9559261','New York, NY ','09:30:00','16:15:00','2020-10-21'),"
					+" (24,30,'Elizondo Joseph MD','Internists','6835 Austin Center Blvd, Austin, TX 78731','30.3512728','-97.752607','Austin, TX','10:00:00','16:00:00','2020-10-21'),"
					+" (25,31,'Dr. Mark S. Rosen, MD','Nephrologists','3000 N Interstate 35 Frontage Rd, Austin, TX 78705','30.2884636','-97.7264813','Austin, TX','08:30:00','15:00:00','2020-10-22'),"
					+" (26,32,'Ayman Layka, MD','Nephrologists','4302 Alton Rd #400, Miami Beach, FL 33140','25.8140468','-80.1418685','Miami Beach, FL','09:30:00','15:00:00','2020-10-24'),"
					+" (27,33,'Wohlfeiler Michael MD','Internists','4308 Alton Rd, Miami Beach, FL 33140','25.8148808','-80.1426972','Miami Beach, FL','08:30:00','16:00:00','2020-10-25'),"
					+" (28,34,'Dr. Gerald H. Watanabe, MD','Internists','321 N Kuakini St STE 802, Honolulu, HI 96817','21.3215492','-157.858117','Honolulu, HI','09:30:00','17:00:00','2020-10-26'),"
					+" (29,35,'Dr. Kore Liow MD','Neurologists','1329 Lusitana St #307, Honolulu, HI 96813','21.3076091','-157.8542824','Kailua, HI','08:00:00','17:00:00','2020-10-27'),"
					+" (30,36,'Cho Jonathan MD','Oncologists','9155 SW Barnes Rd #430, Portland, OR 97225','45.510411','-122.7723133','Portland, OR','08:30:00','17:30:00','2020-10-28');")
                    .execute();








				conn.prepareStatement(
					"INSERT INTO Hospitaldetails (postId, hospitalId, hospitalName, address, locationLat, locationLong, location, OpenTime,closeTime,postTime)"
					+" VALUES (1,61,'Chicago Pediatric and Neonatology','7447 W Talcott Ave # 561, Chicago, IL 60631','41.9876675','-87.8145514','Chicago, IL','09:00:00','17:00:00','2020-08-30'),"
					+" (2,62,'Donohoe Pediatrics','3916 N Damen Ave., Chicago, IL 60618','41.9529772','-87.6790409','Chicago, IL','08:00:00','17:30:00','2020-09-03'),"
					+" (3,63,'MIRACLE MILE PEDIATRICS','6221 Wilshire Blvd #215, Los Angeles, CA 90048','34.063501','-118.3640248','Los Angeles, CA','09:30:00','17:30:00','2020-09-05'),"
					+" (4,64,'San Francisco Medical Center and Medical Offices','2238 Geary Blvd 7th floor, San Francisco, CA 94115','37.7833688','-122.4406775','San Francisco, CA','08:00:00','17:30:00','2020-09-05'),"
					+" (5,65,'Brooklyn Hospital Center-Hematology','121 Dekalb Ave, Brooklyn, NY 11201','40.7240049','-73.9789535','Brooklyn, NY','08:15:00','16:30:00','2020-09-06'),"
					+" (6,66,'Lenox Hill Hospital','100 E 77th St, New York, NY 10075','40.691265','-73.9777743','New York, NY','09:30:00','16:30:00','2020-09-12'),"
					+" (7,67,'Mount Sinai Kravis Childrens Hospital','1184 5th Ave, New York, NY 10029','40.7900579','-73.9538592','New York, NY','09:00:00','17:30:00','2020-09-12'),"
					+" (8,68,'St. David`s South Austin Medical Center','901 W Ben White Blvd, Austin, TX 78704','30.2261149','-97.7745674','Austin, TX','08:00:00','15:30:00','2020-09-15'),"
					+" (9,69,'Baylor Scott & White Medical Center','5245 W US Hwy 290 Service Rd, Austin, TX 78735','30.2358362','-97.8344989','Austin, TX','10:00:00','16:00:00','2020-09-20'),"
					+" (10,70,'Shoal Creek Hospital','3529 Mills Ave, Austin, TX 78731','30.3059555','-97.7484764','Austin, TX','08:00:00','17:00:00','2020-09-22'),"
					+" (11,71,'Orlando Health Arnold Palmer Hospital for Children','92 W Miller St, Orlando, FL 32806','28.4590107','-81.4968755','Orlando, FL','09:00:00','17:30:00','2020-09-27'),"
					+" (12,72,'Orlando Health Heart & Vascular Institute','1222 S Orange Ave, Orlando, FL 32806','28.5233918','-81.3800672','Orlando, FL','08:30:00','16:30:00','2020-09-28'),"
					+" (13,73,'South Miami Hospital','6200 SW 73rd St, Miami, FL 33143','25.7029961','-80.2943575','Miami, FL','08:30:00','16:30:00','2020-10-08'),"
					+" (14,74,'Mercy Hospital','3663 S Miami Ave, Miami, FL 33133','25.7400049','-80.213526','Miami, FL','09:30:00','18:30:00','2020-10-09'),"
					+" (15,75,'Hawaii Cancer Care','1329 Lusitana St #307, Honolulu, HI 96813','21.3076091','-157.8520883','Honolulu, HI','08:30:00','18:00:00','2020-10-11'),"
					+" (16,76,'Straub Outpatient Treatment Center','888 S King St, Honolulu, HI 96813','21.3025814','-157.8501322','Honolulu, HI','08:30:00','17:00:00','2020-10-12'),"
					+" (17,77,'Howard County General Hospital','5755 Cedar Ln, Columbia, MD 21044','39.2141113','-76.885968','Columbia, MD','09:00:00','17:00:00','2020-10-15'),"
					+" (18,78,'Oncology/Hematology California Pacific Medical Center','2238 Geary Blvd 7th floor, San Francisco, CA 94115','37.7833688','-122.4406775','San Francisco, CA','09:00:00','14:00:00','2020-10-16'),"
					+" (19,79,'Pali Momi Medical Center','98-1079 Moanalua Rd, Aiea, HI 96701','21.3835987','-157.9382973','Aiea, HI','08:30:00','16:00:00','2020-10-18'),"
					+" (20,80,'Duke Raleigh Hospital','3400 Wake Forest Rd, Raleigh, NC 27609','35.8284188','-78.6183679','Raleigh, NC','08:30:00','16:00:00','2020-10-19'),"
					+" (21,81,'WakeMed North Hospital','10000 Falls of Neuse Rd, Raleigh, NC 27614','35.9091252','-78.5977337','Raleigh, NC','09:30:00','16:00:00','2020-10-19'),"
					+" (22,82,'Holly Hill Hospital','3019 Falstaff Rd, Raleigh, NC 27610','35.782772','-78.5839164','Raleigh, NC','09:30:00','17:00:00','2020-10-20'),"
					+" (23,83,'Lexington Medical Center','2720 Sunset Blvd, West Columbia, SC 29169','34.0068841','-81.1137621','West Columbia, SC','09:30:00','16:15:00','2020-10-21'),"
					+" (24,84,'St. Michael Medical Center','2520 Cherry Ave, Bremerton, WA 98310','47.5829409','-122.6251543','Bainbridge Island, WA','10:00:00','16:00:00','2020-10-21'),"
					+" (25,85,'Swedish Primary Care','945 Hildebrand Ln NE Suite 100, Bainbridge Island, WA 98110','47.6336543','-122.5177735','Bainbridge Island, WA','08:30:00','15:00:00','2020-10-22'),"
					+" (26,86,'Virginia Mason Hospital and Seattle Medical Center','1100 9th Ave, Seattle, WA 98101','47.6094886','-122.327968','Bainbridge Island, WA','09:30:00','15:00:00','2020-10-24'),"
					+" (27,87,'OHSU Hospital','3181 SW Sam Jackson Park Rd, Portland, OR 97239','45.4980686','-122.6850168','Portland, OR','08:30:00','16:00:00','2020-10-25'),"
					+" (28,88,'Vibra Specialty Hospital of Portland','10300 NE Hancock St, Portland, OR 97220','45.5356684','-122.5565101','Portland, OR','09:30:00','17:00:00','2020-10-26'),"
					+" (29,89,'Providence Portland Medical Center','4805 NE Glisan St, Portland, OR 97213','45.5281789','-122.6119391','Portland, OR','08:00:00','17:00:00','2020-10-27'),"
					+" (30,90,'Legacy Emanuel Medical Center','2801 N Gantenbein Ave, Portland, OR 97227','45.5436861','-122.6695411','Portland, OR','08:30:00','17:30:00','2020-10-28');")
					.execute();
					
				
				conn.prepareStatement(
					"INSERT INTO Insurancedetails (postId, insuranceId, insuranceName, address, locationLat, locationLong, location, OpenTime,closeTime,postTime)"
					+" VALUES (1,91,'Cost-U-Less Insurance','1152 N Capitol Ave, San Jose, CA 95132','37.3879017','-121.8599859','San Jose, CA','09:00:00','17:00:00','2020-08-30'),"
					+" (2,92,'L V Insurance','1625 Flickinger Ave, San Jose, CA 95131','37.3901792','-121.8791306','San Jose, CA','08:00:00','17:30:00','2020-09-03'),"
					+" (3,93,'Fred Loya Insurance','154 S Jackson Ave, San Jose, CA 95116','37.3563654','-121.840821','San Jose, CA','09:30:00','17:30:00','2020-09-05'),"
					+" (4,94,'Texas Mutual Insurance Company','2200 Aldrich St, Austin, TX 78723','30.3010368','-97.7035713','Austin, TX','08:00:00','17:30:00','2020-09-05'),"
					+" (5,95,'Texas Life Insurance Co','229 Parkhill St, Huntsville, TX 77340','30.7061257','-95.5336161','Huntsville, TX','08:15:00','16:30:00','2020-09-06'),"
					+" (6,96,'Texas Insurance Place','4101 W Green Oaks Blvd ste 305-105, Arlington, TX 76016','32.6820499','-97.1948966','Arlington, TX','09:30:00','16:30:00','2020-09-12'),"
					+" (7,97,'Island Insurance Hawaii','1022 Bethel St, Honolulu, HI 96813','21.3101916','-157.8623647','Honolulu, HI','09:00:00','17:30:00','2020-09-12'),"
					+" (8,98,'First Insurance Company of Hawaii, LTD','1100 Ward Ave, Honolulu, HI 96814','21.3035122','-157.8498528','Honolulu, HI','08:00:00','15:30:00','2020-09-15'),"
					+" (9,99,'Farmers Insurance Hawaii','Six Waterfront Plaza, 500 Ala Moana Blvd 5th Floor, Honolulu, HI 96813','21.3018247','-157.8619678','Honolulu, HI','10:00:00','16:00:00','2020-09-20'),"
					+" (10,100,'Insurance Company of Florida','3751 Maryweather Ln #102, Wesley Chapel, FL 33544','28.2118594','-82.356133','Wesley Chapel, FL','08:00:00','17:00:00','2020-09-22'),"
					+" (11,101,'Great Florida Insurance','11362 Orange Blossom Trail, Orlando, FL 32837','28.400058','-81.4060582','Orlando, FL','09:00:00','17:30:00','2020-09-27'),"
					+" (12,102,'Florida Peninsula Insurance Company','903 NW 65th St, Boca Raton, FL 33487','26.406303','-80.1047321','Boca Raton, FL','08:30:00','16:30:00','2020-09-28'),"
					+" (13,103,'Florida Family Insurance Co','27599 Riverview Center Blvd # 100, Bonita Springs, FL 34134','26.3397713','-81.8087294','Bonita Springs, FL','08:30:00','16:30:00','2020-10-08'),"
					+" (14,104,'Westside Portland Insurance Company','1615 SE, SE Tualatin Valley Hwy STE.1, Hillsboro, OR 97123','45.5092931','-122.9661441','Hillsboro, OR','09:30:00','18:30:00','2020-10-09'),"
					+" (15,105,'Timmco Insurance, Inc.','1615 NE Broadway, Portland, OR 97232','45.5352682','-122.6490668','Portland, OR','08:30:00','18:00:00','2020-10-11'),"
					+" (16,106,'Chet Hill Insurance','10402 NE Sandy Blvd, Portland, OR 97220','45.5587471','-122.5555748','Portland, OR','08:30:00','17:00:00','2020-10-12'),"
					+" (17,107,'Vern Fonk Insurance','9955 SE Washington St Ste 103, Portland, OR 97216','45.5186054','-122.560982','Portland, OR','09:00:00','17:00:00','2020-10-15'),"
					+" (18,108,'WelPland Insurance','5845 SE 82nd Ave, Portland, OR 97266','45.4802632','-122.5792714','Portland, OR','09:00:00','14:00:00','2020-10-16'),"
					+" (19,109,'Guardian Life Insurance Company of America','3435 Wilshire Blvd Suite 1550, Los Angeles, CA 90010','34.0622654','-118.2984956','Los Angeles, CA ','08:30:00','16:00:00','2020-10-18'),"
					+" (20,110,'Public Insurance Agency','10941 W Pico Blvd, Los Angeles, CA 90064','34.039236','-118.431037','Los Angeles, CA','08:30:00','16:00:00','2020-10-19'),"
					+" (21,111,'Greater New York Insurance Co','200 Madison Ave #3, New York, NY 10016','40.749138','-73.9825933','New York, NY','09:30:00','16:00:00','2020-10-19'),"
					+" (22,112,'New York Life Insurance Company','1 MetroTech Center 17th floor, Brooklyn, NY 11201','40.6932748','-73.9866898','Brooklyn, NY','09:30:00','17:00:00','2020-10-20'),"
					+" (23,113,'Rizzo Insurance Group, Inc.','310 Broadway, Revere, MA 02151','42.4089016','-71.0136072','Revere, MA','09:30:00','16:15:00','2020-10-21'),"
					+" (24,114,'Boston Mutual Life Insurance Company','120 Royall St, Canton, MA 02021','42.204151','-71.1291713','Canton, MA','10:00:00','16:00:00','2020-10-21'),"
					+" (25,115,'John Hancock Life Insurance Co','200 Berkeley St #1, Boston, MA 02116','42.3498585','-71.0727563','Boston, MA','08:30:00','15:00:00','2020-10-22'),"
					+" (26,116,'Lordea Home Insurance','290 Bennington St, Boston, MA 02128','42.378997','-71.0285815','Boston, MA','09:30:00','15:00:00','2020-10-24'),"
					+" (27,117,'Point Insurance','1103 Commonwealth Avenue, Boston, MA 02215','42.3519424','-71.1254774','Boston, MA','08:30:00','16:00:00','2020-10-25'),"
					+" (28,118,'Winmore Insurance Solutions LLC.','4152 W Armitage Ave, Chicago, IL 60639','41.917378','-87.731106','Chicago, IL','09:30:00','17:00:00','2020-10-26'),"
					+" (29,119,'Pro Insurance Group','2521 Technology Dr Suite 201, Elgin, IL 60124','42.0898523','-88.3392642','Elgin, IL','08:00:00','17:00:00','2020-10-27'),"
					+" (30,120,'Eagle Insurance','2433 W 79th St, Chicago, IL 60652','41.7498883','-87.6842339','Chicago, IL','08:30:00','17:30:00','2020-10-28');")
                    .execute();


            conn.prepareStatement("INSERT INTO Appointment (providerId, customerId, appointmentDate, appointmentTime)"
                    + "VALUES (2,1,'2020-10-20','2020-10-20 10:00:00')," + "(2,10,'2020-10-07','2020-10-07 15:00:00'),"
                    + "(5,3,'2020-10-31','2020-10-31 09:30:00')," + "(5,3,'2020-11-01','2020-11-01 13:45:00'),"
                    + "(5,6,'2020-10-31','2020-10-31 09:30:00')," + "(5,2,'2020-11-01','2020-11-01 13:45:00'),"
                    + "(5,3,'2020-10-31','2020-10-31 09:30:00')," + "(5,6,'2020-11-01','2020-11-01 13:45:00'),"
                    + "(2,3,'2020-11-20','2020-11-20 10:00:00')," + "(7,8,'2020-10-15','2020-10-15 10:15:00'),"
                    + "(7,6,'2020-11-20','2020-11-20 10:30:00')," + "(5,10,'2020-10-06','2020-10-06 09:20:00'),"
                    + "(5,10,'2020-11-06','2020-11-06 14:30:00')," + "(5,8,'2020-10-30','2020-10-30 09:20:00');")
                    .execute();

            conn.prepareStatement("insert into Message ( customerId, providerId)" 
                    +"values (1, 2),"
                    +"(10, 2),"
                    +"(4, 5),"
                    +"(3, 5),"
                    +"(1, 2),"
                    +"(6, 7),"
                    +"(6, 7),"
                    +"(3, 5),"
                    +"(10, 5),"
                    +"(8, 5);")
                    .execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void insertUser(String userName,String  password, String email,String usertype,String address,String locationLat,String locationLong,String location)
	{
		try
		{	

			getConnection();
			String insertIntoRegister = "INSERT INTO Registration(userName, password, email, usertype, address, locationLat, locationLong, location) "
			+ "VALUES (?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertIntoRegister);
			pst.setString(1,userName);
            pst.setString(2,password);
            pst.setString(3,email);
            pst.setString(4,usertype);
            pst.setString(5,address);
            pst.setString(6,locationLat);
            pst.setString(7,locationLong);
			pst.setString(8,location);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println("insertUser error: "+e.getMessage());

		}	
	}
	
	public static HashMap<String,User> selectUser()
	{	
		HashMap<String,User> hm=new HashMap<String,User>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  Registration";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	User user = new User(rs.getInt("userId"),rs.getString("userName"),rs.getString("password"),rs.getString("email"), rs.getString("usertype"), rs.getString("address"),rs.getString("locationLat"),rs.getString("locationLong"),rs.getString("location"));
				hm.put(rs.getString("username"), user);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return hm;			
	}

    public static void updateUser(int userId, String  password,String address,String locationLat,String locationLong,String location)
	{
		try
		{	

			getConnection();
			String insertIntoRegister = "Update Registration set password = ? , address = ? , locationLat = ? , locationLong = ? , location = ? where userid = ? ";	
            
            //Update Registration set password = 'newPassword' , address = '25910 Celtic Terrace Dr' , locationLat = 30 , locationLong = 50 , location = '25910 Celtic Terrace Dr' where userid = 3;

					
			PreparedStatement pst = conn.prepareStatement(insertIntoRegister);
			pst.setString(1,password);
            pst.setString(2,address);
            pst.setString(3,locationLat);
            pst.setString(4,locationLong);
            pst.setString(5,location);
            pst.setInt(6,userId);


			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println("Update error: "+e.getMessage());

		}	
    }
    
    public static void insertDoctor(int doctorId,String realName,String department,String address,String locationLat,String locationLong,String location,String openTime,String closeTime, String postTime)
	{
		try
		{	

			getConnection();
			String insertIntoDoctor = "INSERT INTO Doctordetails( doctorId, realName, department, address, locationLat, locationLong, location, OpenTime,closeTime,postTime) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertIntoDoctor);
			pst.setInt(1,doctorId);
            pst.setString(2,realName);
            pst.setString(3,department);
            pst.setString(4,address);
            pst.setString(5,locationLat);
            pst.setString(6,locationLong);
            pst.setString(7,location);
			pst.setString(8,openTime);
            pst.setString(9,closeTime);
            pst.setString(10,postTime);
			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
    }
	


	public static void insertHospital(int hospitalId,String realName,String address,String locationLat,String locationLong,String location,String openTime,String closeTime, String postTime)
	{
		try
		{	

			getConnection();
			String insertIntoDoctor = "INSERT INTO Hospitaldetails( hospitalId, hospitalName, address, locationLat, locationLong, location, OpenTime,closeTime,postTime) "
			+ "VALUES (?,?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertIntoDoctor);
			pst.setInt(1,hospitalId);
			pst.setString(2,realName);
			pst.setString(3,address);
			pst.setString(4,locationLat);
			pst.setString(5,locationLong);
			pst.setString(6,location);
			pst.setString(7,openTime);
			pst.setString(8,closeTime);
			pst.setString(9,postTime);
			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
	}


	public static void insertInsurance(int companyId,String realName,String address,String locationLat,String locationLong,String location,String openTime,String closeTime, String postTime)
	{
		try
		{	

			getConnection();
			String insertIntoDoctor = "INSERT INTO Insurancedetails( insuranceId, insuranceName, address, locationLat, locationLong, location, OpenTime,closeTime,postTime) "
			+ "VALUES (?,?,?,?,?,?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertIntoDoctor);
			pst.setInt(1,companyId);
			pst.setString(2,realName);
			pst.setString(3,address);
			pst.setString(4,locationLat);
			pst.setString(5,locationLong);
			pst.setString(6,location);
			pst.setString(7,openTime);
			pst.setString(8,closeTime);
			pst.setString(9,postTime);
			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
	}










    public static void insertAppointment(int providerId,int  customerId,String appointmentDate,String appointmentTime)
	{
		try
		{	

			getConnection();
			String insertAppointment = "INSERT INTO Appointment(providerId, customerId, appointmentDate, appointmentTime) "
			+ "VALUES (?,?,?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertAppointment);
			pst.setInt(1,providerId);
			pst.setInt(2,customerId);
            pst.setString(3,appointmentDate);
            pst.setString(4,appointmentTime);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
    }
    
    public static void insertMessage(int customerId,int providerId)
	{
		try
		{	

			getConnection();
			String insertMessage = "INSERT INTO Message( customerId, providerId) "
			+ "VALUES (?,?);";	
					
			PreparedStatement pst = conn.prepareStatement(insertMessage);
			pst.setInt(1,customerId);
			pst.setInt(2,providerId);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}	
    }

    public static void insertBook(int providerId,int  customerId,String appointmentDate,String appointmentTime) {
        insertAppointment(providerId,customerId,appointmentDate,appointmentTime);
        insertMessage(customerId, providerId);
    }
    

    public static void updateMessage(int messageId, String message)
	{	
		try
		{	

			getConnection();
			String updateMessage = "Update Message Set message=? where messageId=?";
					
			PreparedStatement pst = conn.prepareStatement(updateMessage);
			pst.setString(1,message);
			pst.setInt(2,messageId);

			pst.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}				
    }


    public static ArrayList<Book> viewBook()
	{	
		ArrayList<Book> books = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectBookQuery="Select messageId, CustomerName, userName as 'ProviderName', usertype, appointmentDate, appointmentTime, message from process inner join Registration on providerId = Registration.userId join Appointment on messageId = bookId ;";
			ResultSet rs = stmt.executeQuery(selectBookQuery);
			while(rs.next())
			{	
                Book book = new Book(rs.getInt("messageId"),rs.getString("CustomerName"),rs.getString("ProviderName"),rs.getString("usertype"), rs.getString("appointmentDate"),rs.getString("appointmentTime"),rs.getString("message"));
				books.add(book);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return books;			
    }
    
    public static ArrayList<Book> viewCustomerBook(int userId)
	{	
		ArrayList<Book> books = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectBookQuery="Select messageId, CustomerName, userName as 'ProviderName',usertype, appointmentDate, appointmentTime, message from process inner join Registration on providerId = Registration.userId join Appointment on messageId = bookId where customerId ="+ userId;
			ResultSet rs = stmt.executeQuery(selectBookQuery);
			while(rs.next())
			{	
                Book book = new Book(rs.getInt("messageId"),rs.getString("CustomerName"),rs.getString("ProviderName"),rs.getString("usertype"), rs.getString("appointmentDate"),rs.getString("appointmentTime"),rs.getString("message"));
				books.add(book);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return books;			
    }
    
    public static void removeBook(int id )
	{	
		try
		{	

			getConnection();
			String removeBookQuery = "Delete from message where messageId=? ";	
			PreparedStatement pst = conn.prepareStatement(removeBookQuery);
			pst.setInt(1,id);
            pst.execute();
            
            removeBookQuery = "Delete from Appointment where bookId=? ";	
			PreparedStatement pst2 = conn.prepareStatement(removeBookQuery);
			pst2.setInt(1,id);
			pst2.execute();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}				
	}

	public static ArrayList<Doctor> viewDoctor()
	{	
		ArrayList<Doctor> doctors = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Doctordetails;";
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				doctors.add(new Doctor(rs.getInt("postId"), rs.getInt("doctorId"), rs.getString("realName"), rs.getString("department"), rs.getString("address"), rs.getString("locationLat"), rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"), rs.getString("closeTime"), rs.getString("postTime")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return doctors;			
	}



	public static ArrayList<Hospital> viewHospital()
	{	
		ArrayList<Hospital> hospitals = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Hospitaldetails;";
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				hospitals.add(new Hospital(rs.getInt("postId"), rs.getInt("hospitalId"), rs.getString("hospitalName"), rs.getString("address"), rs.getString("locationLat"), rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"), rs.getString("closeTime"), rs.getString("postTime")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return hospitals;			
	}

	public static ArrayList<Insurance> viewInsurance()
	{	
		ArrayList<Insurance> insurances = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Insurancedetails;";
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				insurances.add(new Insurance(rs.getInt("postId"), rs.getInt("insuranceId"), rs.getString("insuranceName"), rs.getString("address"), rs.getString("locationLat"), rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"), rs.getString("closeTime"), rs.getString("postTime")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return insurances;			
	}




	

	public static Doctor getDoctor(int postId)
	{	
		Doctor doctor = new Doctor();

		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Doctordetails where postId =" + postId;
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
	
	public static Hospital getHospital(int postId)
	{	
		Hospital hospital = new Hospital();

		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Hospitaldetails where postId =" + postId;
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				hospital.setPostId(rs.getInt("postId"));
				hospital.setHospitalId(rs.getInt("hospitalId"));
				hospital.setHospitalName(rs.getString("hospitalName"));
				hospital.setAddress(rs.getString("address"));
				hospital.setLocationLat(rs.getString("locationLat"));;
				hospital.setLocationLong(rs.getString("locationLong"));;
				hospital.setLocation(rs.getString("location"));
				hospital.setOpenTime(rs.getString("OpenTime"));
				hospital.setCloseTime(rs.getString("closeTime"));
				hospital.setPostTime(rs.getString("postTime"));
			}

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return hospital;			
	}

	public static Insurance getInsurance(int postId)
	{	
		Insurance insurance = new Insurance();

		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Insurancedetails where postId =" + postId;
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				insurance.setPostId(rs.getInt("postId"));
				insurance.setInsuranceId(rs.getInt("insuranceId"));
				insurance.setInsuranceName(rs.getString("insuranceName"));
				insurance.setAddress(rs.getString("address"));
				insurance.setLocationLat(rs.getString("locationLat"));;
				insurance.setLocationLong(rs.getString("locationLong"));;
				insurance.setLocation(rs.getString("location"));
				insurance.setOpenTime(rs.getString("OpenTime"));
				insurance.setCloseTime(rs.getString("closeTime"));
				insurance.setPostTime(rs.getString("postTime"));
			}

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return insurance;			
	}


	public static ArrayList<Doctor> viewPartialDoctor(String partial)
	{	
		ArrayList<Doctor> doctors = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Doctordetails where realName like '%"+partial+"%' or department like '%"+partial+"%' or location like '%"+partial+"%';";
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				doctors.add(new Doctor(rs.getInt("postId"), rs.getInt("doctorId"), rs.getString("realName"), rs.getString("department"), rs.getString("address"), rs.getString("locationLat"), rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"), rs.getString("closeTime"), rs.getString("postTime")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return doctors;			
	}
	



	public static ArrayList<Hospital> viewPartialHospital(String partial)
	{	
		ArrayList<Hospital> hospitals = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Hospitaldetails where hospitalName like '%"+partial+"%' or location like '%"+partial+"%';";
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				hospitals.add(new Hospital(rs.getInt("postId"), rs.getInt("hospitalId"), rs.getString("hospitalName"), rs.getString("address"), rs.getString("locationLat"), rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"), rs.getString("closeTime"), rs.getString("postTime")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return hospitals;			
	}

	public static ArrayList<Insurance> viewPartialInsurance(String partial)
	{	
		ArrayList<Insurance> insurances = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectDoctorQuery="select * from Insurancedetails where insuranceName like '%"+partial+"%' or location like '%"+partial+"%';";
			ResultSet rs = stmt.executeQuery(selectDoctorQuery);
			while(rs.next())
			{	
				insurances.add(new Insurance(rs.getInt("postId"), rs.getInt("insuranceId"), rs.getString("insuranceName"), rs.getString("address"), rs.getString("locationLat"), rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"), rs.getString("closeTime"), rs.getString("postTime")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return insurances;			
	}



	public static ArrayList<ArrayList<String>> viewTopBookProvider()
	{	
		
		ArrayList<ArrayList<String>> books = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select userName,count(*) as 'count' from Appointment join Registration where Registration.userId=Appointment.providerId group by providerId order by count desc;";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	
				ArrayList<String> book = new ArrayList<>();
				book.add(rs.getString("userName"));
				book.add(rs.getInt("count")+"");
				books.add(book);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return books;			
	}
		
		
	public static ArrayList<ArrayList<String>> viewTopBookDate()
	{	
		
		ArrayList<ArrayList<String>> books = new ArrayList<>();
		try 
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select appointmentDate,count(*) as 'count' from Appointment group by appointmentDate order by appointmentDate;";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	
				ArrayList<String> book = new ArrayList<>();
				book.add(rs.getString("appointmentDate"));
				book.add(rs.getInt("count")+"");
				books.add(book);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		return books;			
	}

}