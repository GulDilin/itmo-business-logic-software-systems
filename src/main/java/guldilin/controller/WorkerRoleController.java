package guldilin.controller;

import guldilin.dto.WorkerRoleDTO;
import guldilin.service.WorkerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WorkerRoleController implements ValidationExceptionHandler {

    private final WorkerRoleService workerRoleService;

    @Autowired
    public WorkerRoleController(WorkerRoleService workerRoleService) {
        this.workerRoleService = workerRoleService;
    }

    @GetMapping("/api/roles")
    public ResponseEntity getRoles(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer level
    ) {
        try {
            return ResponseEntity.ok(workerRoleService.getAll(title, level));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/roles/{id}")
    public ResponseEntity getRole(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(workerRoleService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/roles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createRole(@RequestBody @Valid WorkerRoleDTO workerRoleDTO) {
        try {
            return ResponseEntity.ok(workerRoleService.create(workerRoleDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
