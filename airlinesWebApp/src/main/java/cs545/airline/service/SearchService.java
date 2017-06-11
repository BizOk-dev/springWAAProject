package cs545.airline.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

@Named
@ViewScoped
public class SearchService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FlightService flightService;
	@Inject
	private AirlineService airlineService;
	@Inject
	private AirportService airportService;
	
	private List<Airport> airport;
	private Airline airline;
	private String searchString="";
	private String searchStringDeparture="";
	private String searchStringDestination="";
	public String getSearchStringDeparture() {
		return searchStringDeparture;
	}

	public void setSearchStringDeparture(String searchStringDeparture) {
		this.searchStringDeparture = searchStringDeparture;
	}

	public String getSearchStringDestination() {
		return searchStringDestination;
	}

	public void setSearchStringDestination(String searchStringDestination) {
		this.searchStringDestination = searchStringDestination;
	}

	private Date searchbyDate=null;
	private List<Flight> flights;
	
	@PostConstruct
	public void initContactFlightBean(){
		flights=flightService.findAll();
	}
	
	public void findFlightbyAirline(AjaxBehaviorEvent event){
		
		if(searchString.isEmpty()){
			flights=flightService.findAll();
		}
		else{
			airline=airlineService.findByName(searchString);
			flights=flightService.findByAirline(airline);
		}
	}
	public void findFlightbyDeparture(AjaxBehaviorEvent event){
		
		if(searchbyDate.equals(null)){
			flights=flightService.findAll();
		}
		else{
			flights=flightService.findByDeparture(searchbyDate);
		}
	}
	

	public Date getSearchbyDate() {
		return searchbyDate;
	}

	public void setSearchbyDate(Date searchbyDate) {
		this.searchbyDate = searchbyDate;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public List<Flight> getFlights() {
		return flights;
	}
	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		System.out.println("hello bizen2");
		this.airline = airline;
		System.out.println("hello bizen1");
}
	
	
public void findbyDestination(AjaxBehaviorEvent event){
		System.out.println(searchStringDestination);
		if(searchStringDestination.isEmpty()){
			flights=flightService.findAll();
		}
		else{
			airport=airportService.findByName(searchStringDestination);
			flights=flightService.findByDestination(airport.get(0));
		}
}
}