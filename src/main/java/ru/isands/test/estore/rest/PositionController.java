package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.Position;
import ru.isands.test.estore.dao.service.PositionServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Position", description = "Сервис для выполнения операций над позициями сотрудников")
@RequestMapping(value = "/estore/api/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionServiceImpl positionService;
    @GetMapping
    @Operation(
            summary = "Найти все должности",
            description = "Возвращает список всех должностей",
            responses = {
                    @ApiResponse(description = "Должности найдены")
            }
    )
    public List<Position> findAllPositions() {
        return positionService.findAllPositions();
    }

    @Operation(
            summary = "Получить список позиций с пагинацией",
            description = "Возвращает постраничный список позиций, используя параметры offset и limit",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список позиций получен",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Page.class
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректное значение offset или limit\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/bypages")
    public Page<Position> getPositions(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        return positionService.findAllByPages(pageable);
    }

    @Operation(
            summary = "Получить позицию по ID",
            description = "Возвращает позицию по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Позиция найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": 1, \"name\": \"Менеджер\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Позиция не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Позиция с таким ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        Optional<Position> position = positionService.findPositionById(id);
        return position.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Добавить новую позицию",
            description = "Добавляет новую позицию в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Позиция успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания позиции\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPosition(@Valid @RequestBody Position position) {
        positionService.savePosition(position);
    }

    @Operation(
            summary = "Обновить существующую позицию",
            description = "Обновляет данные существующей позиции в базе данных",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Позиция успешно обновлена"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для обновления позиции\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Позиция не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Позиция с указанным ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePosition(
            @PathVariable Long id,
            @Valid @RequestBody Position position
    ) {
        positionService.updatePosition(id, position);
    }

}
