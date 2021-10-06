package com.devsuperior.exec.tdd.cap02.eventcity.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.exec.tdd.cap02.eventcity.dto.CityDTO;
import com.devsuperior.exec.tdd.cap02.eventcity.entities.City;
import com.devsuperior.exec.tdd.cap02.eventcity.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public List<CityDTO> findAll() {
		List<City> cities = cityRepository.findAll(Sort.by("name"));
		return cities.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
	}
}
