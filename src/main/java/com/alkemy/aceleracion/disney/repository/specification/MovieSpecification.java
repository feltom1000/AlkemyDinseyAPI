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

import com.alkemy.aceleracion.disney.dto.MovieFiltersDTO;
import com.alkemy.aceleracion.disney.entity.GeneroEntity;
import com.alkemy.aceleracion.disney.entity.PeliculaEntity;

@Component
public class MovieSpecification {
	
	public Specification<PeliculaEntity> getByFilters(MovieFiltersDTO filtersDTO){
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("titulo")),
								"%" + filtersDTO.getName().toLowerCase() + "%"
								)
						);
			}
			
			if (!CollectionUtils.isEmpty(filtersDTO.getGenresId())) {
	            Join<GeneroEntity, PeliculaEntity> join = root.join("genero", JoinType.INNER);
	            Expression<String> genreId = join.get("id");
	            predicates.add(genreId.in(filtersDTO.getGenresId()));
	            }
			
			//Removemos Duplicados
			query.distinct(true);
			
			//Order
			String orderByField = "nombre";
			query.orderBy(
					filtersDTO.isASC() ?
							criteriaBuilder.asc(root.get(orderByField)) :
							criteriaBuilder.desc(root.get(orderByField))
			);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
