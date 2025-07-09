package buildWeek.entities;

import buildWeek.enums.TipoRivenditoreAutorizzato;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditori_autorizzati")
public class RivenditoreAutorizzato extends Distributore {

    private TipoRivenditoreAutorizzato tipo;

    public RivenditoreAutorizzato() {
        super();
    }

    public RivenditoreAutorizzato(String posizione, TipoRivenditoreAutorizzato tipo) {
        super(posizione);
        this.tipo = tipo;
    }

    public TipoRivenditoreAutorizzato getTipo() {
        return tipo;
    }

    public void setTipo(TipoRivenditoreAutorizzato tipo) {
        this.tipo = tipo;
    }
}