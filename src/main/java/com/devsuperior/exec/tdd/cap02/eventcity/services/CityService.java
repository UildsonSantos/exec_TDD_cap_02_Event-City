package com.devsuperior.exec.tdd.cap02.eventcity.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.exec.tdd.cap02.eventcity.dto.CityDTO;
import com.devsuperior.exec.tdd.cap02.eventcity.entities.City;
import com.devsuperior.exec.tdd.cap02.eventcity.repositories.CityRepository;
import com.devsuperior.exec.tdd.cap02.eventcity.services.exceptions.DatabaseException;
import com.devsuperior.exec.tdd.cap02.eventcity.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> cities = cityRepository.findAll(Sort.by("name"));
		return cities.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
	}
	
	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);			
		} 
		catch (DataIntegrityViolationException e) {			
			throw new DatabaseException("Integrity violation");
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("City entity with id " + id +" exists!");			
		}		
	}
}
