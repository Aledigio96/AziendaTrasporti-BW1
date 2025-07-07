package buildWeek.entities;

import buildWeek.enums.StatoDistributoreAutomatico;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "distributori_automatici")
public class DistributoriAutomatici extends Distributore {

    private StatoDistributoreAutomatico stato;

    public DistributoriAutomatici() {
        super();
    }

    public DistributoriAutomatici(Tratta posizione, StatoDistributoreAutomatico stato) {
        super(posizione);
        this.stato = stato;
    }

    public StatoDistributoreAutomatico getStato() {
        return stato;
    }

    public void setStato(StatoDistributoreAutomatico stato) {
        this.stato = stato;
    }
}