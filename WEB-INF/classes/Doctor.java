public class Doctor {

    private int postId;
    private int doctorId;
    private String realName;
    private String department;
    private String address;
    private String lat;
    private String longt;
    private String location;
    private String openTime;
    private String closeTime;
    private String postTime;

    public Doctor () {

    }

    public Doctor(int postId, int doctorId, String realName, String department, String address, String lat, String longt, String location, String openTime, String closeTime, String postTime) {
        this.postId = postId;
        this.doctorId = doctorId;
        this.realName = realName;
        this.department = department;
        this.address = address;
        this.lat = lat;
        this.longt = longt;
        this.location = location;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.postTime = postTime;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }
}
