package guldilin.controller;

import guldilin.dto.ProducerDTO;
import guldilin.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProducerController implements ValidationExceptionHandler {

    private final ProducerService ProducerService;

    @Autowired
    public ProducerController(ProducerService ProducerService) {
        this.ProducerService = ProducerService;
    }

    @GetMapping("/api/producers")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String contact
    ) {
            return ResponseEntity.ok(ProducerService.getAll( title, address, contact ));
    }

    @GetMapping("/api/producers/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ProducerService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/producers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid ProducerDTO ProducerDTO) {
            return ResponseEntity.ok(ProducerService.create(ProducerDTO));
    }
}
