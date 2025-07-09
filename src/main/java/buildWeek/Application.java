package buildWeek;

import buildWeek.dao.*;
import buildWeek.entities.*;
import buildWeek.enums.StatoDistributoreAutomatico;
import buildWeek.enums.TipoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AziendaTrasporti");
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        //Presa dei DAO
        UtenteDAO ud= new UtenteDAO(em);
        TrattaDAO td= new TrattaDAO(em);
        TesseraDAO tessd= new TesseraDAO(em);
        MezzoDAO md= new MezzoDAO(em);
        DistributoreDAO dd= new DistributoreDAO(em);
        BigliettoDAO bd= new BigliettoDAO(em);
        AbbonamentoDAO ad=new AbbonamentoDAO(em);
        ManutenzioneDAO mand= new ManutenzioneDAO(em);
        PercorrenzaDAO pd= new PercorrenzaDAO(em);

//        Scanner scanner = new Scanner(System.in);
//
//        //Inizializzazione del database
//        System.out.println("Benvenuto! Identificati per entrare nella tua area riservata.");
//
//        //Richiesta email
//        System.out.print("Inserisci la tua email: ");
//        String email = scanner.nextLine();
//
//        //Richiesta password
//        System.out.print("Inserisci la tua password: ");
//        String password = scanner.nextLine();
//
//        //Controllo se l'utente esiste e se la password è corretta
//        Utente utente = ud.findByEmail(email);
//
//        //Controllo se utente o amministratore
//        if (utente != null && utente.getPassword().equals(password)) {
//
//            //AMMINISTRATORE
//            if (email.contains("@amministratore.com")) {
//
//                //Menù Amministratore
//                System.out.println("Accesso effettuato come AMMINISTRATORE.");
//                System.out.println("Scegli che operazione vuoi effettuare:");
//                System.out.println("1. Lista Utenti");
//                System.out.println("2. Lista Tratte");
//                System.out.println("3. Lista Mezzi");
//                System.out.println("4. Gestione Biglietti");
//                System.out.println("5. Lista Abbonamenti");
//                int scelta = scanner.nextInt();
//                scanner.nextLine();
//
//                switch (scelta) {
//                    case 1:
//                        System.out.println("---------- Lista Utenti ----------");
//                        ud.findAll(); // Mostra tutti gli utenti
//                        break;
//                    case 2:
//                        System.out.println("---------- Lista Tratte ----------");
//                        td.findAll(); // Mostra tutte le tratte
//
//                        // Chiedo se l'amministratore vuole aggiungere una nuova tratta
//                        System.out.println("Vuoi aggiungere una nuova tratta? (s/n)");
//                        String sceltaTratta = scanner.nextLine();
//
//                        if (Objects.equals(sceltaTratta, "s")){
//
//                            // Chiedo i dettagli della nuova tratta
//                            System.out.print("Inserisci la zona di partenza: ");
//                            String zonaPartenza = scanner.nextLine();
//
//                            System.out.print("Inserisci la zona di arrivo: ");
//                            String zonaArrivo = scanner.nextLine();
//
//                            System.out.print("Inserisci la durata della tratta in minuti: ");
//                            int tempoPrevisto = scanner.nextInt();
//                            scanner.nextLine();
//
//                            // Crea e salva la nuova tratta
//                            Tratta nuovaTratta = new Tratta(zonaPartenza, tempoPrevisto, zonaArrivo);
//                            td.save(nuovaTratta);
//                            System.out.println("Nuova tratta aggiunta con successo: " + nuovaTratta);
//
//                        } else{
//                            System.out.println("Nessuna nuova tratta aggiunta.");
//                        }
//                        break;
//
//                    case 3:
//                        System.out.println("---------- Lista Mezzi ----------");
//                        md.findAll(); // Mostra tutti i mezzi
//
//                        // Chiedo se l'amministratore vuole aggiungere un nuovo mezzo
//                        System.out.println("Vuoi aggiungere un nuovo mezzo? (s/n)");
//                        String sceltaMezzo = scanner.nextLine();
//
//                        if (Objects.equals(sceltaMezzo, "s")) {
//
//                            // Chiedo i dettagli del nuovo mezzo
//                            System.out.print("Inserisci il tipo di mezzo (AUTOBUS/TRAM): ");
//                            String tipoMezzo = scanner.nextLine().toUpperCase();
//
//                            System.out.print("Inserisci la capienza del mezzo: ");
//                            int capienza = scanner.nextInt();
//                            scanner.nextLine();
//
//                            TipoMezzo tipo = TipoMezzo.valueOf(tipoMezzo);
//
//                            // Crea e salva il nuovo mezzo
//                            Mezzo nuovoMezzo = new Mezzo(tipo, capienza);
//                            md.save(nuovoMezzo);
//                            System.out.println("Nuovo mezzo aggiunto con successo: " + nuovoMezzo);
//
//                        } else {
//                            System.out.println("Nessun nuovo mezzo aggiunto.");
//                        }
//                        break;
//
//                    case 4:
//                        // Gestione Biglietti
//                        System.out.println("---------- Gestione Biglietti ----------");
//                        System.out.println("1. Visualizza tutti i biglietti");
//                        System.out.println("2. Visualizza biglietti in un intervallo di tempo");
//                        System.out.println("3. Visualizza biglietti emessi da uno specifico distributore");
//
//                        int sceltaBiglietti = scanner.nextInt();
//                        scanner.nextLine();
//
//                        switch (sceltaBiglietti) {
//                            case 1:
//                                bd.findAll(); // Mostra tutti i biglietti
//                                break;
//
//                            case 2:
//                                // Chiedo l'intervallo di date
//                                System.out.print("Data inizio (yyyy-mm-dd): ");
//                                LocalDate inizio = LocalDate.parse(scanner.nextLine());
//
//                                System.out.print("Data fine (yyyy-mm-dd): ");
//                                LocalDate fine = LocalDate.parse(scanner.nextLine());
//
//                                // Trovo i biglietti nell'intervallo
//                                bd.findByPeriodo(inizio, fine);
//                                break;
//
//                            case 3:
//                                // Chiedo l'ID del distributore
//                                System.out.print("ID distributore: ");
//                                String idDistributore = scanner.nextLine();
//
//                                // Trovo i biglietti emessi dal distributore
//                                bd.findByDistributore(UUID.fromString(idDistributore));
//                                break;
//
//                            default:
//                                System.out.println("Scelta non valida.");
//                        }
//                        break;
//
//                    case 5:
//                        // Gestione Abbonamenti
//                        System.out.println("---------- Gestione Abbonamenti ----------");
//                        System.out.println("1. Visualizza tutti gli abbonamenti");
//                        System.out.println("2. Visualizza abbonamenti in un intervallo di tempo");
//                        System.out.println("3. Visualizza abbonamenti emessi da uno specifico distributore");
//
//                        int sceltaAbbonamenti = scanner.nextInt();
//                        scanner.nextLine();
//
//                        switch (sceltaAbbonamenti) {
//                            case 1:
//                                ad.findAll(); // Mostra tutti gli abbonamenti
//                                break;
//                            case 2:
//                                // Chiedo l'intervallo di date
//                                System.out.print("Data inizio (yyyy-mm-dd): ");
//                                LocalDate inizio = LocalDate.parse(scanner.nextLine());
//
//                                System.out.print("Data fine (yyyy-mm-dd): ");
//                                LocalDate fine = LocalDate.parse(scanner.nextLine());
//
//                                // Trovo gli abbonamenti nell'intervallo
//                                ad.findByPeriodo(inizio, fine);
//                                break;
//
//                            case 3:
//                                // Chiedo l'ID del distributore
//                                System.out.print("ID distributore: ");
//                                String idDistributore = scanner.nextLine();
//
//                                // Trovo gli abbonamenti emessi dal distributore
//                                ad.findByDistributore(UUID.fromString(idDistributore));
//                                break;
//
//                            default:
//                                System.out.println("Scelta non valida.");
//                        }
//
//                        break;
//                    default:
//                        System.out.println("Scelta non valida.");
//                }
//            } else if (email.contains("@utente.com")) {
//
//                //Menù utente
//                System.out.println("Accesso effettuato come UTENTE.");
//                System.out.println("Scegli un'operazione:");
//                System.out.println("1. Crea Biglietto");
//                System.out.println("2. Crea Abbonamento");
//                System.out.println("3. Visualizza tratte disponibili");
//                int sceltaUtente = scanner.nextInt();
//                scanner.nextLine();
//
//                switch (sceltaUtente) {
//                    case 1:
//                        //Chiedo l'ID del distributore
//                        System.out.println("Seleziona l'ID del distributore:");
//                        dd.findAll();
//                        String idDistributore = scanner.nextLine();
//
//                        //Controllo se il distributore esiste
//                        System.out.println("Seleziona l'ID del mezzo:");
//                        md.findAll();
//                        String idMezzo = scanner.nextLine();
//
//                        //Identifico distributore e mezzo
//                        Distributore distributore = dd.findById(String.valueOf(UUID.fromString(idDistributore)));
//                        Mezzo mezzo = md.findById(idMezzo);
//
//                        //Creazione del biglietto
//                        Biglietto biglietto = dd.emettiBiglietto(distributore, mezzo);
//                        System.out.println("Biglietto creato con successo: " + biglietto);
//                        break;
//
//                    case 2:
//                        //Chiedo il tipo di abbonamento (settimanale o mensile)
//                        System.out.println("Abbonamento settimanale o mensile? (s/m)");
//                        String tipoAbbonamento = scanner.nextLine();
//                        boolean abbonamentoScelto; // True se settimanale, False se mensile
//
//                        //Controllo la scelta dell'utente
//                        if( tipoAbbonamento.equalsIgnoreCase("s")) {
//                            abbonamentoScelto = true;
//                            System.out.println("Abbonamento settimanale selezionato.");
//                        } else if (tipoAbbonamento.equalsIgnoreCase("m")) {
//                            abbonamentoScelto = false;
//                            System.out.println("Abbonamento mensile selezionato.");
//                        } else {
//                            System.out.println("Scelta non valida. Riprova.");
//                            break;
//                        }
//
//                        //Chiedo l'ID del distributore
//                        System.out.println("Seleziona l'ID del distributore:");
//                        dd.findAll();
//                        String idDistributoreAbb = scanner.nextLine();
//
//                        //Trovo il distributore
//                        System.out.println("Seleziona l'ID della tessera:");
//                        Distributore distributoreAbb = dd.findById(String.valueOf(UUID.fromString(idDistributoreAbb)));
//
//                        //Controllo se l'utente ha una tessera
//                        Tessera tessera = tessd.findByUtenteId(utente.getId().toString());
//                        if (tessera == null) {
//                            System.out.println("Nessuna tessera trovata per questo utente.");
//                            break;
//                        }
//
//                        //Emetto l'abbonamento
//                        dd.emettiAbbonamento(distributoreAbb, tessera, abbonamentoScelto);
//                        System.out.println("Abbonamento creato con successo.");
//                        break;
//
//                    case 3:
//                        System.out.println("---------- Tratte disponibili ----------");
//                        td.findAll();
//                        break;
//                    default:
//                        System.out.println("Scelta non valida.");
//                }
//
//            } else {
//                System.out.println("Ruolo non riconosciuto.");
//            }
//        } else {
//            System.out.println("Credenziali non valide.");
//        }

