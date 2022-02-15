package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.DatabaseArt;

@SpringBootTest(classes = {IDatabaseArtImplTest.class})
public class IDatabaseArtImplTest {
	
@Mock
DatabaseArt db;

@InjectMocks
IDatabaseArtImpl IDatabaseArtService;

List<Artwork> artwork;
Artwork art;
Shop shop;


@Test
public void test_getArtByShop() {
int shopId = 1;
shop = new Shop(1,"Fancyname",435, artwork);
Date date = new Date();
artwork = new ArrayList<Artwork>(); 
art = new Artwork(1,"name1", "nameAuthor1", 100000, date, shop);
artwork.add(art);


	when(db.findAll()).thenReturn(artwork);
	assertEquals(artwork, IDatabaseArtService.getArtByShop(shopId));
}

@Test
public void test_deleteArtByShop() {
Date date = new Date();
int shopId = 1;
shop = new Shop(1,"Fancyname",435, artwork);
art = new Artwork(1,"name1", "nameAuthor1", 100000, date, shop);
artwork = new ArrayList<Artwork>(); 
artwork.add(art);
    
    when(db.findAll()).thenReturn(artwork);
    IDatabaseArtService.deleteArtByShop(shopId);
	verify(db, times(1)).delete(art);
	
}

@Test
public void test_findAll() {
Date date = new Date();
artwork = new ArrayList<Artwork>(); 

artwork.add(new Artwork(1,"name1", "nameAuthor1", 100000, date, shop));
artwork.add(new Artwork(2,"name2", "nameAuthor2", 200000, date, shop));
		
		when(db.findAll()).thenReturn(artwork);
		assertEquals(2, IDatabaseArtService.findAll().size());
}

@Test
public void test_save() {
Date date = new Date();
art = new Artwork(1,"name1", "nameAuthor", 100000, date, shop);


	when(db.save(art)).thenReturn(art);
	assertEquals(art, IDatabaseArtService.save(art));

	
}

}
