package de.dc.spring.fx.dms.thymeleaf.ui.controller;

import de.dc.spring.fx.dms.shared.model.Ticket;
import de.dc.spring.fx.dms.thymeleaf.ui.service.TicketDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class TicketRestController {
  @Autowired
  private TicketDtoService ticketService;
  
  @RequestMapping(path = "/tickets", method = RequestMethod.GET)
  public Ticket[] getAll() {
    return this.ticketService.getTickets();
  }
  
  @RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
  public Ticket getTicketById(@PathVariable("id") final long id) {
    return this.ticketService.getTicketById(id);
  }
}
