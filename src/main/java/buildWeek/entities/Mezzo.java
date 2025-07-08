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



    // Costruttori
    public Mezzo() {}

    public Mezzo(TipoMezzo tipo, int capienza) {
        this.tipo = tipo;
        this.capienza = capienza;

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



    // Setters
    public void setTipo(TipoMezzo tipo) {
        this.tipo = tipo;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", capienza=" + capienza +
                '}';
    }
}
