package com.alkemy.aceleracion.disney.service;

import java.util.List;

import com.alkemy.aceleracion.disney.dto.MovieDTO;

public interface MovieService {
	List<MovieDTO> getAll();
    List<MovieDTO> getByGenre(Long genreId);
    MovieDTO getByTitle(String title);
    MovieDTO save(MovieDTO movie);
    MovieDTO edit(MovieDTO movie);
    void delete(Long movieId);
}
