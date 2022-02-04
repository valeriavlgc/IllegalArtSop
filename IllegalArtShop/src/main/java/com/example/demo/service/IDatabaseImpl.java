package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.Database;

@Service
public class IDatabaseImpl implements IDatabase {

@Autowired 
Database db;
@Autowired 
IDatabaseArt db1;

@Override
public List<Shop> listShops() {
	return db.findAll();
}

@Override
public Shop saveShop(Shop shop) {
	return db.save(shop);
}

//Borrar
@Override
public void deleteArt(Shop shop) {
int shopId = shop.getId();
System.out.println(shopId);
db1.deleteArtByShop(shopId);
	
}


	
}
