package de.dc.spring.fx.dms.thymeleaf.ui.service;

import de.dc.spring.fx.dms.shared.model.Ticket;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("all")
public class TicketDtoService {
  public static String PORT = "8090";
  
  public static String TICKETS = "tickets";
  
  public static String REQUEST = String.format("http://localhost:%s/%s", TicketDtoService.PORT, TicketDtoService.TICKETS);
  
  @Autowired
  private RestTemplate restTemplate;
  
  public Ticket[] getTickets() {
    Ticket[] _xblockexpression = null;
    {
      final Ticket[] result = this.restTemplate.<Ticket[]>getForObject(TicketDtoService.REQUEST, Ticket[].class);
      final Consumer<Ticket> _function = (Ticket e) -> {
        InputOutput.<Ticket>println(e);
      };
      ((List<Ticket>)Conversions.doWrapArray(result)).forEach(_function);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public Ticket getTicketById(final long id) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(TicketDtoService.REQUEST);
    _builder.append("/");
    _builder.append(id);
    return this.restTemplate.<Ticket>getForObject(_builder.toString(), Ticket.class);
  }
  
  public void deleteTicketById(final long id) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(TicketDtoService.REQUEST);
    _builder.append("/");
    _builder.append(id);
    this.restTemplate.delete(_builder.toString());
  }
  
  public void update(final Ticket ticket) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<Ticket> requestBody = new HttpEntity<Ticket>(ticket, headers);
    Long _id = ticket.getId();
    String _plus = ((TicketDtoService.REQUEST + "/") + _id);
    this.restTemplate.put(_plus, requestBody, ((Object[]) ((Object[])Conversions.unwrapArray(Collections.<Object>unmodifiableList(CollectionLiterals.<Object>newArrayList()), Object.class))));
  }
  
  public Ticket create(final Ticket ticket) {
    Ticket _xblockexpression = null;
    {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
      headers.setContentType(MediaType.APPLICATION_JSON);
      final HttpEntity<Ticket> requestBody = new HttpEntity<Ticket>(ticket, headers);
      final ResponseEntity<Ticket> result = this.restTemplate.<Ticket>postForEntity(TicketDtoService.REQUEST, requestBody, Ticket.class);
      _xblockexpression = result.getBody();
    }
    return _xblockexpression;
  }
}
