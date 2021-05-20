package guldilin.controller;

import guldilin.dto.MedicamentTypeDTO;
import guldilin.service.MedicamentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MedicamentTypeController implements ValidationExceptionHandler {

    private final MedicamentTypeService medicamentTypeService;

    @Autowired
    public MedicamentTypeController(MedicamentTypeService medicamentTypeService) {
        this.medicamentTypeService = medicamentTypeService;
    }

    @GetMapping("/api/types")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(medicamentTypeService.getAll(title, description));
    }

    @GetMapping("/api/types/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(medicamentTypeService.get(id));
    }

    @PostMapping(
            path = "/api/types",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentTypeDTO medicamentTypeDTO) {
        return ResponseEntity.ok(medicamentTypeService.create(medicamentTypeDTO));
    }
}
