package de.dc.spring.fx.dms.service

import java.util.List
import java.util.Optional
import de.dc.spring.fx.dms.model.Ticket

interface IService<T> {
	
	def List<T> getAll()

	def Optional<Ticket> findById(Long id)

	def T create(T input)

	def void remove(T input)

}
