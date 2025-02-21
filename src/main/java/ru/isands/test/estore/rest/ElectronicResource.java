package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.repo.ElectronicRepository;
import ru.isands.test.estore.dao.service.ElectronicService;
import ru.isands.test.estore.dao.service.ElectronicServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estore/api/electronics")
@RequiredArgsConstructor
public class ElectronicResource {

    private final ElectronicServiceImpl electronicService;


//    @GetMapping
//    public Page<Electronic> getList(Pageable pageable) {
//        return electronicRepository.findAll(pageable);
//    }
//
//    @GetMapping("/{id}")
//    public Electronic getOne(@PathVariable Long id) {
//        Optional<Electronic> electronicOptional = electronicRepository.findById(id);
//        return electronicOptional.orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
//    }
//
//    @PostMapping
//    public Electronic create(@RequestBody @Valid Electronic electronic) {
//        return electronicRepository.save(electronic);
//    }
//
//    @PatchMapping("/{id}")
//    public Electronic patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
//        Electronic electronic = electronicRepository.findById(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
//
//        objectMapper.readerForUpdating(electronic).readValue(patchNode);
//
//        return electronicRepository.save(electronic);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public Electronic delete(@PathVariable Long id) {
//        Electronic electronic = electronicRepository.findById(id).orElse(null);
//        if (electronic != null) {
//            electronicRepository.delete(electronic);
//        }
//        return electronic;
//    }
}
