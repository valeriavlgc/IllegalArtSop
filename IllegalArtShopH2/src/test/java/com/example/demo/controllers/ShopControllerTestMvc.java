package com.example.demo.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.demo.controller.ShopController;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.service.IDatabaseArt;
import com.example.demo.service.IDatabaseImpl;


//Añadir algún test de fallos y ResquestBody para practicar ObjMapper.

@ComponentScan(basePackages = "com.example.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShopControllerTestMvc.class})
public class ShopControllerTestMvc {

@Autowired	
MockMvc mockMvc;	

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


@BeforeEach
void setUp() {
	 mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();
}

@Test
@DisplayName("test_createShop")
public void test_createShop() throws Exception {
int stock   = 123;
String name = "nameShop";
shop = new Shop(name, stock);

	when(db.saveShop(shop)).thenReturn(shop);
	this.mockMvc.perform(post("/shops/create/{name}/{stock}", name, stock))
	            .andExpect(status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath(".name").value("nameShop"))
	            .andExpect(MockMvcResultMatchers.jsonPath(".stock").value(123))
	            .andDo(print());

}

@Test
public void test_listShops() throws Exception {
shops = new ArrayList<Shop>(); 

shops.add(new Shop(1,"name1", 234, art));
shops.add(new Shop(2,"name2", 235, art));
shops.add(new Shop(3,"name3", 236, art)); 	

   when(db.listShops()).thenReturn(shops);
   this.mockMvc.perform(get("/shops/list"))
			   .andExpect(status().isOk())
			   .andDo(print());
}

@Test
public void test_createArtwork () throws Exception {
int shop_id = 1;
String name = "name"; String author = "author";
double price = 78000;
Date date = new Date();
shop = new Shop(1,"name1", 234, art);
artwork = new Artwork(name, author, price, date, shop);	

	 when(db.findShopById(shop_id)).thenReturn(Optional.of(shop));
     when(db1.save(artwork)).thenReturn(artwork);
	 
	 this.mockMvc.perform(post("/shops/añadirCuadro/{shop_id}/{name}/{author}/{prize}", shop_id, name, author, price))
	             .andExpect(status().isOk())
	             .andDo(print());

}

@Test
public void test_deleteArt() throws Exception {
int shop_id = 1;

	this.mockMvc.perform(delete("/shops/delete/{shop_id}", shop_id))
				.andExpect(status().isOk())
				.andDo(print());

}

@Test
public void test_listArt() throws Exception {
int shop_id = 1;
art = new ArrayList<Artwork>();
shop = new Shop("shopName", 245);
shop.setId(shop_id);
Date date = new Date();
	
art.add(new Artwork(1, "name1", "author1", 70000, date, shop));
	

	when(db.showArt(shop_id)).thenReturn(art);
	
	this.mockMvc.perform(get("/shops/listArt/{shop_id}", shop_id))
			    .andExpect(status().isFound())
			    .andDo(print());
}

}
