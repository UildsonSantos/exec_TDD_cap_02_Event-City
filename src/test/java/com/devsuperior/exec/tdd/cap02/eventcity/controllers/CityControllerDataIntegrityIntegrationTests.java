package com.devsuperior.exec.tdd.cap02.eventcity.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerDataIntegrityIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deleteShouldReturnBadRequestWhenDependentId() throws Exception {		
		long dependentId = 1L;		
		ResultActions resultActions = mockMvc.perform(delete("/cities/{id}", dependentId));				
		resultActions.andExpect(status().isBadRequest());
	}
}
