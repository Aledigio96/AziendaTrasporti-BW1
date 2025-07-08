package buildWeek.entities;

import buildWeek.enums.StatoDistributoreAutomatico;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "distributori_automatici")
public class DistributoreAutomatico extends Distributore {
    @Enumerated
    private StatoDistributoreAutomatico stato;

    public DistributoreAutomatico() {
        super();
    }

    public DistributoreAutomatico(String posizione, StatoDistributoreAutomatico stato) {
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