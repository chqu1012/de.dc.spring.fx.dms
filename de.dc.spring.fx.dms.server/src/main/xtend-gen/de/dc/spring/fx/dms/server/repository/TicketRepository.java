package de.dc.spring.fx.dms.server.repository;

import de.dc.spring.fx.dms.shared.model.Ticket;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public interface TicketRepository extends JpaRepository<Ticket, Long> {
  public abstract List<Ticket> findByName(final String name);
  
  @Query("SELECT p FROM Ticket p WHERE p.name = name AND p.id = id")
  public abstract List<Ticket> find(@Param("id") final long id, @Param("name") final String name);
}
