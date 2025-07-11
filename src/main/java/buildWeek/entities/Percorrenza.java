package buildWeek.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "tempo_effetivo")
    private int tempoEffettivo;//in minuti
    @ManyToOne
    @JoinColumn(name = "id_mezzo", referencedColumnName = "id", nullable = false, unique = true)
    private Mezzo mezzo;
    @ManyToOne
    @JoinColumn(name = "tratta", nullable = false)
    private Tratta idTratta;

    public Percorrenza(){}

    public Percorrenza( int tempoEffettivo, Mezzo mezzo, Tratta idTratta) {
        this.tempoEffettivo = tempoEffettivo;
        this.mezzo = mezzo;
        this.idTratta = idTratta;
    }

    public UUID getId() {
        return id;
    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(int tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
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
                ", tempoEffettivo=" + tempoEffettivo +
                ", mezzo=" + mezzo +
                ", idTratta=" + idTratta +
                '}';
    }
}
