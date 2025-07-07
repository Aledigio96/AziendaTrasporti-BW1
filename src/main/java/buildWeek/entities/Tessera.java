package buildWeek.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    private boolean attiva;

    @OneToOne
    @JoinColumn(name = "utente_id", nullable = false, unique = true)
    private Utente utente;

    public Tessera() {}

    public Tessera(LocalDate dataEmissione, LocalDate dataScadenza, boolean attiva, Utente utente) {
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
        this.attiva = attiva;
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

    public boolean isAttiva() {
        return attiva;
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

    public void setAttiva(boolean attiva) {
        this.attiva = attiva;
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
                ", attiva=" + attiva +
                ", utente=" + utente +
                '}';
    }
}
