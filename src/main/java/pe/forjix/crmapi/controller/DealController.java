package pe.forjix.crmapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.forjix.crmapi.dto.DealRequestDTO;
import pe.forjix.crmapi.dto.DealResponseDTO;
import pe.forjix.crmapi.service.DealService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    // Crear un nuevo Deal (POST /api/deals)
    @PostMapping
    public ResponseEntity<DealResponseDTO> createDeal(@RequestBody DealRequestDTO request) {
        DealResponseDTO newDeal = dealService.createDeal(request);
        return new ResponseEntity<>(newDeal, HttpStatus.CREATED);
    }

    // Obtener todos los Deals (GET /api/deals)
    @GetMapping
    public ResponseEntity<List<DealResponseDTO>> getAllDeals() {
        List<DealResponseDTO> deals = dealService.getAllDeals();
        return ResponseEntity.ok(deals);
    }

    // Obtener un Deal específico por ID (GET /api/deals/{id})
    @GetMapping("/{id}")
    public ResponseEntity<DealResponseDTO> getDealById(@PathVariable UUID id) {
        DealResponseDTO deal = dealService.getDealById(id);
        return ResponseEntity.ok(deal);
    }
}
