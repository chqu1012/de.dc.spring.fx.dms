package de.dc.spring.fx.dms.server.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.dc.spring.fx.dms.shared.model.ICategory;

@Entity
public class Category implements ICategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID", nullable = false, columnDefinition = "BIGINT")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate createdOn;

	public Category() {
	}

	public Category(String name, LocalDate createdOn) {
		this.name = name;
		this.createdOn = createdOn;
	}

	@Override
	public Long getId() {
		return id;
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
	public LocalDate getCreatedOn() {
		return createdOn;
	}

	@Override
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return name;
	}
}
