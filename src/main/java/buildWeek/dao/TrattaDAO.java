package buildWeek.dao;

import buildWeek.entities.Tratta;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.UUID;

public class TrattaDAO {
    private final EntityManager entityManager;

    public TrattaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Metodo per salvare una nuova tratta con gestione sicura della transazione
    public void save(Tratta newTratta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newTratta);
            transaction.commit();
            System.out.println("Tratta salvata con successo");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Errore durante il salvataggio della tratta: " + e.getMessage());
        }
    }

    // Metodo per trovare una tratta per ID
    public Tratta findById(String id) {
        Tratta found = entityManager.find(Tratta.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    // Metodo per trovare tutte le tratte con una determinata zona di partenza
    public List<Tratta> findByZonaPartenza(String zonaPartenza) {
        return entityManager.createQuery(
                        "SELECT t FROM Tratta t WHERE t.zonaPartenza = :zonaPartenza", Tratta.class)
                .setParameter("zonaPartenza", zonaPartenza)
                .getResultList();
    }

    // Metodo per cancellare una tratta per ID con verifica esistenza e gestione sicura della transazione
    public void deleteById(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Tratta tratta = entityManager.find(Tratta.class, id);
            if (tratta != null) {
                entityManager.remove(tratta);
                System.out.println("Tratta cancellata con successo!");
            } else {
                System.out.println("Tratta con ID " + id + " non trovata.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Errore durante l'eliminazione della tratta: " + e.getMessage());
        }
    }

    // Metodo per recuperare tutte le tratte
    public void findAll() {
        List<Tratta> tratte= entityManager.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
        for (Tratta t:tratte) {
            System.out.println(t);
        }

    }
}
