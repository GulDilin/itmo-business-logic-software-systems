package guldilin.controller;

import guldilin.dto.MedicamentDTO;
import guldilin.dto.UpdateMedicamentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MedicamentController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentService MedicamentService;

    @Autowired
    public MedicamentController(guldilin.service.MedicamentService MedicamentService) {
        this.MedicamentService = MedicamentService;
    }

    @GetMapping(
            path = "/api/medicaments",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long groupId,
            @RequestParam(required = false) Long formulaId,
            @RequestParam(required = false) Long activeSubstanceId
    ) {
        return ResponseEntity.ok(MedicamentService.getAll(title, groupId, formulaId, activeSubstanceId));
    }

    @GetMapping(
            path ="/api/medicaments/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(MedicamentService.get(id));
    }

    @PatchMapping(
            path = "/api/medicaments/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> patch(@PathVariable Long id, @RequestBody UpdateMedicamentDTO medicamentDTO) {
        medicamentDTO.setId(id);
        return ResponseEntity.ok(MedicamentService.update(medicamentDTO));
    }

    @PostMapping(
            path = "/api/medicaments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentDTO MedicamentDTO) {
        return ResponseEntity.ok(MedicamentService.create(MedicamentDTO));
    }
}
