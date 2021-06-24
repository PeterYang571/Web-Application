public class Insurance {


    private int postId;
    private int insuranceId;
    private String insuranceName;
    private String address;
    private String locationLat;
    private String locationLong;
    private String location;
    private String openTime;
    private String closeTime;
    private String postTime;

    public Insurance() {
    }

    public Insurance(int postId, int insuranceId, String insuranceName, String address, String locationLat, String locationLong, String location, String openTime, String closeTime, String postTime) {
        this.postId = postId;
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
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

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
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
