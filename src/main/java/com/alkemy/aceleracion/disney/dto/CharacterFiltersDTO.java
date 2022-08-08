package com.alkemy.aceleracion.disney.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterFiltersDTO {
	private String name;
	private Integer age;
	private List<Long> moviesId;
	
	public CharacterFiltersDTO(String name, int age, List<Long> moviesId) {
		this.name = name;
		this.age = age;
		this.moviesId = moviesId;
	}
	
	

}
