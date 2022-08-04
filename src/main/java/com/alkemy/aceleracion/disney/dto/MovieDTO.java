package com.alkemy.aceleracion.disney.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
	private Long id;
    private String img;
    private String name;
    private String creationDate;
    private int calification;
    private Long genreId;
    
    private Set<CharacterDTO> characters;

}
