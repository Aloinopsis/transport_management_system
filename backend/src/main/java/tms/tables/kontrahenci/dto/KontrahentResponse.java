package tms.tables.kontrahenci.dto;

import tms.tables.kontrahenci.Kontrahent;

import java.sql.Timestamp;

public record KontrahentResponse(
        Long id,
        String kod,
        String nazwa,
        String nipKraj,
        String nip,
        String regon,
        String vatEu,
        String adresKodPocztowy,
        String adresMiasto,
        String adresUlica,
        String adresNrBudynku,
        String adresNrLokalu,
        String email,
        String telefon,
        String website,
        short status,
        String uwagi,
        Timestamp dataDodania,
        Timestamp dataOstatniejModyfikacji
        ) {

    public static KontrahentResponse fromEntity(
            Kontrahent kontrahent
    ) {
        return new KontrahentResponse(
                kontrahent.getId(),
                kontrahent.getKod(),
                kontrahent.getNazwa(),
                kontrahent.getNipKraj(),
                kontrahent.getNip(),
                kontrahent.getRegon(),
                kontrahent.getVatEu(),
                kontrahent.getAdresKodPocztowy(),
                kontrahent.getAdresMiasto(),
                kontrahent.getAdresUlica(),
                kontrahent.getAdresNrBudynku(),
                kontrahent.getAdresNrLokalu(),
                kontrahent.getEmail(),
                kontrahent.getTelefon(),
                kontrahent.getWebsite(),
                kontrahent.getStatus(),
                kontrahent.getUwagi(),
                kontrahent.getDataDodania(),
                kontrahent.getDataOstatniejModyfikacji()
        );
    }

}
