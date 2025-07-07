package buildWeek.dao;

import buildWeek.entities.Amministratore;
import buildWeek.entities.Mezzo;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class AmministratoreDAO {
    private final EntityManager entityManager;

    public AmministratoreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Amministratore newmamministratore){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newmamministratore);
        transaction.commit();
        System.out.println("Amministratore salvato con successo");
    }


    public Amministratore findById(String id) {
        Amministratore found = entityManager.find(Amministratore.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Amministratore a WHERE a.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Amministratore cancellato con successo!");
    }
}
