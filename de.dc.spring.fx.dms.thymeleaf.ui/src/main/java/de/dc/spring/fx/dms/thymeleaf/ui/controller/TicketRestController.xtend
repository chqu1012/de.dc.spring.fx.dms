package de.dc.spring.fx.dms.thymeleaf.ui.controller

import de.dc.spring.fx.dms.thymeleaf.ui.service.TicketDtoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController class TicketRestController {
	
	@Autowired TicketDtoService ticketService

	@RequestMapping(path="/tickets", method=RequestMethod::GET) 
	def getAll() {
		return ticketService.getTickets()
	}

	@RequestMapping(value="/tickets/{id}", method=RequestMethod::GET) 
	def getTicketById(@PathVariable("id") long id) {
		return ticketService.getTicketById(id)
	}
}
