
public class foodvibes{
	
	public static void insertBusinessInfo(String name, String address, String openingHours, String image) {
		business newBusiness = new business(name, address, openingHours, image);
		catalog currentCatalog = catalog.getInstance();
		currentCatalog.add(newBusiness);
		
	}
	
}