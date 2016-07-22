package CS509.data;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;


public class airportInformation {
	
	public String Code;
	public double Latitude;
	public double Longitude;
	
	Hashtable<String, Integer> map = new Hashtable<String, Integer>(); 
	
	public List<String> getAirportName(List<airportInformation> airportInfo) {
		List<String> airportName = new ArrayList<String>();
		for (airportInformation ai : airportInfo) {
			String s = ai.Code;
			airportName.add(s);
		}
		return airportName;
	}
	
	
	public void sortAirportByLongtitute(List<airportInformation> airport){
		Comparator<airportInformation> comparator = new Comparator<airportInformation>(){
			public int compare(airportInformation a1, airportInformation a2) {
			    	return (a1.Longitude < a2.Longitude)? 0:1;
			}
		};
		Collections.sort(airport,comparator);
		int i = 0;
		for ( airportInformation ai:airport) {
			map.put(ai.Code, i++);
		}
	}
	
	public String[] sortAirportName( List<airportInformation> airport ){
		 String[] strs = new String[airport.size()]; 
		 for (int k = 0; k < airport.size(); k++) {
			 strs[k] = airport.get(k).Code;
		 }
		 for (int i = 0; i < strs.length-1; i++) {   
			 for (int j =i+1; j < strs.length; j++) {
				 int intTemp = strs[i].compareToIgnoreCase(strs[j]);
				 String strTemp = "";
				 if(intTemp>0){
					 strTemp = strs[j];
					 strs[j] = strs[i];
					 strs[i] = strTemp;
				 }
			 }
		 }
		 return strs;
	}
	
	public int getAirportRank(String[] s, String name){
		Hashtable<String, Integer> map = new Hashtable<String, Integer>();
		for (int i = 0; i < s.length; i++) {
			map.put(s[i], i);
		}
		return map.get(name);
	}
	
	public int airportNumber(List<airportInformation> airport){
		int a = airport.size();
		return a;
	}
	
	public int getInformationByAirportName(String airportName){
		return map.get(airportName);		
	}
	
	public void print(List<airportInformation> airport) {
		for (airportInformation s : airport){
			System.out.println(s.Code);
			System.out.println(s.Latitude);
			System.out.println(s.Longitude);
		}
	}
	public void printAirportName(List<String> airportName) {
		for (String s : airportName){
			System.out.println(s);
		}
	}
	

}
