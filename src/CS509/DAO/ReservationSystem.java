package CS509.DAO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import CS509.DAO.QueryFactory;

public class ReservationSystem {
	private final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

	/**
	 * get the string data of the airport information
	 * @param ticketAgency
	 * @return airport information in string
	 */
	public String getAirports(String ticketAgency){
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try{
			url = new URL(mUrlBase + QueryFactory.getAirports(ticketAgency));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", ticketAgency);
			
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "URF-8" : encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * get the string data of the flight information
	 * @param ticketAgency
	 * @param departingAirport
	 * @param departingDate
	 * @return flight information in string
	 */
	public String getFlights(String ticketAgency, String departingAirport, String departingDate){
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try{
			url = new URL(mUrlBase + QueryFactory.getDepartingFlights(ticketAgency, departingAirport, departingDate));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", ticketAgency);
			
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "URF-8" : encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * get the string data of airplane information
	 * @param ticketAgency
	 * @return airplane information in string
	 */
	public String getAirplane(String ticketAgency){
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try{
			url = new URL(mUrlBase + QueryFactory.getAirplanes(ticketAgency));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", ticketAgency);
			
			int responseCode = connection.getResponseCode();
			if ((responseCode >= 200) && (responseCode <= 299)) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "URF-8" : encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * lock database
	 * @param ticketAgency
	 * @return response code
	 */
	public boolean lock (String ticketAgency) {
	    URL url;
	    HttpURLConnection connection;


	    try {
	        url = new URL(mUrlBase);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("User-Agent", ticketAgency);
	        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	        String params = QueryFactory.lock(ticketAgency);

	        connection.setDoOutput(true);
	        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
	        writer.writeBytes(params);
	        writer.flush();
	        writer.close();

	        int responseCode = connection.getResponseCode();
	        System.out.println("\nSending 'POST' to lock database");
	        System.out.println(("\nResponse Code : " + responseCode));

	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        StringBuffer response = new StringBuffer();

	        while ((line = in.readLine()) != null) {
	            response.append(line);
	        }
	        in.close();

	        System.out.println(response.toString());
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	        return false;
	    }
	    return true;
	}

	/**
	 * update the data in server
	 * @param ticketAgency
	 * @param xmlString
	 * @return response code
	 */
	public boolean updateServer(String ticketAgency,String xmlString) { //name
	    URL url;
	    HttpURLConnection connection;


	    try {
	        url = new URL(mUrlBase);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("User-Agent", ticketAgency);
	        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	        String params = QueryFactory.updateServerURL(ticketAgency,xmlString);

	        connection.setDoOutput(true);
	        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
	        writer.writeBytes(params);
	        writer.flush();
	        writer.close();

	        int responseCode = connection.getResponseCode();
	        System.out.println("\nSending 'POST' to update database");
	        System.out.println(("\nResponse Code : " + responseCode));

	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        StringBuffer response = new StringBuffer();

	        while ((line = in.readLine()) != null) {
	            response.append(line);
	        }
	        in.close();

	        System.out.println(response.toString());
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	        return false;
	    }
	    return true;
	}

	/**
	 * unlock the database
	 * @param ticketAgency
	 * @return response code
	 */
	public boolean unlock(String ticketAgency){
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", ticketAgency);
		    connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		    
			String params = QueryFactory.unlock(ticketAgency);
			
			connection.setDoOutput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to unlock database");
			System.out.println("\nResponse Code:" + responseCode);
			
			
				
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
				
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
				
			System.out.println(response.toString());			
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	
		return true;
	}

	/**
	 * check the database status
	 * @param ticketAgency
	 * @return response code
	 */
	public boolean islockable (String ticketAgency) {
	    URL url;
	    HttpURLConnection connection;


	    try {
	        url = new URL(mUrlBase);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("User-Agent", ticketAgency);
	        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	        String params = QueryFactory.unlock(ticketAgency);

	        connection.setDoOutput(true);
	        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
	        writer.writeBytes(params);
	        writer.flush();
	        writer.close();

	        int responseCode = connection.getResponseCode();

	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        StringBuffer response = new StringBuffer();

	        while ((line = in.readLine()) != null) {
	            response.append(line);
	        }
	        in.close();

	        System.out.println(response.toString());
	        
	        if(responseCode != 202){
	        	return false;
	        }

	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	        return false;
	    }

	    return true;
	}
	

}