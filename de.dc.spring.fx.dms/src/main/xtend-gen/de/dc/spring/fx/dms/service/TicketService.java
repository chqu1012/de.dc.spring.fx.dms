package de.dc.spring.fx.dms.service;

import com.google.common.base.Objects;
import de.dc.spring.fx.dms.model.Ticket;
import de.dc.spring.fx.dms.repository.TicketRepository;
import de.dc.spring.fx.dms.service.IService;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class TicketService implements IService<Ticket> {
  @Autowired
  TicketRepository ticketRepository;
  
  private List<Ticket> tickets;
  
  public List<Ticket> reload() {
    return this.tickets = this.ticketRepository.findAll();
  }
  
  @Override
  public List<Ticket> getAll() {
    List<Ticket> _xblockexpression = null;
    {
      if ((this.tickets == null)) {
        this.reload();
      }
      _xblockexpression = this.tickets;
    }
    return _xblockexpression;
  }
  
  @Override
  public Optional<Ticket> findById(final Long id) {
    final Predicate<Ticket> _function = (Ticket e) -> {
      Long _id = e.getId();
      return (_id == id);
    };
    return this.tickets.stream().filter(_function).findFirst();
  }
  
  @Override
  public Ticket create(final Ticket input) {
    Ticket _xblockexpression = null;
    {
      Ticket ticket = this.ticketRepository.<Ticket>save(input);
      this.tickets.add(ticket);
      _xblockexpression = ticket;
    }
    return _xblockexpression;
  }
  
  @Override
  public void remove(final Ticket input) {
    this.tickets.remove(input);
    this.ticketRepository.delete(input);
  }
  
  public List<Ticket> findTicketsByNameAndId(final String name, final long id) {
    return this.ticketRepository.find(id, name);
  }
  
  public List<Ticket> findByName(final String name) {
    final Predicate<Ticket> _function = (Ticket e) -> {
      String _name = e.getName();
      return Objects.equal(_name, name);
    };
    return this.tickets.stream().filter(_function).collect(Collectors.<Ticket>toList());
  }
}
