package guldilin.controller;

import guldilin.dto.MedicamentFormulaDTO;
import guldilin.service.interfaces.MedicamentFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MedicamentFormulaController implements ValidationExceptionHandler {

    private final MedicamentFormulaService medicamentFormulaService;

    @Autowired
    public MedicamentFormulaController(MedicamentFormulaService medicamentFormulaService) {
        this.medicamentFormulaService = medicamentFormulaService;
    }

    @GetMapping("/api/formulas")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description) {
        return ResponseEntity.ok(medicamentFormulaService.getAll(title, description));
    }

    @PostMapping(
            path = "/api/formulas",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentFormulaDTO medicamentFormulaDTO) {
        return ResponseEntity.ok(medicamentFormulaService.create(medicamentFormulaDTO));
    }
}
