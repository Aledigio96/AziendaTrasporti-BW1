package buildWeek.dao;

import buildWeek.entities.Abbonamento;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.UUID;

public class AbbonamentoDAO {
    private final EntityManager entityManager;

    public AbbonamentoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//SAVE
    public void save(Abbonamento newabbonamento){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newabbonamento);
            transaction.commit();
            System.out.println("Abbonamento salvato con successo");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

//RICERCA TRAMITE ID
    public Abbonamento findById(String id) {
        Abbonamento found = entityManager.find(Abbonamento.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }


//RICERCA TRAMITE ID E CANCELLAZIONE
    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Query query = entityManager.createQuery("DELETE FROM Abbonamento ab WHERE ab.id = :id");
            query.setParameter("id", id);

            int deletedCount = query.executeUpdate();

            transaction.commit();

            if (deletedCount == 0) {
                throw new NotFoundException(id.toString());
            }

            System.out.println("Abbonamento cancellato con successo!");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    //ABBONAMENTO VALIDO O SCADUTO?
    public boolean abbonamentoValidoOscaduto(String id) {
        Abbonamento abbonamento = findById(id);
        LocalDate oggi= LocalDate.now();
        if ((oggi.isEqual(abbonamento.getDataEmissione()))|| oggi.isAfter(abbonamento.getDataEmissione()) &&
                (oggi.isEqual(abbonamento.getDataScadenza()))|| oggi.isBefore(abbonamento.getDataScadenza()))
        {
            System.out.println("L'abbonamento è valido");
            return true;
        } else {
            System.out.println("L'abbonamento è scaduto");
            return false;
        }
    }

//RICERCA TUTTI ABBONAMENTI
    public void findAll() {
        var abbonamenti = entityManager.createQuery("SELECT a FROM Abbonamento a", Abbonamento.class).getResultList();
        abbonamenti.forEach(System.out::println);
    }

//RICERCA ABBONAMENTI PER PERIODO
    public void findByPeriodo(LocalDate inizio, LocalDate fine) {
        var abbonamenti = entityManager.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.dataEmissione BETWEEN :inizio AND :fine", Abbonamento.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getResultList();
        abbonamenti.forEach(System.out::println);
    }

//RICERCA ABBONAMENTI PER DISTRIBUTORE
    public void findByDistributore(UUID distributoreId) {
        var abbonamenti = entityManager.createQuery(
                        "SELECT a FROM Abbonamento a WHERE a.idDistributore.id = :id", Abbonamento.class)
                .setParameter("id", distributoreId)
                .getResultList();
        abbonamenti.forEach(System.out::println);
    }
}
