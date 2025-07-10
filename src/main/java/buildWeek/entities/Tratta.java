package buildWeek.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "zonaPartenza", nullable = false)
    private String zonaPartenza;

    @OneToMany(mappedBy = "tratta")
    private List<Mezzo> mezzi;


    @Column(name = "tempo_previsto")
    private int tempoPrevisto;//in minuti

    @Column(nullable = false)
    private String capolinea;


    public Tratta(){}

    public Tratta(String zonaPartenza, int tempoPrevisto, String capolinea, List<Mezzo> mezzi) {
        this.zonaPartenza = zonaPartenza;
        this.tempoPrevisto = tempoPrevisto;
        this.capolinea = capolinea;
        this.mezzi= mezzi;

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

    public List<Mezzo> getMezzi() {
        return mezzi;
    }
    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", zonaArrivo='" + capolinea + '\'' +
                ", tempoMedioPercorrenza=" + tempoPrevisto +
                ", numeroMezzi=" + (mezzi != null ? mezzi.size() : 0) +
                '}';
    }
}
