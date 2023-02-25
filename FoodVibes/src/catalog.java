import java.util.ArrayList;

public class catalog {
	private static catalog instance = null;
	private catalog() {}
    public static catalog getInstance() {
        if (instance == null) {
            instance = new catalog();
        }
        return instance;
    }
    
	ArrayList<business> businessList = new ArrayList<>();
	
	public void add(business newBusiness) {
		businessList.add(newBusiness);
		this.get();
	}
	public void get() {
		for (int i =0; i<businessList.size(); i++) {
			business aBusiness = businessList.get(i);
			System.out.println(aBusiness.name + " " + aBusiness.address + " " + aBusiness.openingHours + " " +aBusiness.image);
		}
	}
}
