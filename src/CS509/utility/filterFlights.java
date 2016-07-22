package CS509.utility;

import java.util.ArrayList;
import java.util.List;

import CS509.data.airplaneInformation;
import CS509.data.airportInformation;
import CS509.data.allFlightInformation;
import CS509.data.flight;

/**
 *
 */
public class filterFlights {
	/**
	 * @function oneWayFilter
	 * Filter out all the result for NON-stop(zero stop time) flights.
	 * Get the result by query in XML server
	 * @param departingAirport
	 * @param arrivingAirport
	 * @param departingDate
	 * @param classType
	 * @param flightInformation
	 * @param ap
	 * @return
	 * @throws Exception
	 */
	public List<flight> oneWayFilter(String departingAirport, String arrivingAirport, String departingDate, String classType, List<allFlightInformation> flightInformation, airplaneInformation ap) throws Exception {
		List<flight> filteredFlights = new ArrayList<flight>();
		for (allFlightInformation Flight : flightInformation) {
			int seatLeft = Flight.getSeat(classType, ap);
			if (Flight.getDepartingAirport().equals(departingAirport) && Flight.getArrivingAirport().equals(arrivingAirport) && Flight.getDepartingDate().equals(departingDate) 
					&& seatLeft >0) {
				flight f = new flight();
				f.setFlightfromAllFlightInformation(Flight, 0, seatLeft, classType);
				filteredFlights.add(f);
			}
		}
		return filteredFlights;
	}

	/**
	 * @function filterByFlightNumber
	 * Filter out all the result according to the flight number
	 * Get the result by query in XML server
	 * @param ticketAgency
	 * @param flightNumber
	 * @param departingDate
	 * @param depAirport
	 * @param classType
	 * @param ap
	 * @return
	 * @throws Exception
	 */
	public flight filterByFlightNumber(String ticketAgency, String flightNumber, String departingDate, String depAirport, String classType, airplaneInformation ap) throws Exception {
		flight f = new flight();
		Time t = new Time();
		XMLFileParser rf = new XMLFileParser();
		String departingDate1 = t.lastDate(departingDate);
		List<allFlightInformation> unfilteredFlight = rf.transformFlightInfo(ticketAgency, departingDate, depAirport);
		unfilteredFlight.addAll(rf.transformFlightInfo(ticketAgency, departingDate1, depAirport));
		
		for (allFlightInformation afi : unfilteredFlight) {
			if(afi.getNumber().equals(flightNumber)) {
				int seatLeft = afi.getSeat(classType, ap); 
				f.setFlightfromAllFlightInformation(afi, 0, seatLeft, classType);
			}
		}
		
		return f;
	}

	/**
	 * @function filterByFlightNumber
	 * [Function overloading]
	 * Filter out all the result according to the flight number
	 * Get the result by query from Flight matrix
	 * @param flightNumber
	 * @param indicatorMatrix
	 * @param departingDate
	 * @param startDate
	 * @param airport
	 * @return
	 * @throws Exception
	 */
	public flight filterByFlightNumber(String flightNumber, List<List<flight>[][]> indicatorMatrix, String departingDate, String startDate, List<airportInformation> airport) throws Exception {
		flight f = new flight();
		int d = airport.size();
		int index = Integer.parseInt("" + departingDate.charAt(departingDate.length()-2) + departingDate.charAt(departingDate.length()-1)) - 
				Integer.parseInt("" + startDate.charAt(startDate.length()-2) + startDate.charAt(startDate.length()-1));
		for (int i = 0; i < d; i++){
			for (int j = 0; j < d; j++){
				for (int k = 0; k < indicatorMatrix.get(index)[i][j].size(); k++) {
					if (indicatorMatrix.get(index)[i][j].get(k).getFlightNumber().equals(flightNumber)) {
						f = indicatorMatrix.get(index)[i][j].get(k);
					}					
				}
				for (int k = 0; k < indicatorMatrix.get(index+1)[i][j].size(); k++) {
					if (indicatorMatrix.get(index+1)[i][j].get(k).getFlightNumber().equals(flightNumber)) {
						f = indicatorMatrix.get(index+1)[i][j].get(k);
					}					
				}

			}
		}	
		return f;
	}

