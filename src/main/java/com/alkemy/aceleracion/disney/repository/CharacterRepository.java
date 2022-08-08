package com.alkemy.aceleracion.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alkemy.aceleracion.disney.entity.PersonajeEntity;

@Repository
public interface CharacterRepository extends JpaRepository<PersonajeEntity, Long>, JpaSpecificationExecutor<PersonajeEntity> {
	PersonajeEntity findByNombre(String nombre);
    List<PersonajeEntity> findByEdad(int edad);
    List<PersonajeEntity> findByPeliculas(String movieOrSerie);
    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);
}
