package de.dc.spring.fx.dms.service;

import java.util.List;
import java.util.Optional;

import de.dc.spring.fx.dms.model.Ticket;

public interface IService<T> {

	List<T> getAll();

	Optional<Ticket> findById(Long id);

	T create(T input);
	
	void remove(T input);
}
