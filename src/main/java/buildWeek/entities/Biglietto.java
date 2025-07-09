package buildWeek.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime dataemissione;
    private LocalDateTime scadenza;
    private boolean validazione;
    private LocalDateTime orariovalidazione;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false, unique = true)
    private Mezzo idmezzo;

    @ManyToOne
    @JoinColumn(name = "distributore_id",referencedColumnName = "id", nullable = false, unique = true)
    private Distributore idDistributore;

    public Biglietto(){}

    public Biglietto( LocalDateTime dataemissione, boolean validazione, LocalDateTime orariovalidazione, Mezzo idmezzo) {

        this.dataemissione = dataemissione;
        this.validazione = validazione;
        this.orariovalidazione = orariovalidazione;
        this.idmezzo = idmezzo;
        this.scadenza=dataemissione.plusYears(1);
    }

    public UUID getId() {
        return id;
    }



    public LocalDateTime getDataemissione() {
        return dataemissione;
    }

    public void setDataemissione(LocalDateTime dataemissione) {
        this.dataemissione = dataemissione;
    }

    public boolean isValidazione() {
        return validazione;
    }

    public void setValidazione(boolean validazione) {
        this.validazione = validazione;
    }

    public LocalDateTime getOrariovalidazione() {
        return orariovalidazione;
    }

    public void setOrariovalidazione(LocalDateTime orariovalidazione) {
        this.orariovalidazione = orariovalidazione;
    }

    public Mezzo getIdmezzo() {
        return idmezzo;
    }

    public void setIdmezzo(Mezzo idmezzo) {
        this.idmezzo = idmezzo;
    }

    public LocalDateTime getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDateTime scadenza) {
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
                '}';
    }
}
