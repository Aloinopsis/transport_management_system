package tms.modules.kontrahenci;

import org.mapstruct.*;
import tms.common.mapper.TmsMapperConfig;
import tms.model.tables.Kontrahent;
import tms.modules.kontrahenci.dto.KontrahentPatchRequest;
import tms.modules.kontrahenci.dto.KontrahentRequest;
import tms.modules.kontrahenci.dto.KontrahentResponse;

/**
 * Klasa dla encji Kontrahent, która wyklucza pola podczas zapisu w bazie.
 * ID - jest generowane przez bazę.
 * dataDodania - jest tworzona przez @CreationTimestamp w encji.
 * dataOstatniejModyfikacji - jest aktualizowana przez trigger w bazie.
 */

@Mapper(config = TmsMapperConfig.class)

public interface KontrahentMapper {

    KontrahentResponse toResponse(Kontrahent kontrahent);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataDodania", ignore = true)
    @Mapping(target = "dataOstatniejModyfikacji", ignore = true)
    Kontrahent toEntity(KontrahentRequest request);

    @InheritConfiguration(name = "toEntity")
    void updateEntity(
            KontrahentRequest request,
            @MappingTarget Kontrahent kontrahent
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataDodania", ignore = true)
    @Mapping(target = "dataOstatniejModyfikacji", ignore = true)
    void patchEntity(KontrahentPatchRequest request, @MappingTarget Kontrahent kontrahent);

}
