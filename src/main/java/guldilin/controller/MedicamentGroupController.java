package guldilin.controller;

import guldilin.dto.MedicamentGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class MedicamentGroupController implements ValidationExceptionHandler {

    private final guldilin.service.MedicamentGroupService MedicamentGroupService;

    @Autowired
    public MedicamentGroupController(guldilin.service.MedicamentGroupService MedicamentGroupService) {
        this.MedicamentGroupService = MedicamentGroupService;
    }

    @GetMapping("/api/groups")
    public ResponseEntity gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {
        try {
            return ResponseEntity.ok(MedicamentGroupService.getAll( title, description));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/groups/{id}")
    public ResponseEntity get(Integer id) {
        try {
            return ResponseEntity.ok(MedicamentGroupService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/groups",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(@RequestBody @Valid MedicamentGroupDTO MedicamentGroupDTO) {
        try {
            return ResponseEntity.ok(MedicamentGroupService.create(MedicamentGroupDTO));
        } catch (IllegalArgumentException e) {
            Map<String, String> errorMap  = new HashMap<String, String>() {{
                put("error", "Bad request");
                put("message", e.getMessage());
            }};
            return ResponseEntity.badRequest().body(errorMap);
        }
    }
}
