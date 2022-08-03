package com.alkemy.aceleracion.disney.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieBasicDTO {
	private Long id;
    private String img;
    private String name;
    private LocalDate creationDate;

}
