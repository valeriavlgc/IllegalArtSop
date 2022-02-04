package com.example.demo.controller.exceptions;

public class ArtworkNotFoundException extends RuntimeException {

	public ArtworkNotFoundException(String message) {
	     super(message);
	}
}
