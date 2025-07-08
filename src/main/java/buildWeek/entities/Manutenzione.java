package buildWeek.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "manutenzioni")
public class Manutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "inizio_manutenzione")
    private LocalDate inizioManutenzione;
    @Column(name = "fine_manutenzione")
    private LocalDate fineManutenzione;
    @ManyToOne
    @JoinColumn(name = "id_mezzo",referencedColumnName = "id")
    private Mezzo idMezzo;

    public Manutenzione(){}

    public Manutenzione( LocalDate inizioManutenzione, LocalDate fineManutenzione, Mezzo idMezzo) {
        this.inizioManutenzione = inizioManutenzione;
        this.fineManutenzione = fineManutenzione;
        this.idMezzo = idMezzo;
    }

    public UUID getId() {
        return id;
    }


    public LocalDate getInizioManutenzione() {
        return inizioManutenzione;
    }

    public void setInizioManutenzione(LocalDate inizioManutenzione) {
        this.inizioManutenzione = inizioManutenzione;
    }

    public LocalDate getFineManutenzione() {
        return fineManutenzione;
    }

    public void setFineManutenzione(LocalDate fineManutenzione) {
        this.fineManutenzione = fineManutenzione;
    }

    public Mezzo getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Mezzo idMezzo) {
        this.idMezzo = idMezzo;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id=" + id +
                ", inizioManutenzione=" + inizioManutenzione +
                ", fineManutenzione=" + fineManutenzione +
                ", idMezzo=" + idMezzo +
                '}';
    }
}
