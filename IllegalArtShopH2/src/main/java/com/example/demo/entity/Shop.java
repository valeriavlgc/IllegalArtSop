 package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class Shop implements Serializable {
	
@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column
private String name;
@Column(name="Stock")
private int stock;
@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
//@Column(name="Repository")
//add new ArrayList?
private List<Artwork> artRep;

public Shop() {}

public Shop(int id, String name, int stock, List<Artwork> artRep){
	this.id     = id;
	this.name   = name;
	this.stock  = stock;
	this.artRep = artRep;
}

public Shop(String name, int stock) {
	this.name  = name;
	this.stock = stock;
}

public int getId() {
	return id;
}
public String getName() {
	return name;
}
public int getStock() {
	return stock;
}
public void setId(int id) {
	this.id = id;
}
public void setName(String name) {
	this.name = name;
}
public void setStock(int stock) {
	this.stock = stock;
}
public void setArt(List<Artwork> artwork) {
	this.artRep = artwork;
}
public String showArt() {
String list = null;	

	for (Artwork a : artRep) {
	   list += a.toString();
	}
	
	return list;
}
public void deleteArt() {
	artRep.clear();
}

@Override
public String toString() {
	return "Shop [id=" + id + ", name=" + name + ", stockMax=" + stock + "]";
}



}
