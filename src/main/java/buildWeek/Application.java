package buildWeek;

import buildWeek.dao.*;
import buildWeek.entities.*;
import buildWeek.enums.StatoDistributoreAutomatico;
import buildWeek.enums.TipoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto! Identificati per entrare nella tua area riservata.");
        System.out.print("Inserisci la tua email: ");
        String email = scanner.nextLine();
        System.out.print("Inserisci la tua password: ");
        String password = scanner.nextLine();

        Utente utente = ud.findByEmail(email);

        if (utente != null && utente.getPassword().equals(password)) {
            if (email.contains("@amministratore.com")) {
                System.out.println("Accesso effettuato come AMMINISTRATORE.");
                System.out.println("Scegli che operazione vuoi effettuare:");
                System.out.println("1. Lista Utenti");
                System.out.println("2. Lista Tratte");
                System.out.println("3. Lista Mezzi");
                System.out.println("4. Gestione Biglietti");
                System.out.println("5. Lista Abbonamenti");
                int scelta = scanner.nextInt();
                scanner.nextLine();
                switch (scelta) {
                    case 1:
                        System.out.println("---------- Lista Utenti ----------");
                        ud.findAll();
                        break;
                    case 2:
                        System.out.println("---------- Lista Tratte ----------");
                        td.findAll();

                        System.out.println("Vuoi aggiungere una nuova tratta? (s/n)");
                        String sceltaTratta = scanner.nextLine();

                        if (sceltaTratta == "s"){
                            System.out.print("Inserisci la zona di partenza: ");
                            String zonaPartenza = scanner.nextLine();
                            System.out.print("Inserisci la durata della tratta in minuti: ");
                            int tempoPrevisto = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Inserisci la zona di arrivo: ");
                            String zonaArrivo = scanner.nextLine();

                            Tratta nuovaTratta = new Tratta(zonaPartenza, tempoPrevisto, zonaArrivo);
                            td.save(nuovaTratta);
                            System.out.println("Nuova tratta aggiunta con successo: " + nuovaTratta);
                        } else{
                            System.out.println("Nessuna nuova tratta aggiunta.");
                        }
                        break;
                    case 3:
                        System.out.println("---------- Lista Mezzi ----------");
                        md.findAll();

                        System.out.println("Vuoi aggiungere un nuovo mezzo? (s/n)");
                        String sceltaMezzo = scanner.nextLine();
                        if (sceltaMezzo == "s") {
                            System.out.print("Inserisci il tipo di mezzo (AUTOBUS/TRAM): ");
                            String tipoMezzo = scanner.nextLine().toUpperCase();
                            System.out.print("Inserisci la capienza del mezzo: ");
                            int capienza = scanner.nextInt();
                            scanner.nextLine();

                            TipoMezzo tipo = TipoMezzo.valueOf(tipoMezzo);
                            Mezzo nuovoMezzo = new Mezzo(tipo, capienza);
                            md.save(nuovoMezzo);
                            System.out.println("Nuovo mezzo aggiunto con successo: " + nuovoMezzo);
                        } else {
                            System.out.println("Nessun nuovo mezzo aggiunto.");
                        }
                        break;
                    case 4:
                        System.out.println("---------- Gestione Biglietti ----------");
                        System.out.println("1. Visualizza tutti i biglietti");
                        System.out.println("2. Visualizza biglietti in un intervallo di tempo");
                        System.out.println("3. Visualizza biglietti emessi da uno specifico distributore");
                        int sceltaBiglietti = scanner.nextInt();
                        scanner.nextLine();
                        switch (sceltaBiglietti) {
                            case 1:
                                bd.findAll();
                                break;
                            case 2:
                                System.out.print("Data inizio (yyyy-mm-dd): ");
                                LocalDate inizio = LocalDate.parse(scanner.nextLine());
                                System.out.print("Data fine (yyyy-mm-dd): ");
                                LocalDate fine = LocalDate.parse(scanner.nextLine());
                                bd.findByPeriodo(inizio, fine);
                                break;
                            case 3:
                                System.out.print("ID distributore: ");
                                String idDistributore = scanner.nextLine();
                                bd.findByDistributore(UUID.fromString(idDistributore));
                                break;
                            default:
                                System.out.println("Scelta non valida.");
                        }
                        break;
                    case 5:
                        System.out.println("---------- Gestione Abbonamenti ----------");
                        System.out.println("1. Visualizza tutti gli abbonamenti");
                        System.out.println("2. Visualizza abbonamenti in un intervallo di tempo");
                        System.out.println("3. Visualizza abbonamenti emessi da uno specifico distributore");
                        int sceltaAbbonamenti = scanner.nextInt();
                        scanner.nextLine();
                        switch (sceltaAbbonamenti) {
                            case 1:
                                ad.findAll();
                                break;
                            case 2:
                                System.out.print("Data inizio (yyyy-mm-dd): ");
                                LocalDate inizio = LocalDate.parse(scanner.nextLine());
                                System.out.print("Data fine (yyyy-mm-dd): ");
                                LocalDate fine = LocalDate.parse(scanner.nextLine());
                                ad.findByPeriodo(inizio, fine);
                                break;
                            case 3:
                                System.out.print("ID distributore: ");
                                String idDistributore = scanner.nextLine();
                                ad.findByDistributore(UUID.fromString(idDistributore));
                                break;
                            default:
                                System.out.println("Scelta non valida.");
                        }
                        break;
                    default:
                        System.out.println("Scelta non valida.");
                }
            } else if (email.contains("@utente.com")) {
                System.out.println("Accesso effettuato come UTENTE.");
                // logica utente
            } else {
                System.out.println("Ruolo non riconosciuto.");
            }
        } else {
            System.out.println("Credenziali non valide.");
        }

