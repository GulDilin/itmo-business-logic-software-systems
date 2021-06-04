package guldilin.controller;

import guldilin.dto.WorkerRoleDTO;
import guldilin.service.interfaces.WorkerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WorkerRoleController implements ValidationExceptionHandler {

    private final WorkerRoleService workerRoleService;

    @Autowired
    public WorkerRoleController(WorkerRoleService workerRoleService) {
        this.workerRoleService = workerRoleService;
    }

    @GetMapping("/api/roles")
    public ResponseEntity<Object> getRoles(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer level
    ) {
        return ResponseEntity.ok(workerRoleService.getAll(title, level));
    }

    @GetMapping("/api/roles/{id}")
    public ResponseEntity<Object> getRole(@PathVariable Integer id) {
        return ResponseEntity.ok(workerRoleService.get(id));
    }

    @PostMapping(
            path = "/api/roles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createRole(@RequestBody @Valid WorkerRoleDTO workerRoleDTO) {
        return ResponseEntity.ok(workerRoleService.create(workerRoleDTO));
    }
}
