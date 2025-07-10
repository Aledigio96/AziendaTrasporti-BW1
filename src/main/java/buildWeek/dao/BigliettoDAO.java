package buildWeek.dao;

import buildWeek.entities.Biglietto;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BigliettoDAO {
    private final EntityManager entityManager;

    public BigliettoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

// Metodo per salvare un nuovo biglietto
    public void save(Biglietto newbiglietto){
        EntityTransaction transaction=  entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newbiglietto);
            transaction.commit();
            System.out.println("Biglietto salvato con successo");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Errore durante il salvataggio del biglietto", e);
        }
    }

// Metodo per trovare un biglietto per ID
    public Biglietto findById(String id) {
        Biglietto found = entityManager.find(Biglietto.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

// Metodo per cancellare un biglietto per ID
    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Biglietto biglietto = entityManager.find(Biglietto.class, id);
            if (biglietto == null) {
                throw new NotFoundException(id.toString());
            }
            entityManager.remove(biglietto);
            transaction.commit();
            System.out.println("Biglietto cancellato con successo!");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Errore durante la cancellazione del biglietto", e);
        }
    }

// Metodo per validare un biglietto
    public void validaBiglietto(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Biglietto biglietto = entityManager.find(Biglietto.class, id);
            if (biglietto == null) throw new NotFoundException(id.toString());
            biglietto.setValidazione(true);
            biglietto.setOrariovalidazione(java.time.LocalDate.now());
            entityManager.merge(biglietto);
            transaction.commit();
            System.out.println("Biglietto validato con successo!");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Errore durante la validazione del biglietto", e);
        }
    }

// Metodo per verificare se un biglietto è valido o scaduto
    public boolean bigliettoValidoOscaduto(String id) {
        Biglietto biglietto = findById(id);
        LocalDate oggi= LocalDate.now();
        if ((oggi.isEqual(biglietto.getDataemissione()))|| oggi.isAfter(biglietto.getDataemissione()) &&
                (oggi.isEqual(biglietto.getScadenza()))|| oggi.isBefore(biglietto.getScadenza()))
        {
            System.out.println("Il biglietto è valido");
            return true;
        } else {
            System.out.println("Il biglietto è scaduto");
            return false;
        }
    }

// Metodo per trovare tutti i biglietti
    public void findAll() {
        var biglietti = entityManager.createQuery("SELECT b FROM Biglietto b", Biglietto.class).getResultList();
        biglietti.forEach(System.out::println);
    }

// Metodo per trovare biglietti emessi in un periodo specifico
    public void findByPeriodo(LocalDate inizio, LocalDate fine) {
        var biglietti = entityManager.createQuery(
                        "SELECT b FROM Biglietto b WHERE b.dataemissione BETWEEN :inizio AND :fine", Biglietto.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getResultList();
        biglietti.forEach(System.out::println);
    }

// Metodo per trovare biglietti emessi da un distributore specifico
public List<Biglietto> findByDistributore(UUID distributore) {
    return entityManager.createQuery(
                    "SELECT b FROM Biglietto b WHERE b.idDistributore = :distributore", Biglietto.class)
            .setParameter("distributore", distributore)
            .getResultList();
}

}
