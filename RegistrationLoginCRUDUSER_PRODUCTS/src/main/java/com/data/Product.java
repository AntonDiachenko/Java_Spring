package com.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	private Long id;
	private String name;
	private String specs;
	
	protected Product()
	{
		
	}
	
	protected Product(Long id, String name, String specs)
	{
		super();
		this.id=id;
		this.name=name;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSpecs() {
		return specs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
}
