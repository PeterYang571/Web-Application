public class Book {
	private int id;
	private String customerName;
	private String provideName;
	private String appointmentDate;
	private String time;
	private String note;
	private String providerType;

	public Book(int id, String customerName, String provideName,String providerType, String appointmentDate,String time, String note) {
		this.id = id;
		this.customerName = customerName;
		this.provideName = provideName;
		this.providerType = providerType;
		this.appointmentDate = appointmentDate;
		this.time = time;
		this.note = note;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProvideName() {
		return provideName;
	}

	public void setProvideName(String provideName) {
		this.provideName = provideName;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
