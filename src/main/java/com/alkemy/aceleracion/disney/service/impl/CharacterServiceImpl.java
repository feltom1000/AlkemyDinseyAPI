package com.alkemy.aceleracion.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.aceleracion.disney.dto.CharacterBasicDTO;
import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.dto.CharacterFiltersDTO;
import com.alkemy.aceleracion.disney.entity.PersonajeEntity;
import com.alkemy.aceleracion.disney.exception.ParamNotFound;
import com.alkemy.aceleracion.disney.mapper.CharacterMapper;
import com.alkemy.aceleracion.disney.repository.CharacterRepository;
import com.alkemy.aceleracion.disney.repository.specification.CharacterSpecification;
import com.alkemy.aceleracion.disney.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	@Autowired
	private CharacterSpecification specification;
	@Autowired
	private CharacterMapper mapper;
	@Autowired
	private CharacterRepository repository;

	@Override
	public CharacterDTO save(CharacterDTO character) {
		PersonajeEntity entity = mapper.toPersonajeEntity(character);
		PersonajeEntity entitySaved = repository.save(entity);
		CharacterDTO result = mapper.toCharacterDTO(entitySaved, false);
		return result;
	}
	
	@Override
	public CharacterDTO update(Long id, CharacterDTO dto) {
        Optional<PersonajeEntity> entity = repository.findById(id);
        
        // validar si existe
        if (!entity.isPresent()) {
            throw new ParamNotFound("Invalid character id.");
        }
        
        mapper.characterEntityRefreshValues(entity.get(), dto); // Modificar
        PersonajeEntity entitySaved = repository.save(entity.get()); // Persistir
        CharacterDTO resultDTO = mapper.toCharacterDTO(entitySaved, false); // Convertir a DTO
        return resultDTO;    
    }

	@Override
	public void delete(Long characterId) {
		repository.deleteById(characterId);
	}

	@Override
	public List<CharacterBasicDTO> getByFilters(String name, Integer age, List<Long> moviesId) {
		CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, moviesId);
		List<PersonajeEntity> entities = repository.findAll(specification.getByFilters(filtersDTO));
		List<CharacterBasicDTO> dtos = mapper.toCharacterBasicDTOList(entities);
		return dtos;
	}
	
	@Override
	public CharacterDTO getDetails(Long id) {
		Optional<PersonajeEntity> entityOptional = repository.findById(id);
		PersonajeEntity entity = entityOptional.get();
		CharacterDTO result = mapper.toCharacterDTO(entity, true);
		return result;
	}

}
