package com.alkemy.aceleracion.disney.service;

import java.util.List;

import com.alkemy.aceleracion.disney.dto.CharacterBasicDTO;
import com.alkemy.aceleracion.disney.dto.CharacterDTO;

public interface CharacterService {
	CharacterDTO getDetails(Long id);
    List<CharacterBasicDTO> getByFilters(String name, Integer age, List<Long> movieIds);
    CharacterDTO save(CharacterDTO character);
    CharacterDTO update(Long id, CharacterDTO dto);
    void delete(Long characterId);
}
