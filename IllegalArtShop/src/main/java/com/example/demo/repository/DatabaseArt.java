package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Artwork;

@Repository
@Transactional
public interface DatabaseArt extends JpaRepository<Artwork, Integer> {

//public List<Artwork> findArtworkByShopId(int shopId);	
	
}