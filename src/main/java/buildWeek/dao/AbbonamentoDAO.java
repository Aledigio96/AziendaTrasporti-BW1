package buildWeek.dao;

import buildWeek.entities.Abbonamento;
import buildWeek.entities.Mezzo;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.UUID;

public class AbbonamentoDAO {
    private final EntityManager entityManager;

    public AbbonamentoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Abbonamento newabbonamento){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newabbonamento);
        transaction.commit();
        System.out.println("Abbonamento salvato con successo");
    }


    public Abbonamento findById(String id) {
        Abbonamento found = entityManager.find(Abbonamento.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }



    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Abbonamento ab WHERE ab.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Abbonamento cancellato con successo!");
    }
}
