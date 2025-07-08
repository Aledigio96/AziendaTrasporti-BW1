package buildWeek;

import buildWeek.dao.*;
import buildWeek.entities.*;
import buildWeek.enums.StatoDistributoreAutomatico;
import buildWeek.enums.TipoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AziendaTrasporti");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UtenteDAO ud= new UtenteDAO(em);
        TrattaDAO td= new TrattaDAO(em);
        TesseraDAO tessd= new TesseraDAO(em);
        MezzoDAO md= new MezzoDAO(em);
        DistributoreDAO dd= new DistributoreDAO(em);
        BigliettoDAO bd= new BigliettoDAO(em);
        AbbonamentoDAO ad=new AbbonamentoDAO(em);
        ManutenzioneDAO mand= new ManutenzioneDAO(em);

//        Utente utente1= new Utente("Alessandro","Di Giovanni","alessandr.g@gmail.com","1234", LocalDate.now().minusYears(29));
//        ud.save(utente1);
//        Utente utente1fromdb = ud.findById("a644f18d-6188-4127-bf4d-db5196bd2940");
//
//        Tratta tratta1= new Tratta("Roma","Latina");
//        td.save(tratta1);
//
//        Tessera tessera1= new Tessera(LocalDate.now(),LocalDate.now().minusDays(30),utente1fromdb);
//        tessd.save(tessera1);
        Tessera tessera1fromdb = tessd.findById("09fe1c1b-9d10-4cbf-9fa6-bac85a0290f1");
//
//        Mezzo mezzo1=new Mezzo(TipoMezzo.AUTOBUS,50);
//        md.save(mezzo1);
//       Mezzo mezzo1fromdb = md.findById("575cba6f-16a4-48de-a025-17137cf6b7a6");
//
//        DistributoriAutomatici distributore1= new DistributoriAutomatici("Roma", StatoDistributoreAutomatico.INSERVIZIO);
//        dd.save(distributore1);
//
//        Biglietto biglietto1=new Biglietto(LocalDate.now(),false,LocalDate.now().plusDays(1),mezzo1fromdb);
//        bd.save(biglietto1);
//
//        Abbonamento abbonamento1=new Abbonamento(LocalDate.now(),LocalDate.now().plusYears(1),tessera1fromdb);
//        ad.save(abbonamento1);
// Abbonamento abbonamento2 = dd.emettiAbbonamento(tessera1fromdb, false);
//        Abbonamento abbonamento3 = dd.emettiAbbonamento(tessera1fromdb, true);

//        Manutenzione manutenzione1=new Manutenzione(LocalDate.now(),LocalDate.now().minusDays(30),mezzo1fromdb);
//        mand.save(manutenzione1);




 emf.close();
 em.close();
    }
}
