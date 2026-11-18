
package tms.modules.kontrahenci.dto;

import jakarta.validation.constraints.*;

public record KontrahentRequest(

        @NotBlank
        @Size(max = 50)
        String kod,

        @NotBlank
        @Size(max = 200)
        String nazwa,

        @Size(max = 2)
        String nipKraj,

        @Size(max = 20)
        String nip,

        @Size(max = 20)
        String regon,

        @Size(max = 20)
        String vatEu,

        @Size(max = 20)
        String adresKodPocztowy,

        @Size(max = 100)
        String adresMiasto,

        @Size(max = 100)
        String adresUlica,

        @Size(max = 20)
        String adresNrBudynku,

        @Size(max = 20)
        String adresNrLokalu,

        @Email
        @Size(max = 254)
        String email,

        @Size(max = 30)
        String telefon,

        @Size(max = 254)
        String website,

        @NotNull
        Short status,

        String uwagi

) {}
