package buildWeek.dao;

import buildWeek.entities.Mezzo;
import buildWeek.entities.Tratta;
import buildWeek.entities.Utente;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.UUID;

public class MezzoDAO {
    private final EntityManager entityManager;

    public MezzoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

// Metodo per salvare un nuovo mezzo
    public void save(Mezzo newmezzo){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newmezzo);
            transaction.commit();
            System.out.println("Mezzo salvato con successo");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            System.out.println("Errore nel salvataggio del mezzo: " + e.getMessage());
        }
    }

// Metodo per trovare un mezzo per ID
    public Mezzo findById(String id) {
        try {
            Mezzo found = entityManager.find(Mezzo.class, UUID.fromString(id));
            if (found == null) throw new NotFoundException(id);
            return found;
        } catch (Exception e) {
            System.out.println("Errore nella ricerca del mezzo: " + e.getMessage());
            throw e;
        }
    }


// Metodo per trovare un mezzo per zona di partenza
    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query query = entityManager.createQuery("DELETE FROM Mezzo m WHERE m.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            System.out.println( "Mezzo cancellato con successo!");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            System.out.println("Errore nella cancellazione del mezzo: " + e.getMessage());
        }
    }

    // Metodo per contare i mezzi associati a una tratta
    public void findAll(){
        try {
            List<Mezzo> mezzi= entityManager.createQuery("SELECT m FROM Mezzo m",Mezzo.class).getResultList();
            for(Mezzo m: mezzi){
                System.out.println(m);
            }

        }catch (Exception e) {
            System.out.println("Errore nel recupero dei trasporti: " + e.getMessage());
        } finally {
            System.out.println("Tutti i mezzi sono stati recuperati con successo.");
        }
    }
}
