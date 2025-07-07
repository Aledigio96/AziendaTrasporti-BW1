package buildWeek.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "distributore")
public abstract class Distributore {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "zona_partenza", referencedColumnName = "zonaPartenza", nullable = false)
    private Tratta posizione;

    public Distributore() {
    }

    public Distributore(Tratta posizione) {
        this.posizione = posizione;
    }

    public UUID getId() {
        return id;
    }

    public Tratta getPosizione() {
        return posizione;
    }

    public void setPosizione(Tratta posizione) {
        this.posizione = posizione;
    }
}
