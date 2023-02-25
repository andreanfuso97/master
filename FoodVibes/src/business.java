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
}
