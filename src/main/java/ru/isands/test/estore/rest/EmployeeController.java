package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.service.EmployeeServiceImpl;

import javax.validation.Valid;
import java.util.List;


@RestController
@Tag(name = "Employee", description = "Сервис для выполнения операций над сотрудниками магазина")
@RequestMapping("/estore/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeServiceImpl employeeService;
	@GetMapping
	@Operation(
			summary = "Найти всех сотрудников",
			description = "Возвращает всех сотрудников",
			responses = {
		@ApiResponse(description = "Сотрудники")
	})
	public List<Employee> findAllEmployees() {
		return employeeService.findAllEmployees();
	}
	@Operation(
			summary = "Получить сотрудника по ID",
			description = "Возвращает сотрудника по его уникальному идентификатору",
			responses = {
					@ApiResponse(responseCode = "200", description = "сотрудник найден",
							content = @Content(mediaType = "application/json",
									schema = @Schema(
											example = "{ \"id\": 1, \"lastName\": \"Ivanov\", \"firstName\": \"Ivan\", \"surname\": \"Ivanovich\", \"birthDate\": \"1985-01-15\", \"position\": { \"id\": 1, \"name\": \"Manager\" }, \"store\": { \"id\": 1, \"name\": \"Electro Store 1\", \"address\": \"123 Main St, Springfield\" } }"
							)
							)
					),
					@ApiResponse(responseCode = "404", description = "сотрудник не найдено",
							content = @Content(mediaType = "application/json",
									schema = @Schema(
											example = "{ \"error\": \"Устройство с таким ID не найдено\" }"
									)
							)
					)
			}
	)
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(
			 @PathVariable Long id) {

		return employeeService.findEmployeeById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(
			summary = "Добавить нового сотрудника",
			description = "Добавляет нового сотрудника в базу данных",
			responses = {
					@ApiResponse(responseCode = "201", description = "Сотрудник успешно создан",
							content = @Content(mediaType = "application/json",
									schema = @Schema(
											example = "{ \"id\": 1, \"lastName\": \"Ivanov\", \"firstName\": \"Ivan\", \"surname\": \"Ivanovich\", \"birthDate\": \"1985-01-15\", \"position\": { \"id\": 1, \"name\": \"Manager\" }, \"store\": { \"id\": 1, \"name\": \"Electro Store 1\", \"address\": \"123 Main St, Springfield\" }, \"gender\": true, \"electronicsTypes\": [] }"
									)
							)
					),
					@ApiResponse(responseCode = "400", description = "Некорректные данные",
							content = @Content(mediaType = "application/json",
									schema = @Schema(
											example = "{ \"error\": \"Некорректные данные для создания сотрудника\" }"
									)
							)
					)
			}
	)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employee));
	}

}
