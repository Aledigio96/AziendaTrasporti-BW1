package buildWeek.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "tempo_previsto")
    private int tempoPrevisto;//in minuti
    @Column(name = "tempo_effetivo")
    private int tempoEffettivo;//in minuti
    @OneToOne(mappedBy = "mezzi")
    private Mezzo idMezzo;
    @ManyToOne
    @JoinColumn(name = "tratta", nullable = false)
    private Tratta idTratta;

    public Percorrenza(){}

    public Percorrenza(int tempoPrevisto, int tempoEffettivo, Mezzo idMezzo, Tratta idTratta) {
        this.tempoPrevisto = tempoPrevisto;
        this.tempoEffettivo = tempoEffettivo;
        this.idMezzo = idMezzo;
        this.idTratta = idTratta;
    }

    public UUID getId() {
        return id;
    }


    public int getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(int tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(int tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public Mezzo getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Mezzo idMezzo) {
        this.idMezzo = idMezzo;
    }

    public Tratta getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(Tratta idTratta) {
        this.idTratta = idTratta;
    }

    @Override
    public String toString() {
        return "Percorrenze{" +
                "id=" + id +
                ", tempoPrevisto=" + tempoPrevisto +
                ", tempoEffettivo=" + tempoEffettivo +
                ", idMezzo=" + idMezzo +
                ", idTratta=" + idTratta +
                '}';
    }
}
