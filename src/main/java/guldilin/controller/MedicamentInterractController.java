package guldilin.controller;

import guldilin.dto.MedicamentInterractDTO;
import guldilin.service.interfaces.MedicamentInterractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MedicamentInterractController implements ValidationExceptionHandler {

    private final MedicamentInterractService medicamentInterractService;

    @Autowired
    public MedicamentInterractController(MedicamentInterractService medicamentInterractService) {
        this.medicamentInterractService = medicamentInterractService;
    }

    @GetMapping("/api/interracts")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(medicamentInterractService.getAll(description));
    }

    @GetMapping("/api/interracts/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(medicamentInterractService.get(id));
    }

    @PostMapping(
            path = "/api/interracts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentInterractDTO medicamentInterractDTO) {
        return ResponseEntity.ok(medicamentInterractService.create(medicamentInterractDTO));
    }
}
