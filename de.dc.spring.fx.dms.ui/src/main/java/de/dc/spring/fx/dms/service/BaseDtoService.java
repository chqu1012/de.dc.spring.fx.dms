package de.dc.spring.fx.dms.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;

public abstract class BaseDtoService<T> {

	protected String PORT = "8090";

	abstract String resource();
	
	protected String getUrl() {
		return String.format("http://localhost:%s/%s", PORT, resource());
	}
	
	protected abstract List<T> getAll() throws ResourceAccessException;
	protected abstract T findById(long id) throws ResourceAccessException;
	protected abstract void deleteById(long id) throws ResourceAccessException;
	protected abstract void update(T t) throws ResourceAccessException;
	protected abstract void create(T t) throws ResourceAccessException;
	
	protected HttpEntity<T> createHttpEntity(T t) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<T> requestBody = new HttpEntity<T>(t, headers);
		return requestBody;
	}
}
