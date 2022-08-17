package com.alkemy.aceleracion.disney.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.aceleracion.disney.dto.GenreDTO;
import com.alkemy.aceleracion.disney.service.GenreService;

@RestController
@RequestMapping("genero")
public class GenreController {
	
	@Autowired 
	private GenreService service;
	
	@PostMapping
	public ResponseEntity<GenreDTO> save(@Valid @RequestBody GenreDTO genre) {
		GenreDTO genreSaved = service.save(genre);
		return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
	}

}
