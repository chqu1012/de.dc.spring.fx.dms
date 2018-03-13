package de.dc.spring.fx.dms.shared.model;

import java.time.LocalDate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public interface ICategory {

	Long getId();

	void setId(Long id);

	String getName();

	void setName(String name);

	LocalDate getCreatedOn();

	void setCreatedOn(LocalDate createdOn);

}
