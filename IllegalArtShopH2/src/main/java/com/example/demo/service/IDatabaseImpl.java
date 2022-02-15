package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.Database;
import com.example.demo.repository.DatabaseArt;

@Service
@Component
public class IDatabaseImpl implements IDatabase {

@Autowired 
Database db;
@Autowired
IDatabaseArtImpl db1;

@Override
public List<Shop> listShops() {
	return db.findAll();
}

@Override
public Shop saveShop(Shop shop) {
	return db.save(shop);
}

@Override
public void deleteArt(int shop_id) {
    db1.deleteArtByShop(shop_id);
}

public List<Artwork> showArt(int shop_id) {
	List<Artwork> artwork = db1.findAll();
	
	return artwork.stream()
			      .filter(a -> a.getShopId() == shop_id)
			      .collect(Collectors.toList());
	
}

@Override
public Optional<Shop> findShopById(int shop_id) {
	return db.findById(shop_id);
}
	
}
