package tms.tables.kontrahenci;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tms.tables.kontrahenci.dto.KontrahentRequest;
import tms.tables.kontrahenci.dto.KontrahentResponse;

import java.net.URI;

@RestController
@RequestMapping("/api/kontrahenci")
@RequiredArgsConstructor
public class KontrahentController {

    private final KontrahentService service;

    @GetMapping
    public Page<KontrahentResponse> findAll(
            @PageableDefault(size = 20, sort = "kod")
            Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<KontrahentResponse> create(
            @Valid @RequestBody KontrahentRequest request
    ) {
        KontrahentResponse response = service.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }
}
