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
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.dao.service.PurchaseTypeServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "PurchaseType", description = "Сервис для выполнения операций над типами покупок")
@RequestMapping(value = "/estore/api/purchaseType")
@RequiredArgsConstructor
public class PurchaseTypeController {

    private final PurchaseTypeServiceImpl purchaseTypeService;

    // Получить все типы покупок
    @GetMapping
    @Operation(
            summary = "Найти все типы покупок",
            description = "Возвращает список всех типов покупок",
            responses = {
                    @ApiResponse(description = "Типы покупок найдены")
            }
    )
    public List<PurchaseType> findAllPurchaseTypes() {
        return purchaseTypeService.findAllPurchaseTypes();
    }
    @Operation(
            summary = "Получить список типов покупок с пагинацией",
            description = "Возвращает постраничный список типов покупок, используя параметры offset и limit",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список типов покупок получен",
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
    public Page<PurchaseType> getPurchaseTypes(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        return purchaseTypeService.findAllByPages(pageable);
    }

    // Получить тип покупки по ID
    @Operation(
            summary = "Получить тип покупки по ID",
            description = "Возвращает тип покупки по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Тип покупки найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": 1, \"name\": \"Оптовая покупка\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Тип покупки не найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Тип покупки с таким ID не найден\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseType> getPurchaseTypeById(@PathVariable Long id) {
        Optional<PurchaseType> purchaseType = purchaseTypeService.findPurchaseTypeById(id);
        return purchaseType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Добавить новый тип покупки",
            description = "Добавляет новый тип покупки в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Тип покупки успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания типа покупки\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPurchaseType(@Valid @RequestBody PurchaseType purchaseType) {
        purchaseTypeService.savePurchaseType(purchaseType);
    }

    @Operation(
            summary = "Обновить существующий тип покупки",
            description = "Обновляет существующий тип покупки в базе данных по его идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Тип покупки успешно обновлен"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для обновления типа покупки\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Тип покупки не найден",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Тип покупки с указанным ID не найден\" }"
                                    )
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePurchaseType(@PathVariable Long id,
                                           @Valid @RequestBody PurchaseType purchaseType) {
        purchaseTypeService.updatePurchaseType(id, purchaseType);
    }

}

