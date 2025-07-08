package buildWeek.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "zonaPartenza", nullable = false, unique = true)
    private String zonaPartenza;

    @Column(nullable = false)
    private String capolinea;


    public Tratta(){}

    public Tratta(String zonaPartenza, String capolinea) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;

    }

    public UUID getId() {
        return id;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                '}';
    }
}
