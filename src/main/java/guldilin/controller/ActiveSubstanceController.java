package guldilin.controller;

import guldilin.dto.ActiveSubstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ActiveSubstanceController implements ValidationExceptionHandler {

    private final guldilin.service.ActiveSubstanceService ActiveSubstanceService;

    @Autowired
    public ActiveSubstanceController(guldilin.service.ActiveSubstanceService ActiveSubstanceService) {
        this.ActiveSubstanceService = ActiveSubstanceService;
    }

    @GetMapping("/api/substances")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(ActiveSubstanceService.getAll(title, description));
    }

    @GetMapping("/api/substances/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(ActiveSubstanceService.get(id));
    }

    @PostMapping(
            path = "/api/substances",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ActiveSubstanceDTO ActiveSubstanceDTO) {
        return ResponseEntity.ok(ActiveSubstanceService.create(ActiveSubstanceDTO));
    }
}
