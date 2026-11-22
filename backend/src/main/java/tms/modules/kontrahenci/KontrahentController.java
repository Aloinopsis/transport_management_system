package tms.modules.kontrahenci;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tms.modules.kontrahenci.dto.KontrahentRequest;
import tms.modules.kontrahenci.dto.KontrahentResponse;

import java.net.URI;

@RestController
@RequestMapping("/api/kontrahenci")
@RequiredArgsConstructor
public class KontrahentController {

    private final KontrahentService service;

    @Operation(description = "Zwraca listę kontrahentów posortowanych po kodzie")
    @GetMapping
    public Page<KontrahentResponse> findAll(
            @PageableDefault(size = 100, sort = "kod")
            Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Pobiera kontrahenta", description = "Zwraca szczegóły kontrahenta o wskazanym ID")
    @ApiResponse(responseCode = "200", description = "Znaleziono kontrahenta")
    @ApiResponse(responseCode = "404", description = "Kontrahent nie istnieje")
    @GetMapping("/{id}")
    public KontrahentResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(description = "Tworzy nową encję kontrahenta")
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

    @Operation(summary = "Usuwanie kontrahenta", description = "Usuwa kontrahenta o wskazanym id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Kontrahent usunięty poprawnie"),
            @ApiResponse(responseCode = "404", description = "Kontrahent o wskazanym ID nie istnieje"),
            @ApiResponse(responseCode = "409", description = "Kontrahent posiada powiązania")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
