package guldilin.controller;

import guldilin.dto.ProcessDTO;
import guldilin.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProcessController implements ValidationExceptionHandler {

    private final guldilin.service.ProcessService ProcessService;

    @Autowired
    public ProcessController(ProcessService ProcessService) {
        this.ProcessService = ProcessService;
    }

    @GetMapping("/api/process")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long medicamentId
    ) {
        try {
            return ResponseEntity.ok(ProcessService.getAll( status, medicamentId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/process/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ProcessService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/process",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ProcessDTO ProcessDTO) {
        try {
            return ResponseEntity.ok(ProcessService.create(ProcessDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
