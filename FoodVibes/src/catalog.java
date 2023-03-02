import java.util.ArrayList;

public class catalog {
	private static catalog instance = null;
	ArrayList<business> businessList = new ArrayList<>();
	
	private catalog() {}
	
    public static catalog getInstance() {
        if (instance == null) {
            instance = new catalog();
        }
        return instance;
    }
    	
	public void add(business newBusiness) {
		businessList.add(newBusiness);
		System.out.println("new business added (name: " + newBusiness.getName() + ", address: " + newBusiness.getAddress() +
				   ", openingHours: " + newBusiness.getOpeningHours() + ", image: " + newBusiness.getImage());
	}
	
	public void getCatalogEntries() {
		for (int i =0; i<businessList.size(); i++) {
			business aBusiness = businessList.get(i);
			System.out.println("new business added (name: " + aBusiness.getName() + ", address: " + aBusiness.getAddress() +
							   ", openingHours: " + aBusiness.getOpeningHours() + ", image: " + aBusiness.getImage());
		}
	}
	
	//returns a list which contains the found businesses
	public ArrayList<business> getBusinessList(String businessName) {
		ArrayList<business> searchedBusinessList = new ArrayList<>();
		for (int i =0; i<businessList.size(); i++) {
			business aBusiness = businessList.get(i);
			if (aBusiness.getBusiness(businessName)) {
				searchedBusinessList.add(aBusiness);
			}
		}
		return searchedBusinessList;
	}
}
