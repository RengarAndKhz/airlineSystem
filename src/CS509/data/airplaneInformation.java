package CS509.data;

import java.util.Hashtable;
import java.util.List;

import CS509.utility.XMLFileParser;

public class airplaneInformation {
	
    String manufacturer;
    String model;
    int firstClassSeat;
    int coachSeat;
	Hashtable<String, airplaneInformation> map = new Hashtable<String, airplaneInformation>();

	/**
	 *
	 * @return
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 *
	 * @return
	 */
	public String getModel() {
		return model;
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
	public int getCoachSeat() {
		return coachSeat;
	}

	/**
	 *
	 * @return
	 */
	public Hashtable<String, airplaneInformation> getMap() {
		return map;
	}

	/**
	 *
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 *
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
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
	 * @param coachSeat
	 */
	public void setCoachSeat(int coachSeat) {
		this.coachSeat = coachSeat;
	}

	/**
	 *
	 * @param map
	 */
	public void setMap(Hashtable<String, airplaneInformation> map) {
		this.map = map;
	}

	/**
	 *
	 * @param ticketAgency
	 * @throws Exception
	 */
	
    public void airplaneMapToModel(String ticketAgency) throws Exception {
    	XMLFileParser readFile = new XMLFileParser();
    	List<airplaneInformation> airplane = readFile.transformAirplaneInformation(ticketAgency);
        for (airplaneInformation ai : airplane) {
    		map.put(ai.model, ai);
    	} 	
    }

	/**
	 *
	 * @param airplaneModel
	 * @return
	 */
    public airplaneInformation getAirplaneFromModel(String airplaneModel) {
        return map.get(airplaneModel);  	
    }


}
