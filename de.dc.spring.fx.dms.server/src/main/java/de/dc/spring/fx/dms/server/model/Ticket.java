package de.dc.spring.fx.dms.server.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.dc.spring.fx.dms.shared.model.ITicket;

@Entity
public class Ticket implements ITicket{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID", nullable = false, columnDefinition = "BIGINT")
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int categoryId;
	
	@Column(nullable = false)
	private int userId;

	@Column(nullable = false)
	private LocalDateTime createdOn;

	@Column(nullable = true)
	private LocalDateTime updatedOn;

	public Ticket() {
	}
	
	public Ticket(String name, String description, int categoryId, int userId, LocalDateTime createdOn) {
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.userId = userId;
		this.createdOn = createdOn;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getCategoryId() {
		return categoryId;
	}

	@Override
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int getUserId() {
		return userId;
	}

	@Override
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	@Override
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	@Override
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	@Override
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public Long getId() {
		return id;
	}
	
//	@Override
//	public String toString() {
//		return "DMS-"+DMSDetailController.format.format(id)+": "+name;
//	}
}
