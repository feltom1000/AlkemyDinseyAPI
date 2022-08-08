package com.alkemy.aceleracion.disney.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?" )
@Where(clause = "deleted=false")
public class PersonajeEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    
    private boolean deleted = Boolean.FALSE;

	@ManyToMany(mappedBy = "personajes", cascade = CascadeType.PERSIST)
	private Set<PeliculaEntity> peliculas = new HashSet<>();

}
