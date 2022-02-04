package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Artwork;

public interface IDatabaseArt {

	public List<Artwork> getArtByShop(int shopId); 
	public void deleteArtByShop (int shopId);
	public List<Artwork> findAll();
	public Artwork save(Artwork artwork);
	
}
