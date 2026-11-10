package tms.tables.kontrahenci.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public record KontrahentRequest(

        @NotBlank
        @Size(max = 50)
        String kod,

        @NotBlank
        @Size(max = 200)
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

        @Email
        String email,

        String telefon,
        String website,

        Short status,
        String uwagi
) {
    public KontrahentRequest {
        Objects.requireNonNull(kod, "Kod jest wymagany");
        Objects.requireNonNull(nazwa, "Nazwa jest wymagana");
        Objects.requireNonNull(status, "Status jest wymagany");
    }
}
