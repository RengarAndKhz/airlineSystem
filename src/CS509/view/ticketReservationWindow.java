package CS509.view;

import java.lang.reflect.Array;
import java.util.List;

import CS509.data.airplaneInformation;
import CS509.data.flight;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import CS509.DAO.QueryFactory;
import CS509.DAO.ticketReservation;
import CS509.DAO.ReservationSystem;
import CS509.utility.filterFlights;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;


public class ticketReservationWindow extends userInterface{



	protected Shell shlTicketReservation;
	private Table table;
	String ticketAgency = "TeamTricepts";
	ReservationSystem r = new ReservationSystem();
	List<flight> chosenFlight;
	String[] flightNumStr1;
	String[] flightNumStr2;
	String departDate;	
	String arrDate;
	String classType;
	airplaneInformation ap;
	


	public void setChosenFlight(List<flight> chosenFlight) {
		this.chosenFlight = chosenFlight;
	}
	
	public void setDepFlightNumber(String[] str) {
		this.flightNumStr1 = str;
	}
	
	public void setRetFlightNumber(String[] str) {
		this.flightNumStr2 = str;
	}
	
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}

	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public void setAirplaneInformation(airplaneInformation ap) {
		this.ap = ap;
	}
	

	/**
	 * Launch the application.
	 * @param args
	 */
	/*public static void main(String[] args) throws Exception{	
		try {
			ticketReservationWindow window = new ticketReservationWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Open the window.
	 */

	public void open() {
		Display display = Display.getDefault();
		try {
			createContents1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shlTicketReservation.open();
		shlTicketReservation.layout();
		while (!shlTicketReservation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public void createContents1() throws Exception {
		shlTicketReservation = new Shell();
		shlTicketReservation.setSize(955, 320);
		shlTicketReservation.setText("Ticket Reservation");
		
		table = new Table(shlTicketReservation, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(30, 70, 895, 122);
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
	    tc7.setText("Flight Duration");
	    tc8.setText("Seat");
	    tc1.setWidth(125);
	    tc2.setWidth(106);
	    tc3.setWidth(94);
	    tc4.setWidth(175);
	    tc5.setWidth(175);
	    tc6.setWidth(75);
	    tc7.setWidth(64);
	    tc8.setWidth(64);
	    
	    Label lblTicket = new Label(shlTicketReservation, SWT.NONE);
	    lblTicket.setFont(SWTResourceManager.getFont("Lucida Grande", 15, SWT.BOLD));
	    lblTicket.setBounds(30, 35, 135, 29);
	    lblTicket.setText("Available Ticket");
	    
	    for (flight f : chosenFlight) {
	    	TableItem item = new TableItem(table, SWT.NONE);
	    	item.setText(new String[] {f.getFlightNumber(), f.getDepartingAirport(), f.getArrivingAirport(), f.getDepartingDate() + " " + f.getDepartingTime(), 
		    		f.getArrivingDate() + " " + f.getArrivingTime(), Double.toString(f.getTicketPrice()), Integer.toString(f.getFlightTime()), Integer.toString(f.getSeat())});
	    }
	    
	    Button button = new Button(shlTicketReservation, SWT.NONE);
	    button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				boolean m = r.islockable(ticketAgency);
				if(m != true){
					MessageDialog.openConfirm(shell, "Information", "System busy");
				} else {
					ticketReservation tr = new ticketReservation();
					tr.buyTicket(ticketAgency, chosenFlight);
				}
			}			
	    });
	    button.setText("Buy");
	    button.setFont(SWTResourceManager.getFont("Lucida Grande", 14, SWT.BOLD));
	    button.setBounds(750, 215, 121, 34);
	    
	    filterFlights filter = new filterFlights(); 
	    for (int i = 0; i < flightNumStr1.length; i++) {
	    	 System.out.println(table.getItem(i).getText(3).substring(0, 9));
		    flight f = filter.filterByFlightNumber(ticketAgency, flightNumStr1[i], table.getItem(i).getText(3).substring(0, 9), table.getItem(i).getText(2), classType, ap);
		    TableItem item = new TableItem(table, SWT.NONE);
	    	item.setText(new String[] {f.getFlightNumber(), f.getDepartingAirport(), f.getArrivingAirport(), f.getDepartingDate() + " " + f.getDepartingTime(), 
		    		f.getArrivingDate() + " " + f.getArrivingTime(), Double.toString(f.getTicketPrice()), Integer.toString(f.getFlightTime()), Integer.toString(f.getSeat())});
	    }

	    


	}
}
