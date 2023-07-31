package de.ait.timepad.controllers.api;

import de.ait.timepad.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Events")
})
@RequestMapping("/api/events")
public interface EventsApi {

    @Operation(summary = "Создание события", description = "Доступно только администратору")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EventDto> addEvent(@Parameter(required = true, description = "Событие") @RequestBody @Valid NewEventDto newEvent);

    @Operation(summary = "Получение списка событий", description = "Доступно всем")
    @GetMapping
    EventsDto getAllEvents();

    @Operation(summary = "Удаление события", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Событие не найдено",
                    content = {
                            @Content()
                    }),
            @ApiResponse(responseCode = "200", description = "Удаленное событие",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = EventsDto.class))
                    })
    })
    @DeleteMapping("/{event-id}")
    EventDto deleteEvent(@Parameter(required = true, description = "Идентификатор события", example = "1")
                         @PathVariable("event-id") Long eventId);


    @Operation(summary = "Обновление события", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Событие не найдено",
                    content = {
                            @Content()
                    }),
            @ApiResponse(responseCode = "200", description = "Обновленное событие",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))
                    })
    })
    @PutMapping("/{event-id}")
    EventDto updateEvent(@Parameter(required = true, description = "Идентификатор события", example = "1")
                         @PathVariable("event-id") Long eventId,
                         @RequestBody UpdateEventDto updateEvent);

    @Operation(summary = "Получение события", description = "Доступно всем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Событие не найдено",
                    content = {
                            @Content()
                    }),
            @ApiResponse(responseCode = "200", description = "Обновленное событие",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))
                    })
    })
    @GetMapping("/{event-id}")
    EventDto getEvent(@Parameter(required = true, description = "Идентификатор события", example = "1")
                      @PathVariable("event-id") Long eventId);

}
