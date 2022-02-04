package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table
public class Artwork implements Serializable {
	
@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;	
@Column(name="name")
private String name;
@Column(name="author")
private String author;
@Column(name="prize")
private double prize;
@Column(name = "date", columnDefinition="DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date entryDate;
//@ManyToOne(targetEntity = Shop.class, fetch = FetchType.LAZY)
@ManyToOne()
@JoinColumn(name="shop_id")
//@NotNull(message = "\"shop\" with a {\"id\"} element is required")
private Shop shop;

public Artwork(){}

//necesario?
public Artwork(String name, String author, double prize, Date entryDate, Shop shop) {
	this.name      = name;
	this.author    = author;
	this.prize     = prize;
	this.entryDate = entryDate;
	this.shop      = shop;
}

public int getId() {
	return id;
}

public String getName() {
	return name;
}

public String getAuthor() {
	return author;
}

/*public double getPrize() {
	return prize;
}

public Date getEntryDate() {
	return entryDate;
}*/

public void setId(int id) {
	this.id = id;
}

public void setName(String name) {
	this.name = name;
}

public void setAuthor(String author) {
	this.author = author;
}

/*public void setPrize(double prize) {
	this.prize = prize;
}*/

public void setEntryDate(Date entryDate) {
	this.entryDate = entryDate;
}

public Shop getShop() {
	return shop;
}

public int getShopId() {
	return shop.getId();
}

public void setShop(Shop shop) {
	this.shop = shop;
}

@Override
public String toString() {
	return "Artwork [id=" + id + ", name=" + name + ", author=" + author + ", prize=" + prize + ", entryDate="
			+ entryDate + ", shop=" + shop + "]";
}



}
