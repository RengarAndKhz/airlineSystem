package CS509.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import CS509.data.airportInformation;
import CS509.data.flight;

public class Time {
	
	 private DateFormat time = new SimpleDateFormat("HH:mm");

	/**
	 * change to the local time according to the airport information
	 * @param FlightInfo
	 * @param airport
	 * @return list of FlightInfo whose time is local time
	 */
	    public List<flight> timeSwich(List<flight> FlightInfo, List<airportInformation> airport){
	        flight ThisInfo = new flight();
	        airportInformation ai = new airportInformation();
	        Iterator AllInfo = FlightInfo.iterator();
	        
	        SimpleDateFormat OTime = new SimpleDateFormat("yyyy_MM_dd HH:mm");
	        OTime.setTimeZone(TimeZone.getTimeZone("GMT"));
	        SimpleDateFormat NTime = new SimpleDateFormat("yyyy_MM_dd HH:mm");
	        String[] parts;
	        String ThisTime;
	        String[] airportName = ai.sortAirportName(airport);
	 
	        while (AllInfo.hasNext())
	        { 
	            ThisInfo= (flight)AllInfo.next();//From this we can get the arriving airport and departing airport
	            String date = ThisInfo.getDepartingDate()+" "+ThisInfo.getDepartingTime();
	            Date FTime = null;
	            switch (ai.getAirportRank(airportName, ThisInfo.getDepartingAirport())){
	                case 0:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Anchorage"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 1:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 2:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 3:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 4:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 5:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 6:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 7:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 8:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 9:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 10:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 11:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 12:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Denver"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 13:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Detroit"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 14:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 15:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 16:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 17:
	                    NTime.setTimeZone(TimeZone.getTimeZone("Pacific/Honolulu"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 18:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 19:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 20:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Indiana/Indianapolis"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 21:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 22:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 23:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 24:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 25:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 26:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 27:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 28:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 29:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 30:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 31:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 32:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 33:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 34:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 35:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 36:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Phoenix"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 37:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 38:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 39:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 40:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 41:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 42:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 43:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 44:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 45:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Paciifc"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 46:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 47:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 48:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 49:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	                case 50:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTime= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTime);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setDepartingDate(parts[0]);
	                    ThisInfo.setDepartingTime(parts[1]);
	                    break;
	 
	            }
	        }
	        Iterator AllInf = FlightInfo.iterator(); 
	        while (AllInf.hasNext())
	        {   ThisInfo=(flight)AllInf.next();
	            String date = ThisInfo.getArrivingDate()+" "+ThisInfo.getArrivingTime();
	            Date FTim = null;
	            switch (ai.getAirportRank(airportName, ThisInfo.getArrivingAirport())){
	                case 0:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Anchorage"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 1:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 2:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 3:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 4:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 5:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 6:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 7:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 8:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 9:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 10:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 11:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 12:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Denver"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 13:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Detroit"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 14:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 15:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 16:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 17:
	                    NTime.setTimeZone(TimeZone.getTimeZone("Pacific/Honolulu"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 18:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 19:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 20:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Indiana/Indianapolis"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 21:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 22:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 23:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 24:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 25:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 26:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 27:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 28:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 29:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 30:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 31:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 32:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 33:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 34:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 35:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 36:
	                    NTime.setTimeZone(TimeZone.getTimeZone("America/Phoenix"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 37:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 38:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 39:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 40:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 41:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 42:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 43:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 44:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 45:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Paciifc"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 46:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 47:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Central"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 48:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 49:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	                case 50:
	                    NTime.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
	                    try{
	                        FTim= OTime.parse(date);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }
	                    ThisTime = NTime.format(FTim);
	                    parts = ThisTime.split(" ");
	                    ThisInfo.setArrivingDate(parts[0]);
	                    ThisInfo.setArrivingTime(parts[1]);
	                    break;
	 
	            }
	        }
	 
	     return FlightInfo;
	    }

	/**
	 *
	 * @param currentFlightArrivingTime
	 * @param nextFlightDepartingTime
	 * @param currentFlightDate
	 * @param nextFlightDate
	 * @return
	 */
	public int timeInterval(String currentFlightArrivingTime, String nextFlightDepartingTime, String currentFlightDate, String nextFlightDate) {
		int time = 0;
		int date1 = Integer.parseInt("" + currentFlightDate.charAt(8) + currentFlightDate.charAt(9));
		int date2 = Integer.parseInt("" + nextFlightDate.charAt(8) + nextFlightDate.charAt(9));
		int h1 = Integer.parseInt("" + currentFlightArrivingTime.charAt(0) + currentFlightArrivingTime.charAt(1));
		int h2 = Integer.parseInt("" + nextFlightDepartingTime.charAt(0) + nextFlightDepartingTime.charAt(1));
		int m1 = Integer.parseInt("" + currentFlightArrivingTime.charAt(3) + currentFlightArrivingTime.charAt(4));
		int m2 = Integer.parseInt("" + nextFlightDepartingTime.charAt(3) + nextFlightDepartingTime.charAt(4));	
		
		if (date1 == date2) {
			time = (m2 - m1) + 60 * (h2 - h1);
		} else if (date1 < date2) {
			time = (m2 - m1) + 60 * (h2 - h1) + 1440;
		}
		
		
		return time;
	}

	/**
	 *
	 * @param time
	 * @return
	 */
	public int getHourFromTime(String time) {
		return Integer.parseInt("" + time.charAt(0) + time.charAt(1));
	}

	/**
	 *
	 * @param currentDepartingDate
	 * @return
	 */
	public String lastDate(String currentDepartingDate) {
		String lastDate;
		int day = Integer.parseInt("" + currentDepartingDate.charAt(8) + currentDepartingDate.charAt(9) ) + 1;
		if (day > 9) {
			lastDate = "2015_05_" + Integer.toString(day);
		} else {
			lastDate = "2015_05_0" + Integer.toString(day);
		}
		return lastDate;
	}

	/**
	 *
	 * @param f
	 * @return
	 */
	public int dateDifference (flight f) {
		String depDate = f.getDepartingDate();
		String arrDate = f.getArrivingDate();
		int diff = Integer.parseInt("" + arrDate.charAt(8) + arrDate.charAt(9) ) - Integer.parseInt("" + depDate.charAt(8) + depDate.charAt(9));
		return diff;
	}
}
