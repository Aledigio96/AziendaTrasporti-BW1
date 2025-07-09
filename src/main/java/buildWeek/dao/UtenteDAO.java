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



        public void findAll(){
            try { List<Utente> utenti= entityManager.createQuery("SELECT u FROM Utente u",Utente.class).getResultList();
                for(Utente u: utenti){
                    System.out.println(u);
                }

            }catch (Exception e) {
                System.out.println("Errore nel recupero degli utenti: " + e.getMessage());
            } finally {
                System.out.println("Tutti gli utenti sono stati recuperati con successo.");
            }
        }
}
