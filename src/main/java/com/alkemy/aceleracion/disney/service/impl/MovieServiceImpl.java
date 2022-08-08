package com.alkemy.aceleracion.disney.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.dto.MovieFiltersDTO;
import com.alkemy.aceleracion.disney.entity.PeliculaEntity;
import com.alkemy.aceleracion.disney.mapper.MovieMapper;
import com.alkemy.aceleracion.disney.repository.MovieRepository;
import com.alkemy.aceleracion.disney.repository.specification.MovieSpecification;
import com.alkemy.aceleracion.disney.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieSpecification specification;
	@Autowired
	private MovieMapper mapper;
	@Autowired
	private MovieRepository repository;

	@Override
	public List<MovieDTO> getAll() {
		List<PeliculaEntity> entities = repository.findAll();
		List<MovieDTO> result = mapper.toMovieDTOList(entities, false);
		return result;
	}

	@Override
	public MovieDTO save(MovieDTO movie) {
		PeliculaEntity entity = mapper.toPeliculaEntity(movie);
		PeliculaEntity entitySaved = repository.save(entity);
		MovieDTO result = mapper.toMovieDTO(entitySaved, false);
		return result;
	}

	@Override
	public MovieDTO edit(MovieDTO movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long movieId) {
		repository.deleteById(movieId);
	}

	@Override
	public List<MovieDTO> getByFilters(String name, List<Long> genresId, String order) {
		MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genresId, order);
		List<PeliculaEntity> entities = repository.findAll(specification.getByFilters(filtersDTO));
		List<MovieDTO> dtos = mapper.toMovieDTOList(entities, true);
		return null;
	}

}