//       Utente utente1= new Utente("Alessandro","Di Giovanni","alessandr.g@gmail.com","1234", LocalDate.now().minusYears(29));
//        ud.save(utente1);
        Utente utente1fromdb = ud.findById("16613c6c-3f6b-4e79-a9bc-78047f42d4d5");
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

        Tratta tratta1= new Tratta("Roma",140,"Latina");
        //td.save(tratta1);
        Tratta tratta1fromdb= td.findById("462bc54e-97fe-4963-ad53-cc41ff03ea6a");
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

        Tessera tessera1= new Tessera(LocalDate.now(),LocalDate.now().minusDays(30),utente1fromdb);
       // tessd.save(tessera1);
        Tessera tessera1fromdb = tessd.findById("481794d7-6f35-4b91-bebd-bfbc27aee445");

        Mezzo mezzo1=new Mezzo(TipoMezzo.AUTOBUS,50);
        //md.save(mezzo1);


        Mezzo mezzo2 = new Mezzo(TipoMezzo.AUTOBUS, 60);
        Mezzo mezzo3 = new Mezzo(TipoMezzo.AUTOBUS, 70);
        Mezzo mezzo4 = new Mezzo(TipoMezzo.AUTOBUS, 80);
        Mezzo mezzo5 = new Mezzo(TipoMezzo.AUTOBUS, 90);

        Mezzo mezzo6 = new Mezzo(TipoMezzo.TRAM, 100);
        Mezzo mezzo7 = new Mezzo(TipoMezzo.TRAM, 120);
        Mezzo mezzo8 = new Mezzo(TipoMezzo.TRAM, 150);
        Mezzo mezzo9 = new Mezzo(TipoMezzo.TRAM, 180);
        Mezzo mezzo10 = new Mezzo(TipoMezzo.TRAM, 200);
