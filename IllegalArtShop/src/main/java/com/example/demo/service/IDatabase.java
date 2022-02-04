package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Shop;

public interface IDatabase {
	
	List<Shop> listShops(); 
    Shop saveShop(Shop shop); 
    void deleteArt(Shop shop);
}