package de.dc.spring.fx.dms.service

import de.dc.spring.fx.dms.model.Ticket
import de.dc.spring.fx.dms.repository.TicketRepository
import java.util.List
import java.util.stream.Collectors
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service 
class TicketService implements IService<Ticket> {

	@Autowired package TicketRepository ticketRepository
	List<Ticket> tickets

	def reload() {
		tickets = ticketRepository.findAll
	}

	override getAll() {
		if (tickets === null) {
			reload
		}
		tickets
	}

	override findById(Long id) {
		tickets.stream.filter([e|e.id === id]).findFirst
	}

	override create(Ticket input) {
		var ticket = ticketRepository.save(input)
		tickets+=ticket
		ticket
	}

	override remove(Ticket input) {
		tickets-=input
		ticketRepository.delete = input
	}

	def findTicketsByNameAndId(String name, long id) {
		ticketRepository.find(id, name)
	}

	def findByName(String name) {
		tickets.stream.filter[e|e.name==name].collect(Collectors.toList)
	}
}
