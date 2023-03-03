package bin;
import java.util.ArrayList;

public class catalog {
	private static catalog instance = null;
	private ArrayList<business> businessList;
	
	private catalog() {
		businessList = new ArrayList<>();
	}
	
    public static catalog getInstance() {
        if (instance == null) {
            instance = new catalog();
        }
        return instance;
    }
    
    //get
    public ArrayList<business> getBusinessList(){
    	return businessList;
    }
    
    //add to list
	public void add(business newBusiness) {
		businessList.add(newBusiness);
		System.out.println("new business added (name: " + newBusiness.getName() + ", address: " + newBusiness.getAddress() +
				   ", openingHours: " + newBusiness.getOpeningHours() + ", image: " + newBusiness.getImage() + ", owner: " + newBusiness.getOwner().getName());
	}
	
	//returns a list which contains the found businesses
	public ArrayList<business> getBusinessesByName(String businessName) {
		ArrayList<business> searchedBusinessList = new ArrayList<>();
		for (int i =0; i<businessList.size(); i++) {
			business aBusiness = businessList.get(i);
			if (aBusiness.matchName(businessName)) {
				searchedBusinessList.add(aBusiness);
			}
		}
		return searchedBusinessList;
	}
}
