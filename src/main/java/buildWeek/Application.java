package buildWeek;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AziendaTrasporti");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
//        UtenteDAO ut=new UtenteDAO(em);
//        LocalDate data1=LocalDate.of(1996,01,14);
//        Utente utente1= new Utente("Alessandro","Di Giovanni",data1);
////        ut.save(utente1);
//       Utente utente1fromdb = ut.findById("8068df4c-4dd8-4348-9202-59ecb735d8d4");
//
//
//       TesseraDAO tess = new TesseraDAO(em);
//
//        Tessera tessera1 = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1), true, utente1fromdb);
//        //tess.save(tessera1);
//        Tessera tessera1fromdb= tess.findById("9b221d99-34c4-4f9d-b0e1-793a3e852b73");
//
//       MezzoDAO md = new MezzoDAO(em);
//        Mezzo mezzo1 = new Mezzo(TipoMezzo.AUTOBUS, 30, true);
////        md.save(mezzo1);
//        Mezzo mezzo1fromdb = md.findById("eab14c3b-4326-4155-97e9-bf9db8dd193b");
//
//        TrattaDAO td = new TrattaDAO(em);
////        Tratta tratta1 = td.findByZonaPartenza("Latina");
////        if (tratta1 == null) {
////            tratta1 = new Tratta("Latina", "Roma", 45, mezzo1fromdb);
////            td.save(tratta1);
////        }
//        Tratta tratta1fromdb = td.findById("ed2a5249-99c9-4166-9c81-47bc29484d5b");
//
//       DistributoreDAO dd = new DistributoreDAO(em);
//        RivenditoriAutorizzati distributore1 = new RivenditoriAutorizzati(tratta1fromdb);
//   //     dd.save(distributore1);
//        Distributore distributore1fromdb = dd.findById("db23a809-dfac-440b-af3d-8c79c61be9c0");
//
//        dd.emettiBiglietto(distributore1fromdb, mezzo1fromdb);
//
//       dd.emettiAbbonamento(TipoAbbonamento.SETTIMANALE, tessera1fromdb);
 emf.close();
 em.close();
    }
}
