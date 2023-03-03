package bin;
public class business {
	private String name;
	private String address;
	private String openingHours;
	private String image;
	private user owner;
	
	public business(String newName, String newAddress, String newOpeningHours, String newImage, user newOwner) {
		name = newName;
		address = newAddress;
		openingHours = newOpeningHours;
		image = newImage;
		owner = newOwner;
	}
	
	//get
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return address;
	}
	public String getOpeningHours() {
		return openingHours;
	}
	public String getImage() {
		return image;
	}
	public user getOwner() {
		return owner;
	}
	
	//set
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setOwner(user owner) {
		this.owner = owner;
	}
	
}
