package buildWeek.dao;

import buildWeek.entities.Tessera;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class TesseraDAO {
    private final EntityManager entityManager;

    public TesseraDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Tessera newtessera){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newtessera);
        transaction.commit();
        System.out.println("tessera salvata con successo");
    }


    public Tessera findById(String id) {
        Tessera found = entityManager.find(Tessera.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Tessera t WHERE t.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Tessera cancellata con successo!");
    }
}
