package buildWeek.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tessere")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;



    @OneToOne
    @JoinColumn(name = "utente_id", nullable = false, unique = true)
    private Utente utente;

    public Tessera() {}

    public Tessera(LocalDate dataEmissione, LocalDate dataScadenza,  Utente utente) {
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;

        this.utente = utente;
    }

    // Getter
    public UUID getId() {
        return id;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }


    public Utente getUtente() {
        return utente;
    }

    // Setter
    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }


    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", utente=" + utente +
                '}';
    }
}
