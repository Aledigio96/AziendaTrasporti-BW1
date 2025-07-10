package buildWeek.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @ManyToOne
    @JoinColumn(name = "tessera_id", nullable = false)
    private Tessera tessera;

    @ManyToOne
    @JoinColumn(name = "distributore_id",referencedColumnName = "id", nullable = false)
    private Distributore idDistributore;


    public Abbonamento() {}

    public Abbonamento( LocalDate dataEmissione, LocalDate dataScadenza, Tessera tessera) {

        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
        this.tessera = tessera;
    }

    // Getters
    public UUID getId() {
        return id;
    }


    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public Tessera getTessera() {
        return tessera;
    }

    // Setters

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", tesseraId=" + (tessera != null ? tessera.getId() : null) +
                '}';
    }
}
