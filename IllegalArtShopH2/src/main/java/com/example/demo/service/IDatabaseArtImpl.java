package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Artwork;
import com.example.demo.repository.DatabaseArt;

@Service
@Component
public class IDatabaseArtImpl implements IDatabaseArt {

@Autowired
DatabaseArt db;

@Override
public List<Artwork> getArtByShop(int shopId) {
List<Artwork> art = db.findAll();
List<Artwork> filteredArt = art.stream()
                               .filter(a -> a.getShopId()== shopId)
                               .collect(Collectors.toList());
	
    return filteredArt;
}

@Override
public void deleteArtByShop(int shopId) {
	
	for (Artwork a : db.findAll()) {
		if(a.getShopId() == shopId) {
			db.delete(a);
		}
	}

}

@Override
public List<Artwork> findAll() {
	return db.findAll();
}

@Override
public Artwork save(Artwork artwork) {
   return db.save(artwork);
}
}
