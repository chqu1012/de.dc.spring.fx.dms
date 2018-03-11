package de.dc.spring.fx.dms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.dc.spring.fx.dms.controller.DMSDetailController;

@Entity
public class Ticket {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	@Override
	public String toString() {
		return "DMS-"+DMSDetailController.format.format(id)+": "+name;
	}
}