	/**
	 * @function roundtripFilter
	 * Filter out all the result for the round trip of NON-stop(zero stop time) flights.
	 * Get the result by query from Flight matrix
	 * @param ticketAgency
	 * @param departingAirport
	 * @param arrivingAirport
	 * @param departingDate
	 * @param returningDate
	 * @param classType
	 * @param depFlightInformation
	 * @param retFlightInformation
	 * @param ap
	 * @return
	 * @throws Exception
	 */
	public List<List<flight>> roundtripFilter(String ticketAgency, String departingAirport, String arrivingAirport, String departingDate, String returningDate, String classType, 
			List<allFlightInformation> depFlightInformation, List<allFlightInformation> retFlightInformation, airplaneInformation ap) throws Exception {
		List<flight> filteredDepartFlight = new ArrayList<flight>();
		List<flight> filteredReturnFlight = new ArrayList<flight>();
		List<List<flight>> doubleList = new ArrayList<List<flight>>(); 
		
		filteredDepartFlight = oneWayFilter(departingAirport, arrivingAirport, departingDate, classType, depFlightInformation, ap);
		filteredReturnFlight = oneWayFilter(arrivingAirport, departingAirport, returningDate, classType, retFlightInformation, ap);						
		doubleList.add(filteredDepartFlight);
		doubleList.add(filteredReturnFlight);
		
		return doubleList;
	}

