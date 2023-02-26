import java.util.ArrayList;

public class catalog {
	private static catalog instance = null;
	private catalog() {}
    public static catalog getInstance() {
        if (instance == null) {
            instance = new catalog();
            instance.add(new business("aaaaa","b","c","d"));
            instance.add(new business("bbbbb","f","g","h"));
            instance.add(new business("ccccc","b","c","d"));
        }
        return instance;
    }
    
	ArrayList<business> businessList = new ArrayList<>();
	
	public void add(business newBusiness) {
		businessList.add(newBusiness);
		System.out.println("new business added (name: " + newBusiness.name + ", address: " + newBusiness.address +
				   ", openingHours: " + newBusiness.openingHours + ", image: " + newBusiness.image);
	}
	
	public void getCatalogEntries() {
		for (int i =0; i<businessList.size(); i++) {
			business aBusiness = businessList.get(i);
			System.out.println("new business added (name: " + aBusiness.name + ", address: " + aBusiness.address +
							   ", openingHours: " + aBusiness.openingHours + ", image: " + aBusiness.image);
		}
	}
	
	public ArrayList<business> getBusinessList(String businessName) {
		ArrayList<business> searchedBusinessList = new ArrayList<>();
		for (int i =0; i<businessList.size(); i++) {
			business aBusiness = businessList.get(i);
			if (aBusiness.name.contains(businessName)) {
				searchedBusinessList.add(aBusiness);
			}
		}
		return searchedBusinessList;
	}
}
