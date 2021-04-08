package guldilin.controller;

import guldilin.dto.MedicamentClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class MedicamentClassController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentClassService MedicamentClassService;

    @Autowired
    public MedicamentClassController(guldilin.service.MedicamentClassService MedicamentClassService) {
        this.MedicamentClassService = MedicamentClassService;
    }

    @GetMapping("/api/classes")
    public ResponseEntity gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        try {
            return ResponseEntity.ok(MedicamentClassService.getAll( title, description));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/classes/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(MedicamentClassService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/classes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(@RequestBody @Valid MedicamentClassDTO MedicamentClassDTO) {
        try {
            return ResponseEntity.ok(MedicamentClassService.create(MedicamentClassDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
