package tms.tables.kontrahenci;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tms.common.exception.BusinessConflictException;
import tms.common.exception.ResourceNotFoundException;
import tms.common.mapper.KontrahentMapper;
import tms.tables.kontrahenci.dto.KontrahentRequest;
import tms.tables.kontrahenci.dto.KontrahentResponse;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KontrahentService {

    private final KontrahentRepository repository;
    private final KontrahentMapper mapper;

    public Page<KontrahentResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(KontrahentResponse::fromEntity);
    }

    public KontrahentResponse findById(Long id) {
        return KontrahentResponse.fromEntity(getEntity(id));
    }

    @Transactional
    public KontrahentResponse create(KontrahentRequest request) {

        if (repository.existsByKod(request.kod())) {
            throw new BusinessConflictException("Kontrahent o kodzie już istnieje");
        }

        Kontrahent kontrahent = mapper.toEntity(request);

        kontrahent.setDataDodania(new Timestamp(System.currentTimeMillis()));

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

}
