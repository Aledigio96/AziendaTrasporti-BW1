package buildWeek;

import buildWeek.dao.*;
import buildWeek.entities.*;
import buildWeek.enums.StatoDistributoreAutomatico;
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
        UtenteDAO ud= new UtenteDAO(em);
        TrattaDAO td= new TrattaDAO(em);
        TesseraDAO tessd= new TesseraDAO(em);
        MezzoDAO md= new MezzoDAO(em);
        DistributoreDAO dd= new DistributoreDAO(em);
        BigliettoDAO bd= new BigliettoDAO(em);
        AbbonamentoDAO ad=new AbbonamentoDAO(em);
        ManutenzioneDAO mand= new ManutenzioneDAO(em);
        PercorrenzaDAO pd= new PercorrenzaDAO(em);

//        Utente utente1= new Utente("Alessandro","Di Giovanni","alessandr.g@gmail.com","1234", LocalDate.now().minusYears(29));
//        ud.save(utente1);
//        Utente utente1fromdb = ud.findById("a644f18d-6188-4127-bf4d-db5196bd2940");
        Utente utente2 = new Utente("Maria", "Rossi", "maria.rossi@gmail.com", "abcd", LocalDate.now().minusYears(25));
        Utente utente3 = new Utente("Giovanni", "Bianchi", "giovanni.bianchi@gmail.com", "password", LocalDate.now().minusYears(34));
        Utente utente4 = new Utente("Laura", "Verdi", "laura.verdi@gmail.com", "qwerty", LocalDate.now().minusYears(22));
        Utente utente5 = new Utente("Marco", "Neri", "marco.neri@gmail.com", "9876", LocalDate.now().minusYears(30));
        Utente utente6 = new Utente("Federica", "Gialli", "federica.gialli@gmail.com", "12345", LocalDate.now().minusYears(28));
        Utente utente7 = new Utente("Luca", "Blu", "luca.blu@gmail.com", "password1", LocalDate.now().minusYears(35));
        Utente utente8 = new Utente("Elena", "Perni", "elena.perni@gmail.com", "elena123", LocalDate.now().minusYears(31));
        Utente utente9 = new Utente("Francesco", "Marroni", "francesco.marroni@gmail.com", "francesco99", LocalDate.now().minusYears(26));
        Utente utente10 = new Utente("Giulia", "Rossi", "giulia.rossi@gmail.com", "giulia2023", LocalDate.now().minusYears(24));
//        ud.save(utente2);
//        ud.save(utente3);
//        ud.save(utente4);
//        ud.save(utente5);
//        ud.save(utente6);
//        ud.save(utente7);
//        ud.save(utente8);
//        ud.save(utente9);
//        ud.save(utente10);
//
//        Tratta tratta1= new Tratta("Roma","Latina");
        Tratta tratta1fromdb= td.findById("8024c49b-ce4a-407c-9a71-c347918b0d99");
//        td.save(tratta1);
        Tratta tratta2 = new Tratta("Frosinone", "Afragola");
        Tratta tratta3 = new Tratta("Viterbo", "Rieti");
        Tratta tratta4 = new Tratta("Civitavecchia", "Ostia");
        Tratta tratta5 = new Tratta("Ciampino", "Frascati");
        Tratta tratta6 = new Tratta("Albano Laziale", "Pomezia");
        Tratta tratta7 = new Tratta("Cassi", "Anzio");
        Tratta tratta8 = new Tratta("Velletri", "Ariccia");
        Tratta tratta9 = new Tratta("Tivoli", "Guidonia");
        Tratta tratta10 = new Tratta("Sora", "Monteporzio Catone");
//        td.save(tratta2);
//        td.save(tratta3);
//        td.save(tratta4);
//        td.save(tratta5);
//        td.save(tratta6);
//        td.save(tratta7);
//        td.save(tratta8);
//        td.save(tratta9);
//        td.save(tratta10);
//
//        Tessera tessera1= new Tessera(LocalDate.now(),LocalDate.now().minusDays(30),utente1fromdb);
//        tessd.save(tessera1);
    //    Tessera tessera1fromdb = tessd.findById("09fe1c1b-9d10-4cbf-9fa6-bac85a0290f1");
