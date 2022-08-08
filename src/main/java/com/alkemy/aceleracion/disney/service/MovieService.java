package com.alkemy.aceleracion.disney.service;

import java.util.List;

import com.alkemy.aceleracion.disney.dto.MovieDTO;

public interface MovieService {
	List<MovieDTO> getAll();
    List<MovieDTO> getByFilters(String name, List<Long> genresId, String order);
    MovieDTO save(MovieDTO movie);
    MovieDTO edit(MovieDTO movie);
    void delete(Long movieId);
}
