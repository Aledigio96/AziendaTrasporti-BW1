package buildWeek.dao;

import buildWeek.entities.Mezzo;
import buildWeek.entities.Utente;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class MezzoDAO {
    private final EntityManager entityManager;

    public MezzoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Mezzo newmezzo){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newmezzo);
        transaction.commit();
        System.out.println("Mezzo salvato con successo");
    }


    public Mezzo findById(String id) {
        Mezzo found = entityManager.find(Mezzo.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Mezzo m WHERE m.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Mezzo cancellato con successo!");
    }
}
