package com.data.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

 	@Entity
	public class Product {
 		@Id
 		@GeneratedValue(strategy = GenerationType.IDENTITY)
 		private Long id;
 		
 		@NotNull
 		@Size(min = 5, max = 20)
 		private String name;
 		
 		@NotNull
 		@Size(max = 10)
 		private String brand;
 		
 		@NotNull
 		@Size(min = 5)
 		private String madein;
 		
 		@NotNull
 		@Min(100)
 		@Max(100000)
 		private float price;
 		
 		@NotNull
 		@Max(10)
 		private int year;
 		
 		@NotNull
 		@Size(min = 10)
 		private String note;
	
	protected Product() {
	} 
	
	protected Product(Long id, String name, String brand, String madein, float price, int year, String note) {
		super();
		this.id= id;
		this.name= name;
		this.brand= brand;
		this.madein= madein;
		this.price= price;
		this.year= year;
		this.note= note;
	}
	

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name; 
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getMadein() {
		return madein;
	}
	
	public float getPrice() {
		return price;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setId(Long id) {
		this.id= id;
	}
	
	public void setName(String name) {
		this.name= name;
	}
	
	public void setBrand(String brand) {
		this.brand= brand;
	}
	
	public void setMadein(String madein) {
		this.madein= madein;
	}
	
	public void setPrice(float price) {
		this.price= price;
	} 
	
	public void setYear(int year) {
		this.year= year;
	}
	
	public void setNote(String note) {
		this.note= note;
	}
 }

