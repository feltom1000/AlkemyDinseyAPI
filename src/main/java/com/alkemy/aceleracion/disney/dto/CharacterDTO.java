package com.alkemy.aceleracion.disney.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
	private Long id;
    private String img;
    private String name;
    private int age;
    private int weight;
    private String story;

    private List<MovieDTO> movies;


}
