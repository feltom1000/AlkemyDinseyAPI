package com.alkemy.aceleracion.disney.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="genero")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genero SET deleted = true WHERE id=?" )
@Where(clause = "deleted=false")
public class GeneroEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private String imagen;
    
    private boolean deleted = Boolean.FALSE;

}
