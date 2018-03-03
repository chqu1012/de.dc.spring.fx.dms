package de.dc.spring.fx.dms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.repository.TicketRepository;

@Service
public class TicketService implements IService<Ticket>{

	@Autowired TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Optional<Ticket> findById(Long id) {
		return ticketRepository.findById(id);
	}

	@Override
	public Ticket create(Ticket input) {
		return ticketRepository.save(input);
	}

	@Override
	public void remove(Ticket input) {
		ticketRepository.delete(input);
	}

	public String[] getAutocompletion(){
		return ticketRepository.findAll().stream().map(e->e.getName()).toArray(String[]::new);
	}
}
