package user;

public class Address {
	
	private int number;	
	private String street;
	private String city;
	private String zipC;
	
	
	public Address(int number, String street, String city, String zipC) {
		super();
		this.number = number;
		this.street = street;
		this.city = city;
		this.zipC = zipC;
	}
	
	@Override
	public String toString() {
		return "Address [number=" + number + ", street=" + street + ", city=" + city + ", zipC=" + zipC + "]";
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipC() {
		return zipC;
	}
	public void setZipC(String zipC) {
		this.zipC = zipC;
	}
	

}
