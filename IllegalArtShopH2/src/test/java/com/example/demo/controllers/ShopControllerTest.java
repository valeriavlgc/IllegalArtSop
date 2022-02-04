package com.example.demo.controllers;
//Por último, hemos declarado un ExceptionHandler que se encargará de convertir 
//cualquier excepción controlada SpringMockMvcException en un ResponseEntity.	
//https://blog.marcnuri.com/mockmvc-introduccion-a-spring-mvc-testing	

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.controller.ShopController;
import com.example.demo.controller.exceptions.ArtworkNotFoundException;
import com.example.demo.controller.exceptions.ShopNotFoundException;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.Database;
import com.example.demo.service.IDatabaseArt;
import com.example.demo.service.IDatabaseImpl;

@SpringBootTest(classes = {ShopControllerTest.class})
//no funciona sin esta anotación. 
@RunWith(SpringRunner.class)
public class ShopControllerTest {
	
@Mock
IDatabaseImpl db;

@Mock
IDatabaseArt db1;	

@InjectMocks
ShopController shopController;

Shop shop;
List<Shop> shops;
List<Artwork> art;
Artwork artwork;

//beforeEach.
/*@Before
void setUp() {
	 mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();
}*/

@Test
public void test_CreateShop() {
int stock   = 123;
String name = "nameShop";
shop = new Shop(name, stock);

	when(db.saveShop(shop)).thenReturn(shop);
	ResponseEntity<Shop> response = shopController.createShop(name, stock);
	assertEquals(HttpStatus.OK, response.getStatusCode());
}

@Test
public void test_listShops() {
shops = new ArrayList<Shop>(); 

shops.add(new Shop(1,"name1", 234, art));
shops.add(new Shop(2,"name2", 235, art));
shops.add(new Shop(3,"name3", 236, art)); 
	
   when(db.listShops()).thenReturn(shops);
   ResponseEntity<?> response = shopController.listShops();
   assertEquals(HttpStatus.OK, response.getStatusCode());
   assertEquals(3, ((List<Shop>) response.getBody()).size());
}


@Test
public void test_createArtwork() {
int shop_id = 1;
Date date = new Date();
String name = "name"; String author = "author";
double price = 78000;
shops = new ArrayList<Shop>(); 
art = new ArrayList<Artwork>();

shop = new Shop(1,"name1", 234, art);
shops.add(shop);
artwork = new Artwork(name, author, price, date, shop);
art.add(artwork);
	
	when(db.findShopById(shop_id)).thenReturn(shop);
	when(db1.save(artwork)).thenReturn(artwork);
	ResponseEntity<Artwork> response = shopController.createArtwork(name, author, price, shop_id);
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(shop_id, response.getBody().getShop().getId());
}  

@Test
public void testDeleteArt() {
int shop_id = 1;

	ResponseEntity response = shopController.DeleteArt(shop_id);
	verify(db, times(1)).deleteArt(shop_id);
	assertEquals(HttpStatus.OK, response.getStatusCode());
}

@Test
public void testListArt() {
int shop_id = 1;
art = new ArrayList<Artwork>();
shop = new Shop("shopName", 245);
Date date = new Date();

art.add(new Artwork("name1", "author1", 70000, date, shop));
art.add(new Artwork("name2", "autho2r", 60000, date, shop));

    when(db.showArt(shop_id)).thenReturn(art);
    ResponseEntity<?> response = shopController.listArt(shop_id);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(2, ((List<Shop>) response.getBody()).size());

}

}
