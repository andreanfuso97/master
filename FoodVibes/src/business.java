public class business {
	String name;
	String address;
	String openingHours;
	String image;
	
	public business(String newName, String newAddress, String newOpeningHours, String newImage) {
		name = newName;
		address = newAddress;
		openingHours = newOpeningHours;
		image = newImage;
	}
	
	
	public static void main(String[] args) {
		business newBusiness = new business("nome","cognome","orario","imaggine");
		System.out.println(newBusiness.name+" "+newBusiness.address);
	}
	
	public String getName() {
		return this.name;
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

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
