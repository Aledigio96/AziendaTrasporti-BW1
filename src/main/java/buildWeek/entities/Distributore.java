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

    private String posizione;

    public Distributore() {
    }

    public Distributore(String posizione) {
        this.posizione = posizione;
    }

    public UUID getId() {
        return id;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    @Override
    public String toString() {
        return "Distributore{" +
                "id=" + id +
                ", posizione='" + posizione + '\'' +
                '}';
    }
}
