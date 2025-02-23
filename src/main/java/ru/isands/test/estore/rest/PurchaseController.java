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
import ru.isands.test.estore.dao.dto.PurchaseDto;
import ru.isands.test.estore.dao.dto.PurchaseDtoGet;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dao.mapper.PurchaseGetMapper;
import ru.isands.test.estore.dao.mapper.PurchaseMapper;
import ru.isands.test.estore.dao.service.PurchaseServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;


@RestController
@Tag(name = "Purchase", description = "Сервис для управления покупками")
@RequestMapping(value = "/estore/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseServiceImpl purchaseService;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseGetMapper purchaseGetMapper;
    @GetMapping("/sorted")
    @Operation(
            summary = "Получить сортированный список покупок",
            description = "Возвращает список покупок с возможностью сортировки по дате",
            responses = {
                    @ApiResponse(description = "Список покупок получен"),
                    @ApiResponse(responseCode = "400", description = "Некорректное направление сортировки",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректное направление сортировки\" }"
                                    )
                            )
                    )
            }
    )
    public List<PurchaseDtoGet> getPurchases() {
        return purchaseService.findAllPurchasesSorted();
    }

    @Operation(
            summary = "Получить список покупок с пагинацией",
            description = "Возвращает постраничный список покупок, используя параметры offset и limit",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список покупок получен",
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
    public Page<PurchaseDtoGet> getPurchases(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0)Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100)Integer limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        return purchaseService.findAllByPages(pageable);
    }
    @GetMapping
    @Operation(
            summary = "Получить все покупки",
            description = "Возвращает список всех покупок",
            responses = {
                    @ApiResponse(description = "Покупки найдены")
            }
    )
    public List<PurchaseDtoGet> findAllPurchases() {
        return purchaseService.findAllPurchases();
    }

    @Operation(
            summary = "Получить покупку по ID",
            description = "Возвращает покупку по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Покупка найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": 1, \"electronicsId\": 2, \"employeeId\": 3, \"storeId\": 1, \"purchaseDatetime\": \"2024-02-21T10:30:00Z\", \"purchaseTypeId\": 4 }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Покупка не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Покупка с таким ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDtoGet> getPurchaseById(@PathVariable Long id) {
        PurchaseDtoGet purchaseDtoGet = purchaseGetMapper
                .toDto(purchaseService.findPurchaseById(id)
                        .orElseThrow(() -> new RuntimeException("Store with ID " + id + " not found")));
//        return ResponseEntity.ok(purchaseDtoGet);
        return ResponseEntity.ok(purchaseDtoGet);
    }

    @Operation(
            summary = "Добавить новую покупку",
            description = "Добавляет новую покупку в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Покупка успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания покупки\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPurchase(@Valid @RequestBody PurchaseDto purchaseDto) {
        Purchase purchase = purchaseMapper.toEntity(purchaseDto);
        purchaseService.savePurchase(purchase, purchaseDto.getElectronicsId(), purchaseDto.getEmployeeId(),
                purchaseDto.getStoreId(), purchaseDto.getPurchaseTypeId());
    }

    @Operation(
            summary = "Обновить существующую покупку",
            description = "Обновляет данные существующей покупки в базе данных",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Покупка успешно обновлена"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для обновления покупки\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Покупка не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Покупка с указанным ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @PutMapping("/{purchaseId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePurchase(
            @PathVariable Long purchaseId,
            @Valid @RequestBody PurchaseDto purchaseDto) {

        Purchase purchase = purchaseMapper.toEntity(purchaseDto);
        purchaseService.updatePurchase(purchaseId, purchase,
                purchaseDto.getElectronicsId(),
                purchaseDto.getEmployeeId(),
                purchaseDto.getStoreId(),
                purchaseDto.getPurchaseTypeId());
    }

}
