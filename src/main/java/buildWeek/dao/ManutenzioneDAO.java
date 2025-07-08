package buildWeek.dao;

import buildWeek.entities.Biglietto;
import buildWeek.entities.Manutenzione;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class ManutenzioneDAO {
    private final EntityManager entityManager;

    public ManutenzioneDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Manutenzione newmanutenzione){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newmanutenzione);
        transaction.commit();
        System.out.println("Manutenzione salvata con successo");
    }


    public Manutenzione findById(String id) {
        Manutenzione found = entityManager.find(Manutenzione.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Manutenzione m WHERE m.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Manutenzione cancellata con successo!");
    }
}
