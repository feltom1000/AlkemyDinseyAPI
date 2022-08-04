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

import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.service.MovieService;

@RestController
@RequestMapping("movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping
    public ResponseEntity<List<MovieDTO>> getAll(){
        List<MovieDTO> movies = service.getAll();
        return ResponseEntity.ok().body(movies);
    }
	
	@PostMapping
	public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
		MovieDTO movieSaved = service.save(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
	}
	
}
