package com.alkemy.aceleracion.disney.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.alkemy.aceleracion.disney.dto.GenreDTO;
import com.alkemy.aceleracion.disney.entity.GeneroEntity;

@Mapper(componentModel = "spring")
public interface GenreMapper {

	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "nombre", target = "name"),
		@Mapping(source = "imagen", target = "img")
	})
	GenreDTO toGenreDTO(GeneroEntity genero);

	List<GenreDTO> toGenreDTOList(List<GeneroEntity> generos);

	@InheritInverseConfiguration
	GeneroEntity toGeneroEntity(GenreDTO genre);

}
