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
    @OneToOne
    @JoinColumn(name = "id_mezzo", referencedColumnName = "id", nullable = false, unique = true)
    private Mezzo mezzo;
    @ManyToOne
    @JoinColumn(name = "tratta", nullable = false)
    private Tratta idTratta;

    public Percorrenza(){}

    public Percorrenza(int tempoPrevisto, int tempoEffettivo, Mezzo mezzo, Tratta idTratta) {
        this.tempoPrevisto = tempoPrevisto;
        this.tempoEffettivo = tempoEffettivo;
        this.mezzo = mezzo;
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
        return mezzo;
    }

    public void setIdMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
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
                ", idMezzo=" + mezzo +
                ", idTratta=" + idTratta +
                '}';
    }
}