//
//        Mezzo mezzo1=new Mezzo(TipoMezzo.AUTOBUS,50);
//        md.save(mezzo1);
      Mezzo mezzo1fromdb = md.findById("575cba6f-16a4-48de-a025-17137cf6b7a6");
        Mezzo mezzo2fromdb = md.findById("2a809425-78ed-4563-841a-e7c08c1baab1");
        Mezzo mezzo3fromdb = md.findById("472c77c1-3c10-4315-96d7-6c07c73d49de");
        Mezzo mezzo4fromdb = md.findById("575cba6f-16a4-48de-a025-17137cf6b7a6");
        Mezzo mezzo5fromdb = md.findById("91361440-4e5f-49f5-8589-105149e4b6a7");
//        Mezzo mezzo2 = new Mezzo(TipoMezzo.AUTOBUS, 60);
//        Mezzo mezzo3 = new Mezzo(TipoMezzo.AUTOBUS, 70);
//        Mezzo mezzo4 = new Mezzo(TipoMezzo.AUTOBUS, 80);
//        Mezzo mezzo5 = new Mezzo(TipoMezzo.AUTOBUS, 90);
//
//        Mezzo mezzo6 = new Mezzo(TipoMezzo.TRAM, 100);
//        Mezzo mezzo7 = new Mezzo(TipoMezzo.TRAM, 120);
//        Mezzo mezzo8 = new Mezzo(TipoMezzo.TRAM, 150);
//        Mezzo mezzo9 = new Mezzo(TipoMezzo.TRAM, 180);
//        Mezzo mezzo10 = new Mezzo(TipoMezzo.TRAM, 200);
//        md.save(mezzo2);
//        md.save(mezzo3);
//        md.save(mezzo4);
//        md.save(mezzo5);
//        md.save(mezzo6);
//        md.save(mezzo7);
//        md.save(mezzo8);
//        md.save(mezzo9);
//        md.save(mezzo10);

//
//        DistributoriAutomatici distributore1= new DistributoriAutomatici("Roma", StatoDistributoreAutomatico.INSERVIZIO);
//        dd.save(distributore1);
//
//        Biglietto biglietto1=new Biglietto(LocalDate.now(),false,LocalDate.now().plusDays(1),mezzo1fromdb);
//        bd.save(biglietto1);
//        Biglietto biglietto1fromdb= bd.findById("1608a88d-7947-41c5-a624-7032f3a3c294");
//
//        Abbonamento abbonamento1=new Abbonamento(LocalDate.now(),LocalDate.now().plusYears(1),tessera1fromdb);
//        ad.save(abbonamento1);
//        Abbonamento abbonamento2 = dd.emettiAbbonamento(tessera1fromdb, false);
//        Abbonamento abbonamento3 = dd.emettiAbbonamento(tessera1fromdb, true);

//        Manutenzione manutenzione1=new Manutenzione(LocalDate.now(),LocalDate.now().minusDays(30),mezzo1fromdb);
//        Manutenzione manutenzione1 = new Manutenzione(LocalDate.now(), LocalDate.now().minusDays(30), mezzo1fromdb);
//        Manutenzione manutenzione2 = new Manutenzione(LocalDate.now().minusDays(5), LocalDate.now().minusDays(35), mezzo2fromdb);
//        Manutenzione manutenzione3 = new Manutenzione(LocalDate.now().minusDays(10), LocalDate.now().minusDays(40), mezzo3fromdb);
//        Manutenzione manutenzione4 = new Manutenzione(LocalDate.now().minusDays(15), LocalDate.now().minusDays(45), mezzo4fromdb);
//        Manutenzione manutenzione5 = new Manutenzione(LocalDate.now().minusDays(20), LocalDate.now().minusDays(50), mezzo5fromdb);
//
//        mand.save(manutenzione1);
//        mand.save(manutenzione2);
//        mand.save(manutenzione3);
//        mand.save(manutenzione4);
//        mand.save(manutenzione5);

//        bd.validaBiglietto(UUID.fromString("1608a88d-7947-41c5-a624-7032f3a3c294"));
//        mand.countManutenzioniPerMezzo(mezzo1fromdb);
//        Abbonamento abbonamento1fromdb= ad.findById("c006d461-6fa0-4b98-82ed-3bdace728722");
//        ad.abbonamentoValidoOscaduto("c006d461-6fa0-4b98-82ed-3bdace728722");
//        Percorrenza percorrenza1= new Percorrenza(30,40,mezzo1fromdb,tratta1fromdb);
//        pd.save(percorrenza1);
        


//        dd.stampaConteggioEmissioni(LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 8));


 emf.close();
 em.close();
    }
}
