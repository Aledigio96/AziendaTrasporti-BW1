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

    // Metodo per salvare una nuova tessera
    public void save(Tessera newtessera) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newtessera);
            transaction.commit();
            System.out.println("Tessera salvata con successo");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    // Metodo per trovare una tessera tramite il suo id
    public Tessera findById(String id) {
        Tessera found = entityManager.find(Tessera.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    // Metodo per trovare una tessera tramite il suo id ed eliminarla
    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Query query = entityManager.createQuery("DELETE FROM Tessera t WHERE t.id = :id");
            query.setParameter("id", id);

            int deleted = query.executeUpdate();
            if (deleted == 0) {
                transaction.rollback();
                throw new NotFoundException(id.toString());
            }

            transaction.commit();
            System.out.println("Tessera cancellata con successo!");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    // Metodo per trovare tutte le tessere
    public void findAll() {
        try {
            List<Tessera> tessere = entityManager.createQuery("SELECT t FROM Tessera t", Tessera.class).getResultList();
            for (Tessera t : tessere) {
                System.out.println(t);
            }
        } catch (Exception e) {
            System.out.println("Errore nel recupero delle tessere: " + e.getMessage());
        } finally {
            System.out.println("Tutte le tessere sono state recuperate con successo.");
        }
    }

    // Metodo per trovare una tessera tramite l'id dell'utente
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
