package buildWeek.entities;

import buildWeek.enums.TipoMezzo;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "mezzi")
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoMezzo tipo;

    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    private int capienza;
    private LocalTime orarioPartenza;



    // Costruttori
    public Mezzo() {}

    public Mezzo(TipoMezzo tipo, int capienza, LocalTime orarioPartenza, Tratta tratta) {
        this.tipo = tipo;
        this.capienza = capienza;
        this.orarioPartenza = orarioPartenza;
        this.tratta = tratta;

    }

    // Getters
    public UUID getId() {
        return id;
    }

    public TipoMezzo getTipo() {
        return tipo;
    }

    public int getCapienza() {
        return capienza;
    }
    public LocalTime getOrarioPartenza() {
        return orarioPartenza;
    }
    public Tratta getTratta() {
        return tratta;
    }



    // Setters
    public void setTipo(TipoMezzo tipo) {
        this.tipo = tipo;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
    public void setOrarioPartenza(LocalTime orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }
    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", capienza=" + capienza +
                ", orarioPartenza=" + orarioPartenza +
                ", trattaId=" + (tratta != null ? tratta.getId() : null) +
                '}';
    }
}
