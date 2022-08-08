package com.alkemy.aceleracion.disney.controller;

import java.util.List;

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

import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.dto.MovieBasicDTO;
import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.service.MovieService;

@RestController
@RequestMapping("movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping("/details")
    public ResponseEntity<List<MovieDTO>> getAll(){
        List<MovieDTO> movies = service.getAll();
        return ResponseEntity.ok().body(movies);
    }
	
	@GetMapping
	public ResponseEntity<List<MovieBasicDTO>> getAllBasicData(){
		List<MovieBasicDTO> movies = service.getAllBasicData();
		return ResponseEntity.ok().body(movies);
	}
	
	@GetMapping("/filters")
	public ResponseEntity<List<MovieDTO>> getDetailsByFilter(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) List<Long> genreId,
			@RequestParam(required = false, defaultValue = "ASC") String order
			){
		List<MovieDTO> movies = service.getByFilters(name, genreId, order);
		return ResponseEntity.ok(movies);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto){
		MovieDTO resultDTO = service.update(id, dto);
		return ResponseEntity.ok().body(resultDTO);
	}
	
	@PostMapping
	public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
		MovieDTO movieSaved = service.save(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
