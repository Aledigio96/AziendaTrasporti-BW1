package buildWeek.entities;

import buildWeek.enums.TipoAbbonamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipo;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @ManyToOne
    @JoinColumn(name = "tessera_id", nullable = false)
    private Tessera tessera;

    // Per dopo: aggiungi distributore/rivenditore
    // @ManyToOne
    // @JoinColumn(name = "punto_vendita_id")
    // private PuntoVendita puntoVendita;

    public Abbonamento() {}

    public Abbonamento(TipoAbbonamento tipo, LocalDate dataEmissione, LocalDate dataScadenza, Tessera tessera) {
        this.tipo = tipo;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
        this.tessera = tessera;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public TipoAbbonamento getTipo() {
        return tipo;
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
    public void setTipo(TipoAbbonamento tipo) {
        this.tipo = tipo;
    }

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
                ", tipo=" + tipo +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", tesseraId=" + (tessera != null ? tessera.getId() : null) +
                '}';
    }
}
