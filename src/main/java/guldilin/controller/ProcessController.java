package guldilin.controller;

import guldilin.dto.ProcessDTO;
import guldilin.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProcessController implements ValidationExceptionHandler {

    private final guldilin.service.ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/api/process")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long medicamentId
    ) {
        return ResponseEntity.ok(processService.getAll(status, medicamentId));
    }

    @GetMapping("/api/process/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(processService.get(id));
    }

    @PostMapping(
            path = "/api/process",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ProcessDTO processDTO) {
        return ResponseEntity.ok(processService.create(processDTO));
    }
}
