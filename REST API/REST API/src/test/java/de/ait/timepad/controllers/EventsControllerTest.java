package de.ait.timepad.controllers;

import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EventsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventsRepository eventsRepository;

    @BeforeEach
    public void setUp() {
        eventsRepository.clear();
    }

    @Test
    void addEvent() throws Exception {
        mockMvc.perform(post("/api/events")
                        .header("Content-Type", "application/json")
                .content("{\n" +
                        "  \"name\": \"RHCP Concert\",\n" +
                        "  \"location\": \"Stuttgart\",\n" +
                        "  \"price\": 100\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("RHCP Concert")))
                .andExpect(jsonPath("$.location", is("Stuttgart")))
                .andExpect(jsonPath("$.state", is("CREATED")));
    }

    @Test
    void getAllEvents() throws Exception {
        eventsRepository.save(Event.builder().id(5L).state(Event.State.CREATED).build());
        eventsRepository.save(Event.builder().id(6L).state(Event.State.CREATED).build());

        mockMvc.perform(get("/api/events"))
                .andDo(print())
                .andExpect(jsonPath("$.count", is(2)));
    }
}