package com.alkemy.aceleracion.disney.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.aceleracion.disney.dto.CharacterBasicDTO;
import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.service.CharacterService;

@RestController
@RequestMapping("characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	
	@GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<Long> movies) {

        List<CharacterBasicDTO> characters = service.getByFilters(name, age, movies);
        return ResponseEntity.ok(characters);
    }
	
	@PostMapping
	public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO character) {
		CharacterDTO characterSaved = service.save(character);
		return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @Valid @RequestBody CharacterDTO dto) {
        CharacterDTO resultDTO = service.update(id, dto);
        return ResponseEntity.ok().body(resultDTO);
    }

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	@GetMapping("/{id}/details")
	public ResponseEntity<CharacterDTO> getDetails(@PathVariable Long id){
		CharacterDTO character = service.getDetails(id);
		return ResponseEntity.ok().body(character);
	}

}
