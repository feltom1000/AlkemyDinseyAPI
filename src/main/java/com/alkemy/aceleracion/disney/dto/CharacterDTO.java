package com.alkemy.aceleracion.disney.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
	private int id;
    private String img;
    private String name;
    private int age;
    private int weight;
    private String story;

    private Set<MovieDTO> movies;


}
