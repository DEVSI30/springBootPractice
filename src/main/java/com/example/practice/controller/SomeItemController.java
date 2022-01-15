package com.example.practice.controller;

import com.example.practice.dto.ResponseDTO;
import com.example.practice.dto.SomeItemDTO;
import com.example.practice.service.SomeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/someItems")
public class SomeItemController {

    private final SomeItemService service;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<SomeItemDTO>>> getAllSomeItems() {
        return ResponseEntity.ok().body(service.getList());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<SomeItemDTO>> saveSomeItem(@RequestBody SomeItemDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<SomeItemDTO>> updateSomeItem(@RequestBody SomeItemDTO dto) {
        ResponseDTO<SomeItemDTO> updateResult = service.update(dto);

        if (updateResult.getError() != null &&updateResult.getError().equalsIgnoreCase("No Entity")) {
            return ResponseEntity.badRequest().body(updateResult);
        }

        return ResponseEntity.ok().body(updateResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<SomeItemDTO>> getSomeItemById(@PathVariable String id) {
        ResponseDTO<SomeItemDTO> result = service.findById(id);

        if (result.getError() != null && result.getError().equalsIgnoreCase("No Entity")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok().body(result);
    }


}
