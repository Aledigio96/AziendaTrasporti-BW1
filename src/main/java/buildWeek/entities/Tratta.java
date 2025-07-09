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


    @Column(name = "tempo_previsto")
    private int tempoPrevisto;//in minuti

    @Column(nullable = false)
    private String capolinea;


    public Tratta(){}

    public Tratta(String zonaPartenza, int tempoPrevisto, String capolinea) {
        this.zonaPartenza = zonaPartenza;
        this.tempoPrevisto = tempoPrevisto;
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

    public int getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(int tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", tempoPrevisto=" + tempoPrevisto +
                ", capolinea='" + capolinea + '\'' +
                '}';
    }
}
