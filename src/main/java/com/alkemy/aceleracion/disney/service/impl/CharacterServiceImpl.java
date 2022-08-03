package com.alkemy.aceleracion.disney.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.entity.PersonajeEntity;
import com.alkemy.aceleracion.disney.mapper.CharacterMapper;
import com.alkemy.aceleracion.disney.repository.CharacterRepository;
import com.alkemy.aceleracion.disney.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	@Autowired
	private CharacterMapper mapper;
	@Autowired
	private CharacterRepository repository;

	@Override
	public List<CharacterDTO> getAll() {
		List<PersonajeEntity> entities = repository.findAll();
		List<CharacterDTO> result = mapper.toCharacterDTOList(entities);
		return result;
	}

	@Override 
	public CharacterDTO getByName(String name) {
		PersonajeEntity entityFound = repository.findByNombre(name);
		CharacterDTO result = mapper.toCharacterDTO(entityFound);
		return result;
	}

	@Override
	public List<CharacterDTO> getByAge(int age) {
		List<PersonajeEntity> entitiesFound = repository.findByEdad(age);
		List<CharacterDTO> result = mapper.toCharacterDTOList(entitiesFound);
		return result;
	}

//	@Override
//	public List<CharacterDTO> getByMovie(int idMovie) {
//		return null;
//	}

	@Override
	public CharacterDTO save(CharacterDTO character) {
		PersonajeEntity entity = mapper.toPersonajeEntity(character);
		PersonajeEntity entitySaved = repository.save(entity);
		CharacterDTO result = mapper.toCharacterDTO(entitySaved);
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

}
