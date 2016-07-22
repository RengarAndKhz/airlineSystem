package CS509.data;

import java.util.ArrayList;
import java.util.List;


public class allFlightInformation {

	String airplaneCode;
	int flightTime;
	String number;
	String departingAirport;
	String departingDate;
	String departingTime;
	String arrivingAirport;
	String arrivingDate;
	String arrivingTime;
	double firstClassPrice;
	int firstClassSeat;
	double coachPrice;
	int coachSeat;

	/**
	 *
	 * @return
	 */
	public String getAirplaneCode() {
		return airplaneCode;
	}

	/**
	 *
	 * @return
	 */
	public int getFlightTime() {
		return flightTime;
	}

	/**
	 *
	 * @return
	 */
	public String getNumber() {
		return number;
	}

	/**
	 *
	 * @return
	 */
	public String getDepartingAirport() {
		return departingAirport;
	}

	/**
	 *
	 * @return
	 */
	public String getDepartingDate() {
		return departingDate;
	}

	/**
	 *
	 * @return
	 */
	public String getDepartingTime() {
		return departingTime;
	}

	/**
	 *
	 * @return
	 */
	public String getArrivingAirport() {
		return arrivingAirport;
	}

	/**
	 *
	 * @return
	 */
	public String getArrivingDate() {
		return arrivingDate;
	}

	/**
	 *
	 * @return
	 */
	public String getArrivingTime() {
		return arrivingTime;
	}

	/**
	 *
	 * @return
	 */
	public double getFirstClassPrice() {
		return firstClassPrice;
	}

	/**
	 *
	 * @return
	 */
	public int getFirstClassSeat() {
		return firstClassSeat;
	}

	/**
	 *
	 * @return
	 */
	public double getCoachPrice() {
		return coachPrice;
	}

	/**
	 *
	 * @return
	 */
	public int getCoachSeat() {
		return coachSeat;
	}

	/**
	 *
	 * @param classType
	 * @return
	 */

	public double getPrice(String classType) {
		if (classType.equals("FirstClass")) {
			return firstClassPrice;
		} else
			return coachPrice;
	}

	/**
	 *
	 * @param classType
	 * @param ap
	 * @return
	 * @throws Exception
	 */
	public int getSeat(String classType, airplaneInformation ap) throws Exception {
		airplaneInformation ainfo = ap.getAirplaneFromModel(airplaneCode);
		if (classType.equals("FirstClass")) {
			return ainfo.firstClassSeat - firstClassSeat;
		} else
			return ainfo.coachSeat - coachSeat;
	}

	/**
	 *
	 * @param airplaneCode
	 */
	public void setAirplaneCode(String airplaneCode) {
		this.airplaneCode = airplaneCode;
	}

	/**
	 *
	 * @param flightTime
	 */
	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	/**
	 *
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 *
	 * @param departingAirport
	 */
	public void setDepartingAirport(String departingAirport) {
		this.departingAirport = departingAirport;
	}

	/**
	 *
	 * @param departingDate
	 */
	public void setDepartingDate(String departingDate) {
		this.departingDate = departingDate;
	}

	/**
	 *
	 * @param departingTime
	 */
	public void setDepartingTime(String departingTime) {
		this.departingTime = departingTime;
	}

	/**
	 *
	 * @param arrivingAirport
	 */
	public void setArrivingAirport(String arrivingAirport) {
		this.arrivingAirport = arrivingAirport;
	}

	/**
	 *
	 * @param arrivingDate
	 */
	public void setArrivingDate(String arrivingDate) {
		this.arrivingDate = arrivingDate;
	}

	/**
	 *
	 * @param arrivingTime
	 */
	public void setArrivingTime(String arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	/**
	 *
	 * @param firstClassPrice
	 */
	public void setFirstClassPrice(double firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	/**
	 *
	 * @param firstClassSeat
	 */
	public void setFirstClassSeat(int firstClassSeat) {
		this.firstClassSeat = firstClassSeat;
	}

	/**
	 *
	 * @param coachPrice
	 */
	public void setCoachPrice(double coachPrice) {
		this.coachPrice = coachPrice;
	}

	/**
	 *
	 * @param coachSeat
	 */
	public void setCoachSeat(int coachSeat) {
		this.coachSeat = coachSeat;
	}
	


}
