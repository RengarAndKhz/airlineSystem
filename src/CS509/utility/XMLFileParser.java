package CS509.utility;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import CS509.DAO.ReservationSystem;
import CS509.data.airplaneInformation;
import CS509.data.airportInformation;
import CS509.data.allFlightInformation;


public class XMLFileParser {
	/**
	 * convert the xml file into string
	 * @param ticketAgency
	 * @return data of airport information in string
	 * @throws Exception
	 */
	public List<airportInformation> transformAirportInformation(String ticketAgency) throws Exception{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		ReservationSystem rs = new ReservationSystem();
		String XmlInfo = rs.getAirports(ticketAgency);
		InputSource MainInfo = new InputSource();
		MainInfo.setCharacterStream(new StringReader(XmlInfo));
		Document document = db.parse(MainInfo);
		NodeList list = document.getElementsByTagName("Airport");
		//System.out.print("list length:"+list.getLength());
		List<airportInformation> Rengar = new ArrayList<airportInformation>();
		airportInformation Sonna;
		
		
		for (int i = 0; i < list.getLength(); ++i){
			Sonna = new airportInformation();
			
			Element element=(Element) list.item(i);
		    
			Sonna.Code = element.getAttribute("Code");
			
			Sonna.Latitude =  Double.valueOf(element.getElementsByTagName("Latitude").item(0).getFirstChild().getNodeValue()).doubleValue();
			
			Sonna.Longitude = Double.valueOf(element.getElementsByTagName("Longitude").item(0).getFirstChild().getNodeValue()).doubleValue();
		
	       Rengar.add(Sonna);
		}
		return Rengar;
	}

	/**
	 * convert the xml file into string
	 * @param ticketAgency
	 * @return data of airplane information in string
	 * @throws Exception
	 */
    public List<airplaneInformation> transformAirplaneInformation(String ticketAgency) throws Exception{
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		ReservationSystem rs = new ReservationSystem();
		String XmlInfo = rs.getAirplane(ticketAgency);
		InputSource MainInfo = new InputSource();
		MainInfo.setCharacterStream(new StringReader(XmlInfo));
        Document D = db.parse(MainInfo);
        NodeList list = D.getElementsByTagName("Airplane");
        List<airplaneInformation> AirplaneFeature = new ArrayList();
        airplaneInformation Info;
        for(int i=0; i<list.getLength();++i){
            Info = new airplaneInformation();
            Element element = (Element) list.item(i);
            Info.setManufacturer(element.getAttribute("Manufacturer"));
            Info.setModel(element.getAttribute("Model"));
            Info.setCoachSeat(Integer.valueOf(element.getElementsByTagName("CoachSeats").item(0).getFirstChild().getNodeValue()));
            Info.setFirstClassSeat(Integer.valueOf(element.getElementsByTagName("FirstClassSeats").item(0).getFirstChild().getNodeValue()));  
            AirplaneFeature.add(Info);
        }

        return AirplaneFeature;
    }

	/**
	 * convert the xml into stirng
	 * @param ticketAgency
	 * @param DepatureDate
	 * @param DepatureAirport
	 * @return the data of flight information in string
	 * @throws Exception
	 */
	public List<allFlightInformation> transformFlightInfo(String ticketAgency,
			String DepatureDate, String DepatureAirport) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		ReservationSystem rs = new ReservationSystem();
		String XmlInfo = rs.getFlights(ticketAgency, DepatureAirport,
				DepatureDate);
		InputSource MainInfo = new InputSource();
		MainInfo.setCharacterStream(new StringReader(XmlInfo));
		Document document = db.parse(MainInfo);
		NodeList list = document.getElementsByTagName("Flight");
		NodeList lst = document.getElementsByTagName("FirstClass");
		NodeList lst2 = document.getElementsByTagName("Coach");
		List<allFlightInformation> outputInfo = new ArrayList<allFlightInformation>();
		allFlightInformation InfoGet;

		for (int i = 0; i < list.getLength(); ++i) {
			Element element = (Element) list.item(i);
			Element ele = (Element) lst.item(i);
			Element ele2 = (Element) lst2.item(i);

			InfoGet = new allFlightInformation();
			String ap = element.getAttribute("Airplane");
			InfoGet.setAirplaneCode(ap);

			int ft = Integer.parseInt(element.getAttribute("FlightTime"));
			InfoGet.setFlightTime(ft);

			String num = element.getAttribute("Number");
			InfoGet.setNumber(num);

			String code = element.getElementsByTagName("Code").item(0)
					.getFirstChild().getNodeValue();
			InfoGet.setDepartingAirport(code);

			String depDate = element.getElementsByTagName("Time").item(0)
					.getFirstChild().getNodeValue();
			String[] SpString = depDate.split(" ");
			InfoGet.setDepartingDate(SpString[0] + "_05_" + SpString[2]); 

			String depTime = element.getElementsByTagName("Time").item(0)
					.getFirstChild().getNodeValue();
			SpString = depTime.split(" ");
			InfoGet.setDepartingTime(SpString[3] + " " + SpString[4]);

			String arrAirport = element.getElementsByTagName("Code").item(1)
					.getFirstChild().getNodeValue();
			InfoGet.setArrivingAirport(arrAirport);

			String arrDate = element.getElementsByTagName("Time").item(1)
					.getFirstChild().getNodeValue();
			SpString = arrDate.split(" ");
			InfoGet.setArrivingDate(SpString[0] + "_05_" + SpString[2]);

			String arrTime = element.getElementsByTagName("Time").item(1)
					.getFirstChild().getNodeValue();
			SpString = arrTime.split(" ");
			InfoGet.setArrivingTime(SpString[3] + " " + SpString[4]);

			String price1 = ele.getAttribute("Price").replace("$", "");
			String modPrice1;
			double firstClassPrice;
			if (price1.indexOf(",") >= 0) {
				modPrice1 = price1.replace(",", "");
				firstClassPrice = Double.valueOf(modPrice1).doubleValue();
				InfoGet.setFirstClassPrice(firstClassPrice);
			} else {
				firstClassPrice = Double.valueOf(price1).doubleValue();
				InfoGet.setFirstClassPrice(firstClassPrice);
			}

			int firstClassSeat = Integer.parseInt(element
					.getElementsByTagName("FirstClass").item(0).getFirstChild()
					.getNodeValue());
			InfoGet.setFirstClassSeat(firstClassSeat);

			String price2 = ele2.getAttribute("Price").replace("$", "");
			String modPrice2;
			double coachPrice;
			if (price2.indexOf(",") >= 0) {
				modPrice2 = price2.replace(",", "");
				coachPrice = Double.valueOf(modPrice2).doubleValue();
				InfoGet.setCoachPrice(coachPrice);
			} else {
				coachPrice = Double.valueOf(price2).doubleValue();
				InfoGet.setCoachPrice(coachPrice);
			}

			int coachSeat = Integer.parseInt(element
					.getElementsByTagName("Coach").item(0).getFirstChild()
					.getNodeValue());
			InfoGet.setCoachSeat(coachSeat);

			outputInfo.add(InfoGet);
		}		

		return outputInfo;

	}

	/**
	 *
	 * @param ticketAgency
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<allFlightInformation> flightInOneDay(String ticketAgency, String date) throws Exception{
		XMLFileParser rf = new XMLFileParser();
		List<airportInformation> a = rf.transformAirportInformation("TeamTricepts");
		List<allFlightInformation> infor = new ArrayList<allFlightInformation>();
		for (airportInformation i : a){
			infor.addAll(rf.transformFlightInfo(ticketAgency, date, i.Code));
		}
		return infor;
	}
}
