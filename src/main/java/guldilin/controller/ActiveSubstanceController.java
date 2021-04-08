package guldilin.controller;

import guldilin.dto.ActiveSubstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
        try {
            return ResponseEntity.ok(ActiveSubstanceService.getAll( title,  description));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/substances/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ActiveSubstanceService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/substances",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ActiveSubstanceDTO ActiveSubstanceDTO) {
        try {
            return ResponseEntity.ok(ActiveSubstanceService.create(ActiveSubstanceDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
