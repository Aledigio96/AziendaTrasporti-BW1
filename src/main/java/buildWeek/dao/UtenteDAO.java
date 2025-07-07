package buildWeek.dao;

import buildWeek.entities.Utente;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class UtenteDAO {
    private final EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Utente newutente){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newutente);
        transaction.commit();
        System.out.println("Utente salvato con successo");
    }


    public Utente findById(String id) {
        Utente found = entityManager.find(Utente.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;

    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Utente u WHERE u.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Utente cancellato con successo!");
    }
}
