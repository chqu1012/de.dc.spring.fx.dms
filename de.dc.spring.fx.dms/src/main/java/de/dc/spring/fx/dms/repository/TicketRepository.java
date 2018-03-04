package de.dc.spring.fx.dms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.dc.spring.fx.dms.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByName(String name);

	@Query("SELECT p FROM Ticket p WHERE p.name = name AND p.id = id")
	List<Ticket> find(@Param("id") long id, @Param("name") String name);
}
