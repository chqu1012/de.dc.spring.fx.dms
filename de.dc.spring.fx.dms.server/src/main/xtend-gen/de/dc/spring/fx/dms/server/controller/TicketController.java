package de.dc.spring.fx.dms.server.controller;

import de.dc.spring.fx.dms.server.controller.IRestController;
import de.dc.spring.fx.dms.shared.model.Ticket;
import de.dc.spring.fx.dms.shared.repository.TicketRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@SuppressWarnings("all")
public class TicketController implements IRestController<Ticket> {
  @Autowired
  private TicketRepository ticketRepository;
  
  @GetMapping("/tickets")
  @Override
  public List<Ticket> getAll() {
    return this.ticketRepository.findAll();
  }
  
  @GetMapping("/tickets/{id}")
  @Override
  public Ticket findById(@PathVariable final long id) {
    return this.ticketRepository.findById(Long.valueOf(id)).get();
  }
  
  @DeleteMapping("/tickets/{id}")
  @Override
  public void deleteById(@PathVariable final long id) {
    this.ticketRepository.deleteById(Long.valueOf(id));
  }
  
  @PutMapping("/tickets/{id}")
  @Override
  public ResponseEntity<Object> update(@RequestBody final Ticket ticket, @PathVariable final long id) {
    ResponseEntity<Object> _xblockexpression = null;
    {
      Optional<Ticket> ticketOptional = this.ticketRepository.findById(Long.valueOf(id));
      boolean _isPresent = ticketOptional.isPresent();
      boolean _not = (!_isPresent);
      if (_not) {
        return ResponseEntity.notFound().<Object>build();
      }
      ticket.setId(Long.valueOf(id));
      this.ticketRepository.<Ticket>save(ticket);
      _xblockexpression = ResponseEntity.noContent().<Object>build();
    }
    return _xblockexpression;
  }
  
  @PostMapping("/tickets")
  @Override
  public ResponseEntity<Object> create(@RequestBody final Ticket ticket) {
    Ticket saveTicket = this.ticketRepository.<Ticket>save(ticket);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
      saveTicket.getId()).toUri();
    return ResponseEntity.created(location).<Object>body(saveTicket);
  }
}
