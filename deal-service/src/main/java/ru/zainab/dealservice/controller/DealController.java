package ru.zainab.dealservice.controller;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zainab.dealservice.dto.DealCreateRequest;
import ru.zainab.dealservice.dto.DealResponse;
import ru.zainab.dealservice.dto.DealUpdateRequest;
import ru.zainab.dealservice.service.DealService;

import java.util.List;


@RestController
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @PostMapping
    public ResponseEntity<DealResponse> createDeal(@Valid @RequestBody DealCreateRequest request) {
        DealResponse response = dealService.createDeal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealResponse> updateDeal(
            @PathVariable Long id,
            @Valid @RequestBody DealUpdateRequest request
    ) {
        DealResponse response = dealService.updateDeal(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealResponse> getDealId(@PathVariable Long id) {
        DealResponse response = dealService.getDeal(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DealResponse>> getAllDeals() {
        return ResponseEntity.ok(dealService.findAllDeals());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DealResponse> deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}
