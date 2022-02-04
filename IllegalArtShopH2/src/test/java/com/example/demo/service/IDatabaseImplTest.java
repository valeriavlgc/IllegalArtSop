package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.Database;

@SpringBootTest(classes = {IDatabaseImplTest.class})
public class IDatabaseImplTest {

@Mock 
Database db;

@Mock
IDatabaseArtImpl db1;

@InjectMocks 
IDatabaseImpl IDatabaseService;

List<Shop> shops;
List<Artwork> art;
Shop shop;


@Test
public void test_listShops() {

shops = new ArrayList<Shop>(); 

shops.add(new Shop(1,"name1", 234, art));
shops.add(new Shop(2,"name2", 235, art));
shops.add(new Shop(3,"name3", 236, art));	
	
	when(db.findAll()).thenReturn(shops);
	assertEquals(3, IDatabaseService.listShops().size());

}

@Test
public void saveShop() {

shop = new Shop(1,"name1", 234, art);	

	when(db.save(shop)).thenReturn(shop);
	assertEquals(shop ,IDatabaseService.saveShop(shop));
		
}

@Test
public void test_deleteArt() {
int shopId = 1;

    IDatabaseService.deleteArt(shopId);
	verify(db1, times(1)).deleteArtByShop(shopId);
	
}

@Test
public void test_showArt() {
int shopId = 1;
art = new ArrayList<Artwork>(); 
Date date = new Date();
shop = new Shop(1,"name1", 234, art);

art.add(new Artwork(1,"name1", "nameAuthor", 100000, date, shop));

	when(db1.findAll()).thenReturn(art);
	assertEquals(art, IDatabaseService.showArt(shopId));

}

@Test
public void test_findShopById() {
int shopId = 1;
shops = new ArrayList<Shop>(); 
shop = new Shop(1,"name1", 234, art);

shops.add(shop);
shops.add(new Shop(2,"name2", 235, art));
shops.add(new Shop(3,"name3", 236, art));	

	when(db.findById(shopId)).thenReturn(Optional.of(shop));
	assertEquals(1, IDatabaseService.findShopById(shopId).getId());

}
	
}
