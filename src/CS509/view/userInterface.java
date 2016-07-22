package CS509.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.DateTime;
//import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.dialogs.MessageDialog;

import java.util.Timer;
import java.util.TimerTask;









//import CS509.DAO.ticketReservation;
import CS509.data.airplaneInformation;
import CS509.data.airportInformation;
import CS509.data.allFlightInformation;
import CS509.data.flight;
import CS509.utility.FlightFileConvertor;
import CS509.utility.Time;
import CS509.utility.XMLFileParser;
import CS509.utility.filterFlights;
import CS509.utility.flightAvailableMatrix;
import CS509.utility.flightTableItem;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;




public class userInterface {

	protected Shell shell;
	private Table table;
	
	List<flight> myFlight;
	List<List<flight>> transFlight1;
	List<List<flight>> transFlight2;

	List<List<flight>> myFlight1;
	List<List<flight>> myFlight2;
	List<List<flight>> myFlight3;
	List<List<List<flight>>> transFlight21;
	List<List<List<flight>>> transFlight22;
	List<List<flight>[][]> firstClassMatrix = null;
	List<List<flight>[][]> coachMatrix = null;

	
	List<flight> chosenFlight = new ArrayList<flight>();
	flightAvailableMatrix matrix = new flightAvailableMatrix();
	final airportInformation ai = new airportInformation();
	final airplaneInformation ap = new airplaneInformation();
	XMLFileParser readFile = new XMLFileParser();
	FlightFileConvertor ffc = new FlightFileConvertor();
	String startDate = "2015_05_08";
	String endDate = "2015_05_18";
	String classType1  = "FirstClass";
	String classType2  = "Coach";
    String ticketAgency = "TeamTricepts";
	List<airportInformation> ainfo;
	int NumMatrix = 10;

