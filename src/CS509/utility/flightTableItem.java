package CS509.utility;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import CS509.data.flight;

public class flightTableItem extends TableItem{
	/**
	 *
	 * @param parent
	 * @param style
	 */
	public flightTableItem(Table parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param F
	 */
	public void setTextFromFlight(flight F){
		this.setText(new String[] {F.getFlightNumber(), F.getDepartingAirport(), F.getArrivingAirport(), F.getDepartingDate() + " " + F.getDepartingTime(), 
				F.getArrivingDate() + " " + F.getArrivingTime(), Double.toString(F.getTicketPrice()), Integer.toString(F.getStopTimes())});
	}

}
