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
import ru.isands.test.estore.dao.dto.ElectronicsTypeEmployeeDto;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployee;
import ru.isands.test.estore.dao.entity.ElectronicsTypeEmployeeId;
import ru.isands.test.estore.dao.service.ElectronicsTypeEmployeeService;
import ru.isands.test.estore.dao.service.ElectronicsTypeEmployeeServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "ElectronicsTypeEmployee", description = "Сервис для работы с типами электроники и сотрудниками")
@RequestMapping(value = "/estore/api/electronicsTypeEmployee")
@RequiredArgsConstructor
public class ElectronicsTypeEmployeeController {

    private final ElectronicsTypeEmployeeServiceImpl electronicsTypeEmployeeService;

    @GetMapping
    @Operation(
            summary = "Найти все связи типа электроники и сотрудников",
            description = "Возвращает список всех записей связи типа электроники и сотрудников",
            responses = {
                    @ApiResponse(description = "Связи найдены")
            }
    )
    public List<ElectronicsTypeEmployee> findAllElectronicsTypeEmployees() {
        return electronicsTypeEmployeeService.findAllElectronicsTypeEmployee();
    }

    @Operation(
            summary = "Получить связь типа электроники и сотрудника по ID",
            description = "Возвращает связь по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Связь найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"electronicsTypeId\": 1, \"employeeId\": 2 }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Связь не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Запись с таким ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{electronicsTypeId}/{employeeId}")
    public ResponseEntity<ElectronicsTypeEmployee> getElectronicsTypeEmployeeById(@PathVariable Long electronicsTypeId, @PathVariable Long employeeId) {
        ElectronicsTypeEmployeeId id = new ElectronicsTypeEmployeeId();
        id.setElectronicsTypeId(electronicsTypeId);
        id.setEmployeeId(employeeId);
        Optional<ElectronicsTypeEmployee> entity = electronicsTypeEmployeeService.findElectronicsTypeEmployeeById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Добавить связь типа электроники и сотрудника",
            description = "Добавляет новую связь в базу данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Запись успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Некорректные данные для создания записи\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addElectronicsTypeEmployee(@Valid @RequestBody ElectronicsTypeEmployeeDto dto) {
        electronicsTypeEmployeeService.saveElectronicsTypeEmployee(dto);
    }
}
