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
	private String searchforUpdate="";
	private String setforUpdate="";
	private Airline airline;

	

	@PostConstruct
	public void initContactFlightBean(){
		airlinelist=airlineService.findAll();
	}

	public void add(AjaxBehaviorEvent event){
		airlineService.create(new Airline(searchString));
	}
	
	public void delete(AjaxBehaviorEvent event){
		airline=airlineService.findByName(searchString1);
		airlineService.delete(airline);
	}
	
	public void update(AjaxBehaviorEvent event){
		airline=airlineService.findByName(searchforUpdate);
		airline.setName(setforUpdate);
		airlineService.update(airline);
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
	public String getSearchforUpdate() {
		return searchforUpdate;
	}

	public void setSearchforUpdate(String searchforUpdate) {
		this.searchforUpdate = searchforUpdate;
	}
	
	public String getSetforUpdate() {
		return setforUpdate;
	}

	public void setSetforUpdate(String setforUpdate) {
		this.setforUpdate = setforUpdate;
	}

	public String getSearchString1() {
		return searchString1;
	}

	public void setSearchString1(String searchString1) {
		this.searchString1 = searchString1;
	}
}
