package de.dc.spring.fx.dms.shared.model;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public interface ITicket {

	Long getId();

	void setId(Long id);

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	int getCategoryId();

	void setCategoryId(int categoryId);

	int getUserId();

	void setUserId(int userId);

	LocalDateTime getCreatedOn();

	void setCreatedOn(LocalDateTime createdOn);

	LocalDateTime getUpdatedOn();

	void setUpdatedOn(LocalDateTime updatedOn);
}
