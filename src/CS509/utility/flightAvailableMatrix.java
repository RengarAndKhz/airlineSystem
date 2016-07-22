package CS509.utility;

import java.util.ArrayList;
import java.util.List;

import CS509.data.airplaneInformation;
import CS509.data.airportInformation;
import CS509.data.allFlightInformation;
import CS509.data.flight;

public class flightAvailableMatrix {

	/**
	 * @function flightAvailableMatrixOnGivenDate
	 * Generate the flight matrix on the given date
	 * Get the query result from XML server
	 * @param ticketAgency
	 * @param airport
	 * @param departingDate
	 * @param classType
	 * @param ap
	 * @return
	 * @throws Exception
	 */
	public List<flight>[][] flightAvailableMatrixOnGivenDate(String ticketAgency, List<airportInformation> airport, String departingDate, String classType, airplaneInformation ap) throws Exception{
		XMLFileParser parser = new XMLFileParser();
		filterFlights filter = new filterFlights();
		Time timeSwitchFlight = new Time();
		String departDate1 = timeSwitchFlight.lastDate(departingDate);
		List<allFlightInformation> flightInformation = parser.flightInOneDay(ticketAgency, departingDate);
		List<allFlightInformation> flightInformation1 = parser.flightInOneDay(ticketAgency, departDate1);

		int d = airport.size();
		List<flight>[][] indicatorMatrix = new ArrayList[d][d];

		
		for (int i = 0; i < d; i++){
			for (int j = 0; j < d; j++){
				List<flight> f = timeSwitchFlight.timeSwich(filter.oneWayFilter(airport.get(i).Code, airport.get(j).Code, departingDate, classType, flightInformation, ap), airport);
				List<flight> f1 = timeSwitchFlight.timeSwich(filter.oneWayFilter(airport.get(i).Code, airport.get(j).Code, departDate1, classType, flightInformation1, ap), airport);
				indicatorMatrix[i][j] = new ArrayList<flight>();
				if (!f.isEmpty() || !f1.isEmpty()) {
					if ( !f.isEmpty() ){
						for(flight F : f) {
							if (F.getDepartingDate().equals(departingDate) ) {
								indicatorMatrix[i][j].add(F);
							}
						}
					}
					if ( !f1.isEmpty() ){
						for(flight F : f1) {
							if (F.getDepartingDate().equals(departingDate) ) {
								indicatorMatrix[i][j].add(F);
								
							}
						}
						
					} 
				} else {
					indicatorMatrix[i][j] = new ArrayList<flight>();
				}
			}
		}		
		return indicatorMatrix;
	}
	/**
	 * @function allMatrix
	 * Add every generated matrix (from function "flightAvailableMatrixOnGivenDate") into the big matrix list, which is the final matrix list we use for the flight query.
	 * (There are two kinds of matrix list, one is "firstclass" matrix list and one is "coach" matrix list.
 	 *  They are only different from price. This big redundency could be future improvement)
 	 *  Each final matrix list("firstclass" or "coach") has 10 small matrix of flight, which stands for one matrix per day.
	 * @param ticketAgency
	 * @param airport
	 * @param departingDate
	 * @param classType
	 * @param ap
	 * @return
	 * @throws Exception
	 */
	public List<List<flight>[][]> allMatrix(String ticketAgency, List<airportInformation> airport, String classType, String startDate, airplaneInformation ap){
		List<List<flight>[][]> matrixList = new ArrayList<List<flight>[][]>();
		for (int i = 0; i < 10; ++i){
			try {
				matrixList.add(flightAvailableMatrixOnGivenDate(ticketAgency, airport, startDate, classType, ap));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int date = Integer.parseInt("" + startDate.charAt(startDate.length()-2) + startDate.charAt(startDate.length()-1)) + 1;
			if ( date < 10 ){
				startDate = "2015_05_0" + Integer.toString(date);
			}else {
				startDate = "2015_05_" + Integer.toString(date);
			}
			System.out.println(i);
			
		}
		return matrixList;
	}
	

}
