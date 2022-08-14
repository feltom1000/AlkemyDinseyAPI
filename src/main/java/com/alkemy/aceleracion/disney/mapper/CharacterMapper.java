package com.alkemy.aceleracion.disney.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.aceleracion.disney.dto.CharacterBasicDTO;
import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.entity.PersonajeEntity;

@Component
public class CharacterMapper {
	
	@Autowired
	MovieMapper movieMapper;

    public CharacterDTO toCharacterDTO(PersonajeEntity entity, boolean loadMovies) {
    	CharacterDTO dto = new CharacterDTO();
    	dto.setId(entity.getId());
    	dto.setImg(entity.getImagen());
    	dto.setName(entity.getNombre());
    	dto.setAge(entity.getEdad());
    	dto.setWeight(entity.getPeso());
    	dto.setStory(entity.getHistoria());
    	if(loadMovies) {
    		List<MovieDTO> moviesDTOs = movieMapper.toMovieDTOList(entity.getPeliculas(), false);
    		dto.setMovies(moviesDTOs);
    	}
    	
    	return dto;
    };
    
    public PersonajeEntity toPersonajeEntity(CharacterDTO dto) {
    	PersonajeEntity entity = new PersonajeEntity();
    	entity.setImagen(dto.getImg());
    	entity.setNombre(dto.getName());
    	entity.setEdad(dto.getAge());
    	entity.setPeso(dto.getWeight());
    	entity.setHistoria(dto.getStory());
    	
    	return entity;
    }
    
    public List<CharacterDTO> toCharacterDTOList(Collection<PersonajeEntity> entities, boolean loadMovies){
    	List<CharacterDTO> dtos = new ArrayList<>();
    	for (PersonajeEntity entity : entities) {
    		dtos.add(this.toCharacterDTO(entity, loadMovies));
    	}
    	return dtos;
    }
    
    public List<CharacterBasicDTO> toCharacterBasicDTOList(Collection<PersonajeEntity> entities){
    	List<CharacterBasicDTO> dtos = new ArrayList<>();
    	CharacterBasicDTO basicDTO;
    	for(PersonajeEntity entity : entities) {
    		basicDTO = new CharacterBasicDTO();
    		basicDTO.setId(entity.getId());
    		basicDTO.setImg(entity.getImagen());
    		basicDTO.setName(entity.getNombre());
    		dtos.add(basicDTO);
    	}
    	return dtos;
    }
    
    public void characterEntityRefreshValues(PersonajeEntity entity, CharacterDTO dto) {
        entity.setImagen(dto.getImg());
        entity.setNombre(dto.getName());
        entity.setEdad(dto.getAge());
        entity.setPeso(dto.getWeight());
        entity.setHistoria(dto.getStory());
    }
    
}
