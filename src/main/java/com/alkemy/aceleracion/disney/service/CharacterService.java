package com.alkemy.aceleracion.disney.service;

import java.util.List;

import com.alkemy.aceleracion.disney.dto.CharacterDTO;

public interface CharacterService {
	List<CharacterDTO> getAll();
    List<CharacterDTO> getByFilters(String name, Integer age, List<Long> movieIds);
    CharacterDTO save(CharacterDTO character);
    CharacterDTO edit(CharacterDTO character);
    void delete(Long characterId);

}
