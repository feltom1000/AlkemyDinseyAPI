package com.alkemy.aceleracion.disney.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.aceleracion.disney.dto.GenreDTO;
import com.alkemy.aceleracion.disney.entity.GeneroEntity;
import com.alkemy.aceleracion.disney.mapper.GenreMapper;
import com.alkemy.aceleracion.disney.repository.GenreRepository;
import com.alkemy.aceleracion.disney.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService{
	
	@Autowired
	private GenreMapper mapper;
	@Autowired
	private GenreRepository repository;

	@Override
	public GenreDTO save(GenreDTO genre) {
		GeneroEntity entity = mapper.toGeneroEntity(genre);
		GeneroEntity entitySaved = repository.save(entity);
		GenreDTO result = mapper.toGenreDTO(entitySaved);
		return result;
	}

}
