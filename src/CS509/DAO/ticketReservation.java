package CS509.DAO;
import java.util.List;

import CS509.data.flight;

public class ticketReservation {
	/**
	 * buy ticket
	 * @param ticketAgency
	 * @param flight
	 */
	public void buyTicket(String ticketAgency,List<flight> flight){
		

		ReservationSystem rs = new ReservationSystem();

		String xmlString = QueryFactory.getXMLBeginString();;
		System.out.println(flight.size());
		
		rs.lock(ticketAgency);
		
        if(!flight.isEmpty()) {
    		for (int i = 0; i < flight.size(); i++) { 
    		
    			xmlString = xmlString + QueryFactory.getXMLFlights(flight.get(i).getFlightNumber(), flight.get(i).getClassType());
    			
    		}	
    			xmlString = xmlString + QueryFactory.getXMLEndString();
    			
    	  		System.out.println(xmlString);
    	        rs.updateServer(ticketAgency, xmlString);	
    	        
    			
		} 
        
        rs.unlock(ticketAgency);
		
	}

	
}
