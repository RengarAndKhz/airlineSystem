package CS509.utility;

import CS509.data.flight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FlightFileConvertor {
	int numAiport=52;

	/**
	 * @function writeToFile
	 *  Write the arraylist of the flight matrix into file as local database.
     *  When the next time application boot up, it will check the file exists or not.
     *  If the file exist, just call function "loadFromFile".
	 *  (There are two kinds of matrix list, one is "firstclass" matrix list and one is "coach" matrix list.
 	 *  They are only different from price. This big redundency could be future improvement)
	 * @param flightMatrix
	 * @param matrixName
	 * @return
	 */
	public boolean writeToFile(List<List<flight>[][]> flightMatrix, String matrixName ){
		
		try {
			//start to write in to the file "matrix.db"
			for(int m = 0; m < flightMatrix.size(); m++){
				File filename = new File(matrixName+"_matrix_"+m+".db");
				filename.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(filename));
				for(int i = 0; i < flightMatrix.get(m).length; i ++ ){
					for(int j = 0; j < flightMatrix.get(m)[i].length; j ++ ){
						//print every flight in this flight arraylist
						//The format of each line will be:
						//"airport_x,airport_y|flightNumber,departingAirport, ...,ticketPrice|flightNumber, ..., ticketPrice"
						//                     (This is the first flight)                    (This is the second flight)
						if(!flightMatrix.get(m)[i][j].isEmpty()){
							out.write(i+","+j);
							for ( flight f : flightMatrix.get(m)[i][j] ){
								out.write("|" +f.getFlightNumber()+","
							            +f.getDepartingAirport()+","
							            +f.getArrivingAirport()+","
							            +f.getDepartingDate()+","
							            +f.getArrivingDate()+","
							            +f.getDepartingTime()+","
							            +f.getArrivingTime()+","
							            +f.getClassType()+","
							            +f.getFlightTime()+","
							            +f.getSeat()+","
							            +f.getTicketPrice()    );
							}
							out.write("\r\n");
						}
						
					}
				}
				out.flush();  
		        out.close();
			} 
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * @function loadFromFile
	 * Load the flight matrix from reading the files
	 * (There are two kinds of matrix list, one is "firstclass" matrix list and one is "coach" matrix list.
 	 * They are only different from price. This big redundency could be future improvement)
	 * @param matrixIndex
	 * @param matrixName
	 * @return
	 */
	public List<flight>[][] loadFromFile( int matrixIndex, String matrixName){
		List<flight>[][] resultMatrix = new ArrayList[numAiport][numAiport];
        for(int i =0; i < resultMatrix.length; i++ ){
			for(int j =0; j < resultMatrix[i].length; j++ ){
				//Initialize every arraylist
				resultMatrix[i][j] = new ArrayList<flight>();
			}
		}
        
        String fileName= matrixName+"_matrix_" + matrixIndex + ".db";
        String line="";
        String[] tempFlightStr = new String[100];
        try
        {
	        BufferedReader in=new BufferedReader(new FileReader(fileName));
	        line= in.readLine();
	        while (line != null)
	        {
	        	//System.out.println(line);
	        	//The format of each line will be:
				//"airport_x,airport_y|flightNumber,departingAirport, ...,ticketPrice|flightNumber, ..., ticketPrice"
				//                     (This is the first flight)                     (This is the second flight)
	           	
	        	//We should split the string by "|"
	        	tempFlightStr = line.split("\\|");
	        	
	        	//The first element of the string array is airport axis
	        	String[] airportAxis = new String[2];
	        	airportAxis = tempFlightStr[0].split(",");
	        	int x = Integer.parseInt(airportAxis[0]);
	        	int y = Integer.parseInt(airportAxis[1]);
	        	
	        	//The second element and the element after it will all be flights.
	        	for(int i=1; i<tempFlightStr.length; i++){
		        	String[] flightInformation = new String[11];
		        	flightInformation = tempFlightStr[i].split(",");
		        	flight tmpF = new flight();
		        	tmpF.setFlightNumber(flightInformation[0]);
		        	tmpF.setDepartingAirport(flightInformation[1]);
		        	tmpF.setArrivingAirport(flightInformation[2]);
		        	tmpF.setDepartingDate(flightInformation[3]);
		        	tmpF.setArrivingDate(flightInformation[4]);
		        	tmpF.setDepartingTime(flightInformation[5]);
		        	tmpF.setArrivingTime(flightInformation[6]);
		        	tmpF.setClassType(flightInformation[7]);
		        	tmpF.setFlightTime(Integer.parseInt(flightInformation[8]));
		        	tmpF.setSeat(Integer.parseInt(flightInformation[9]));
		        	tmpF.setTicketPrice(Double.parseDouble(flightInformation[10]));
                          //Add the flight to that unit of the flight matrix.
		        	resultMatrix[x][y].add(tmpF);
	        	}
	        			
	        	line=in.readLine();
	        }
        	in.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

		return resultMatrix;
	}

}
