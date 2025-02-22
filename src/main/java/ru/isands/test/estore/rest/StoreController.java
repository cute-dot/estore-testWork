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

import org.springframework.web.bind.annotation.RequestBody;
import ru.isands.test.estore.dao.entity.Store;
import ru.isands.test.estore.dao.service.StoreServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Store", description = "Сервис для выполнения операций над магазинами")
@RequestMapping(value = "/estore/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreServiceImpl storeService;

    // Получить все магазины
    @GetMapping
    @Operation(
            summary = "Найти все магазины",
            description = "Возвращает список всех магазинов",
            responses = {
                    @ApiResponse(description = "Магазины найдены")
            }
    )
    public List<Store> findAllStores() {
        return storeService.findAllStores();
    }
    @Operation(
            summary = "Получить список магазинов с пагинацией",
            description = "Возвращает постраничный список магазинов, используя параметры offset и limit",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список магазинов получен",
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
    public Page<Store> getStores(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        return storeService.findAllByPages(pageable);
    }
    // Получить магазин по ID
    @Operation(
            summary = "Получить магазин по ID",
            description = "Возвращает магазин по его уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Магазин найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": 1, \"name\": \"Магазин электроники\", \"address\": \"Москва, ул. Тверская, д. 10\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Магазин не найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Магазин с таким ID не найден\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Optional<Store> store = storeService.findStoreById(id);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить новый магазин
    @Operation(
            summary = "Добавить новый магазин",
            description = "Добавляет новый магазин в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Магазин успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания магазина\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStore(@Valid @RequestBody Store store) {
        storeService.saveStore(store);
    }
}
