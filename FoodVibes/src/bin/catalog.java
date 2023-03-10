package bin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;

public class catalog implements Observer{
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
    	sortCatalog();
    	return businessList;
    }
    
	//-------------------------------------------------------------------------------------------------------------------
    //		GESTIONE CATALOGO
	//-------------------------------------------------------------------------------------------------------------------
    
	public void add(business newBusiness) {
		businessList.add(newBusiness);
		System.out.println("new business added (name: " + newBusiness.getName() + ", address: " + newBusiness.getAddress() +
				   ", openingHours: " + newBusiness.getOpeningHours() + ", image: " + newBusiness.getImage() + ", owner: " + newBusiness.getOwner().getName());
		newBusiness.addObserver(this);
	}
	public void removeFromList(business aBusiness) {
		businessList.remove(aBusiness);
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
	//		ORDINAMENTO CATALOGO
	//-------------------------------------------------------------------------------------------------------------------
	
	public void sortCatalog() {
		Collections.sort(businessList, new Comparator<business>() {
			public int compare(business o1, business o2) {
				return Float.compare(o1.getAvgVote(), o2.getAvgVote());
			}
		});
	}

	//-------------------------------------------------------------------------------------------------------------------
	//		OBSERVER
	//-------------------------------------------------------------------------------------------------------------------
	
	public void update(Observable business, Object arg) {
		sortCatalog();
	}
}
