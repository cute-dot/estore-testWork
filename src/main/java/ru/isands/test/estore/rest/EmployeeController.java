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
import ru.isands.test.estore.dao.dto.EmployeeDtoPost;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.mapper.EmployeeMapper;
import ru.isands.test.estore.dao.service.EmployeeServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@RestController
@Tag(name = "Employee", description = "Сервис для выполнения операций над сотрудниками магазина")
@RequestMapping(value = "/estore/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeServiceImpl employeeService;
	private final EmployeeMapper employeeMapper;
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
											example = "{ \"error\": \"Cотрудник с таким ID не найдено\" }"
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
			summary = "Получить список сотрудников с пагинацией",
			description = "Возвращает постраничный список сотрудников, используя параметры offset и limit",
			responses = {
					@ApiResponse(responseCode = "200", description = "Список сотрудников получен",
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
	public Page<Employee> getEmployees(
			@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
			@RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit) {

		Pageable pageable = PageRequest.of(offset, limit);
		return employeeService.findAllByPages(pageable);
	}

	@Operation(
			summary = "Добавить нового сотрудника",
			description = "Добавляет нового сотрудника в базу данных",
			responses = {
					@ApiResponse(responseCode = "201", description = "Сотрудник успешно создан"),

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
	public void addEmployee(@Valid @RequestBody EmployeeDtoPost employeeDtoPost) {
		Employee employee = employeeMapper.toEntity(employeeDtoPost);
		employeeService.saveEmployee(employee, employeeDtoPost.getPositionId(), employeeDtoPost.getStoreId());
	}

	@Operation(
			summary = "Обновить данные сотрудника",
			description = "Обновляет данные сотрудника в базе данных по его ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Сотрудник успешно обновлён"),
					@ApiResponse(responseCode = "400", description = "Некорректные данные",
							content = @Content(mediaType = "application/json",
									schema = @Schema(
											example = "{ \"error\": \"Некорректные данные для обновления сотрудника\" }"
									)
							)
					),
					@ApiResponse(responseCode = "404", description = "Сотрудник не найден",
							content = @Content(mediaType = "application/json",
									schema = @Schema(
											example = "{ \"error\": \"Сотрудник не найден\" }"
									)
							)
					)
			}
	)
	@PutMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.OK)
	public void updateEmployee(
			@PathVariable Long employeeId,
			@Valid @RequestBody EmployeeDtoPost employeeDtoPost
	) {
		Employee employee = employeeMapper.toEntity(employeeDtoPost);

		employeeService.updateEmployee(employeeId, employee,
				employeeDtoPost.getPositionId(),
				employeeDtoPost.getStoreId());
	}

}
