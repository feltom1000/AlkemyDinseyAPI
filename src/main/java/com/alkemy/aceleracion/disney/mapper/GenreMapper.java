package com.alkemy.aceleracion.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.alkemy.aceleracion.disney.dto.GenreDTO;
import com.alkemy.aceleracion.disney.entity.GeneroEntity;

public class GenreMapper {

	GenreDTO toGenreDTO(GeneroEntity genero) {
		GenreDTO dto = new GenreDTO();
		dto.setId(genero.getId());
		dto.setImg(genero.getImagen());
		dto.setName(genero.getNombre());
		
		return dto;
	}
	
	GeneroEntity toGeneroEntity(GenreDTO dto) {
		GeneroEntity entity = new GeneroEntity();
		entity.setImagen(dto.getImg());
		entity.setNombre(dto.getName());
		
		return entity;
	}

	List<GenreDTO> toGenreDTOList(List<GeneroEntity> generos){
		List<GenreDTO> dtos = new ArrayList<>();
		for(GeneroEntity entity : generos) {
			dtos.add(this.toGenreDTO(entity));
		}
		return dtos;
	}

}
