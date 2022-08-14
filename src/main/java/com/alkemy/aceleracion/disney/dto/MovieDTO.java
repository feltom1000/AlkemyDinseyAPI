package com.alkemy.aceleracion.disney.dto;

import java.util.List;

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
    
    private List<CharacterDTO> characters;

}