//        md.save(mezzo2);
//        md.save(mezzo3);
//        md.save(mezzo4);
//        md.save(mezzo5);
//        md.save(mezzo6);
//        md.save(mezzo7);
//        md.save(mezzo8);
//        md.save(mezzo9);
//        md.save(mezzo10);

        Mezzo mezzo1fromdb = md.findById("07c3b7de-8f99-4ce8-bf9c-3fff0376412f");
        Mezzo mezzo2fromdb = md.findById("16257258-e5dc-4981-8f33-c10404bc7762");
        Mezzo mezzo3fromdb = md.findById("1b579e11-3743-4cda-a730-1d4b701f34a6");
        Mezzo mezzo4fromdb = md.findById("315991f7-549c-4a53-8ab0-bfff9e7d3b23");
        Mezzo mezzo5fromdb = md.findById("95bfe0d7-0b31-4bcf-9296-c8d64ee5953e");
        DistributoreAutomatico distributore1= new DistributoreAutomatico("Roma", StatoDistributoreAutomatico.INSERVIZIO);
        //dd.save(distributore1);


//        Biglietto biglietto1=new Biglietto(LocalDate.now(),false,LocalDate.now().plusDays(1),mezzo1fromdb);
//        bd.save(biglietto1);
       // Biglietto biglietto1fromdb= bd.findById("1608a88d-7947-41c5-a624-7032f3a3c294");

