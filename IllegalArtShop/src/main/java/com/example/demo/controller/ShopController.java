package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.DatabaseArt;
import com.example.demo.service.IDatabase;

//no funciona controller, restcontroller si.
@Controller
@RequestMapping("/shops")
public class ShopController {

@Autowired	
IDatabase db;
Model model;
List<Shop> shops;

//GET formulario para la creación de tienda
@GetMapping("/create")
public String createShop() {
	return "formularioShop";
}
//POST guardar con la información del formulario.
@PostMapping("")
//ResponseEntity<Shop>
public ResponseEntity<Shop> process(Shop shop) {
	db.saveShop(shop);	
	//return shop.showArt();
	return ResponseEntity.ok(shop);	
}

//¿recibir formulario para que quede más bonito?
//GET lista de las tiendas
@GetMapping("/listShops")
public ResponseEntity<List<Shop>> listShops() {
	return ResponseEntity.ok(db.listShops());	
}

//not working. repositorio en entity is null.
@GetMapping("/deleteArtwork")
public String DeleteArt(Model model) {
	shops =  db.listShops();
	model.addAttribute("shops", shops);
	return "formularioDelete";	
}

@PostMapping("/")
public ResponseEntity<String> process2(@ModelAttribute("shops") List<Shop> shops, Model model) {
Shop shop = shops.get(0);
String response = "Todas las obras han sido eliminadas " + shop.toString();
	
    db.deleteArt(shop);	 
	return ResponseEntity.ok(response);	
}

}
