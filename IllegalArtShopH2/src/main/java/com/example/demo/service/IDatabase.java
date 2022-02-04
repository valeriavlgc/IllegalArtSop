package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;

public interface IDatabase {
	
	Shop findShopById(int shop_id);
	List<Shop> listShops(); 
    Shop saveShop(Shop shop); 
    void deleteArt(int shop_id);
    List<Artwork> showArt(int shop_id);
}