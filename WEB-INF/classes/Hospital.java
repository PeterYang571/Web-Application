public class Hospital {


    private int postId;
    private int hospitalId;
    private String hospitalName;
    private String address;
    private String locationLat;
    private String locationLong;
    private String location;
    private String openTime;
    private String closeTime;
    private String postTime;

    public Hospital() {
    }

    public Hospital(int postId, int hospitalId, String hospitalName, String address, String locationLat, String locationLong, String location, String openTime, String closeTime, String postTime) {
        this.postId = postId;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.address = address;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
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

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(String locationLong) {
        this.locationLong = locationLong;
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
