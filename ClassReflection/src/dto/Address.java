package dto;

public class Address {
	
	private int postCode;
	private String city;
	private String street;
	private int building;
	private int appt;
	
	public Address(int postCode, String city, String street, int building, int appt) {
		super();
		this.postCode = postCode;
		this.city = city;
		this.street = street;
		this.building = building;
		this.appt = appt;
	}

	@Override
	public String toString() {
		return "Address [postCode=" + postCode + ", city=" + city + ", street=" + street + ", building=" + building
				+ ", appt=" + appt + "]";
	}
	
	
}
