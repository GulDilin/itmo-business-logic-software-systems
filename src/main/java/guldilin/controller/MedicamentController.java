package guldilin.controller;

import guldilin.dto.MedicamentDTO;
import guldilin.dto.MedicamentFormulaDTO;
import guldilin.dto.UpdateMedicamentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MedicamentController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentService medicamentService;

    @Autowired
    public MedicamentController(guldilin.service.MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
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
        return ResponseEntity.ok(medicamentService.getAll(title, groupId, formulaId, activeSubstanceId));
    }

    @GetMapping(
            path ="/api/medicaments/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentService.get(id));
    }

    @GetMapping(
            path ="/api/medicaments/{id}/formula",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getFormula(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentService.getFormula(id));
    }

    @GetMapping(
            path ="/api/medicaments/{id}/processes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getProcesses(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentService.getProcesses(id));
    }

    @GetMapping(
            path ="/api/medicaments/{id}/processes/{processId}/approves",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getProcessApproves(@PathVariable Long id, @PathVariable Long processId) {
        return ResponseEntity.ok(medicamentService.getProcessesApproves(id, processId));
    }

    @PostMapping(
            path = "/api/medicaments/{id}/formula",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@PathVariable Long id, @RequestBody @Valid MedicamentFormulaDTO medicamentFormulaDTO) {
        return ResponseEntity.ok(medicamentService.createFormula(id ,medicamentFormulaDTO));
    }

    @PatchMapping(
            path = "/api/medicaments/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> patch(@PathVariable Long id, @RequestBody UpdateMedicamentDTO medicamentDTO) {
        medicamentDTO.setId(id);
        return ResponseEntity.ok(medicamentService.update(medicamentDTO));
    }

    @PostMapping(
            path = "/api/medicaments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentDTO medicamentDTO) {
        return ResponseEntity.ok(medicamentService.create(medicamentDTO));
    }
}
