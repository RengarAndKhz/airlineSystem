package CS509.data;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class flight {

	String flightNumber;
	String departingAirport;
	String arrivingAirport;
	String departingDate;
	String arrivingDate;
	String departingTime;
	String arrivingTime;
	String classType;
	int flightTime;
	int seat;
	double ticketPrice = 0;

	int stopTimes;
	public void setFlightfromAllFlightInformation(allFlightInformation Flight,int numStop, int seatLeft, String classType){
		this.flightNumber = Flight.number;
		this.departingAirport = Flight.departingAirport;
		this.arrivingAirport = Flight.arrivingAirport;
		this.departingDate = Flight.departingDate;
		this.arrivingDate = Flight.arrivingDate;
		this.departingTime = Flight.departingTime;
		this.arrivingTime = Flight.arrivingTime;
		this.classType = classType;
		this.ticketPrice = Flight.getPrice(classType);
		this.flightTime = Flight.getFlightTime();
		this.stopTimes = 0;   
		this.seat = seatLeft;
	}
	public void combinedFlight(int numStops, int row, List<List<flight>> transferFlight){
		this.arrivingAirport = transferFlight.get(numStops).get(row).arrivingAirport;
		this.arrivingDate = transferFlight.get(numStops).get(row).arrivingDate;
		this.arrivingTime = transferFlight.get(numStops).get(row).arrivingTime;
		this.classType = transferFlight.get(numStops).get(row).classType;
		this.departingAirport = transferFlight.get(0).get(row).departingAirport;
		this.departingDate = transferFlight.get(0).get(row).departingDate;
		this.departingTime = transferFlight.get(0).get(row).departingTime;
		this.classType = transferFlight.get(0).get(row).classType;
		this.flightNumber = transferFlight.get(0).get(row).flightNumber + "/" + transferFlight.get(1).get(row).flightNumber;
		if( numStops == 2 ){
			this.flightNumber += "/" + transferFlight.get(2).get(row).flightNumber;
		}
		this.stopTimes = numStops;
	}
	
    public void sortByFlightTime(List<flight> Info){
    	Comparator<flight> C = new Comparator<flight>() {
    		@Override
    		public int compare(flight o1, flight o2) {
    			return Integer.valueOf(o1.flightTime).compareTo(Integer.valueOf(o2.flightTime));
    		}
    	};
    	Collections.sort(Info,C);  	
    }
    
    public void sortByPrice(List<flight> Info){
        Comparator<flight> C = new Comparator<flight>() {
            @Override
            public int compare(flight o1, flight o2) {
                return Double.valueOf(o1.getTicketPrice()).compareTo(Double.valueOf(o2.getTicketPrice()));
            }
        };
        Collections.sort(Info, C);
    }
    
	public void addTicketPrice(double price){
		this.ticketPrice +=price;
	}
	public void addFlightTime(int time){
		this.ticketPrice += flightTime;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public String getDepartingTime() {
		return departingTime;
	}
	public String getArrivingTime() {
		return arrivingTime;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public String getDepartingAirport() {
		return departingAirport;
	}
	public String getArrivingAirport() {
		return arrivingAirport;
	}
	public String getDepartingDate() {
		return departingDate;
	}
	public String getArrivingDate() {
		return arrivingDate;
	}
	public String getClassType() {
		return classType;
	}
	public int getSeat() {
		return seat;
	}
	public int getFlightTime() {
		return flightTime;
	}
 	public int getStopTimes() {
		return stopTimes;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public void setDepartingAirport(String departingAirport) {
		this.departingAirport = departingAirport;
	}
	public void setArrivingAirport(String arrivingAirport) {
		this.arrivingAirport = arrivingAirport;
	}
	public void setDepartingDate(String departingDate) {
		this.departingDate = departingDate;
	}
	public void setArrivingDate(String arrivingDate) {
		this.arrivingDate = arrivingDate;
	}
	public void setDepartingTime(String departingTime) {
		this.departingTime = departingTime;
	}
	public void setArrivingTime(String arrivingTime) {
		this.arrivingTime = arrivingTime;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public void setStopTimes(int stopTimes) {
		this.stopTimes = stopTimes;
	}
	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}
	
}

