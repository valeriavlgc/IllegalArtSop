package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Artwork;

public interface IDatabaseArt {

	public List<Artwork> getArtByShopId(int shopId); 
	public void deleteArtByShop (int shopId);
	
	
}
