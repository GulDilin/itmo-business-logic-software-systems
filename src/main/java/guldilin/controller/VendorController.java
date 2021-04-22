package guldilin.controller;

import guldilin.dto.VendorDTO;
import guldilin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VendorController implements ValidationExceptionHandler {

    private final VendorService VendorService;

    @Autowired
    public VendorController(VendorService VendorService) {
        this.VendorService = VendorService;
    }

    @GetMapping("/api/vendors")
    public ResponseEntity<Object> gets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String contact
    ) {
            return ResponseEntity.ok(VendorService.getAll( title, address, contact ));
    }

    @GetMapping("/api/vendors/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(VendorService.get(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/api/vendors",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody @Valid VendorDTO VendorDTO) {
            return ResponseEntity.ok(VendorService.create(VendorDTO));
    }
}
