
import java.io.IOException;
import java.io.*;




public class Review implements Serializable{
	private String id;
	private String reviewrating;
	private String reviewdate;
	private String reviewtext;

	public Review(String id, String reviewrating, String reviewdate, String reviewtext) {
		this.id = id;
		this.reviewrating = reviewrating;
		this.reviewdate = reviewdate;
		this.reviewtext = reviewtext;


	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getReviewrating() {
		return reviewrating;
	}

	public void setReviewrating(String reviewrating) {
		this.reviewrating = reviewrating;
	}

	public String getReviewdate() {
		return reviewdate;
	}

	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;
	}

	public String getReviewtext() {
		return reviewtext;
	}

	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}


}