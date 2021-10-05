package com.devsuperior.exec.tdd.cap02.eventcity.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
}
