package buildWeek.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditori_autorizzati")
public class RivenditoriAutorizzati extends Distributore {

    public RivenditoriAutorizzati() {
        super();
    }

    public RivenditoriAutorizzati(Tratta posizione) {
        super(posizione);
    }
}