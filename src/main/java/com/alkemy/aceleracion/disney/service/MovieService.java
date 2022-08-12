package com.alkemy.aceleracion.disney.service;

import java.util.List;

import com.alkemy.aceleracion.disney.dto.MovieDTO;

public interface MovieService {
	MovieDTO getDetails(Long id);
    List<MovieDTO> getByFilters(String name, List<Long> genresId, String order);
    MovieDTO save(MovieDTO movie);
    MovieDTO update(Long id, MovieDTO dto);
    void delete(Long movieId);

}
