package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.controller.exceptions.ArtworkNotFoundException;
import com.example.demo.controller.exceptions.GlobalException;
import com.example.demo.controller.exceptions.ShopNotFoundException;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.DatabaseArt;
import com.example.demo.service.IDatabase;
import com.example.demo.service.IDatabaseArt;
import com.example.demo.service.IDatabaseImpl;

//no funciona controller, restcontroller si.

@Controller
@RequestMapping("/shops")
public class ShopController {

//por qué funciona con la interfaz donde no están desarrollados los métodos? por que busca donde se injecta con @Service??	
@Autowired	
IDatabaseImpl db;
@Autowired
IDatabaseArt db1;

GlobalException globalException = new GlobalException();

//new ResponseEntity<List<String>(strings, HttpStatus.FOUND)

//POST. Creación tienda
@PostMapping("/create/{name}/{stock}")
public ResponseEntity<Shop> createShop(@PathVariable(name = "name") String name, @PathVariable(name = "stock") int stock) {
	Shop shop = new Shop(name, stock);
	db.saveShop(shop);
	return ResponseEntity.ok(shop);
}

//Segunda opción para crear tienda con un request.
	/*@PostMapping
	public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
		Shop shop1 = db.saveShop(shop);
		return ResponseEntity.ok(shop1);
	}*/

//GET. Lista de tiendas
@GetMapping("/list")
public ResponseEntity<?> listShops() {
	
	try {
		if(db.listShops().isEmpty()) {
		  throw new ShopNotFoundException("Error 01: No hay tiendas en la base de datos.");
		} 
	} catch(ShopNotFoundException e) {
		  return globalException.handleShopNotFoundException(e);
	  }
	
	return ResponseEntity.ok(db.listShops());			
}

//POST. Añadir cuadro a una tienda.
@PostMapping("/añadirCuadro/{shop_id}/{name}/{author}/{prize}")
public ResponseEntity<Artwork> createArtwork(@PathVariable(name = "name") String name, @PathVariable(name = "author") String author, @PathVariable(name = "prize") double prize, @PathVariable(name = "shop_id") int shop_id) {
Shop shop = db.findShopById(shop_id).get();
Date date = new Date();

Artwork art = new Artwork(name, author, prize, date, shop);
db1.save(art);

	return ResponseEntity.ok(art);
}

//DELETE. Borrar arte de una tienda
@DeleteMapping("/delete/{shop_id}")
public ResponseEntity DeleteArt(@PathVariable(name = "shop_id") int shop_id) {

	try {
		if (!db.findShopById(shop_id).isPresent()) {
			throw new ShopNotFoundException("Error 02: La tienda seleccionada no existe.");
		} else {

			try {
			if(db.showArt(shop_id).isEmpty()) {
				throw new ArtworkNotFoundException("No hay obras en la tienda seleccionada.");
			}
			db.deleteArt(shop_id);
			return ResponseEntity.ok("Galería borrada con éxito." + db.showArt(shop_id));
			} catch (ArtworkNotFoundException e) {
				return globalException.handleArtworkNotFoundException(e);
			}
		}
		
	 } catch(ShopNotFoundException e) {
	     return globalException.handleShopNotFoundException(e);
     }
	  
}


//GET. Filtra arte por tienda
@GetMapping("/listArt/{shop_id}")
public ResponseEntity<?> listArt(@PathVariable(name = "shop_id") int shop_id) {
	try {
		if(db.showArt(shop_id).isEmpty()) {
		  throw new ArtworkNotFoundException("No hay obras en la tienda seleccionada.");
		}
	} catch(ArtworkNotFoundException e) {
  		  return globalException.handleArtworkNotFoundException(e);
	  }

	return new ResponseEntity<List<Artwork>>(db.showArt(shop_id), HttpStatus.FOUND);
}


}
