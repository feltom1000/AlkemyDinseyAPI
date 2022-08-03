package com.alkemy.aceleracion.disney.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.entity.PersonajeEntity;

@Mapper(componentModel = "spring", uses = {MovieMapper.class})
public interface CharacterMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "imagen", target = "img"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "edad", target = "age"),
            @Mapping(source = "peso", target = "weight"),
            @Mapping(source = "historia", target = "story"),
            @Mapping(source = "peliculas", target = "movies")
    })
    CharacterDTO toCharacterDTO(PersonajeEntity personaje);
    List<CharacterDTO> toCharacterDTOList(List<PersonajeEntity> personajes);

    @InheritInverseConfiguration
    PersonajeEntity toPersonajeEntity(CharacterDTO character);
}
