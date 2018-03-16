package de.dc.spring.fx.dms.server.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

interface IRestController<T> {
	
	List<T> getAll();
	
	T findById(@PathVariable long id);
	
	void deleteById(@PathVariable long id);
	
	ResponseEntity<Object> update(@RequestBody T t, @PathVariable long id);
	
	ResponseEntity<Object> create(@RequestBody T t);
}
