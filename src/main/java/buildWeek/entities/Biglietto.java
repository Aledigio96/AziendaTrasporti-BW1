package buildWeek.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate dataemissione;
    @ManyToOne
    @JoinColumn(name = "distrubutori_id", nullable = false, unique = true)
    private Distributore distributore;
    private boolean validazione;
    private LocalDate orariovalidazione;
    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false, unique = true)
    private Mezzo idmezzo;

    public Biglietto(){}

    public Biglietto( LocalDate dataemissione, Distributore distributore, boolean validazione, LocalDate orariovalidazione, Mezzo idmezzo) {

        this.dataemissione = dataemissione;
        this.distributore = distributore;
        this.validazione = validazione;
        this.orariovalidazione = orariovalidazione;
        this.idmezzo = idmezzo;
    }

    public UUID getId() {
        return id;
    }



    public LocalDate getDataemissione() {
        return dataemissione;
    }

    public void setDataemissione(LocalDate dataemissione) {
        this.dataemissione = dataemissione;
    }

    public Distributore getDistributore() {
        return distributore;
    }

    public void setDistributore(Distributore distributore) {
        this.distributore = distributore;
    }

    public boolean isValidazione() {
        return validazione;
    }

    public void setValidazione(boolean validazione) {
        this.validazione = validazione;
    }

    public LocalDate getOrariovalidazione() {
        return orariovalidazione;
    }

    public void setOrariovalidazione(LocalDate orariovalidazione) {
        this.orariovalidazione = orariovalidazione;
    }

    public Mezzo getIdmezzo() {
        return idmezzo;
    }

    public void setIdmezzo(Mezzo idmezzo) {
        this.idmezzo = idmezzo;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", dataemissione=" + dataemissione +
                ", distributore=" + distributore +
                ", validazione=" + validazione +
                ", orariovalidazione=" + orariovalidazione +
                ", idmezzo=" + idmezzo +
                '}';
    }
}
