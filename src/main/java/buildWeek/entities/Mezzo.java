package buildWeek.entities;

import buildWeek.enums.TipoMezzo;
import jakarta.persistence.*;

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

    private int capienza;

    @Column(name = "in_servizio")
    private boolean inServizio;

    // Costruttori
    public Mezzo() {}

    public Mezzo(TipoMezzo tipo, int capienza, boolean inServizio) {
        this.tipo = tipo;
        this.capienza = capienza;
        this.inServizio = inServizio;
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

    public boolean isInServizio() {
        return inServizio;
    }

    // Setters
    public void setTipo(TipoMezzo tipo) {
        this.tipo = tipo;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", capienza=" + capienza +
                ", inServizio=" + inServizio +
                '}';
    }
}
