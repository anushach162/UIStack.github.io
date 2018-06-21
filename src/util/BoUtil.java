package util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BoUtil {
	
	public void splitTheDatesIntoDaysList(String actType, List<String> inputDateList, Map<String, Map<String, List<String>>> daysMap) {
		
		try {

			for (String inputDate : inputDateList) {
				
				String finalInputDate = inputDate.substring(0, inputDate.indexOf("T"));
				
				DateFormat format2=new SimpleDateFormat("EEEE");
				SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
				Date dt1 = format1.parse(finalInputDate);
				String finalDay=format2.format(dt1);
			
				if(finalDay != null) {
					
					if (daysMap.containsKey(actType)) {
					
						Map<String, List<String>> finalDaysMap = new HashMap<String, List<String>>();
						List<String> finalDaysList = new ArrayList<String>();
						finalDaysMap = daysMap.get(actType);
						
						if(finalDaysMap.containsKey(finalDay)) {
							finalDaysList = finalDaysMap.get(finalDay);
							finalDaysList.add(inputDate);
							finalDaysMap.put(finalDay, finalDaysList);
							daysMap.put(actType, finalDaysMap);
						} else {
							finalDaysList.add(inputDate);
							finalDaysMap.put(finalDay, finalDaysList);
							daysMap.put(actType, finalDaysMap);
						}
						
					} else {
						
						Map<String, List<String>> finalDaysMap = new HashMap<String, List<String>>();
						List<String> finalDaysList = new ArrayList<String>();
						
						if(finalDaysMap.containsKey(finalDay)) {
							finalDaysList = finalDaysMap.get(finalDay);
							finalDaysList.add(inputDate);
							finalDaysMap.put(finalDay, finalDaysList);
							daysMap.put(actType, finalDaysMap);
						} else {
							finalDaysList.add(inputDate);
							finalDaysMap.put(finalDay, finalDaysList);
							daysMap.put(actType, finalDaysMap);
						}
						
					}
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	

public void findMostActiveTimeSlots(Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String,
		Map<String, Integer>> finalCountAfterWeightageMap,
		Map<String, Map<String, Map<String, Integer>>> dayMap) {
	
	Map<String, Integer> finalCountAfterMondayWeightageMap = new  HashMap<String, Integer>();
	Map<String, Integer> finalCountAfterTuesdayWeightageMap = new  HashMap<String, Integer>();
	Map<String, Integer> finalCountAfterWednesdayWeightageMap = new  HashMap<String, Integer>();
	Map<String, Integer> finalCountAfterThursdayWeightageMap = new  HashMap<String, Integer>();
	Map<String, Integer> finalCountAfterFridayWeightageMap = new  HashMap<String, Integer>();
	Map<String, Integer> finalCountAfterSaturdayWeightageMap = new  HashMap<String, Integer>();
	Map<String, Integer> finalCountAfterSundayWeightageMap = new  HashMap<String, Integer>();
	
	Map<String, Map<String, Integer>> finalCountMondayMap = new  HashMap<String, Map<String, Integer>>();
	Map<String, Map<String, Integer>> finalCountTuesdayMap = new  HashMap<String, Map<String, Integer>>();
	Map<String, Map<String, Integer>> finalCountWednesdayMap = new  HashMap<String, Map<String, Integer>>();
	Map<String, Map<String, Integer>> finalCountThursdayMap = new  HashMap<String, Map<String, Integer>>();
	Map<String, Map<String, Integer>> finalCountFridayMap = new  HashMap<String, Map<String, Integer>>();
	Map<String, Map<String, Integer>> finalCountSaturdayMap = new  HashMap<String, Map<String, Integer>>();
	Map<String, Map<String, Integer>> finalCountSundayMap = new  HashMap<String, Map<String, Integer>>();

	for (Entry<String, Map<String, List<String>>> entry : daysMap.entrySet()) { 
        
		//System.out.println("Key = " + entry.getKey() +", Value = " + entry.getValue());
		
		Map<String, List<String>> dataMap = entry.getValue();
		String activityType = entry.getKey();
		
		for(Entry<String, List<String>> dataEntry : dataMap.entrySet()) {
			
		//	System.out.println("Key = " + dataEntry.getKey() +", Value = " + dataEntry.getValue());
			
			List<String> timeStampList = dataEntry.getValue();
			String dayOfWeek = dataEntry.getKey();
			
			for(String timeStampEntry : timeStampList) {
				
				timeStampEntry = timeStampEntry.substring(timeStampEntry.indexOf("T")+1, timeStampEntry.indexOf("T")+3);
				
				if(dayOfWeek.equalsIgnoreCase("Monday")) {

					findMostActiveTimeSlotsonMonday(timeStampEntry, activityType, daysMap, finalCountMondayMap, finalCountAfterMondayWeightageMap);
					//dayMap.put(dayOfWeek, finalCountMondayMap);
				}
				
				if(dayOfWeek.equalsIgnoreCase("Tuesday")) {
					
					findMostActiveTimeSlotsonTuesday(timeStampEntry, activityType, daysMap, finalCountTuesdayMap, finalCountAfterTuesdayWeightageMap);
					//dayMap.put(dayOfWeek, finalCountTuesdayMap);
				}
				
				if(dayOfWeek.equalsIgnoreCase("Wednesday")) {
					
					findMostActiveTimeSlotsonWednesday(timeStampEntry, activityType, daysMap, finalCountWednesdayMap, finalCountAfterWednesdayWeightageMap);
					//dayMap.put(dayOfWeek, finalCountWednesdayMap);
				}
				
				if(dayOfWeek.equalsIgnoreCase("Thursday")) {
					
					findMostActiveTimeSlotsonThursday(timeStampEntry, activityType, daysMap, finalCountThursdayMap, finalCountAfterThursdayWeightageMap);
					//dayMap.put(dayOfWeek, finalCountThursdayMap);
				}
				
				if(dayOfWeek.equalsIgnoreCase("Friday")) {
					
					findMostActiveTimeSlotsonFriday(timeStampEntry, activityType, daysMap, finalCountFridayMap, finalCountAfterFridayWeightageMap);
					//dayMap.put(dayOfWeek, finalCountFridayMap);
				}
				
				
				if(dayOfWeek.equalsIgnoreCase("Saturday")) {
					
					findMostActiveTimeSlotsonSaturday(timeStampEntry, activityType, daysMap, finalCountSaturdayMap, finalCountAfterSaturdayWeightageMap);
					//dayMap.put(dayOfWeek, finalCountSaturdayMap);
				}
				
				if(dayOfWeek.equalsIgnoreCase("Sunday")) {
					
					findMostActiveTimeSlotsonSunday(timeStampEntry, activityType, daysMap, finalCountSundayMap, finalCountAfterSundayWeightageMap);
					//dayMap.put(dayOfWeek, finalCountSundayMap);
				}
			}
		}
	}
	
	
	dayMap.put("Monday", finalCountMondayMap);
	dayMap.put("Tuesday", finalCountTuesdayMap);
	dayMap.put("Wednesday", finalCountWednesdayMap);
	dayMap.put("Thursday", finalCountThursdayMap);
	dayMap.put("Friday", finalCountFridayMap);
	dayMap.put("Saturday", finalCountSaturdayMap);
	dayMap.put("Sunday", finalCountSundayMap);
	
	System.out.println("------Monday :");
	System.out.println(finalCountMondayMap);
	System.out.println(finalCountAfterMondayWeightageMap);
	System.out.println("------Tuesday :");
	System.out.println(finalCountAfterTuesdayWeightageMap);
	System.out.println("------Wednesday :");
	System.out.println(finalCountAfterWednesdayWeightageMap);
	System.out.println("------Thursday :");
	System.out.println(finalCountAfterThursdayWeightageMap);
	System.out.println("------Friday :");
	System.out.println(finalCountAfterFridayWeightageMap);
	System.out.println("------Saturday :");
	System.out.println(finalCountAfterSaturdayWeightageMap);
	System.out.println("------Sunday :");
	System.out.println(finalCountAfterSundayWeightageMap);
	
	System.out.println("---------------------------------------------------------------------------");
	System.out.println("------Monday :");
	System.out.println(finalCountMondayMap);
	System.out.println("------Tuesday :");
	System.out.println(finalCountTuesdayMap);
	System.out.println("------Wednesday :");
	System.out.println(finalCountWednesdayMap);
	System.out.println("------Thursday :");
	System.out.println(finalCountThursdayMap);
	System.out.println("------Friday :");
	System.out.println(finalCountFridayMap);
	System.out.println("------Saturday :");
	System.out.println(finalCountSaturdayMap);
	System.out.println("------Sunday :");
	System.out.println(finalCountSundayMap);
	
	
	System.out.println("------MONDAY : Most Active Time Slot : ");
	String timeMonInterval = findMostActiveTimeSlot(finalCountAfterMondayWeightageMap);
	if(timeMonInterval!= null) {
		System.out.println(timeMonInterval.substring(0, 2) + " Hrs To " + timeMonInterval.substring(2, 4) + " Hrs");
	} else {
		System.out.println("There is No Activity on Monday.");
	}
	
	System.out.println("------Tuesday : Most Active Time Slot : ");
	String timeTueInterval = findMostActiveTimeSlot(finalCountAfterTuesdayWeightageMap);
	if(timeTueInterval!= null) {
		System.out.println(timeTueInterval.substring(0, 2) + " Hrs To " + timeTueInterval.substring(2, 4) + " Hrs");
	} else {
		System.out.println("There is No Activity on Tuesday.");
	}
	
	System.out.println("------Wednesday : Most Active Time Slot : ");
	String timeWedInterval = findMostActiveTimeSlot(finalCountAfterWednesdayWeightageMap);
	if(timeWedInterval!= null) {
		System.out.println(timeWedInterval.substring(0, 2) + " Hrs To " + timeWedInterval.substring(2, 4) + " Hrs");
	} else {
		System.out.println("There is No Activity on Wednesday.");
	}

	System.out.println("------Thursday : Most Active Time Slot : ");
	String timeThursInterval = findMostActiveTimeSlot(finalCountAfterThursdayWeightageMap);
	if(timeThursInterval!= null) {
		System.out.println(timeThursInterval.substring(0, 2) + " Hrs To " + timeThursInterval.substring(2, 4) + " Hrs");
	} else {
		System.out.println("There is No Activity on Thursday.");
	}
	
	System.out.println("------Friday : Most Active Time Slot : ");
	String timeFriInterval = findMostActiveTimeSlot(finalCountAfterFridayWeightageMap);
	if(timeFriInterval!= null) {
		System.out.println(timeFriInterval.substring(0, 2) + " Hrs To " + timeFriInterval.substring(2, 4) + " Hrs");
	} else {
		System.out.println("There is No Activity on Friday.");
	}
	
	System.out.println("------Saturday : Most Active Time Slot : ");
	String timeSatInterval = findMostActiveTimeSlot(finalCountAfterSaturdayWeightageMap);
	if(timeSatInterval!= null) {
		System.out.println(timeSatInterval.substring(0, 2) + " Hrs To " + timeSatInterval.substring(2, 4) + " Hrs");
	} else {
		System.out.println("There is No Activity on Saturday.");
	}

	System.out.println("------Sunday : Most Active Time Slot : ");
	String timeSunInterval = findMostActiveTimeSlot(finalCountAfterSundayWeightageMap);
	if(timeSunInterval!= null) {
		System.out.println(timeSunInterval.substring(0, 2) + " Hrs To " + timeSunInterval.substring(2, 4) + " Hrs");
	} else {
		System.out.println("There is No Activity on Sunday.");
	}
}


public void findMostActiveTimeSlotsonSunday(String timeStampEntry, String activityType, Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String, Integer> finalCountAfterSundayWeightageMap) {

	splitTheDaysIntoTimeSlots(timeStampEntry, activityType, finalCountMap);
	
	getFinalCountsAfterWeightage(finalCountMap,finalCountAfterSundayWeightageMap);
	
}

public void findMostActiveTimeSlotsonSaturday(String timeStampEntry, String activityType, Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String, Integer> finalCountAfterSaturdayWeightageMap) {

	splitTheDaysIntoTimeSlots(timeStampEntry, activityType, finalCountMap);
	
	getFinalCountsAfterWeightage(finalCountMap,finalCountAfterSaturdayWeightageMap);
	
}

public void findMostActiveTimeSlotsonFriday(String timeStampEntry, String activityType, Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String, Integer> finalCountAfterFridayWeightageMap) {

	splitTheDaysIntoTimeSlots(timeStampEntry, activityType, finalCountMap);
	
	getFinalCountsAfterWeightage(finalCountMap,finalCountAfterFridayWeightageMap);
	
}

public void findMostActiveTimeSlotsonThursday(String timeStampEntry, String activityType, Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String, Integer> finalCountAfterThursdayWeightageMap) {

	splitTheDaysIntoTimeSlots(timeStampEntry, activityType, finalCountMap);
	
	getFinalCountsAfterWeightage(finalCountMap,finalCountAfterThursdayWeightageMap);
	
}

public void findMostActiveTimeSlotsonWednesday(String timeStampEntry, String activityType, Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String, Integer> finalCountAfterWednesdayWeightageMap) {
	
	splitTheDaysIntoTimeSlots(timeStampEntry, activityType, finalCountMap);
	
	getFinalCountsAfterWeightage(finalCountMap,finalCountAfterWednesdayWeightageMap);
	
}

public void findMostActiveTimeSlotsonTuesday(String timeStampEntry, String activityType, Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String, Integer> finalCountAfterTuesdayWeightageMap) {

	splitTheDaysIntoTimeSlots(timeStampEntry, activityType, finalCountMap);
	
	getFinalCountsAfterWeightage(finalCountMap,finalCountAfterTuesdayWeightageMap);
	
}

public void findMostActiveTimeSlotsonMonday(String timeStampEntry, String activityType, Map<String, Map<String, List<String>>> daysMap, Map<String, Map<String, Integer>> finalCountMap, Map<String, Integer> finalCountAfterMondayWeightageMap) {
	
	splitTheDaysIntoTimeSlots(timeStampEntry, activityType, finalCountMap);
	
	getFinalCountsAfterWeightage(finalCountMap , finalCountAfterMondayWeightageMap);
	
}


public String findMostActiveTimeSlot(Map<String, Integer> finalCountAfterWeightageMap) {

	int max = 0;
	
	String interval = null;
	
	for (Entry<String, Integer> entry : finalCountAfterWeightageMap.entrySet()) { 
		
	//	System.out.println("Key = " + entry.getKey() +", Value = " + entry.getValue());
		
		if(max < entry.getValue()) {

			max = entry.getValue();
			interval = entry.getKey();
		}

	}
	 return interval;
}

public void getFinalCountsAfterWeightage(Map<String, Map<String, Integer>> finalCountMap, Map<String,Integer> finalCountAfterWeightageMap) {
	
	for (Entry<String, Map<String, Integer>> entry : finalCountMap.entrySet()) { 
		
	//	System.out.println("Key = " + entry.getKey() +", Value = " + entry.getValue());
		
		Map<String, Integer> countMap = entry.getValue();
		String interval = entry.getKey();
		
		int finalCount = 0;
		
		for(Entry<String, Integer> dataEntry : countMap.entrySet()) {
			
			int likesCount = 0;
			int sharesCount = 0;
			int commentsCount = 0;
			
			//System.out.println("Key = " + dataEntry.getKey() +", Value = " + dataEntry.getValue());
			
			String actType = dataEntry.getKey();
			int count = dataEntry.getValue().intValue();
			
			if(actType.equalsIgnoreCase("Comments")) {
				
				commentsCount = count * 30;
			}
			
			if(actType.equalsIgnoreCase("Shares")) {
				
				sharesCount = count * 40;
			}
		
			if(actType.equalsIgnoreCase("Likes")) {
				
				likesCount = count * 60;
			}
			
			finalCount = finalCount + commentsCount + sharesCount + likesCount;
			
		}
		
		finalCountAfterWeightageMap.put(interval, finalCount);
		
	}
	
}

public void splitTheDaysIntoTimeSlots(String timeStampEntry, String activityType, Map<String, Map<String, Integer>> finalCountMap) {
	
	if(isBetweenInterval(timeStampEntry,"00","02",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"02","04",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"04","06",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"06","08",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"08","10",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"10","12",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"12","14",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"14","16",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"16","18",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"18","20",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"20","22",activityType, finalCountMap)) {
	} else if(isBetweenInterval(timeStampEntry,"22","24",activityType, finalCountMap)) {
	}
}

public boolean isBetweenInterval(String timeStamp, String start, String end, String activityType, Map<String, Map<String, Integer>> finalCountMap) {
	
	int likesCount = 0;
	int sharesCount = 0;
	int commentsCount = 0;
	
	boolean flag = ((timeStamp.compareTo(start) >= 0) && (timeStamp.compareTo(end) < 0));
	
	if(flag) {
	
	if(activityType.equalsIgnoreCase("Comments")) {
		commentsCount ++;
	}
	
	if(activityType.equalsIgnoreCase("Likes")) {
		likesCount ++;
	}
		
	if(activityType.equalsIgnoreCase("Shares")) {
		sharesCount ++;
	}
		
	if (finalCountMap.containsKey(start+end)) {
			
		Map<String, Integer> finalActCountMap = new HashMap<String, Integer>();
		
		finalActCountMap = finalCountMap.get(start+end);
			
		if(finalActCountMap.containsKey(activityType)) {
			
			if(activityType.equalsIgnoreCase("Comments")) {
				commentsCount = commentsCount + finalActCountMap.get(activityType) ;
				finalActCountMap.put(activityType, commentsCount);
			}
			if(activityType.equalsIgnoreCase("Shares")) {
				sharesCount = sharesCount + finalActCountMap.get(activityType);
				finalActCountMap.put(activityType, sharesCount);
			}
			if(activityType.equalsIgnoreCase("Likes")) {
				likesCount = likesCount + finalActCountMap.get(activityType);
				finalActCountMap.put(activityType, likesCount);
			}
				
			finalCountMap.put(start+end, finalActCountMap);
				
		} else {
			if(activityType.equalsIgnoreCase("Comments")) {
				finalActCountMap.put(activityType, commentsCount);
			}
			if(activityType.equalsIgnoreCase("Shares")) {
				finalActCountMap.put(activityType, sharesCount);
			}
			if(activityType.equalsIgnoreCase("Likes")) {
				finalActCountMap.put(activityType, likesCount);
			}
			
			finalCountMap.put(start+end, finalActCountMap);
		}
			
	} else {
			
		Map<String, Integer> finalActCountMap = new HashMap<String, Integer>();
			
		if(finalActCountMap.containsKey(activityType)) {
				
			if(activityType.equalsIgnoreCase("Comments")) {
				commentsCount = commentsCount + finalActCountMap.get(activityType) ;
				finalActCountMap.put(activityType, commentsCount);
			}
			if(activityType.equalsIgnoreCase("Shares")) {
				sharesCount = sharesCount + finalActCountMap.get(activityType);
				finalActCountMap.put(activityType, sharesCount);
			}
			if(activityType.equalsIgnoreCase("Likes")) {
				likesCount = likesCount + finalActCountMap.get(activityType);
				finalActCountMap.put(activityType, likesCount);
			}
				
			finalCountMap.put(start+end, finalActCountMap);
				
		} else {
			if(activityType.equalsIgnoreCase("Comments")) {
				finalActCountMap.put(activityType, commentsCount);
			}
			if(activityType.equalsIgnoreCase("Shares")) {
				finalActCountMap.put(activityType, sharesCount);
			}
			if(activityType.equalsIgnoreCase("Likes")) {
				finalActCountMap.put(activityType, likesCount);
			}
			
			finalCountMap.put(start+end, finalActCountMap);
		}
	}
	
	}
	
	return flag;
}

}