//        Abbonamento abbonamento1=new Abbonamento(LocalDate.now(),LocalDate.now().plusYears(1),tessera1fromdb);
//        ad.save(abbonamento1);
//        Abbonamento abbonamento2 = dd.emettiAbbonamento(tessera1fromdb, false);
//        Abbonamento abbonamento3 = dd.emettiAbbonamento(tessera1fromdb, true);

        Manutenzione manutenzione1=new Manutenzione(LocalDate.now(),LocalDate.now().minusDays(30),mezzo1fromdb);
        Manutenzione manutenzione2 = new Manutenzione(LocalDate.now().minusDays(5), LocalDate.now().minusDays(35), mezzo2fromdb);
        Manutenzione manutenzione3 = new Manutenzione(LocalDate.now().minusDays(10), LocalDate.now().minusDays(40), mezzo3fromdb);
        Manutenzione manutenzione4 = new Manutenzione(LocalDate.now().minusDays(15), LocalDate.now().minusDays(45), mezzo4fromdb);
        Manutenzione manutenzione5 = new Manutenzione(LocalDate.now().minusDays(20), LocalDate.now().minusDays(50), mezzo5fromdb);

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
        


        dd.stampaConteggioEmissioni(LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 8));

//scanner.close();
 emf.close();
 em.close();
    }
}
