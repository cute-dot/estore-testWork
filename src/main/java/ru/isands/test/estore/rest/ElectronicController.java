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
import ru.isands.test.estore.dao.dto.ElectronicDto;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.mapper.ElectronicMapper;
import ru.isands.test.estore.dao.service.ElectronicServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Electronic", description = "Сервис для выполнения операций над электроникой")
@RequestMapping(value = "/estore/api/electronic")
@RequiredArgsConstructor
public class ElectronicController {
    private final ElectronicServiceImpl electronicService;
    private final ElectronicMapper electronicMapper;

    @GetMapping
    @Operation(
            summary = "Найти всю электронику",
            description = "Возвращает список всей электроники",
            responses = {
                    @ApiResponse(description = "Электроника найдена")
            }
    )
    public List<Electronic> findAllElectronics() {
        return electronicService.findAllElectronics();
    }

    @Operation(
            summary = "Получить список электроники с пагинацией",
            description = "Возвращает постраничный список электроники, используя параметры offset и limit",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список электроники получен",
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
    public Page<Electronic> getElectronics(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        return electronicService.findAllByPages(pageable);
    }
    @GetMapping("/{id}/available")
    public ResponseEntity<Boolean> checkProductAvailability(
            @PathVariable Long productId) {

        boolean isAvailable = productService.isProductAvailable(productId);

        if (isAvailable) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
    @Operation(
            summary = "Получить электронику по ID",
            description = "Возвращает электронику по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Электроника найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": 1, \"name\": \"Телевизор Samsung\", \"typeId\": 2, \"price\": 59999.99, \"quantity\": 10, \"archived\": false, \"description\": \"Современный LED-телевизор\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Электроника не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Электроника с таким ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Electronic> getElectronicById(@PathVariable Long id) {
        Optional<Electronic> electronic = electronicService.findElectronicById(id);
        return electronic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Добавить новую электронику",
            description = "Добавляет новый товар в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Электроника успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания товара\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addElectronic(@Valid @RequestBody ElectronicDto electronicDto) {
        Electronic electronic = electronicMapper.toEntity(electronicDto);
        electronicService.saveElectronic(electronic, electronicDto.getTypeId());
    }
}

