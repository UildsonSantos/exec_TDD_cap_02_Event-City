package com.devsuperior.exec.tdd.cap02.eventcity.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.exec.tdd.cap02.eventcity.dto.EventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EventControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void updateShouldUpdateResourceWhenIdExists() throws Exception {

		long existingId = 1L;

		EventDTO eventDTO = new EventDTO(null, "Expo XP", LocalDate.of(2021, 5, 18), "https://expoxp.com.br", 7L);
		String jsonBody = objectMapper.writeValueAsString(eventDTO);

		ResultActions resultActions = mockMvc.perform(put("/events/{id}", existingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.id").exists());
		resultActions.andExpect(jsonPath("$.id").value(1L));		
		resultActions.andExpect(jsonPath("$.name").value("Expo XP"));
		resultActions.andExpect(jsonPath("$.date").value("2021-05-18"));
		resultActions.andExpect(jsonPath("$.url").value("https://expoxp.com.br"));
		resultActions.andExpect(jsonPath("$.cityId").value(7L));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		long nonExistingId = 1000L;
		
		EventDTO eventDTO = new EventDTO(null, "Expo XP", LocalDate.of(2021, 5, 18), "https://expoxp.com.br", 7L);
		String jsonBody = objectMapper.writeValueAsString(eventDTO);
		
		ResultActions resultActions = mockMvc.perform(put("/events/{id}", nonExistingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isNotFound());
	}
}
