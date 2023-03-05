package bin;

import java.util.HashMap;

public class reportedReviews {
	private static reportedReviews instance = null;
	private HashMap<review, String> reportedReviewsList;
	
	private reportedReviews	() {
		reportedReviewsList = new HashMap<>();
	}
	
    public static reportedReviews getInstance() {
        if (instance == null) {
            instance = new reportedReviews();
        }
        return instance;
    }
    
	//-------------------------------------------------------------------------------------------------------------------
    //		METODI GET
	//-------------------------------------------------------------------------------------------------------------------
    
    public HashMap<review, String> getReportedReviewsList(){
    	return reportedReviewsList;
    }
    
	//-------------------------------------------------------------------------------------------------------------------
    //		AGGIUNTA DI ATTIVITÀ ALLA LISTA
	//-------------------------------------------------------------------------------------------------------------------
    
	public void add(review reportedReview, String reportType) {
		reportedReviewsList.put(reportedReview, reportType);
		this.toString();
	}

	@Override
	public String toString() {
		return "reportedReviews [reportedReviewsList=" + reportedReviewsList + "]";
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------
	
	
}
