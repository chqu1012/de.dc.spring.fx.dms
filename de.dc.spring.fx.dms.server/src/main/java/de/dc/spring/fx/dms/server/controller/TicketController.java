package de.dc.spring.fx.dms.server.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dc.spring.fx.dms.server.model.Ticket;
import de.dc.spring.fx.dms.server.repository.TicketRepository;

@RestController
public class TicketController {

    @Autowired private TicketRepository ticketRepository;
    
    @GetMapping("/tickets")
    public List<Ticket> getAll(){
    	return ticketRepository.findAll();
    }
}