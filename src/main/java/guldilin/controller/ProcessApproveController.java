package guldilin.controller;

import guldilin.dto.ProcessApproveDTO;
import guldilin.service.ProcessApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ProcessApproveController implements ValidationExceptionHandler {

    private final ProcessApproveService processApproveService;

    @Autowired
    public ProcessApproveController(guldilin.service.ProcessApproveService ProcessApproveService) {
        this.processApproveService = ProcessApproveService;
    }

    @GetMapping("/api/approve")
    public ResponseEntity<List<ProcessApproveDTO>> gets(
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) Long workerBy,
            @RequestParam(required = false) Long workerTo
    ) {
        return ResponseEntity.ok(processApproveService.getAll(null, level, workerBy, workerTo));
    }

    @GetMapping("/api/approve/{id}")
    public ResponseEntity<ProcessApproveDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(processApproveService.get(id));
    }

    @PostMapping(
            path = "/api/approve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ProcessApproveDTO ProcessApproveDTO) {
        return ResponseEntity.ok(processApproveService.create(ProcessApproveDTO));

    }

    @PostMapping(
            path = "/api/approve/{id}/approve",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> approve(@PathVariable Long id) {
        ProcessApproveDTO processApproveDTO = processApproveService.get(id);
        processApproveDTO.setApproved(true);
        return ResponseEntity.ok(processApproveService.update(processApproveDTO));
    }
}