	/**
	 * @function oneStopTimeTransferFlight
	 * Filter out all the possible first flight and second flight compositions for one-direction(not round trip)
	 * And store these flight compositions into two arraylist seperately while keeping the order.
	 * Get the query result from Flight matrix.
	 * @param departingAirport
	 * @param arrivingAirport
	 * @param departingDate
	 * @param startDate
	 * @param indicatorMatrix
	 * @param info
	 * @return
	 */
	public List<List<flight>> oneStopTimeTransferFlight(String departingAirport, String arrivingAirport, String departingDate, String startDate, List<List<flight>[][]> indicatorMatrix, airportInformation info){
		List<List<flight>> f = new ArrayList<List<flight>>();
		List<flight> firstFlight = new ArrayList<flight>();
		List<flight> secondFlight = new ArrayList<flight>();
		int i = info.getInformationByAirportName(departingAirport);
		int j = info.getInformationByAirportName(arrivingAirport);
		int matrixIndex = Integer.parseInt("" + departingDate.charAt(departingDate.length()-2) + departingDate.charAt(departingDate.length()-1)) - 
		Integer.parseInt("" + startDate.charAt(startDate.length()-2) + startDate.charAt(startDate.length()-1));
		if (matrixIndex < 9) {
			if (i < j){
				//From a western airport to a eastern airport
				for (int k = i; k < j; k++){
					if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex)[k][j].isEmpty() ){
						for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
							for (int s = 0; s < indicatorMatrix.get(matrixIndex)[k][j].size(); s++) {
								Time timer = new Time();
								int timeInterval = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
										indicatorMatrix.get(matrixIndex)[k][j].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
										indicatorMatrix.get(matrixIndex)[k][j].get(s).getDepartingDate());
								if ( timeInterval > 30 && timeInterval < 200) {
									firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
									secondFlight.add(indicatorMatrix.get(matrixIndex)[k][j].get(s));
								}
							}
							for (int m = 0; m < indicatorMatrix.get(matrixIndex)[i][k].size(); m++) {
								for (int l = 0; l < indicatorMatrix.get(matrixIndex + 1)[k][j].size(); l++) {
									Time time = new Time();
									if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex+1)[k][j].isEmpty() ) {
										int timeInterval1 = time.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(m).getArrivingTime(),
												indicatorMatrix.get(matrixIndex+1)[k][j].get(l).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(m).getArrivingDate(),
												indicatorMatrix.get(matrixIndex+1)[k][j].get(l).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(m));
											secondFlight.add(indicatorMatrix.get(matrixIndex+1)[k][j].get(l));
									}
									}
								}
							}
						}
					}
				} 
			} else {
				//It is same as the previous part, but it is the reversed direction, from a eastern airport to western airport
				for (int k = j; k < i; k++){
					if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex)[k][j].isEmpty() ){
						for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
							for (int s = 0; s < indicatorMatrix.get(matrixIndex)[k][j].size(); s++) {
								Time timer = new Time();
									int timeInterval = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
											indicatorMatrix.get(matrixIndex)[k][j].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
											indicatorMatrix.get(matrixIndex)[k][j].get(s).getDepartingDate());
									if ( timeInterval > 30 && timeInterval < 200 ) {								
										firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
										secondFlight.add(indicatorMatrix.get(matrixIndex)[k][j].get(s));
									}
							}
							for (int m = 0; m < indicatorMatrix.get(matrixIndex)[i][k].size(); m++) {
								for (int l = 0; l < indicatorMatrix.get(matrixIndex + 1)[k][j].size(); l++) {
									Time time = new Time();
									if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex+1)[k][j].isEmpty() ) {
										int timeInterval1 = time.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(m).getArrivingTime(),
												indicatorMatrix.get(matrixIndex+1)[k][j].get(l).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(m).getArrivingDate(),
												indicatorMatrix.get(matrixIndex+1)[k][j].get(l).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(m));
											secondFlight.add(indicatorMatrix.get(matrixIndex+1)[k][j].get(l));
									}
									}
								}
							}
						}
					}
				} 
			}
				
		}
		
		f.add(0, firstFlight);
		f.add(1, secondFlight);
		return f;
	}

	/**
	 * @function oneStopTimeFlight
	 * This is only for display issue.
	 * It will just combined two filghtNO and added the total price together.
	 * Thus in the general list in the main interface, 
	 * it will show as one flight with original start and final destination, 
	 * with flight number concatenated together and total price.
	 * @param transferFlight
	 * @return
	 */
	public List<flight> oneStopTimeFlight(List<List<flight>> transferFlight){
		List<flight> fList = new ArrayList<flight>();
		for (int i = 0; i < transferFlight.get(0).size(); i++){
			flight f = new flight();
			Time timer  = new Time();
			f.combinedFlight(1, i, transferFlight);
			f.addTicketPrice(transferFlight.get(0).get(i).getTicketPrice());
			f.addTicketPrice(transferFlight.get(1).get(i).getTicketPrice());
			f.setFlightTime(timer.timeInterval(f.getDepartingTime(), f.getArrivingTime(), f.getDepartingDate(), f.getArrivingDate()));
			fList.add(f);
		}
		return fList;
		
	}

	/**
	 * @function twoStopTimeTransferFlight
	 * It is the same principle as function "oneStopTimeTransferFlight"
	 * Filter out all the possible first, second and third flight compositions for one-direction(not round trip)
	 * And store these flight compositions into three arraylist seperately while keeping the order.
	 * @param departingAirport
	 * @param arrivingAirport
	 * @param departingDate
	 * @param startDate
	 * @param indicatorMatrix
	 * @param info
	 * @return
	 */
	public List<List<flight>> twoStopTimeTransferFlight(String departingAirport, String arrivingAirport, String departingDate, String startDate, List<List<flight>[][]> indicatorMatrix, airportInformation info){
		List<List<flight>> f = new ArrayList<List<flight>>();
		List<flight> firstFlight = new ArrayList<flight>();
		List<flight> secondFlight = new ArrayList<flight>();
		List<flight> thirdFlight = new ArrayList<flight>();

		int i = info.getInformationByAirportName(departingAirport);
		int j = info.getInformationByAirportName(arrivingAirport);
		int matrixIndex = Integer.parseInt("" + departingDate.charAt(departingDate.length()-2) + departingDate.charAt(departingDate.length()-1)) - 
		Integer.parseInt("" + startDate.charAt(startDate.length()-2) + startDate.charAt(startDate.length()-1));
		if (matrixIndex < 9) {
			if (j - i > 2){
				
				for (int k = i + 1; k < j - 1; k++){
					for (int m = k + 1; m < j; m++) {
						if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex)[k][m].isEmpty() && !indicatorMatrix.get(matrixIndex)[m][j].isEmpty()){
							for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
								for (int s = 0; s < indicatorMatrix.get(matrixIndex)[k][m].size(); s++) {
									for (int n = 0; n < indicatorMatrix.get(matrixIndex)[m][j].size(); n++) {
										Time timer = new Time();
										int timeInterval1 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingDate());
										int timeInterval2 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingTime(),
												indicatorMatrix.get(matrixIndex)[m][j].get(n).getDepartingTime(), indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingDate(),
												indicatorMatrix.get(matrixIndex)[m][j].get(n).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200 && timeInterval2 > 30 && timeInterval2 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
											secondFlight.add(indicatorMatrix.get(matrixIndex)[k][m].get(s));
											thirdFlight.add(indicatorMatrix.get(matrixIndex)[m][j].get(n));
										}
									}
								}
							}
						}
						
						if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex + 1)[k][m].isEmpty() && !indicatorMatrix.get(matrixIndex + 1)[m][j].isEmpty()){
							for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
								for (int s = 0; s < indicatorMatrix.get(matrixIndex + 1)[k][m].size(); s++) {
									for (int n = 0; n < indicatorMatrix.get(matrixIndex + 1)[m][j].size(); n++) {
										Time timer = new Time();
										int timeInterval1 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
												indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
												indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getDepartingDate());
										int timeInterval2 = timer.timeInterval(indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getArrivingTime(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingTime(), indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getArrivingDate(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200 && timeInterval2 > 30 && timeInterval2 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
											secondFlight.add(indicatorMatrix.get(matrixIndex + 1)[k][m].get(s));
											thirdFlight.add(indicatorMatrix.get(matrixIndex + 1)[m][j].get(n));
										}
									}
								}
							}
						}
						
						if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex)[k][m].isEmpty() && !indicatorMatrix.get(matrixIndex + 1)[m][j].isEmpty()){
							for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
								for (int s = 0; s < indicatorMatrix.get(matrixIndex)[k][m].size(); s++) {
									for (int n = 0; n < indicatorMatrix.get(matrixIndex + 1)[m][j].size(); n++) {
										Time timer = new Time();
										int timeInterval1 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingDate());
										int timeInterval2 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingTime(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingTime(), indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingDate(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200 && timeInterval2 > 30 && timeInterval2 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
											secondFlight.add(indicatorMatrix.get(matrixIndex)[k][m].get(s));
											thirdFlight.add(indicatorMatrix.get(matrixIndex + 1)[m][j].get(n));
										}
									}
								}
							}
						}
						
						
					}
				} 
				f.add(firstFlight);
				f.add(secondFlight);
				f.add(thirdFlight);
			} else if (i - j > 2){
				
				for (int k = j + 1; k < i - 1; k++){
					for (int m = k + 1; m < i; m++) {
						if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex)[k][m].isEmpty() && !indicatorMatrix.get(matrixIndex)[m][j].isEmpty()){
							for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
								for (int s = 0; s < indicatorMatrix.get(matrixIndex)[k][m].size(); s++) {
									for (int n = 0; n < indicatorMatrix.get(matrixIndex)[m][j].size(); n++) {
										Time timer = new Time();
										int timeInterval1 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingDate());
										int timeInterval2 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingTime(),
												indicatorMatrix.get(matrixIndex)[m][j].get(n).getDepartingTime(), indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingDate(),
												indicatorMatrix.get(matrixIndex)[m][j].get(n).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200 && timeInterval2 > 30 && timeInterval2 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
											secondFlight.add(indicatorMatrix.get(matrixIndex)[k][m].get(s));
											thirdFlight.add(indicatorMatrix.get(matrixIndex)[m][j].get(n));
										}
									}
								}
							}
						}
						
						if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex + 1)[k][m].isEmpty() && !indicatorMatrix.get(matrixIndex + 1)[m][j].isEmpty()){
							for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
								for (int s = 0; s < indicatorMatrix.get(matrixIndex + 1)[k][m].size(); s++) {
									for (int n = 0; n < indicatorMatrix.get(matrixIndex + 1)[m][j].size(); n++) {
										Time timer = new Time();
										int timeInterval1 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
												indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
												indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getDepartingDate());
										int timeInterval2 = timer.timeInterval(indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getArrivingTime(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingTime(), indicatorMatrix.get(matrixIndex + 1)[k][m].get(s).getArrivingDate(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200 && timeInterval2 > 30 && timeInterval2 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
											secondFlight.add(indicatorMatrix.get(matrixIndex + 1)[k][m].get(s));
											thirdFlight.add(indicatorMatrix.get(matrixIndex + 1)[m][j].get(n));
										}
									}
								}
							}
						}
						
						if ( !indicatorMatrix.get(matrixIndex)[i][k].isEmpty() &&  !indicatorMatrix.get(matrixIndex)[k][m].isEmpty() && !indicatorMatrix.get(matrixIndex + 1)[m][j].isEmpty()){
							for (int t = 0; t < indicatorMatrix.get(matrixIndex)[i][k].size(); t++) {
								for (int s = 0; s < indicatorMatrix.get(matrixIndex)[k][m].size(); s++) {
									for (int n = 0; n < indicatorMatrix.get(matrixIndex + 1)[m][j].size(); n++) {
										Time timer = new Time();
										int timeInterval1 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingTime(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingTime(), indicatorMatrix.get(matrixIndex)[i][k].get(t).getArrivingDate(),
												indicatorMatrix.get(matrixIndex)[k][m].get(s).getDepartingDate());
										int timeInterval2 = timer.timeInterval(indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingTime(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingTime(), indicatorMatrix.get(matrixIndex)[k][m].get(s).getArrivingDate(),
												indicatorMatrix.get(matrixIndex + 1)[m][j].get(n).getDepartingDate());
										if ( timeInterval1 > 30 && timeInterval1 < 200 && timeInterval2 > 30 && timeInterval2 < 200) {
											firstFlight.add(indicatorMatrix.get(matrixIndex)[i][k].get(t));
											secondFlight.add(indicatorMatrix.get(matrixIndex)[k][m].get(s));
											thirdFlight.add(indicatorMatrix.get(matrixIndex + 1)[m][j].get(n));
										}
									}
								}
							}
						}
					}
				} 
				
				f.add(firstFlight);
				f.add(secondFlight);
				f.add(thirdFlight);
			} 		
		} else {
			f = null;
		}		
		return f;

		
	}

	/**
	 * @function twoStopTimeFlight
	 * This the same as function oneStopTimeFlight
	 * This is only for display issue. 
	 * It will just combined three filghtNO and added the total price together.
	 * Thus in the general list in the main interface, 
	 * it will show as one flight with original start and final destination, 
	 * with flight number concatenated together and total price.
	 * @param transferFlight
	 * @return
	 */
	public List<flight> twoStopTimeFlight(List<List<flight>> transferFlight){
		List<flight> fList = new ArrayList<flight>();
		System.out.println(transferFlight.isEmpty());
		if (!transferFlight.isEmpty()) {
			for (int i = 0; i < transferFlight.get(0).size(); i++){
				flight f = new flight();
				Time timer = new Time();
				f.combinedFlight(2,i,transferFlight);
				f.addTicketPrice(transferFlight.get(0).get(i).getTicketPrice());
				f.addTicketPrice(transferFlight.get(1).get(i).getTicketPrice());
				f.addTicketPrice(transferFlight.get(2).get(i).getTicketPrice());
				f.setFlightTime(timer.timeInterval(f.getDepartingTime(), f.getArrivingTime(), f.getDepartingDate(), f.getArrivingDate()));
				fList.add(f);
			}
		}
		return fList;
		
	}

	/**
	 * @function oneStopTimeTransferFlightForRoundTrip
	 * Filter out all the possible first flight and second flight compositions for round trip
	 * Just call the function "oneStopTimeTransferFlightForRoundTrip" for two directions
	 * @param departingAirport
	 * @param arrivingAirport
	 * @param departingDate
	 * @param arrivingDate
	 * @param startDate
	 * @param indicatorMatrix
	 * @param info
	 * @return
	 */
	public List<List<List<flight>>> oneStopTimeTransferFlightForRoundTrip(String departingAirport, String arrivingAirport, String departingDate,String arrivingDate, String startDate, List<List<flight>[][]> indicatorMatrix, airportInformation info) {
		List<List<List<flight>>> roundtripTransferFlight = new ArrayList<List<List<flight>>>();
		List<List<flight>> departFlightCombination = oneStopTimeTransferFlightForRoundTrip(departingAirport, arrivingAirport, departingDate, startDate, indicatorMatrix, info);
		List<List<flight>> returnFlightCombination = oneStopTimeTransferFlight(arrivingAirport, departingAirport,  arrivingDate, startDate, indicatorMatrix, info);
		roundtripTransferFlight.add(departFlightCombination);
		roundtripTransferFlight.add(returnFlightCombination);
		
		return roundtripTransferFlight;
	}

	/**
	 * @function oneStopTimeFlightForRoundTrip
	 * This the same as function oneStopTimeFlight
	 * This is only for display issue for round trip
	 * It will just combined two filghtNO and added the total price together.
	 * Thus in the general list in the main interface, 
	 * it will show as one flight with original start and final destination, 
	 * with flight number concatenated together and total price.
	 * @param roundtripTransferFlight
	 * @return
	 */
	public List<List<flight>> oneStopTimeFlightForRoundTrip(List<List<List<flight>>> roundtripTransferFlight) {
		List<List<flight>> roundtripFlight = new ArrayList<List<flight>>();
		roundtripFlight.add(oneStopTimeFlight(roundtripTransferFlight.get(0)));
		roundtripFlight.add(oneStopTimeFlight(roundtripTransferFlight.get(1)));
		
		return roundtripFlight;
	}

	/**
	 * @function twoStopTimeTransferFlightForRoundTrip
	 * Filter out all the possible first, second and third flight compositions for round trip
	 * Just call the function "twoStopTimeTransferFlightForRoundTrip" for two directions
	 * @param departingAirport
	 * @param arrivingAirport
	 * @param departingDate
	 * @param arrivingDate
	 * @param startDate
	 * @param indicatorMatrix
	 * @param info
	 * @return
	 */
	public List<List<List<flight>>> twoStopTimeTransferFlightForRoundTrip(String departingAirport, String arrivingAirport, String departingDate, String arrivingDate, String startDate, List<List<flight>[][]> indicatorMatrix, airportInformation info) {
		List<List<List<flight>>> roundtripTransferFlight = new ArrayList<List<List<flight>>>();
		List<List<flight>> departTransferFlight = twoStopTimeTransferFlight(departingAirport, arrivingAirport, departingDate, startDate, indicatorMatrix, info);
		List<List<flight>> returnTransferFlight = twoStopTimeTransferFlight(arrivingAirport, departingAirport, arrivingDate, startDate, indicatorMatrix, info);
		roundtripTransferFlight.add(departTransferFlight);
		roundtripTransferFlight.add(returnTransferFlight);
		
		return roundtripTransferFlight;
	}

	/**
	 * @function twoStopTimeFlightForRoundTrip
	 * This the same as function twoStopTimeFlight
	 * This is only for display issue for round trip
	 * It will just combined three filghtNO and added the total price together.
	 * Thus in the general list in the main interface, 
	 * it will show as one flight with original start and final destination, 
	 * with flight number concatenated together and total price.
	 * @param roundtripTransferFlight
	 * @return
	 */
	public List<List<flight>> twoStopTimeFlightForRoundTrip(List<List<List<flight>>> roundtripTransferFlight) {
		List<List<flight>> roundtripFlight = new ArrayList<List<flight>>();
		roundtripFlight.add(twoStopTimeFlight(roundtripTransferFlight.get(0)));
		roundtripFlight.add(twoStopTimeFlight(roundtripTransferFlight.get(1)));
		
		return roundtripFlight;
	}
}
