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


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.isands.test.estore.dao.entity.ElectronicsType;
import ru.isands.test.estore.dao.service.ElectronicsTypeServiceImpl;


@RestController
@Tag(name = "ElectronicsType", description = "Сервис для выполнения операций над типами электроники")
@RequestMapping(value = "/estore/api/electronicsType")
@RequiredArgsConstructor
public class ElectronicsTypeController {

    private final ElectronicsTypeServiceImpl electronicsTypeService;

    @Operation(
            summary = "Получить список типов электроники с пагинацией",
            description = "Возвращает постраничный список типов электроники, используя параметры offset и limit",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список типов электроники получен",
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
    public Page<ElectronicsType> getElectronicsTypes(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        return electronicsTypeService.findAllByPages(pageable);
    }


    // Получить все типы электроники
    @GetMapping
    @Operation(
            summary = "Найти все типы электроники",
            description = "Возвращает список всех типов электроники",
            responses = {
                    @ApiResponse(description = "Типы электроники найдены")
            }
    )
    public List<ElectronicsType> findAllElectronicsTypes() {
        return electronicsTypeService.findAllElectronicsTypes();
    }

    // Получить тип электроники по ID
    @Operation(
            summary = "Получить тип электроники по ID",
            description = "Возвращает тип электроники по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Тип электроники найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": 1, \"name\": \"Телевизоры\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Тип электроники не найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Тип электроники с таким ID не найден\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ElectronicsType> getElectronicsTypeById(@PathVariable Long id) {
        Optional<ElectronicsType> electronicsType = electronicsTypeService.findElectronicsTypeById(id);
        return electronicsType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить новый тип электроники
    @Operation(
            summary = "Добавить новый тип электроники",
            description = "Добавляет новый тип электроники в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Тип электроники успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания типа электроники\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addElectronicsType(@Valid @RequestBody ElectronicsType electronicsType) {
        electronicsTypeService.saveElectronicsType(electronicsType);
    }
}