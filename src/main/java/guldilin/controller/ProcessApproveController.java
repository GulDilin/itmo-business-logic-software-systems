package guldilin.controller;

import guldilin.dto.ProcessApproveDTO;
import guldilin.service.ProcessApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProcessApproveController implements ValidationExceptionHandler {

    private final guldilin.service.ProcessApproveService ProcessApproveService;

    @Autowired
    public ProcessApproveController(guldilin.service.ProcessApproveService ProcessApproveService) {
        this.ProcessApproveService = ProcessApproveService;
    }

    @GetMapping("/api/approve")
    public ResponseEntity gets(
            @RequestParam(required = false) String level,
            @RequestParam(required = false) Long workerBy,
            @RequestParam(required = false) Long workerTo,
            @RequestParam(required = false) Date create,
            @RequestParam(required = false) Date update
    ) {
        try {
            return ResponseEntity.ok(ProcessApproveService.getAll( level, workerBy, workerTo, create, update ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/approve/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ProcessApproveService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/approve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(@RequestBody @Valid ProcessApproveDTO ProcessApproveDTO) {
        try {
            return ResponseEntity.ok(ProcessApproveService.create(ProcessApproveDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
