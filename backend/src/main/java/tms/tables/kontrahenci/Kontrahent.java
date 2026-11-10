package tms.tables.kontrahenci;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
//@RequiredArgsConstructor
//@NoArgsConstructor
@Table(name = "KONTRAHENCI")
public class Kontrahent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "KOD")
    private String kod;

    @Column(name = "NAZWA")
    private String nazwa;

    @Column(name = "NIP_KRAJ")
    private String nipKraj;

    @Column(name = "NIP")
    private String nip;

    @Column(name = "REGON")
    private String regon;

    @Column(name = "VAT_EU")
    private String vatEu;

    @Column(name = "ADRES_KOD_POCZTOWY")
    private String adresKodPocztowy;

    @Column(name = "ADRES_MIASTO")
    private String adresMiasto;

    @Column(name = "ADRES_ULICA")
    private String adresUlica;

    @Column(name = "ADRES_NR_BUDYNKU")
    private String adresNrBudynku;

    @Column(name = "ADRES_NR_LOKALU")
    private String adresNrLokalu;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFON")
    private String telefon;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "STATUS")
    private short  status;

    @Column(name = "UWAGI")
    private String uwagi;

    @Column(name = "DATA_DODANIA")
    private Timestamp dataDodania;

    @Column(name = "DATA_OSTATNIEJ_MODYFIKACJI")
    private Timestamp dataOstatniejModyfikacji;

}
