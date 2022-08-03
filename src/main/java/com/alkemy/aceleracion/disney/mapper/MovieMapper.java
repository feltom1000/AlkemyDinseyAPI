package com.alkemy.aceleracion.disney.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.entity.PeliculaEntity;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, CharacterMapper.class})
public interface MovieMapper {
	@Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "imagen", target = "img"),
        @Mapping(source = "titulo", target = "name"),
        @Mapping(source = "fechaDeCreacion", target = "creationDate"),
        @Mapping(source = "calificacion", target = "calification"),
        @Mapping(source = "generoId", target = "genreId"),
        @Mapping(source = "genero", target = "genre"),
        @Mapping(source = "personajes", target = "characters"),
})
MovieDTO toMovieDTO(PeliculaEntity peliculaOSerie);
List<MovieDTO> toMovieDTOList(List<PeliculaEntity> peliculas);

@InheritInverseConfiguration
PeliculaEntity toPeliculaEntity(MovieDTO movie);

}
