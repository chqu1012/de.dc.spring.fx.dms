package de.dc.spring.fx.dms.server.controller

import de.dc.spring.fx.dms.shared.repository.TicketRepository
import de.dc.spring.fx.dms.shared.model.Ticket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController 
class TicketController implements IRestController<Ticket> {
	
	@Autowired TicketRepository ticketRepository

	@GetMapping("/tickets") 
	override getAll() {
		ticketRepository.findAll
	}

	@GetMapping("/tickets/{id}") 
	override findById(@PathVariable long id) {
		ticketRepository.findById(id).get
	}

	@DeleteMapping("/tickets/{id}") 
	override deleteById(@PathVariable long id) {
		ticketRepository.deleteById(id)
	}

	@PutMapping("/tickets/{id}") 
	override update(@RequestBody Ticket ticket, @PathVariable long id) {
		var ticketOptional = ticketRepository.findById(id)
		
		if(!ticketOptional.isPresent) 
			return ResponseEntity.notFound.build
		
		ticket.setId(id)
		ticketRepository.save(ticket)
		ResponseEntity.noContent.build
	}

	@PostMapping("/tickets") 
	override create(@RequestBody Ticket ticket) {
		var saveTicket = ticketRepository.save(ticket)
		var location = ServletUriComponentsBuilder.fromCurrentRequest.path("/{id}").buildAndExpand(
			saveTicket.id).toUri
		return ResponseEntity.created(location).body(saveTicket)
	}
}
