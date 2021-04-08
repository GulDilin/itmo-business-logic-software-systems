package guldilin.controller;

import guldilin.dto.MedicamentClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class MedicamentClassController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentClassService MedicamentClassService;

    @Autowired
    public MedicamentClassController(guldilin.service.MedicamentClassService MedicamentClassService) {
        this.MedicamentClassService = MedicamentClassService;
    }

    @GetMapping("/api/classes")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(MedicamentClassService.getAll(title, description));
    }

    @GetMapping("/api/classes/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        return ResponseEntity.ok(MedicamentClassService.get(id));
    }

    @PostMapping(
            path = "/api/classes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentClassDTO MedicamentClassDTO) {
        return ResponseEntity.ok(MedicamentClassService.create(MedicamentClassDTO));
    }
}
