package com.alkemy.aceleracion.disney.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.dto.CharacterFiltersDTO;
import com.alkemy.aceleracion.disney.entity.PersonajeEntity;
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
	public List<CharacterDTO> getAll() {
		List<PersonajeEntity> entities = repository.findAll();
		List<CharacterDTO> result = mapper.toCharacterDTOList(entities, false);
		return result;
	}

	@Override
	public CharacterDTO save(CharacterDTO character) {
		PersonajeEntity entity = mapper.toPersonajeEntity(character);
		PersonajeEntity entitySaved = repository.save(entity);
		CharacterDTO result = mapper.toCharacterDTO(entitySaved, false);
		return result;
	}

	@Override
	public CharacterDTO edit(CharacterDTO character) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long characterId) {
		repository.deleteById(characterId);
	}

	@Override
	public List<CharacterDTO> getByFilters(String name, Integer age, List<Long> moviesId) {
		CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, moviesId);
		List<PersonajeEntity> entities = repository.findAll(specification.getByFilters(filtersDTO));
		List<CharacterDTO> dtos = mapper.toCharacterDTOList(entities, true);
		return dtos;
	}

}
