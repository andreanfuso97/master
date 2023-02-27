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
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getBusiness(String nameSearched) {
		return this.name.contains(nameSearched);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public user getOwner() {
		return owner;
	}

	public void setOwner(user owner) {
		this.owner = owner;
	}
}
