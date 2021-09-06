package guldilin.controller;

import guldilin.dto.ProcessApproveDTO;
import guldilin.model.Worker;
import guldilin.repository.WorkerRepository;
import guldilin.service.interfaces.ProcessApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ProcessApproveController implements ValidationExceptionHandler {

    private final ProcessApproveService processApproveService;
    private final WorkerRepository workerRepository;

    @Autowired
    public ProcessApproveController(
            ProcessApproveService processApproveService,
            WorkerRepository workerRepository
    ) {
        this.processApproveService = processApproveService;
        this.workerRepository = workerRepository;
    }

    @GetMapping("/api/approve")
    public ResponseEntity<List<ProcessApproveDTO>> gets(
            @RequestParam(required = false) String level,
            @RequestParam(required = false) Long workerBy,
            @RequestParam(required = false) Long workerTo,
            @RequestParam(required = false) Date create,
            @RequestParam(required = false) Date update
    ) {
        return ResponseEntity.ok(processApproveService.getAll(level, workerBy, workerTo, create, update));
    }

    @GetMapping("/api/approve/{id}")
    public ResponseEntity<ProcessApproveDTO> get(@PathVariable Integer id) {
        return ResponseEntity.ok(processApproveService.get(id));
    }

    @PostMapping(
            path = "/api/approve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ProcessApproveDTO processApproveDTO, Authentication authentication) {
        Worker worker = workerRepository.findByLogin(authentication.getName()).orElseThrow(
                () -> new IllegalArgumentException("Cant find worker")
        );
        processApproveDTO.setWorkerBy(worker.getId());
        return ResponseEntity.ok(processApproveService.create(processApproveDTO));

    }

    @PostMapping(
            path = "/api/approve/{id}/approve",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> approve(@PathVariable Integer id) {
        ProcessApproveDTO processApproveDTO = processApproveService.get(id);
        processApproveDTO.setApproved(true);
        return ResponseEntity.ok(processApproveService.update(processApproveDTO));
    }
}
