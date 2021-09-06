package guldilin.controller;

import guldilin.dto.ActiveSubstanceDTO;
import guldilin.service.interfaces.ActiveSubstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ActiveSubstanceController implements ValidationExceptionHandler {

    private final ActiveSubstanceService activeSubstanceService;

    @Autowired
    public ActiveSubstanceController(ActiveSubstanceService activeSubstanceService) {
        this.activeSubstanceService = activeSubstanceService;
    }

    @GetMapping("/api/substances")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(activeSubstanceService.getAll(title, description));
    }

    @GetMapping("/api/substances/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(activeSubstanceService.get(id));
    }

    @PostMapping(
            path = "/api/substances",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ActiveSubstanceDTO activeSubstanceDTO) {
        return ResponseEntity.ok(activeSubstanceService.create(activeSubstanceDTO));
    }
}
