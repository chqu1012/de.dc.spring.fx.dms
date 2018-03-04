package de.dc.spring.fx.dms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.repository.TicketRepository;

@Service
public class TicketService implements IService<Ticket>{

	@Autowired TicketRepository ticketRepository;
	
	private List<Ticket> tickets;
	
	public List<Ticket> reload(){
		tickets = ticketRepository.findAll();
		return tickets;
	}
	
	@Override
	public List<Ticket> getAll() {
		if (tickets==null) {
			reload();
		}
		return tickets;
	}

	@Override
	public Optional<Ticket> findById(Long id) {
		return tickets.stream().filter(e->e.getId()==id).findFirst();
	}

	@Override
	public Ticket create(Ticket input) {
		Ticket ticket = ticketRepository.save(input);
		tickets.add(ticket);
		return ticket;
	}

	@Override
	public void remove(Ticket input) {
		tickets.remove(input);
		ticketRepository.delete(input);
	}

	public List<Ticket> findTicketsByNameAndId(String name, long id) {
		return ticketRepository.find(id, name);
	}
	
	public List<Ticket> findByName(String name) {
		return tickets.stream().filter(e->e.getName().equals(name)).collect(Collectors.toList());
	}
}
