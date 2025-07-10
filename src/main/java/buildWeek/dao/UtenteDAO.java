package buildWeek.dao;

import buildWeek.entities.Utente;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.UUID;

public class UtenteDAO {
    private final EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Metodo per salvare un nuovo utente con gestione sicura della transazione
    public void save(Utente newutente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newutente);
            transaction.commit();
            System.out.println("Utente salvato con successo");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Errore nel salvataggio utente: " + e.getMessage());
        }
    }

    // Metodo per cercare un utente per ID
    public Utente findById(String id) {
        Utente found = entityManager.find(Utente.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    // Metodo per cercare un utente per ID e cancellarlo in modo sicuro
    public void deleteById(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Utente utente = entityManager.find(Utente.class, id);
            if (utente == null) {
                throw new NotFoundException(id.toString());
            }

            entityManager.remove(utente);
            transaction.commit();
            System.out.println("Utente cancellato con successo!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Errore durante la cancellazione: " + e.getMessage());
        }
    }

    // Metodo per cercare un utente per email
    public Utente findByEmail(String email) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Utente u WHERE u.email = :email", Utente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Metodo per cercare tutti gli utenti (non modificato in questa richiesta)
    public void findAll() {
        try {
            List<Utente> utenti = entityManager.createQuery("SELECT u FROM Utente u", Utente.class)
                    .getResultList();
            for (Utente u : utenti) {
                System.out.println(u);
            }
        } catch (Exception e) {
            System.out.println("Errore nel recupero degli utenti: " + e.getMessage());
        } finally {
            System.out.println("Tutti gli utenti sono stati recuperati con successo.");
        }
    }
}
