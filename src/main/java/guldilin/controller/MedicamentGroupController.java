package guldilin.controller;

import guldilin.dto.MedicamentGroupDTO;
import guldilin.service.interfaces.MedicamentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MedicamentGroupController implements ValidationExceptionHandler {

    private final MedicamentGroupService medicamentGroupService;

    @Autowired
    public MedicamentGroupController(MedicamentGroupService medicamentGroupService) {
        this.medicamentGroupService = medicamentGroupService;
    }

    @GetMapping("/api/groups")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(medicamentGroupService.getAll(title, description));
    }

    @GetMapping("/api/groups/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(medicamentGroupService.get(id));
    }

    @PostMapping(
            path = "/api/groups",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentGroupDTO medicamentGroupDTO) {
        return ResponseEntity.ok(medicamentGroupService.create(medicamentGroupDTO));
    }
}