//        Utente utente1= new Utente("Alessandro","Di Giovanni","alessandr.g@gmail.com","1234", LocalDate.now().minusYears(29));
//        ud.save(utente1);
//        Utente utente1fromdb = ud.findById("a644f18d-6188-4127-bf4d-db5196bd2940");
        Utente utente2 = new Utente("Maria", "Rossi", "maria.rossi@amministratore.com", "abcd", LocalDate.now().minusYears(25));
        Utente utente3 = new Utente("Giovanni", "Bianchi", "giovanni.bianchi@utente.com", "password", LocalDate.now().minusYears(34));
        Utente utente4 = new Utente("Laura", "Verdi", "laura.verdi@utente.com", "qwerty", LocalDate.now().minusYears(22));
        Utente utente5 = new Utente("Marco", "Neri", "marco.neri@utente.com", "9876", LocalDate.now().minusYears(30));
        Utente utente6 = new Utente("Federica", "Gialli", "federica.gialli@utente.com", "12345", LocalDate.now().minusYears(28));
        Utente utente7 = new Utente("Luca", "Blu", "luca.blu@utente.com", "password1", LocalDate.now().minusYears(35));
        Utente utente8 = new Utente("Elena", "Perni", "elena.perni@utente.com", "elena123", LocalDate.now().minusYears(31));
        Utente utente9 = new Utente("Francesco", "Marroni", "francesco.marroni@amministratore.com", "francesco99", LocalDate.now().minusYears(26));
        Utente utente10 = new Utente("Giulia", "Rossi", "giulia.rossi@utente.com", "giulia2023", LocalDate.now().minusYears(24));
//        ud.save(utente2);
//        ud.save(utente3);
//        ud.save(utente4);
//        ud.save(utente5);
//        ud.save(utente6);
//        ud.save(utente7);
//        ud.save(utente8);
//        ud.save(utente9);
//        ud.save(utente10);

//        Tratta tratta1= new Tratta("Roma","Latina");
  //      Tratta tratta1fromdb= td.findById("8024c49b-ce4a-407c-9a71-c347918b0d99");
//        td.save(tratta1);
        Tratta tratta2 = new Tratta("Frosinone",120, "Afragola");
        Tratta tratta3 = new Tratta("Viterbo",60, "Rieti");
        Tratta tratta4 = new Tratta("Civitavecchia", 50,"Ostia");
        Tratta tratta5 = new Tratta("Ciampino",40, "Frascati");
        Tratta tratta6 = new Tratta("Albano Laziale",70, "Pomezia");
        Tratta tratta7 = new Tratta("Cassi",80, "Anzio");
        Tratta tratta8 = new Tratta("Velletri",120, "Ariccia");
        Tratta tratta9 = new Tratta("Tivoli",20, "Guidonia");
        Tratta tratta10 = new Tratta("Sora",120, "Monteporzio Catone");
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
//      Mezzo mezzo1fromdb = md.findById("575cba6f-16a4-48de-a025-17137cf6b7a6");
//        Mezzo mezzo2fromdb = md.findById("2a809425-78ed-4563-841a-e7c08c1baab1");
//        Mezzo mezzo3fromdb = md.findById("472c77c1-3c10-4315-96d7-6c07c73d49de");
//        Mezzo mezzo4fromdb = md.findById("575cba6f-16a4-48de-a025-17137cf6b7a6");
//        Mezzo mezzo5fromdb = md.findById("91361440-4e5f-49f5-8589-105149e4b6a7");
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

scanner.close();
 emf.close();
 em.close();
    }
}
