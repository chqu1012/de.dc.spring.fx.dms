package de.dc.spring.fx.dms.service;

import de.dc.spring.fx.dms.model.Ticket;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public interface IService<T extends Object> {
  public abstract List<T> getAll();
  
  public abstract Optional<Ticket> findById(final Long id);
  
  public abstract T create(final T input);
  
  public abstract void remove(final T input);
}
