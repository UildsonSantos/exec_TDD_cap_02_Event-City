package com.devsuperior.exec.tdd.cap02.eventcity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devsuperior.exec.tdd.cap02.eventcity.dto.CityDTO;
import com.devsuperior.exec.tdd.cap02.eventcity.services.CityService;

@Controller
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() {
		List<CityDTO> cityDTOs = cityService.findAll();
		return ResponseEntity.ok().body(cityDTOs);
	}	
}
