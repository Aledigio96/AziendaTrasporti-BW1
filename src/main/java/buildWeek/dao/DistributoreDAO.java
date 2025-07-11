package buildWeek.dao;

import buildWeek.entities.*;
import buildWeek.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DistributoreDAO {
    private final EntityManager entityManager;

    public DistributoreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

// Metodo per salvare un nuovo distributore
    public void save(Distributore newdistributore){
        EntityTransaction transaction=  entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newdistributore);
            transaction.commit();
            System.out.println("Distrubutore salvato con successo");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Errore durante il salvataggio del distributore", e);
        }
    }

// Metodo per trovare un distributore per ID
    public Distributore findById(String id) {
        Distributore found = entityManager.find(Distributore.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

// Metodo per trovare un distributore per nome
    public void findByIdandDelete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Query query = entityManager.createQuery("DELETE FROM Distributore d WHERE d.id = :id");
            query.setParameter("id", id);

            query.executeUpdate();

            transaction.commit();

            System.out.println( "Distributore cancellato con successo!");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Errore durante l'eliminazione del distributore", e);
        }
    }

    //Emissione biglietto
    public Biglietto emettiBiglietto(Distributore distributore, Mezzo mezzo) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Biglietto biglietto = new Biglietto();
            biglietto.setDataemissione(LocalDate.now());
            biglietto.setValidazione(false);
            biglietto.setIdmezzo(mezzo);
            biglietto.setIdDistributore(distributore.getId());

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
    public Abbonamento emettiAbbonamento(Distributore distribuore, Tessera tessera, boolean settimanale) {
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
            abbonamento.setIdDistributore(distribuore);

            entityManager.persist(abbonamento);

            tx.commit();
            System.out.println("Abbonamento " + (settimanale ? "settimanale" : "mensile") + " emesso con successo.");
            return abbonamento;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Errore durante l'emissione dell'abbonamento", e);
        }
    }

    // Metodo per contare le emissioni di biglietti e abbonamenti in un intervallo di date
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

    // Metodo per trovare tutti i distributori
    public void findAll() {
        try {
            List<Distributore> distributori = entityManager.createQuery("SELECT d FROM Distributore d", Distributore.class).getResultList();
            for (Distributore d : distributori) {
                System.out.println(d);
            }
        } catch (Exception e) {
            System.out.println("Errore nel recupero dei distributori: " + e.getMessage());
        } finally {
            System.out.println("Tutti i distributori sono stati recuperati con successo.");
        }
    }
}
