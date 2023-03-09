package bin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class reportList {
	private static reportList instance = null;
	private List<report> reportList;
	
	private reportList	() {
		reportList = new ArrayList<>();
	}
	
    public static reportList getInstance() {
        if (instance == null) {
            instance = new reportList();
        }
        return instance;
    }
    
	//-------------------------------------------------------------------------------------------------------------------
    //		METODI GET
	//-------------------------------------------------------------------------------------------------------------------
    
    public List<report> getReportedReviewsList(){
    	return reportList;
    }
    
	//-------------------------------------------------------------------------------------------------------------------
    //		AGGIUNTA DI ATTIVITÀ ALLA LISTA
	//-------------------------------------------------------------------------------------------------------------------
    
	public void addReport(review reportedReview,business aBusiness, String reportType, user author) {
		report newReport = new report(reportedReview, aBusiness, reportType, author);
		reportList.add(newReport);
		this.toString();
	}

	@Override
	public String toString() {
		return "reportedReviews [reportedReviewsList=" + reportList + "]";
	}
	
	public void removeReport(report aReport) {
		Iterator<report> iterator = reportList.iterator();
		while(iterator.hasNext()) {
			if( iterator.next().getReview() == aReport.getReview()) {
				iterator.remove();
			}
		}
		aReport.getBusiness().removeReview(aReport.getReview());;
		aReport.getBusiness().updateAvgVote();
	}
	//-------------------------------------------------------------------------------------------------------------------
	
	
}
