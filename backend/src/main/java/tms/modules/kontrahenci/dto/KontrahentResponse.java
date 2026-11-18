package tms.modules.kontrahenci.dto;

import java.time.LocalDateTime;

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
        LocalDateTime dataDodania,
        LocalDateTime dataOstatniejModyfikacji
        ) {}
