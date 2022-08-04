package com.alkemy.aceleracion.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.aceleracion.disney.dto.GenreDTO;
import com.alkemy.aceleracion.disney.entity.GeneroEntity;

@Component
public class GenreMapper {

	public GenreDTO toGenreDTO(GeneroEntity genero) {
		GenreDTO dto = new GenreDTO();
		dto.setId(genero.getId());
		dto.setImg(genero.getImagen());
		dto.setName(genero.getNombre());
		
		return dto;
	}
	
	public GeneroEntity toGeneroEntity(GenreDTO dto) {
		GeneroEntity entity = new GeneroEntity();
		entity.setImagen(dto.getImg());
		entity.setNombre(dto.getName());
		
		return entity;
	}

	public List<GenreDTO> toGenreDTOList(List<GeneroEntity> generos){
		List<GenreDTO> dtos = new ArrayList<>();
		for(GeneroEntity entity : generos) {
			dtos.add(this.toGenreDTO(entity));
		}
		return dtos;
	}

}
