package guldilin.controller;

import guldilin.dto.MedicamentFormulaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class MedicamentFormulaController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentFormulaService MedicamentFormulaService;

    @Autowired
    public MedicamentFormulaController(guldilin.service.MedicamentFormulaService MedicamentFormulaService) {
        this.MedicamentFormulaService = MedicamentFormulaService;
    }

    @GetMapping("/api/formulas")
    public ResponseEntity gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        try {
            return ResponseEntity.ok(MedicamentFormulaService.getAll( title, description));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/formulas/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(MedicamentFormulaService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/formulas",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(@RequestBody @Valid MedicamentFormulaDTO MedicamentFormulaDTO) {
        try {
            return ResponseEntity.ok(MedicamentFormulaService.create(MedicamentFormulaDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
