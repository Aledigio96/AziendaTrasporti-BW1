package buildWeek.dao;

import buildWeek.entities.Tessera;

import buildWeek.exceptions.NoResultException;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
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

    public void findAll(){
        try { List<Tessera> tessere= entityManager.createQuery("SELECT t FROM Tessera t",Tessera.class).getResultList();
            for(Tessera t: tessere){
                System.out.println(t);
            }

        }catch (Exception e) {
            System.out.println("Errore nel recupero degli tessere: " + e.getMessage());
        } finally {
            System.out.println("Tutti gli tessere sono stati recuperati con successo.");
        }
    }

    public Tessera findByUtenteId(String utenteId) {
        try {
            return entityManager.createQuery(
                            "SELECT t FROM Tessera t WHERE t.utente.id = :utenteId", Tessera.class)
                    .setParameter("utenteId", UUID.fromString(utenteId))
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
