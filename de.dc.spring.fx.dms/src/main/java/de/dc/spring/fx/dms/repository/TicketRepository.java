package de.dc.spring.fx.dms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dc.spring.fx.dms.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	List<Ticket> findByName(String name);
}