	/**
	 * Launch the application.
	 * @param args
	 */


	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		try {
			createContents();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
				
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() throws Exception{
		ainfo = readFile.transformAirportInformation(ticketAgency);
		ai.sortAirportByLongtitute(ainfo);
		System.out.println(ainfo.size());
		ap.airplaneMapToModel(ticketAgency);
		File dir = new File("coach_matrix_9.db");
		firstClassMatrix = new ArrayList<List<flight>[][]>();
		coachMatrix = new ArrayList<List<flight>[][]>();
		if( !dir.exists()) { 
			firstClassMatrix = matrix.allMatrix(ticketAgency, ainfo, classType1, startDate, ap);
			coachMatrix = matrix.allMatrix(ticketAgency, ainfo, classType2, startDate, ap);
		} else {
			for(int i =0; i< NumMatrix ; i++){
				firstClassMatrix.add(ffc.loadFromFile(i, "firstClass"));
				coachMatrix.add(ffc.loadFromFile(i, "coach"));
			}
			System.out.println("Read from file");
		}
		ffc.writeToFile(coachMatrix,"coach");
		ffc.writeToFile(firstClassMatrix, "firstClass");
		
		
		

		
		shell = new Shell();
		shell.setSize(1230, 710);
		shell.setBackgroundImage(SWTResourceManager.getImage("/Users/hujiachen/Downloads/universe-wallpapers.jpg"));
		shell.setText("Triceps Flight ");
		
		Label lblTicketType = new Label(shell, SWT.NONE);
		lblTicketType.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTicketType.setFont(SWTResourceManager.getFont("Lucida Grande", 12, SWT.BOLD));
		lblTicketType.setBounds(405, 27, 91, 18);
		lblTicketType.setText("Ticket Type");
		
		final Button btnOneWay = new Button(shell, SWT.RADIO);
		btnOneWay.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnOneWay.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		btnOneWay.setSelection(true);
		btnOneWay.setBounds(556, 25, 83, 18);
		btnOneWay.setText("One way");

		

		
		final Button btnRoundTrip = new Button(shell, SWT.RADIO);
		btnRoundTrip.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRoundTrip.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		btnRoundTrip.setBounds(675, 25, 91, 18);
		btnRoundTrip.setText("Round trip");
		
		Label lblFrom = new Label(shell, SWT.NONE);
		lblFrom.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFrom.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		lblFrom.setBounds(405, 60, 59, 14);
		lblFrom.setText("From");
		
		final CCombo combo = new CCombo(shell, SWT.BORDER | SWT.READ_ONLY);
		combo.setText("Anchorage(ANC)");
		List<String> s1 = new ArrayList<String>();

		s1.add("Anchorage(ANC)");

        s1.add("Atlanta(ATL)");

        s1.add("Austin(AUS)");

        s1.add("Baltimore(BWI)");

        s1.add("Boston(BOS)");

        s1.add("Charlotte(CLT)");

        s1.add("Chicago(MDW)");

        s1.add("Chicago(ORD)");

        s1.add("Cincinnati(CVG)");

        s1.add("Cleveland(CLE)");

        s1.add("Columbus(CMH)");

        s1.add("Dallas(DFW)");

        s1.add("Denver(DEN)");

        s1.add("Detroit(DTW)");

        s1.add("Fort Lauderdale(FLL)");

        s1.add("Fort Myers(RSW)");

        s1.add("Hartford(BDL)");

        s1.add("Honolulu(HNL)");

        s1.add("Houston(HOU)");

        s1.add("Houston(IAH)");

        s1.add("Indianapolis(IND)");

        s1.add("Kansas City(MCI)");

        s1.add("Las Vegas(LAS)");

        s1.add("Los Angeles(LAX)");

        s1.add("Memphis(MEM)");

        s1.add("Miami(MIA)");

        s1.add("Minneapolis(MSP)");

        s1.add("Nashville(BNA)");

        s1.add("New Orleans(MSY)");

        s1.add("New York(JFK)");

        s1.add("New York(LGA)");

        s1.add("Oakland(OAK)");

        s1.add("Ontario(ONT)");

        s1.add("Orlando(MCO)");

        s1.add("Portland(PDX)");

        s1.add("Philadelphia(PHL)");

        s1.add("Phoenix(PHX)");

        s1.add("Pittsburgh(PIT)");

        s1.add("Raleigh(RDU)");

        s1.add("Sacramento(SMF)");

        s1.add("Salt Lake City(SLC)");

        s1.add("San Antonio(SAT)");

        s1.add("San Diego(SAN)");

        s1.add("San Francisco(SFO)");

        s1.add("San Jose(SJC)");

        s1.add("Santa Ana(SNA)");

        s1.add("Seattle(SEA)");

        s1.add("St Louis(STL)");

        s1.add("Tampa(TPA)");

        s1.add("Washington(DCA)");

        s1.add("Washington(IAD)");



		for (String s : s1) {
			combo.add(s);
		}
		combo.setBounds(405, 80, 142, 18);
		
		Label lblTo = new Label(shell, SWT.NONE );
		lblTo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTo.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		lblTo.setBounds(610, 60, 59, 14);
		lblTo.setText("To");
		
		final CCombo combo_1 = new CCombo(shell, SWT.BORDER | SWT.READ_ONLY);
		for (String s : s1) {
			combo_1.add(s);
		}
		combo_1.setBounds(610, 80, 142, 18);
		combo_1.setText("Anchorage(ANC)");
		
		  Label lblNewLabel = new Label(shell, SWT.NONE);
		    lblNewLabel.setFont(SWTResourceManager.getFont("Lucida Grande", 12, SWT.BOLD));
		    lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblNewLabel.setBounds(72, 249, 117, 27);
		    lblNewLabel.setText("Departure Flight");
		    
		    final Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		    lblNewLabel_1.setFont(SWTResourceManager.getFont("Lucida Grande", 12, SWT.BOLD));
		    lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblNewLabel_1.setBounds(72, 439, 117, 18);
		    lblNewLabel_1.setText("Returning Flight");
		    
		    final Combo combo_3 = new Combo(shell, SWT.NONE | SWT.READ_ONLY);
		    combo_3.setItems(new String[] {"Price", "Flight time"});
		    combo_3.setBounds(810, 79, 110, 22);
			combo_3.setText("");
		    
		    Label lblSortBy = new Label(shell, SWT.NONE);
		    lblSortBy.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		    lblSortBy.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblSortBy.setBounds(810, 60, 59, 18);
		    lblSortBy.setText("Sort by");
		    
		    Label lblDepartBetween = new Label(shell, SWT.NONE);
		    lblDepartBetween.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblDepartBetween.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		    lblDepartBetween.setBounds(72, 288, 93, 14);
		    lblDepartBetween.setText("Depart between");
		    
		    final CCombo combo_4 = new CCombo(shell, SWT.BORDER | SWT.READ_ONLY);
		    combo_4.setItems(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"});
		    combo_4.setBounds(72, 308, 35, 18);
		    combo_4.setText("0");
		    
		    final CCombo combo_5 = new CCombo(shell, SWT.BORDER | SWT.READ_ONLY);
		    combo_5.setItems(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"});
		    combo_5.setBounds(130, 308, 35, 18);
		    combo_5.setText("24");
		    
		    final Label label = new Label(shell, SWT.NONE);
		    label.setText("Depart between");
		    label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    label.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		    label.setBounds(72, 482, 93, 14);
		    
		    final CCombo combo_6 = new CCombo(shell, SWT.BORDER | SWT.READ_ONLY);
		    combo_6.setItems(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"});
		    combo_6.setBounds(72, 502, 35, 18);
		    combo_6.setText("0");
		    
		    final CCombo combo_7 = new CCombo(shell, SWT.BORDER | SWT.READ_ONLY);
		    combo_7.setItems(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"});
		    combo_7.setBounds(130, 502, 35, 18);
		    combo_7.setText("24");
		
		Label lblDepartDate = new Label(shell, SWT.NONE);
		lblDepartDate.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDepartDate.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		lblDepartDate.setBounds(405, 115, 74, 14);
		lblDepartDate.setText("Depart date");
		
		final DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(405, 135, 110, 27);
		dateTime.setDay(8);
		dateTime.setMonth(4);
		dateTime.setYear(2015);
		final DateTime dateTime_1 = new DateTime(shell, SWT.BORDER);
		dateTime_1.setBounds(610, 135, 110, 27);
		dateTime_1.setDay(8);
		dateTime_1.setMonth(4);
		dateTime_1.setYear(2015);

		/*dateTime_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (dateTime.getDay() >= dateTime_1.getDay() || dateTime.getMonth() > dateTime_1.getMonth() 
						|| dateTime.getYear() > dateTime_1.getYear()) {
					MessageDialog.openConfirm(shell, "Error", "Return date should be later than departing date!");
					//dateTime_1.setDay(dateTime.getDay());
					//dateTime_1.setMonth(dateTime.getMonth());
					//dateTime_1.setYear(dateTime.getYear());	
				}
			}
		});*/
		
		final Label lblReturnDate = new Label(shell, SWT.NONE);
		lblReturnDate.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblReturnDate.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		lblReturnDate.setBounds(610, 115, 74, 14);
		lblReturnDate.setText("Return date");
		
		final CCombo combo_2 = new CCombo(shell, SWT.BORDER | SWT.READ_ONLY);
		combo_2.setItems(new String[] {"Coach", "FirstClass"});
		combo_2.setBounds(405, 203, 91, 18);
		combo_2.setText("Coach");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(222, 288, 920, 122);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);


	    TableColumn tc1 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc2 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc3 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc4 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc5 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc6 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc7 = new TableColumn(table, SWT.CENTER);
	    TableColumn tc8 = new TableColumn(table, SWT.CENTER);
	    tc1.setText("Flight Number");
	    tc2.setText("Departing Airport");
	    tc3.setText("Arriving Airport");
	    tc4.setText("Departing Time");
	    tc5.setText("Arriving Time");
	    tc6.setText("Ticket Price");
	    tc7.setText("Stop Time");
	    tc8.setText("Flight Duration");
	    tc1.setWidth(125);
	    tc2.setWidth(106);
	    tc3.setWidth(94);
	    tc4.setWidth(175);
	    tc5.setWidth(175);
	    tc6.setWidth(75);
	    tc7.setWidth(64);
	    tc8.setWidth(93);
	    
	    
	    final Table table_1 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(222, 482, 920, 122);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);


	    TableColumn tc1_1 = new TableColumn(table_1, SWT.CENTER);
	    TableColumn tc2_1 = new TableColumn(table_1, SWT.CENTER);
	    TableColumn tc3_1 = new TableColumn(table_1, SWT.CENTER);
	    TableColumn tc4_1 = new TableColumn(table_1, SWT.CENTER);
	    TableColumn tc5_1 = new TableColumn(table_1, SWT.CENTER);
	    TableColumn tc6_1 = new TableColumn(table_1, SWT.CENTER);
	    TableColumn tc7_1 = new TableColumn(table_1, SWT.CENTER);
	    TableColumn tc8_1 = new TableColumn(table_1, SWT.CENTER);
	    tc1_1.setText("Flight Number");
	    tc2_1.setText("Departing Airport");
	    tc3_1.setText("Arriving Airport");
	    tc4_1.setText("Departing Time");
	    tc5_1.setText("Arriving Time");
	    tc6_1.setText("Ticket Price");
	    tc7_1.setText("Stop Time");
	    tc8_1.setText("Flight Duration");
	    tc1_1.setWidth(125);
	    tc2_1.setWidth(106);
	    tc3_1.setWidth(94);
	    tc4_1.setWidth(175);
	    tc5_1.setWidth(175);
	    tc6_1.setWidth(75);
	    tc7_1.setWidth(64);
	    tc8_1.setWidth(93);
	    
	    
	    
	    table.setHeaderVisible(true);

		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setFont(SWTResourceManager.getFont("Lucida Grande", 13, SWT.BOLD));
		btnSearch.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				table_1.removeAll();
				if (btnOneWay.getSelection()) {
					
					String comboInfo1 = combo.getText();
					String comboInfo2 = combo_1.getText();
					String departDate = new String();
					String depAirport = new StringBuilder().append(comboInfo1.charAt(comboInfo1.length()-4)).append(comboInfo1.charAt(comboInfo1.length()-3)).append(comboInfo1.charAt(comboInfo1.length()-2)).toString();
					String arrAirport = new StringBuilder().append(comboInfo2.charAt(comboInfo2.length()-4)).append(comboInfo2.charAt(comboInfo2.length()-3)).append(comboInfo2.charAt(comboInfo2.length()-2)).toString();
					String classType = combo_2.getText();
					if (dateTime.getDay() <= 7 || dateTime.getDay() >= 18 || dateTime.getYear() != 2015 || dateTime.getMonth() != 4) {
						MessageDialog.openConfirm(shell, "Information", "No flights on given date");
					} else if ( dateTime.getDay() > 9) {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_" + Integer.toString(dateTime.getDay());
					} else {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_0" + Integer.toString(dateTime.getDay());
					} 
					if (dateTime.getDay() > 7 && dateTime.getDay() < 18 && dateTime.getYear() == 2015 && dateTime.getMonth() == 4) {
						
						XMLFileParser rf = new XMLFileParser();
						try {
							Time timeSwitchFlight = new Time();
							String departDate1 = timeSwitchFlight.lastDate(departDate);						
							List<allFlightInformation> unfilteredFlights = rf.transformFlightInfo(ticketAgency, departDate, depAirport);
							List<allFlightInformation> unfilteredFlights1 = rf.transformFlightInfo(ticketAgency, departDate1, depAirport);
							filterFlights Flight = new filterFlights();
							myFlight = timeSwitchFlight.timeSwich(Flight.oneWayFilter(depAirport, arrAirport, departDate, classType, unfilteredFlights, ap), ainfo);
							List<flight> nextDayFlight = timeSwitchFlight.timeSwich(Flight.oneWayFilter(depAirport, arrAirport, departDate1, classType, unfilteredFlights1, ap), ainfo);
							for (int i = 0; i < nextDayFlight.size(); i++) {
								if (nextDayFlight.get(i).getDepartingDate().equals(departDate)) {
									myFlight.add(nextDayFlight.get(i));
								}
							}
							
							for (int i = 0 ; i < myFlight.size(); i++) {
								flight f = myFlight.get(i);
								if (!f.getDepartingDate().equals(departDate)) {
									myFlight.remove(f);
								}
							}
							if ( classType.equals("FirstClass")) {
								transFlight1 = Flight.oneStopTimeTransferFlight(depAirport, arrAirport, departDate, startDate, firstClassMatrix, ai);
								transFlight2 = Flight.twoStopTimeTransferFlight(depAirport, arrAirport, departDate, startDate, firstClassMatrix, ai);
								if (!transFlight1.isEmpty()) {
									myFlight.addAll(Flight.oneStopTimeFlight(transFlight1));
								}
								if (!transFlight2.isEmpty()) {
									myFlight.addAll(Flight.twoStopTimeFlight(transFlight2));
								}
							} else {
								transFlight1 = Flight.oneStopTimeTransferFlight(depAirport, arrAirport, departDate, startDate, coachMatrix, ai);
								transFlight2 = Flight.twoStopTimeTransferFlight(depAirport, arrAirport, departDate, startDate, coachMatrix, ai);
								if (!transFlight1.isEmpty()) {
									myFlight.addAll(Flight.oneStopTimeFlight(transFlight1));
								}
								if (!transFlight2.isEmpty()) {
									myFlight.addAll(Flight.twoStopTimeFlight(transFlight2));
								}
							}

						// time window filter
						//switch time zone myFlight.departingTime & arrivingTime
							flight flt = new flight();
							if (combo_3.getText().equals("Price")) {						
								flt.sortByPrice(myFlight);
							} else if (combo_3.getText().equals("Flight time")) {
								flt.sortByFlightTime(myFlight);
							}

							for( int i = 0; i < myFlight.size(); i++) {
								Time timer = new Time();
								flight F = myFlight.get(i);
								if (timer.getHourFromTime(F.getDepartingTime()) > Integer.parseInt(combo_4.getText()) && timer.dateDifference(F) <2 &&
										timer.getHourFromTime(F.getDepartingTime()) < Integer.parseInt(combo_5.getText())) {
									TableItem item = new TableItem(table, SWT.NONE);
									item.setText(new String[] {F.getFlightNumber(), F.getDepartingAirport(), F.getArrivingAirport(), F.getDepartingDate() + " " + F.getDepartingTime(), 
											F.getArrivingDate() + " " + F.getArrivingTime(), Double.toString(F.getTicketPrice()), Integer.toString(F.getStopTimes()), Integer.toString(F.getFlightTime())});
								} else {
									myFlight.remove(F);
								}
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					
					}
					
				} else if (btnRoundTrip.getSelection()) {
					
					String comboInfo1 = combo.getText();
					String comboInfo2 = combo_1.getText();
					String depAirport = new StringBuilder().append(comboInfo1.charAt(comboInfo1.length()-4)).append(comboInfo1.charAt(comboInfo1.length()-3)).append(comboInfo1.charAt(comboInfo1.length()-2)).toString();
					String arrAirport = new StringBuilder().append(comboInfo2.charAt(comboInfo2.length()-4)).append(comboInfo2.charAt(comboInfo2.length()-3)).append(comboInfo2.charAt(comboInfo2.length()-2)).toString();
					String classType = combo_2.getText();
					String departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_" + Integer.toString(dateTime.getDay());
					String arrDate = Integer.toString(dateTime_1.getYear()) + "_0" + Integer.toString(dateTime_1.getMonth()+1) + "_" + Integer.toString(dateTime_1.getDay());
					XMLFileParser rf = new XMLFileParser();
					
					if (dateTime.getDay() <= 7 || dateTime.getDay() >= 18 || dateTime.getYear() != 2015 || dateTime.getMonth() != 4 ||
							dateTime_1.getDay() <= 7 || dateTime_1.getDay() >= 18 || dateTime_1.getYear() != 2015 || dateTime_1.getMonth() != 4) {
						MessageDialog.openConfirm(shell, "Information", "No flights on given date");
					} else if ( dateTime.getDay() > 9) {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_" + Integer.toString(dateTime.getDay());
						arrDate = Integer.toString(dateTime_1.getYear()) + "_0" + Integer.toString(dateTime_1.getMonth()+1) + "_" + Integer.toString(dateTime_1.getDay());
					} else {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_0" + Integer.toString(dateTime.getDay());
						arrDate = Integer.toString(dateTime_1.getYear()) + "_0" + Integer.toString(dateTime_1.getMonth()+1) + "_0" + Integer.toString(dateTime_1.getDay());
					}
					
					
					if (dateTime.getDay() > 7 && dateTime.getDay() < 18 && dateTime.getYear() == 2015 && dateTime.getMonth() == 4 &&
							dateTime_1.getDay() > 7 && dateTime_1.getDay() < 18 && dateTime_1.getYear() == 2015 && dateTime_1.getMonth() == 4 && dateTime.getDay() <= dateTime_1.getDay()) {
						try {
							Time timeSwitchFlight = new Time();
							List<allFlightInformation> unfilteredDepFlights = rf.transformFlightInfo(ticketAgency, departDate, depAirport);
							List<allFlightInformation> unfilteredRetFlights = rf.transformFlightInfo(ticketAgency, arrDate, arrAirport);
							filterFlights Flight = new filterFlights();
							myFlight1 = Flight.roundtripFilter(ticketAgency, depAirport, arrAirport, departDate, arrDate, classType, unfilteredDepFlights, unfilteredRetFlights, ap);
							myFlight1.set(0, timeSwitchFlight.timeSwich(myFlight1.get(0), ainfo));
							myFlight1.set(1, timeSwitchFlight.timeSwich(myFlight1.get(1), ainfo));
							myFlight2 = new ArrayList<List<flight>>();
							myFlight3 = new ArrayList<List<flight>>();
							
							if( !myFlight1.get(0).isEmpty() ) {
								for (int i = 0; i < myFlight1.get(0).size(); i++) {
									flight f = myFlight1.get(0).get(i);
									if (!f.getDepartingDate().equals(departDate)) {
										myFlight1.get(0).remove(f);
									}
								}
							}

							if( !myFlight1.get(1).isEmpty() ) {
								for (int i = 0; i < myFlight1.get(1).size(); i++) {
									flight f = myFlight1.get(1).get(i);
									if (!f.getArrivingDate().equals(arrDate)) {
										myFlight1.get(1).remove(f);
									}
								}
							}
							
							if ( classType.equals("FirstClass")) {							
								transFlight21 = Flight.oneStopTimeTransferFlightForRoundTrip(depAirport, arrAirport, departDate, arrDate, startDate, firstClassMatrix, ai);
								transFlight22 = Flight.twoStopTimeTransferFlightForRoundTrip(depAirport, arrAirport, departDate, arrDate, startDate, firstClassMatrix, ai);
								myFlight2 = Flight.oneStopTimeFlightForRoundTrip(transFlight21);
								myFlight3 = Flight.twoStopTimeFlightForRoundTrip(transFlight22);
							} else {
								transFlight21 = Flight.oneStopTimeTransferFlightForRoundTrip(depAirport, arrAirport, departDate, arrDate, startDate, coachMatrix, ai);
								transFlight22 = Flight.twoStopTimeTransferFlightForRoundTrip(depAirport, arrAirport, departDate, arrDate, startDate, coachMatrix, ai);
								myFlight2 = Flight.oneStopTimeFlightForRoundTrip(transFlight21);
								myFlight3 = Flight.twoStopTimeFlightForRoundTrip(transFlight22);
							}
							flight flt = new flight();
							List<flight> flightList1 = new ArrayList<flight>();
							List<flight> flightList2 = new ArrayList<flight>();
							if (!myFlight1.isEmpty()) {
								flightList1.addAll(myFlight1.get(0));
								flightList2.addAll(myFlight1.get(1));
							}
							if (!myFlight2.isEmpty()) {
								flightList1.addAll(myFlight2.get(0));
								flightList2.addAll(myFlight2.get(1));
							}
							if (!myFlight3.isEmpty()) {
								flightList1.addAll(myFlight3.get(0));
								flightList2.addAll(myFlight3.get(1));
							}
							if (combo_3.getText() == "Price") {						
								flt.sortByPrice(flightList1);
								flt.sortByPrice(flightList2);
							} else if (combo_3.getText() == "Flight time") {
								flt.sortByFlightTime(flightList1);
								flt.sortByFlightTime(flightList2);
							}	
							if (combo_3.getText().equals("Price")) {						
								flt.sortByPrice(flightList1);
								flt.sortByPrice(flightList2);
							} else if (combo_3.getText().equals("Flight time")) {
								flt.sortByFlightTime(flightList1);
								flt.sortByFlightTime(flightList2);							
							}

							for (flight F : flightList1){
								Time timer = new Time();
								if (timer.getHourFromTime(F.getDepartingTime()) > Integer.parseInt(combo_4.getText()) && timer.dateDifference(F) <2 &&
										timer.getHourFromTime(F.getDepartingTime()) < Integer.parseInt(combo_5.getText())) {
									TableItem item = new TableItem(table, SWT.NONE);
									item.setText(new String[] {F.getFlightNumber(), F.getDepartingAirport(), F.getArrivingAirport(), F.getDepartingDate() + " " + F.getDepartingTime(), 
											F.getArrivingDate() + " " + F.getArrivingTime(), Double.toString(F.getTicketPrice()), Integer.toString(F.getStopTimes()),Integer.toString(F.getFlightTime())});
								}

							}
						
							for (flight F : flightList2){	
								Time timer = new Time();
								if (timer.getHourFromTime(F.getDepartingTime()) > Integer.parseInt(combo_4.getText()) &&timer.dateDifference(F) <2 &&
										timer.getHourFromTime(F.getDepartingTime()) < Integer.parseInt(combo_5.getText())) {
									TableItem item = new TableItem(table_1, SWT.NONE);
									item.setText(new String[] {F.getFlightNumber(), F.getDepartingAirport(), F.getArrivingAirport(), F.getDepartingDate() + " " + F.getDepartingTime(), 
											F.getArrivingDate() + " " + F.getArrivingTime(), Double.toString(F.getTicketPrice()), Integer.toString(F.getStopTimes()),Integer.toString(F.getFlightTime())});
								}
							}						

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (dateTime.getDay() > dateTime_1.getDay() && dateTime.getDay() > 7 && dateTime.getDay() < 18
							&& dateTime_1.getDay() > 7 && dateTime_1.getDay() < 18 && dateTime_1.getMonth() == 4 && dateTime.getMonth() == 4) {
						MessageDialog.openConfirm(shell, "Information", "Departing date should be earlier than returning date");
					}
					
				}
			}
		});
		btnSearch.setBounds(900, 215, 110, 34);
		btnSearch.setText("Search");
		
	    
	    final Button btnShowDetail = new Button(shell, SWT.NONE);
	    btnShowDetail.setFont(SWTResourceManager.getFont("Lucida Grande", 13, SWT.BOLD));
	    btnShowDetail.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		ticketReservationWindow window = new ticketReservationWindow(); 
	    		filterFlights filter = new filterFlights();
    			String classType = combo_2.getText();
    			List<List<flight>[][]> matrix = new ArrayList<List<flight>[][]>();
    			if(classType.equals("Coach")) {
    				matrix = coachMatrix;
    			} else if (classType.equals("FirstClass")) {
    				matrix = firstClassMatrix;
    			}

	    		if (btnOneWay.getSelection()) {					
	    			int chosenIndex  = table.getSelectionIndex();
	    			List<flight> chosenFlight = new ArrayList<flight>();
	    			String flightNumber = table.getItem(chosenIndex).getText();
	    			String[] strarray = flightNumber.split("/"); 
	    			String departDate = "";

					
	    			if ( dateTime.getDay() > 9) {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_" + Integer.toString(dateTime.getDay());
					} else if ( dateTime.getDay() == 9){
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_0" + Integer.toString(dateTime.getDay());
					} else {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_0" + Integer.toString(dateTime.getDay());
					}
	    			
					try {
						for (int i = 0; i < strarray.length; i++) {
							flight ft = filter.filterByFlightNumber(strarray[i], matrix, departDate, startDate, ainfo);
							if(ft.getDepartingTime() != null) {
								chosenFlight.add(ft);
							}
						}

					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					window.setDepFlightNumber(strarray);
					window.setDepartDate(departDate);
    				window.setChosenFlight(chosenFlight);
    				window.setClassType(classType);
    				window.setAirplaneInformation(ap);
    				window.open();
	    			chosenFlight.clear();
	    		} else if (btnRoundTrip.getSelection()) {
	    			int chosenIndex1  = table.getSelectionIndex();
	    			int chosenIndex2  = table_1.getSelectionIndex();
	    			List<flight> chosenFlight = new ArrayList<flight>();
	    			String flightNumber1 = table.getItem(chosenIndex1).getText();
	    			String flightNumber2 = table_1.getItem(chosenIndex2).getText();
	    			String[] strarray1 = flightNumber1.split("/"); 
	    			String[] strarray2 = flightNumber2.split("/"); 
	    			String departDate = "";
	    			String arriveDate = "";
	    			
	    			if ( dateTime.getDay() > 9 && dateTime_1.getDay() > 9) {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_" + Integer.toString(dateTime.getDay());
						arriveDate = Integer.toString(dateTime_1.getYear()) + "_0" + Integer.toString(dateTime_1.getMonth()+1) + "_" + Integer.toString(dateTime_1.getDay());
					} else if ( dateTime.getDay() == 9 && dateTime_1.getDay() > 9){
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_0" + Integer.toString(dateTime.getDay());
						arriveDate = Integer.toString(dateTime_1.getYear()) + "_0" + Integer.toString(dateTime_1.getMonth()+1) + "_" + Integer.toString(dateTime_1.getDay());
					} else if ( dateTime.getDay() <= 9 && dateTime_1.getDay() <= 9) {
						departDate = Integer.toString(dateTime.getYear()) + "_0" + Integer.toString(dateTime.getMonth()+1) + "_0" + Integer.toString(dateTime.getDay());
						arriveDate = Integer.toString(dateTime_1.getYear()) + "_0" + Integer.toString(dateTime_1.getMonth()+1) + "_0" + Integer.toString(dateTime_1.getDay());
					}
	    			
	    			
					try {
						for (int i = 0; i < strarray1.length; i++) {
						flight ft1 = filter.filterByFlightNumber(strarray1[i], matrix, departDate, startDate, ainfo);					
						if(ft1.getDepartingTime() != null) {
							chosenFlight.add(ft1);
						}
					}
						
						for (int i = 0; i < strarray2.length; i++) {
						flight ft2 = filter.filterByFlightNumber(strarray2[i], matrix, arriveDate, startDate, ainfo);
						if(ft2.getDepartingTime() != null) {
							chosenFlight.add(ft2);
						}
					}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
	    			
					window.setDepFlightNumber(strarray1);
					window.setRetFlightNumber(strarray2);
					window.setDepartDate(departDate);
					window.setArrDate(arriveDate);
    				window.setChosenFlight(chosenFlight);
    				window.setClassType(classType);
    				window.setAirplaneInformation(ap);
    				window.open();
	    			chosenFlight.clear();
	    			btnShowDetail.setEnabled(false);
	    			
	    		}
	    		
	    	}
	    });
	    btnShowDetail.setBounds(900, 630, 110, 34);
	    btnShowDetail.setText("Show Detail");
	    btnShowDetail.setEnabled(false);
	    
	    Label label_1 = new Label(shell, SWT.NONE);
	    label_1.setText("Cabin Class");
	    label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    label_1.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
	    label_1.setBounds(405, 183, 74, 14);
	    
	    if (btnOneWay.getSelection()) {
			dateTime_1.setVisible(false);
			lblReturnDate.setVisible(false);
			table_1.setVisible(false);
			combo_6.setVisible(false);
			combo_7.setVisible(false);
			lblNewLabel_1.setVisible(false);
			label.setVisible(false);
	    }
	    			    
		
		btnOneWay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.doit) {
					dateTime_1.setVisible(false);
					lblReturnDate.setVisible(false);
					table_1.setVisible(false);
					combo_6.setVisible(false);
					combo_7.setVisible(false);
					lblNewLabel_1.setVisible(false);
					label.setVisible(false);
				}
			}

		});
		
		btnRoundTrip.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.doit) {
					dateTime_1.setVisible(true);
					lblReturnDate.setVisible(true);
					table_1.setVisible(true);
					combo_6.setVisible(true);
					combo_7.setVisible(true);
					lblNewLabel_1.setVisible(true);
					label.setVisible(true);
				}
			}
		});
		
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(e.doit & btnOneWay.getSelection()) {
					btnShowDetail.setEnabled(true);
				} else if (e.doit & btnRoundTrip.getSelection()) {
					table_1.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e1) {
							if(e1.doit) {
								btnShowDetail.setEnabled(true);
							}
						}
					});
				}

			}
		});

		
	}
}
