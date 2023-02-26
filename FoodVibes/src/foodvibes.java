import java.util.ArrayList;

public class foodvibes{
	
	public static void insertBusinessInfo(String name, String address, String openingHours, String image) {
		business newBusiness = new business(name, address, openingHours, image);
		catalog currentCatalog = catalog.getInstance();
		currentCatalog.add(newBusiness);
	}
	
	public static void searchBusiness(String businessName) {
		catalog currentCatalog = catalog.getInstance();
		ArrayList<business> searchedBusinessList = currentCatalog.getBusinessList(businessName);
		for (int i =0; i<searchedBusinessList.size(); i++) {
			business aBusiness = searchedBusinessList.get(i);
			//GUI_Frame.getFrames().
			System.out.println("matching business found (name: " + aBusiness.name + ", address: " + aBusiness.address + 
							   ", openingHours: " + aBusiness.openingHours + ", image: " + aBusiness.image);
		}
	}	
}