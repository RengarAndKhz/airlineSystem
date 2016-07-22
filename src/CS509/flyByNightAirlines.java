package CS509;


import java.util.ArrayList;
import java.util.List;

import CS509.DAO.QueryFactory;
import CS509.DAO.ReservationSystem;
import CS509.data.airplaneInformation;
import CS509.data.airportInformation;
import CS509.data.allFlightInformation;
import CS509.data.flight;
import CS509.utility.XMLFileParser;
import CS509.utility.filterFlights;
import CS509.utility.flightAvailableMatrix;
import CS509.view.userInterface;

public class flyByNightAirlines {
	/**
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{	
	
		try {
			userInterface window = new userInterface();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		/*ReservationSystem rs = new ReservationSystem();
		String xml  = QueryFactory.flightOrder("2101", "Coach");
		System.out.println(xml);
		rs.lock("TeamTricepts");
		rs.updateServer("TeamTricepts",xml);
		rs.unlock("TeamTricepts");*/
		
 
		/*String ticketAgency = "TeamTricepts";
		String departingDate = "2015_05_15";
		String startDate = "2015_05_08";
		String classType  = "FirstClass";
		airportInformation ai = new airportInformation();
		airplaneInformation ap = new airplaneInformation();
		XMLFileParser readFile = new XMLFileParser();
		flightAvailableMatrix fm = new flightAvailableMatrix();
		filterFlights filter = new filterFlights();
		List<airportInformation> ainfo = readFile.transformAirportInformation(ticketAgency);
		List<allFlightInformation> flightInformation = readFile.transformFlightInfo(ticketAgency, "2015_05_15", "BOS");
		ai.sortAirportByLongtitute(ainfo);
		ap.airplaneMapToModel(ticketAgency);
		List<List<flight>[][]> ml = new ArrayList<List<flight>[][]>();
		System.out.println(filter.oneWayFilter("BOS", "PIT", departingDate, classType, flightInformation, ap).size());
		ml = fm.allMatrix(ticketAgency, ainfo, classType, startDate, ap);
		//System.out.println(filter.twoStopTimeTransferFlight("BOS", "OAK", "2015_05_15", startDate, ml,ai).get(0).size());
		//int a1 = filter.oneStopTimeFlightForRoundTrip(filter.oneStopTimeTransferFlightForRoundTrip("BOS", "OAK", "2015_05_11", "2015_05_12", startDate, ml,ai)).get(0).size();
		//int a2 = filter.twoStopTimeFlightForRoundTrip(filter.twoStopTimeTransferFlightForRoundTrip("BOS", "OAK", "2015_05_11", "2015_05_12", startDate, ml,ai)).get(1).size();
		//System.out.println(a1);
		//System.out.println(a2);
		//List<List<flight>> flt = filter.twoStopTimeTransferFlight("ATL", "OAK", "2015_05_15", startDate, ml,ai);
		//System.out.println(flt.get(0).size());
		//System.out.println(flt.get(1).size());
		//System.out.println(flt.get(2).size());

		/*flight f = new flight();
		List<airportInformation> a = ai.transformAirportInformation("TeamTricepts");
		ai.sortAirportByLongtitute(a);
		//ai.print(a);
		allFlightInformation afi = new allFlightInformation();
		List<allFlightInformation> infor = new ArrayList<allFlightInformation>();
		for (airportInformation i : a){
			infor.addAll(afi.transformFlightInfo("TeamTricepts", "2015_05_15", i.Code));
		}*/
		//afi.flightInOneDay("TeamTricepts", "2015_05_15");
		//System.out.print(matrix[0][0]);
		//System.out.print(f.oneWayFilter("BOS", "BOS", "2015_05_15", "Coach", infor).isEmpty());
		
		
	}	
	
}
