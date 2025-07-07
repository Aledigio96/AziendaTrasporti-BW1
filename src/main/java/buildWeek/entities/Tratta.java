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

    @Column(name = "tempo_previsto", nullable = false)
    private int tempoPrevisto; //in minuti

    @ManyToOne
    @JoinColumn(name = "mezzo", nullable = false)
    private Mezzo mezzo;

    public Tratta(){}

    public Tratta(String zonaPartenza, String capolinea, int tempoPrevisto, Mezzo mezzo) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
        this.mezzo = mezzo;
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

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }
}
