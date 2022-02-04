package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Artwork;

public interface IDatabaseArt {
    //Lo utilizaré?
	public List<Artwork> getArtByShop(int shopId); 
	public void deleteArtByShop (int shopId);
	
}
