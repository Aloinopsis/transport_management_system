package tms.modules.kontrahenci;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tms.common.exception.BusinessConflictException;
import tms.common.exception.ResourceNotFoundException;
import tms.modules.kontrahenci.dto.KontrahentRequest;
import tms.modules.kontrahenci.dto.KontrahentResponse;
import tms.model.tables.Kontrahent;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KontrahentService {

    private final KontrahentRepository repository;
    private final KontrahentMapper mapper;

    public Page<KontrahentResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public KontrahentResponse findById(Long id) {
        return mapper.toResponse(getEntity(id));
    }

    @Transactional
    public KontrahentResponse create(KontrahentRequest request) {

        if (repository.existsByKod(request.kod())) {
            throw new BusinessConflictException("Kontrahent o kodzie już istnieje");
        }

        Kontrahent kontrahent = mapper.toEntity(request);

        Kontrahent saved = repository.saveAndFlush(kontrahent);

        return mapper.toResponse(saved);
    }

    private Kontrahent getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Nie znaleziono kontrahenta o ID: "
                                        + id
                        )
                );
    }

    @Transactional
    public void delete(Long id) {
        Kontrahent kontrahent = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono kontrahenta o ID: " + id));
        repository.delete(kontrahent);
    }

}
