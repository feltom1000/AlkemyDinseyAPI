package com.alkemy.aceleracion.disney.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
	private int id;
    private String img;
    private String name;
    private LocalDate creationDate;
    private int calification;

    private int genreId;

    private GenreDTO genre;
    private Set<CharacterDTO> characters;

}
