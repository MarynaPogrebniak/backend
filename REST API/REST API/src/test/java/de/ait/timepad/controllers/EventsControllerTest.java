package de.ait.timepad.controllers;

import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventsRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("EventsController is works: ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventsRepository eventsRepository;

    @BeforeEach
    public void setUp() {
        eventsRepository.clear();
    }

    @Nested
    @DisplayName("POST /api/events is works: ")
    class AddEventTests {
        @Test
        void add_event() throws Exception {
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
    }

    @Nested
    @DisplayName("GET /api/events is works: ")
    class GetAllEventsTests {
        @Test
        void get_all_events() throws Exception {
            eventsRepository.save(Event.builder().state(Event.State.CREATED).build());
            eventsRepository.save(Event.builder().state(Event.State.CREATED).build());

            mockMvc.perform(get("/api/events"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.count", is(2)));
        }
    }

    @Nested
    @DisplayName("DELETE /api/events/eventId is works: ")
    class DeleteEventTests {

        @Test
        void delete_exist_event() throws Exception {
            eventsRepository.save(Event.builder().state(Event.State.CREATED).build());

            mockMvc.perform(delete("/api/events/1"))
                    .andExpect(status().isOk());
        }

        @Test
        void delete_not_exist_event() throws Exception {
            mockMvc.perform(delete("/api/events/1"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("PUT /api/events/eventId is works: ")
    class UpdateEventTests{
        @Test
        void update_exist_event() throws Exception{
            eventsRepository.save(Event.builder().state(Event.State.CREATED).build());
            mockMvc.perform(put("/api/events/1")
                    .header("Content-Type", "application/json")
                    .content("{\n" +
                            "  \"newName\" : \"Metallica\",\n" +
                            "  \"newLocation\" : \"Singen\",\n" +
                            "  \"newState\" : \"IN_PROGRESS\"\n" +
                            "}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.state", is("IN_PROGRESS")))
                    .andExpect(jsonPath("$.name", is("Metallica")))
                    .andExpect(jsonPath("$.location", is("Singen")));
        }
    }

    @Nested
    @DisplayName("GET /api/events/eventId is works: ")
    class GetEventTests{
        @Test
        void get_exist_event() throws Exception{
            eventsRepository.save(Event.builder().state(Event.State.CREATED).build());
            mockMvc.perform(get("/api/events/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.state", is("CREATED")));
        }
    }

}