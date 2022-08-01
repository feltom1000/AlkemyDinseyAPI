package com.alkemy.aceleracion.disney.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pelicula")
@Getter
@Setter
public class PeliculaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDeCreacion;

    private Integer calificacion;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;

    @Column(name = "genero_id", nullable = false)
    private Long generoId;
    
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(
			name = "personaje_pelicula",
			joinColumns = @JoinColumn(name = "pelicula_id"),
			inverseJoinColumns = @JoinColumn(name = "personaje_id"))
	private Set<PersonajeEntity> personajes = new HashSet<>();

}
