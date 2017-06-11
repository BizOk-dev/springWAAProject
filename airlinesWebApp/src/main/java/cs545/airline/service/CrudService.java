package cs545.airline.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;

@Named
@ViewScoped
public class CrudService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AirlineService airlineService;
	
	private List<Airline> airlinelist;
	private String searchString="";
	private String searchString1="";
	private Airline airline;
	public String getSearchString1() {
		return searchString1;
	}

	public void setSearchString1(String searchString1) {
		this.searchString1 = searchString1;
	}

	@PostConstruct
	public void initContactFlightBean(){
		airlinelist=airlineService.findAll();
	}

	/*public void findAirlines(AjaxBehaviorEvent event){
		if(searchString.isEmpty()){
			airlinelist=airlineService.findAll();
		}
		
		else{
			//airlinelist=airlineService.findByName(searchString);
		}
	}*/
	
	public void add(){
		airlineService.create(new Airline(searchString));
	}
	
	public void delete(){
		airline=airlineService.findByName(searchString1);
		airlineService.delete(airline);
	}
	public List<Airline> getAirlinelist() {
		return airlinelist;
	}

	public void setAirlinelist(List<Airline> airlinelist) {
		this.airlinelist = airlinelist;
	}
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
}
