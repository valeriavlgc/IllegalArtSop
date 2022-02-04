package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.Artwork;
import com.example.demo.entity.Shop;
import com.example.demo.repository.DatabaseArt;
import com.example.demo.service.IDatabase;

@Controller
@RequestMapping("/artwork")
public class ArtController {
@Autowired	
DatabaseArt db1;

@Autowired
IDatabase db2;
Model model;
List<Shop> shops;


@GetMapping("/create")
public String createArtwork(Model model) {
	shops =  db2.listShops();
	model.addAttribute("shops", shops);
	return "formularioArtwork";
}

@PostMapping("")
public ResponseEntity<Artwork> process1(Artwork art) {
	art.setEntryDate(new Date());
	db1.save(art);		
	return ResponseEntity.ok(art);	
}	

@GetMapping("/listArt")
public ResponseEntity<List<Artwork>> listArt() {
	return ResponseEntity.ok(db1.findAll());	
}

	
}
