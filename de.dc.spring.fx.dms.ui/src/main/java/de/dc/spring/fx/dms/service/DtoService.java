package de.dc.spring.fx.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.dc.spring.fx.dms.shared.model.Ticket;

@Service
public class DtoService {

	public static final String PORT = "8090";
	public static final String URL = "http://localhost:"+PORT;
	
	@Autowired RestTemplate restTemplate;
	
	public Ticket[] getTickets() {
		return restTemplate.getForObject(URL+"/tickets", Ticket[].class);
	}
	
}
