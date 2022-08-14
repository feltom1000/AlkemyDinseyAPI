package com.alkemy.aceleracion.disney.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {
	private String titulo;
	private List<Long> genresId;
	private String order;

	public MovieFiltersDTO(String titulo, List<Long> genreId, String order) {
		this.titulo = titulo;
		this.genresId = genreId;
		this.order = order;
	}

	public boolean isASC() {
		return this.order.compareToIgnoreCase("ASC") == 0;
	}

	public boolean isDESC() {
		return this.order.compareToIgnoreCase("DESC") == 0;
	}

}
