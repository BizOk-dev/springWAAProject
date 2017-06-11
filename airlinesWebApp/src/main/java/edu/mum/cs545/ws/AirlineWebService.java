package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named
@Path("/airline")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AirlineWebService {
	
	@Inject
	private AirlineService airlineService;

	@POST
	@Path("/new")
	public void create(Airline airport) {
		airlineService.create(airport);
	}
	@DELETE
	@Path("/delete")
	public void delete(Airline airport) {
		airlineService.delete(airport);
	}
	@PUT
	@Path("/update")
	public Airline update(Airline airport) {
		return airlineService.update(airport);
	}
	@GET
	@Path("/find")
	public Airline find(Airline airport) {
		return airlineService.find(airport);
	}
	@GET
	@Path("/{findbyname}")
	public Airline findByName(String name) {
		return airlineService.findByName(name);
	}
	@GET
	@Path("/findbyflight")
	public List<Airline> findByFlight(Flight flight) {
		return airlineService.findByFlight(flight);
	}
	@GET
	@Path("/findall")
	public List<Airline> findAll() {
		return airlineService.findAll();
}
}
