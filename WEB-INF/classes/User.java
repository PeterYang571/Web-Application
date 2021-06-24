public class User {
	private int id;
	private String userName;
	private String email;
	private String password;
	private String usertype;
	private String lat;
	private String longt;
	private String address;
	private String location;

	public User(int id,String userName, String password,String email, String usertype,String address, String lat, String longt, String location) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
		this.lat = lat;
		this.longt = longt;
		this.address = address;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongt() {
		return longt;
	}

	public void setLongt(String longt) {
		this.longt = longt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
