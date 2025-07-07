package buildWeek.dao;

import buildWeek.entities.*;
import buildWeek.enums.TipoAbbonamento;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.UUID;

public class DistributoreDAO {
    private final EntityManager entityManager;

    public DistributoreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(Distributore newdistributore){
        EntityTransaction transaction=  entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newdistributore);
        transaction.commit();
        System.out.println("Distrubutore salvato con successo");
    }


    public Distributore findById(String id) {
        Distributore found = entityManager.find(Distributore.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }


    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("DELETE FROM Distributore d WHERE d.id = :id");
        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        System.out.println( "Distributore cancellato con successo!");
    }


    //Emissione biglietto
    public Biglietto emettiBiglietto(Distributore distributore, Mezzo mezzo) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Biglietto biglietto = new Biglietto();
            biglietto.setDataemissione(LocalDate.now());
            biglietto.setDistributore(distributore);
            biglietto.setValidazione(false);
            biglietto.setIdmezzo(mezzo);

            entityManager.persist(biglietto);

            tx.commit();
            System.out.println("Biglietto emesso con successo.");
            return biglietto;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Errore durante l'emissione del biglietto", e);
        }
    }


    //Emissione abbonamento
    public Abbonamento emettiAbbonamento(TipoAbbonamento tipo, Tessera tessera) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            LocalDate oggi = LocalDate.now();
            LocalDate scadenza;

            switch (tipo) {
                case SETTIMANALE -> scadenza = oggi.plusWeeks(1);
                case MENSILE -> scadenza = oggi.plusMonths(1);
                default -> throw new IllegalArgumentException("Tipo abbonamento non valido");
            }

            Abbonamento abbonamento = new Abbonamento(tipo, oggi, scadenza, tessera);
            entityManager.persist(abbonamento);

            tx.commit();
            System.out.println("Abbonamento emesso con successo.");
            return abbonamento;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Errore durante l'emissione dell'abbonamento", e);
        }
    }
}
