package guldilin.controller;

import guldilin.dto.MedicamentFormulaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MedicamentFormulaController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentFormulaService MedicamentFormulaService;

    @Autowired
    public MedicamentFormulaController(guldilin.service.MedicamentFormulaService MedicamentFormulaService) {
        this.MedicamentFormulaService = MedicamentFormulaService;
    }

    @GetMapping("/api/formulas")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(MedicamentFormulaService.getAll(title, description));
    }

    @GetMapping("/api/formulas/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(MedicamentFormulaService.get(id));
    }

    @PostMapping(
            path = "/api/formulas",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentFormulaDTO MedicamentFormulaDTO) {
        return ResponseEntity.ok(MedicamentFormulaService.create(MedicamentFormulaDTO));
    }
}
