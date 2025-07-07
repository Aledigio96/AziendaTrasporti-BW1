package buildWeek.dao;

import buildWeek.entities.Biglietto;
import buildWeek.entities.Mezzo;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class BigliettoDAO {
    private final EntityManager entityManager;

    public BigliettoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Biglietto newbiglietto){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newbiglietto);
        transaction.commit();
        System.out.println("Biglietto salvato con successo");
    }


    public Biglietto findById(String id) {
        Biglietto found = entityManager.find(Biglietto.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Biglietto b WHERE b.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Biglietto cancellato con successo!");
    }
}
