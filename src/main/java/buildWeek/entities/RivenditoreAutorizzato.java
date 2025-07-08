package buildWeek.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditori_autorizzati")
public class RivenditoreAutorizzato extends Distributore {

    public RivenditoreAutorizzato() {
        super();
    }

    public RivenditoreAutorizzato(String posizione) {
        super(posizione);
    }
}