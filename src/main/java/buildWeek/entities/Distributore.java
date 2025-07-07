package buildWeek.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "distributore")
public abstract class Distributore {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    @ManyToOne
    @JoinColumn(name = "zona_partenza", referencedColumnName = "zonaPartenza", nullable = false)
    private Tratta posizione;

    public Distributore() {
    }

    public Distributore(Tratta posizione) {
        this.posizione = posizione;
    }

    public long getId() {
        return id;
    }

    public Tratta getPosizione() {
        return posizione;
    }

    public void setPosizione(Tratta posizione) {
        this.posizione = posizione;
    }
}
