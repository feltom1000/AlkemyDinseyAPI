package com.alkemy.aceleracion.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.aceleracion.disney.dto.MovieBasicDTO;
import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.dto.MovieFiltersDTO;
import com.alkemy.aceleracion.disney.entity.PeliculaEntity;
import com.alkemy.aceleracion.disney.exception.ParamNotFound;
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
	public MovieDTO save(MovieDTO movie) {
		PeliculaEntity entity = mapper.toPeliculaEntity(movie);
		PeliculaEntity entitySaved = repository.save(entity);
		MovieDTO result = mapper.toMovieDTO(entitySaved, false);
		return result;
	}

	@Override
	public MovieDTO update(Long id, MovieDTO dto) {
		Optional<PeliculaEntity> entity = repository.findById(id);
		
		if (!entity.isPresent()) {
			throw new ParamNotFound("Invalid Movie Id.");
		}
		
		mapper.movieEntityRefreshValues(entity.get(), dto);
		PeliculaEntity entitySaved = repository.save(entity.get());
		MovieDTO result = mapper.toMovieDTO(entitySaved, true);
		return result;
	}
	
	@Override
	public void delete(Long movieId) {
		Optional<PeliculaEntity> entity = repository.findById(movieId);

		if (!entity.isPresent()) {
			throw new ParamNotFound("Invalid Movie Id.");
		}
		
		repository.deleteById(movieId);
	}

	@Override
	public List<MovieBasicDTO> getByFilters(String titulo, List<Long> genresId, String order) {
		MovieFiltersDTO filtersDTO = new MovieFiltersDTO(titulo, genresId, order);
		List<PeliculaEntity> entities = repository.findAll(specification.getByFilters(filtersDTO));
		List<MovieBasicDTO> dtos = mapper.toMovieBasicDTOList(entities);
		return dtos;
	}
	
	@Override
	public MovieDTO getDetails(Long id) {
		Optional<PeliculaEntity> entityOptional = repository.findById(id);
		PeliculaEntity entity = entityOptional.get();
		MovieDTO result = mapper.toMovieDTO(entity, true);
		return result;
	}

}
