package guldilin.controller;

import guldilin.dto.WorkerDTO;
import guldilin.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class WorkerController implements ValidationExceptionHandler {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/api/workers")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long workerRole,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String password
    ) {
        return ResponseEntity.ok(workerService.getAll(name, workerRole, email, login, password));
    }

    @GetMapping("/api/workers/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(workerService.get(id));
    }
}
