package buildWeek;

import buildWeek.dao.UtenteDAO;
import buildWeek.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AziendaTrasporti");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UtenteDAO ut=new UtenteDAO(em);
        LocalDate data1=LocalDate.of(1996,01,14);
        Utente utente1= new Utente("Alessandro","Di Giovanni",data1);
        //ut.save(utente1);
        Utente utente1FromDB=ut.findById("ea042ca8-5d5e-4c9c-8f72-bf62930905db");
        System.out.println(utente1FromDB);
        ut.findByIdandDelete(UUID.fromString("ea042ca8-5d5e-4c9c-8f72-bf62930905db"));
    }
}
