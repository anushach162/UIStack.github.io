package bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.BoUtil;
import util.ReadGraphAPIFeed;

public class MainBusinessLogic {

	public static void main(String[] args) {
		
		BoUtil boUtil = new BoUtil();
		Map<String,Map<String, List<String>>> daysMap = new HashMap<String,Map<String, List<String>>>();
		Map<String, Map<String, Integer>> finalCountMap = new  HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountAfterWeightageMap = new  HashMap<String, Map<String, Integer>>();
		
/*		Map<String, Map<String, Integer>> finalCountMondayMap = new  HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountTuesdayMap = new  HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountWednesdayMap = new  HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountThursdayMap = new  HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountFridayMap = new  HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountSaturdayMap = new  HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountSundayMap = new  HashMap<String, Map<String, Integer>>();
		*/
		Map<String, Map<String, Map<String, Integer>>> dayMap = new HashMap<String, Map<String, Map<String, Integer>>>();
		
		ReadGraphAPIFeed graphAPI = new ReadGraphAPIFeed();
		List<String> commentsList = graphAPI.getCommentsList();
		List<String> sharesList = graphAPI.getSharesList();
		List<String> likesList = graphAPI.getLikesList();
		
		boUtil.splitTheDatesIntoDaysList("Shares",sharesList,daysMap); 
		boUtil.splitTheDatesIntoDaysList("Likes",likesList,daysMap);
		boUtil.splitTheDatesIntoDaysList("Comments",commentsList,daysMap);
		
		System.out.println("----------------daysMap : ");
		System.out.println(daysMap);

		boUtil.findMostActiveTimeSlots(daysMap, finalCountMap, finalCountAfterWeightageMap,dayMap);
		
		String finalJsonObj = graphAPI.convertMaptoJsonObj(dayMap);
		System.out.println("------Final Json Object : \n" +finalJsonObj);
		
	/*	String monJson = graphAPI.convertMaptoJson(finalCountMondayMap);
		System.out.println("\n ------Monday Json : " +monJson);
		String tueJson =graphAPI.convertMaptoJson(finalCountTuesdayMap);
		System.out.println("------Tuesday Json : " +tueJson);
		String wedJson =graphAPI.convertMaptoJson(finalCountWednesdayMap);
		System.out.println("------Wednesday Json : " +wedJson);
		String thursJson =graphAPI.convertMaptoJson(finalCountThursdayMap);
		System.out.println("------Thursday Json : " +thursJson);
		String friJson =graphAPI.convertMaptoJson(finalCountFridayMap);
		System.out.println("------Friday Json : " +friJson);
		String satJson =graphAPI.convertMaptoJson(finalCountSaturdayMap);
		System.out.println("------Saturday Json : " +satJson);
		String sunJson =graphAPI.convertMaptoJson(finalCountSundayMap);
		System.out.println("------Sunday Json : " +sunJson);*/
		
	}

}
