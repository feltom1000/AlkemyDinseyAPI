package com.alkemy.aceleracion.disney.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.dto.MovieBasicDTO;
import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.entity.PeliculaEntity;

public class MovieMapper {
	
	@Autowired
	GenreMapper genreMapper;
	@Autowired
	CharacterMapper characterMapper;


	public MovieDTO toMovieDTO(PeliculaEntity entity, boolean loadCharacters) {
		MovieDTO dto = new MovieDTO();
		dto.setId(entity.getId());
		dto.setImg(entity.getImagen());
		dto.setName(entity.getTitulo());
		dto.setGenre(genreMapper.toGenreDTO(entity.getGenero()));
		dto.setCreationDate(entity.getFechaDeCreacion());
		dto.setCalification(entity.getCalificacion());
		if(loadCharacters) {
			Collection<CharacterDTO> dtos = characterMapper.toCharacterDTOList(entity.getPersonajes(), false);
			dto.setCharacters((Set<CharacterDTO>) dtos);
		}
		return dto;
	}
	
	public PeliculaEntity toPeliculaEntity(MovieDTO dto){
		PeliculaEntity entity = new PeliculaEntity();
		entity.setImagen(dto.getImg());
		entity.setTitulo(dto.getName());
		entity.setGenero(genreMapper.toGeneroEntity(dto.getGenre()));
		entity.setFechaDeCreacion(dto.getCreationDate());
		entity.setCalificacion(dto.getCalification());
		
		return entity;
	}

	public Collection<MovieDTO> toMovieDTOList(Collection<PeliculaEntity> entities, boolean loadCharacters){
		List<MovieDTO> dtos = new ArrayList<>();
		for(PeliculaEntity entity : entities) {
			dtos.add(this.toMovieDTO(entity, loadCharacters));
		}
		return dtos;
	}
	
	public Collection<PeliculaEntity> toPeliculaEntityList(Collection<MovieDTO> dtos){
		Collection<PeliculaEntity> entities = new ArrayList<>();
		for(MovieDTO dto : dtos) {
			entities.add(this.toPeliculaEntity(dto));
		}
		return entities;
	}
	
	public List<MovieBasicDTO> toMovieBasicDTO(Collection<PeliculaEntity> entities){
    	List<MovieBasicDTO> dtos = new ArrayList<>();
    	MovieBasicDTO basicDTO;
    	for(PeliculaEntity entity : entities) {
    		basicDTO = new MovieBasicDTO();
    		basicDTO.setId(entity.getId());
    		basicDTO.setImg(entity.getImagen());
    		basicDTO.setName(entity.getTitulo());
    		basicDTO.setCreationDate(entity.getFechaDeCreacion());
    		dtos.add(basicDTO);
    	}
    	return dtos;
    }
	
}
