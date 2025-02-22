package ru.isands.test.estore.rest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.dto.ElectronicsStoreDtoPost;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.ElectronicsStore;
import ru.isands.test.estore.dao.entity.ElectronicsStoreId;
import ru.isands.test.estore.dao.entity.Position;
import ru.isands.test.estore.dao.mapper.ElectronicsStoreMappper;
import ru.isands.test.estore.dao.service.ElectronicStoreServiceImpl;
import ru.isands.test.estore.dao.service.PositionServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@Tag(name = "ElectronicsStore", description = "Сервис для выполнения операций над складами электроники")
@RequestMapping(value = "/estore/api/electronicsStore")
@RequiredArgsConstructor
public class ElectronicsStoreController {

    private final ElectronicStoreServiceImpl electronicsStoreService;


    // Получить все записи о наличии электроники в магазинах
    @GetMapping
    @Operation(
            summary = "Найти все записи о наличии электроники в магазинах",
            description = "Возвращает список всех записей о наличии электроники в магазинах",
            responses = {
                    @ApiResponse(description = "Записи о наличии электроники в магазинах найдены")
            }
    )
    public List<ElectronicsStore> findAllElectronicsStores() {
        return electronicsStoreService.findAllElectronicStores();
    }

    // Получить запись о наличии электроники в магазине по ID
    @Operation(
            summary = "Получить запись о наличии электроники в магазине по ID",
            description = "Возвращает запись о наличии электроники в магазине по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Запись найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"electronicsId\": 1, \"storeId\": 2, \"quantity\": 100 }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Запись не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Запись с таким ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{electronicsId}/{storeId}")
    public ResponseEntity<ElectronicsStore> getElectronicsStoreById(@PathVariable Long electronicsId, @PathVariable Long storeId) {
        ElectronicsStoreId electronicsStoreId = new ElectronicsStoreId();
        electronicsStoreId.setElectronicsId(electronicsId);
        electronicsStoreId.setStoreId(storeId);
        Optional<ElectronicsStore> electronicsStore = electronicsStoreService.findElectronicStoreById(electronicsStoreId);
        return electronicsStore.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Добавить запись о наличии электроники в магазине
    @Operation(
            summary = "Добавить запись о наличии электроники в магазине",
            description = "Добавляет запись о наличии электроники в магазине в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Запись успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания записи о наличии электроники\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addElectronicsStore(@Valid @RequestBody ElectronicsStoreDtoPost electronicsStoreDtoPost) {
        electronicsStoreService.saveElectronicStore(electronicsStoreDtoPost);
    }
}

