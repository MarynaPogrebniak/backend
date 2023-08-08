package de.ait.todolist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.todolist.dto.NewTaskDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TasksController is works: ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class TasksIntegrationTest {

    private static final NewTaskDto NEW_TASK = NewTaskDto.builder()
            .title("1")
            .description("Описание задачи")
            .startDate("2024-02-02")
            .finishDate("2024-02-05")
            .build();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("POST /users is works: ")
    class AddTaskTest {

        @Test
        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void add_task_for_exist_user() throws Exception {

            String body = objectMapper.writeValueAsString(NEW_TASK);

            mockMvc.perform(post("/users/1/tasks")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.title", is("1")))
                    .andExpect(jsonPath("$.description", is("Описание задачи")))
                    .andExpect(jsonPath("$.startDate", is("2024-02-02")))
                    .andExpect(jsonPath("$.finishDate", is("2024-02-05")))
            //executor id
            ;
        }

        @Test
        public void add_task_for_not_exist_user() throws Exception {
            String body = objectMapper.writeValueAsString(NEW_TASK);

            mockMvc.perform(post("/users/1/tasks")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isUnprocessableEntity());
        }
    }
}