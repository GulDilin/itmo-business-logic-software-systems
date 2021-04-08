package guldilin.controller;

import guldilin.dto.MedicamentInterractDTO;
import guldilin.service.MedicamentInterractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MedicamentInterractController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentInterractService MedicamentInterractService;

    @Autowired
    public MedicamentInterractController(guldilin.service.MedicamentInterractService MedicamentInterractService) {
        this.MedicamentInterractService = MedicamentInterractService;
    }

    @GetMapping("/api/interracts")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String description
    ) {
        try {
            return ResponseEntity.ok(MedicamentInterractService.getAll( description));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/interracts/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(MedicamentInterractService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/interracts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid MedicamentInterractDTO MedicamentInterractDTO) {
        try {
            return ResponseEntity.ok(MedicamentInterractService.create(MedicamentInterractDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
