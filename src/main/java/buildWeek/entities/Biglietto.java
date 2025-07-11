package buildWeek.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate dataemissione;
    private LocalDate scadenza;
    private boolean validazione;
    private LocalDate orariovalidazione;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo idmezzo;

    @Column(name = "distributore_id")
    private UUID idDistributore;

    public Biglietto(){}

    public Biglietto( LocalDate dataemissione, boolean validazione, LocalDate orariovalidazione, Mezzo idmezzo, UUID idDistributore) {

        this.dataemissione = dataemissione;
        this.validazione = validazione;
        this.orariovalidazione = orariovalidazione;
        this.idmezzo = idmezzo;
        this.scadenza=dataemissione.plusYears(1);
        this.idDistributore = idDistributore;
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

    public UUID getIdDistributore() {
        return idDistributore;
    }

    public void setIdDistributore(UUID idDistributore) {
        this.idDistributore = idDistributore;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", dataemissione=" + dataemissione +
                ", scadenza=" + scadenza +
                ", validazione=" + validazione +
                ", orariovalidazione=" + orariovalidazione +
                ", idmezzo=" + idmezzo +
                ", idDistributore=" + idDistributore +
                '}';
    }
}

