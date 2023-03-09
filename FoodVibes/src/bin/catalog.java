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
    
	//-------------------------------------------------------------------------------------------------------------------
    //		METODI GET
	//-------------------------------------------------------------------------------------------------------------------
    
    public ArrayList<business> getBusinessList(){
    	return businessList;
    }
    
	//-------------------------------------------------------------------------------------------------------------------
    //		AGGIUNTA DI ATTIVITÀ AL CATALOGO
	//-------------------------------------------------------------------------------------------------------------------
    
	public void add(business newBusiness) {
		businessList.add(newBusiness);
		System.out.println("new business added (name: " + newBusiness.getName() + ", address: " + newBusiness.getAddress() +
				   ", openingHours: " + newBusiness.getOpeningHours() + ", image: " + newBusiness.getImage() + ", owner: " + newBusiness.getOwner().getName());
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		RICERCA NELLA LISTA DELLE ATTIVITÀ ATTRAVERSO IL NOME
	//-------------------------------------------------------------------------------------------------------------------
	public ArrayList<business> getBusinessesByName(String businessName) {
		ArrayList<business> searchedBusinessList = new ArrayList<>();
		for (int i =0; i<businessList.size(); i++) {
			business aBusiness = businessList.get(i);
			if (!businessName.isBlank() && aBusiness.getName().toLowerCase().contains(businessName.toLowerCase())) {	
				searchedBusinessList.add(aBusiness);
			}
		}
		return searchedBusinessList;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	
	public void removeFromList(business aBusiness) {
		businessList.remove(aBusiness);
		
	}
}
