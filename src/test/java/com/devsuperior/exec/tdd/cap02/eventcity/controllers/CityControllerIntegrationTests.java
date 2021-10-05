package com.devsuperior.exec.tdd.cap02.eventcity.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CityControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deleteShouldReturnNoContentWhenIndependentId() throws Exception {
		long independentId = 5l;
		ResultActions resultActions = mockMvc.perform(delete("/cities/{id}", independentId));
		resultActions.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenNonExistingId() throws Exception {
		long nonExistinId = 50L;
		ResultActions resultActions = mockMvc.perform(delete("/cities/{id}", nonExistinId));
		resultActions.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/cities").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$[0].name").value("Belo Horizonte"));
		resultActions.andExpect(jsonPath("$[1].name").value("Belém"));
		resultActions.andExpect(jsonPath("$[2].name").value("Brasilia"));
	}
}
