package tms.tables.kontrahenci;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontrahentRepository extends JpaRepository<Kontrahent, Long> {
    boolean existsByKod(String kod);
    boolean existsByKodAndIdNot(String kod, Long id);
}
