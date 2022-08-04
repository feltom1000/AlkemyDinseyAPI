package com.alkemy.aceleracion.disney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.service.CharacterService;

@RestController
@RequestMapping("characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	
	@GetMapping
	public ResponseEntity<List<CharacterDTO>> getAll(){
		List<CharacterDTO> characters = service.getAll();
		return ResponseEntity.ok().body(characters);
	}
	
	@PostMapping
	public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
		CharacterDTO characterSaved = service.save(character);
		return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
	}

}
