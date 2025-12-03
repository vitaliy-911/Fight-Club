package com.example.fight_club.controller;

import com.example.fight_club.dto.FighterResponseDto;
import com.example.fight_club.model.Fighter;
import com.example.fight_club.servise.FightersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fighter")
public class FightersController {

    private final FightersService fightersService;

    @Autowired
    public FightersController(FightersService fightersService) {
        this.fightersService = fightersService;
    }

    @PostMapping("/createFighter")
    @Operation(summary = "Create a new fighter", description = "Create a new fighter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fighter created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Fighter> createFighter(
            @Parameter(description = "Fighter details", required = true)
            @Valid @RequestBody Fighter fighter) {
        Fighter createdFighter = fightersService.createFighter(fighter);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFighter);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get 16 Fighters after sorting")
    public ResponseEntity<FighterResponseDto> getAllFighters() {
        List<Fighter> fighterList = fightersService.getAll();
        List<Fighter> collect = fighterList.stream()
                .sorted(Comparator.comparing(Fighter::getBeltColorFighter))
                .limit(16)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new FighterResponseDto(collect));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get fighter by id")
    public Fighter getFighterById(@PathVariable Long id) {
        return fightersService.getFighterById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deleteFighterById")
    public void deleteFighterById(@PathVariable Long id) {
        fightersService.deleteFighterById(id);
    }
}
