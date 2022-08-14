package com.alkemy.aceleracion.disney.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.dto.MovieBasicDTO;
import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.entity.GeneroEntity;
import com.alkemy.aceleracion.disney.entity.PeliculaEntity;
import com.alkemy.aceleracion.disney.exception.ParamNotFound;
import com.alkemy.aceleracion.disney.repository.GenreRepository;

@Component
public class MovieMapper {
	
	@Autowired
	private CharacterMapper characterMapper;
	@Autowired
	private GenreRepository genreRepository;


	public MovieDTO toMovieDTO(PeliculaEntity entity, boolean loadCharacters) {
		MovieDTO dto = new MovieDTO();
		dto.setId(entity.getId());
		dto.setImg(entity.getImagen());
		dto.setName(entity.getTitulo());
		dto.setGenreId((entity.getGeneroId()));
		dto.setCreationDate(entity.getFechaDeCreacion().toString());
		dto.setCalification(entity.getCalificacion());
		if(loadCharacters) {
			List<CharacterDTO> dtos = characterMapper.toCharacterDTOList(entity.getPersonajes(), false);
			dto.setCharacters(dtos);
		}
		return dto;
	}
	
	public PeliculaEntity toPeliculaEntity(MovieDTO dto){
		PeliculaEntity entity = new PeliculaEntity();
		entity.setImagen(dto.getImg());
		entity.setTitulo(dto.getName());
		entity.setGeneroId(dto.getGenreId());
		entity.setFechaDeCreacion(stringToLocalDate(dto.getCreationDate()));
		entity.setCalificacion(dto.getCalification());
		
		return entity;
	}
	
	private LocalDate stringToLocalDate(String stringDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(stringDate, formatter);
		return date;
	}

	public List<MovieDTO> toMovieDTOList(Collection<PeliculaEntity> entities, boolean loadCharacters){
		List<MovieDTO> dtos = new ArrayList<>();
		for(PeliculaEntity entity : entities) {
			dtos.add(this.toMovieDTO(entity, loadCharacters));
		}
		return dtos;
	}
	
	public Collection<PeliculaEntity> toPeliculaEntityList(Collection<MovieDTO> dtos){
		Collection<PeliculaEntity> entities = new ArrayList<>();
		for(MovieDTO dto : dtos) {
			entities.add(this.toPeliculaEntity(dto));
		}
		return entities;
	}
	
	public List<MovieBasicDTO> toMovieBasicDTOList(Collection<PeliculaEntity> entities){
    	List<MovieBasicDTO> dtos = new ArrayList<>();
    	MovieBasicDTO basicDTO;
    	for(PeliculaEntity entity : entities) {
    		basicDTO = new MovieBasicDTO();
    		basicDTO.setId(entity.getId());
    		basicDTO.setImg(entity.getImagen());
    		basicDTO.setName(entity.getTitulo());
    		basicDTO.setCreationDate(entity.getFechaDeCreacion());
    		dtos.add(basicDTO);
    	}
    	return dtos;
    }
	
	public void movieEntityRefreshValues(PeliculaEntity entity, MovieDTO dto) {
        entity.setImagen(dto.getImg());
        entity.setTitulo(dto.getName());
        entity.setFechaDeCreacion(this.stringToLocalDate(dto.getCreationDate()));
        entity.setCalificacion(dto.getCalification());
        entity.setGeneroId(dto.getGenreId());

        // Buscamos si existe el genero por el Id de estar Ok seteamos
        Optional<GeneroEntity> resultGenre = genreRepository.findById(entity.getGeneroId());
        if (resultGenre.isPresent()) {
            entity.setGenero(resultGenre.get());
        } else {
            throw new ParamNotFound("Invalid id Genre.");
        }
    }
	
}
