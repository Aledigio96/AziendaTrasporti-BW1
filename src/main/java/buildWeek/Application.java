package buildWeek;

import buildWeek.dao.*;
import buildWeek.entities.*;
import buildWeek.enums.TipoAbbonamento;
import buildWeek.enums.TipoMezzo;
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
//        ut.save(utente1);
//        Utente utente1fromdb = ut.findById("328be5b7-7687-47af-b877-966f314bb0e5");


        TesseraDAO tess = new TesseraDAO(em);

        Tessera tessera1 = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1), true, utente1);
        //tess.save(tessera1);

        MezzoDAO md = new MezzoDAO(em);
        Mezzo mezzo1 = new Mezzo(TipoMezzo.AUTOBUS, 30, true);
        md.save(mezzo1);

        TrattaDAO td = new TrattaDAO(em);
        Tratta tratta1 = new Tratta("Latina", "Roma", 45, mezzo1);
        td.save(tratta1);

        DistributoreDAO dd = new DistributoreDAO(em);
        RivenditoriAutorizzati distributore1 = new RivenditoriAutorizzati(tratta1.getZonaPartenza());
        dd.save(distributore1);

        dd.emettiBiglietto(distributore1, mezzo1);

//        dd.emettiAbbonamento(TipoAbbonamento.SETTIMANALE, tessera1);
    }
}
