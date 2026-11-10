package tms.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import tms.tables.kontrahenci.Kontrahent;
import tms.tables.kontrahenci.dto.KontrahentRequest;
import tms.tables.kontrahenci.dto.KontrahentResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)

public interface KontrahentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataDodania", ignore = true)
    @Mapping(target = "dataOstatniejModyfikacji", ignore = true)
    Kontrahent toEntity(KontrahentRequest request);

    KontrahentResponse toResponse(Kontrahent kontrahent);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataDodania", ignore = true)
    @Mapping(target = "dataOstatniejModyfikacji", ignore = true)
    void updateEntity(
            KontrahentRequest request,
            @MappingTarget Kontrahent kontrahent
    );

}
