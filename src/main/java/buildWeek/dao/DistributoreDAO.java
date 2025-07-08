package buildWeek.dao;

import buildWeek.entities.*;
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
    public Biglietto emettiBiglietto( Mezzo mezzo) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Biglietto biglietto = new Biglietto();
            biglietto.setDataemissione(LocalDate.now());
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
    public Abbonamento emettiAbbonamento(Tessera tessera, boolean settimanale) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Abbonamento abbonamento = new Abbonamento();
            LocalDate dataEmissione = LocalDate.now();
            LocalDate dataScadenza = settimanale
                    ? dataEmissione.plusWeeks(1)
                    : dataEmissione.plusMonths(1);// default: mensile

            abbonamento.setDataEmissione(dataEmissione);
            abbonamento.setDataScadenza(dataScadenza);
            abbonamento.setTessera(tessera);

            entityManager.persist(abbonamento);

            tx.commit();
            System.out.println("Abbonamento " + (settimanale ? "settimanale" : "mensile") + " emesso con successo.");
            return abbonamento;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Errore durante l'emissione dell'abbonamento", e);
        }
    }

    public void stampaConteggioEmissioni(LocalDate inizio, LocalDate fine) {
        // Conteggio biglietti
        Long numeroBiglietti = entityManager.createQuery("""
            SELECT COUNT(b) FROM Biglietto b
            WHERE b.dataemissione BETWEEN :inizio AND :fine
        """, Long.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getSingleResult();

        // Conteggio abbonamenti
        Long numeroAbbonamenti = entityManager.createQuery("""
            SELECT COUNT(a) FROM Abbonamento a
            WHERE a.dataEmissione BETWEEN :inizio AND :fine
        """, Long.class)
                .setParameter("inizio", inizio)
                .setParameter("fine", fine)
                .getSingleResult();

        System.out.println("Dal " + inizio + " al " + fine + ":");
        System.out.println("Biglietti emessi: " + numeroBiglietti);
        System.out.println("Abbonamenti emessi: " + numeroAbbonamenti);
    }
}
