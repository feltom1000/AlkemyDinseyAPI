package com.alkemy.aceleracion.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.aceleracion.disney.entity.PeliculaEntity;

@Repository
public interface MovieRepository extends JpaRepository<PeliculaEntity, Long> {
	
	List<PeliculaEntity> findAll(Specification<PeliculaEntity> spec);
	List<PeliculaEntity> findByGeneroId(Long genreId);
    PeliculaEntity findByTitulo(String titulo);
}
