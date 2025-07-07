package buildWeek.dao;

import buildWeek.entities.Distributore;
import buildWeek.entities.Mezzo;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class DistributoreDAO {
    private final EntityManager entityManager;

    public DistributoreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Distributore newdistributore){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newdistributore);
        transaction.commit();
        System.out.println("Distrubutore salvato con successo");
    }


    public Distributore findById(String id) {
        Distributore found = entityManager.find(Distributore.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Distributore d WHERE d.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Distributore cancellato con successo!");
    }
}
