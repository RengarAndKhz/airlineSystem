package CS509.DAO;

public class QueryFactory {
	/**
	 * get the name of ticket agent
	 * @return name of ticket agent
	 */
	public static String getTicketAgency(){
		return "TeamTricepts";
	}

	/**
	 * get the information of airport from database
	 * @param ticketAgency
	 * @return xml file of airport information
	 */
	public static String getAirports(String ticketAgency){
		return "?team=" + ticketAgency + "&action=list&list_type=airports";
	}

	/**
	 * get the information of airplane from database
	 * @param ticketAgency
	 * @return xml file of airplane information
	 */
	public static String getAirplanes(String ticketAgency){
		return "?team=" + ticketAgency + "&action=list&list_type=airplanes";
	}

	/**
	 * get the information of departing flights in some day
	 * @param ticketAgency
	 * @param departingAirport
	 * @param departingDate
	 * @return xml file of departing flights information
	 */
	public static String getDepartingFlights(String ticketAgency, String departingAirport, String departingDate){
		return "?team=" + ticketAgency + "&action=list&list_type=departing&airport=" + 
				departingAirport + "&day=" + departingDate;
	}

	/**
	 * get the information of arriving flight in some day
	 * @param ticketAgency
	 * @param arrivingAirport
	 * @param arrivingDate
	 * @return the xml file of departing flights information
	 */
	public static String getArrivingFlights(String ticketAgency, String arrivingAirport, String arrivingDate){
		return "?team=" + ticketAgency + "&action=list&list_type=departing&airport=" + 
				arrivingAirport + "&day=" + arrivingDate;
	}

	/**
	 * get the lock database command
	 * @param ticketAgency
	 * @return string statement
	 */
	public static String lock(String ticketAgency){
		return "team=" + ticketAgency + "&action=lockDB";
	}

	/**
	 * get the unlock the database command
	 * @param ticketAgency
	 * @return string statement
	 */
	public static String unlock(String ticketAgency){
		return "team=" + ticketAgency + "&action=unlockDB";
	
	}
	/**
	 *
	 */
	public static String getXMLBeginString() {
	    return "<Flights>";
	}

	/**
	 *
	 * @param flightNumber
	 * @param classType
	 * @return
	 */

	public static String getXMLFlights(String  flightNumber,  String classType) {
		return "<Flight number='" + flightNumber + "' seating='" + classType + "' />";
	}

	/**
	 *
	 * @return
	 */
	public static String getXMLEndString() {
		return "</Flights>";
	}

	/**
	 *
	 * @param ticketAgency
	 * @param xmlFlights
	 * @return
	 */
	public static String updateServerURL(String ticketAgency, String xmlFlights) {
		return "team=" + ticketAgency + "&action=buyTickets"
				  + "&flightData=" + xmlFlights;
	}
}
