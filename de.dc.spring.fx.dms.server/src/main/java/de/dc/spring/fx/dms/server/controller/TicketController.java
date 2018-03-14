package de.dc.spring.fx.dms.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.dc.spring.fx.dms.server.repository.TicketRepository;
import de.dc.spring.fx.dms.shared.model.Ticket;

@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping("/tickets")
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}
	
	@GetMapping("/tickets/{id}")
	public Ticket findById(@PathVariable long id) {
		return ticketRepository.findById(id).get();
	}
	
	@DeleteMapping("/tickets/{id}") 
	public void deleteById(@PathVariable long id) {
		ticketRepository.deleteById(id);
	}
}