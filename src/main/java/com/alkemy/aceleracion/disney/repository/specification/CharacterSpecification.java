package com.alkemy.aceleracion.disney.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alkemy.aceleracion.disney.dto.CharacterFiltersDTO;
import com.alkemy.aceleracion.disney.entity.PeliculaEntity;
import com.alkemy.aceleracion.disney.entity.PersonajeEntity;

@Component
public class CharacterSpecification {
	
	public Specification<PersonajeEntity> getByFilters(CharacterFiltersDTO filtersDTO){
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if (StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("nombre")),
								"%" + filtersDTO.getName().toLowerCase() + "%"
						)
				);
			}
			
			if (filtersDTO.getAge() != null) {
                predicates.add(criteriaBuilder.equal(root.get("edad"), filtersDTO.getAge()));
            }
			
			if (!CollectionUtils.isEmpty(filtersDTO.getMoviesId())) {
				Join<PeliculaEntity, PersonajeEntity> join = root.join("peliculas", JoinType.INNER);
				Expression<String> moviesId = join.get("id");
				predicates.add(moviesId.in(filtersDTO.getMoviesId()));
			}
			
			//Removemos Duplicados
			query.distinct(true);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
	}

}
