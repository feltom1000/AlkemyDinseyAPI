package com.alkemy.aceleracion.disney.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="personaje")
@Getter
@Setter
public class PersonajeEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;

	@ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
	private Set<PeliculaEntity> peliculas = new HashSet<>();

}
