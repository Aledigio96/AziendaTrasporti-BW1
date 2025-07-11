package buildWeek;

import buildWeek.dao.*;
import buildWeek.entities.*;
import buildWeek.enums.StatoDistributoreAutomatico;
import buildWeek.enums.TipoMezzo;
import buildWeek.enums.TipoRivenditoreAutorizzato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

        Scanner scanner = new Scanner(System.in);

        //Inizializzazione del database
        System.out.println("Benvenuto! Identificati per entrare nella tua area riservata.");

        try {
            //Richiesta email
            System.out.print("Inserisci la tua email: ");
            String email = scanner.nextLine();

            //Richiesta password
            System.out.print("Inserisci la tua password: ");
            String password = scanner.nextLine();

            //Controllo se l'utente esiste e se la password è corretta
            Utente utente = null;
            try {
                utente = ud.findByEmail(email);
            } catch (Exception e) {
                System.out.println("Errore durante la ricerca dell'utente: " + e.getMessage());
            }

            //Controllo se utente o amministratore
            if (utente != null && utente.getPassword().equals(password)) {

                //AMMINISTRATORE
                if (email.contains("@amministratore.com")) {
                    boolean anncycle= true;
                    System.out.println("Accesso effettuato come AMMINISTRATORE.");
                    while (anncycle){
                        try {
                            //Menù Amministratore

                            System.out.println("Scegli che operazione vuoi effettuare:");
                            System.out.println("1. Lista Utenti");
                            System.out.println("2. Lista Tratte");
                            System.out.println("3. Lista Mezzi");
                            System.out.println("4. Gestione Biglietti");
                            System.out.println("5. Lista Abbonamenti");
                            System.out.println("6. Verifica numero manutenzioni per mezzo");
                            System.out.println("0. Esci dal programma");
                            int scelta = scanner.nextInt();
                            scanner.nextLine();

                            switch (scelta) {
                                case 1:
                                    try {
                                        System.out.println("---------- Lista Utenti ----------");
                                        ud.findAll(); // Mostra tutti gli utenti
                                    } catch (Exception e) {
                                        System.out.println("Errore nella visualizzazione utenti: " + e.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        System.out.println("---------- Lista Tratte ----------");
                                        td.findAll(); // Mostra tutte le tratte

                                        // Chiedo se l'amministratore vuole aggiungere una nuova tratta
                                        System.out.println("Vuoi aggiungere una nuova tratta? (s/n)");
                                        String sceltaTratta = scanner.nextLine();

                                        if (Objects.equals(sceltaTratta.toLowerCase(), "s")) {
                                            // Chiedo i dettagli della nuova tratta
                                            System.out.print("Inserisci la zona di partenza: ");
                                            String zonaPartenza = scanner.nextLine();

                                            System.out.print("Inserisci la zona di arrivo: ");
                                            String zonaArrivo = scanner.nextLine();

                                            System.out.print("Inserisci la durata prevista della tratta in minuti: ");
                                            int tempoPrevisto = scanner.nextInt();
                                            scanner.nextLine();

                                            // Crea e salva la nuova tratta
                                            Tratta nuovaTratta = new Tratta(zonaPartenza, tempoPrevisto, zonaArrivo, null);
                                            td.save(nuovaTratta);

                                            System.out.println("Nuova tratta aggiunta con successo: " + nuovaTratta);
                                        } else {
                                            System.out.println("Nessuna nuova tratta aggiunta.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Errore nella gestione delle tratte: " + e.getMessage());
                                    }
                                    break;

                                case 3:
                                    try {
                                        System.out.println("---------- Lista Mezzi ----------");
                                        md.findAll(); // Mostra tutti i mezzi

                                        // Chiedo se l'amministratore vuole aggiungere un nuovo mezzo
                                        System.out.println("Vuoi aggiungere un nuovo mezzo? (s/n)");
                                        String sceltaMezzo = scanner.nextLine();

                                        if (Objects.equals(sceltaMezzo.toLowerCase(), "s")) {
                                            // Chiedo i dettagli del nuovo mezzo
                                            System.out.print("Inserisci il tipo di mezzo (AUTOBUS/TRAM): ");
                                            String tipoMezzo = scanner.nextLine().toUpperCase();

                                            System.out.print("Inserisci la capienza del mezzo: ");
                                            int capienza = scanner.nextInt();
                                            scanner.nextLine();

                                            System.out.print("Inserisci l'orario di partenza (HH:mm): ");
                                            String orarioStr = scanner.nextLine();
                                            LocalTime orarioPartenza = LocalTime.parse(orarioStr);

                                            // Mostra le tratte disponibili
                                            System.out.println("Tratte disponibili:");
                                            td.findAll();
                                            System.out.print("Inserisci l'ID della tratta: ");
                                            String trattaId = scanner.nextLine();

                                            Tratta tratta = td.findById(trattaId);

                                            if (tratta == null) {
                                                System.out.println("Tratta non trovata con l'ID specificato.");
                                                break;
                                            }

                                            TipoMezzo tipo = TipoMezzo.valueOf(tipoMezzo);

                                            // Crea e salva il nuovo mezzo
                                            Mezzo nuovoMezzo = new Mezzo(tipo, capienza, orarioPartenza, tratta);
                                            md.save(nuovoMezzo);
                                            System.out.println("Nuovo mezzo aggiunto con successo: " + nuovoMezzo);

                                        } else {
                                            System.out.println("Nessun nuovo mezzo aggiunto.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Errore nella gestione dei mezzi: " + e.getMessage());
                                    }
                                    break;

                                case 4:
                                    boolean sceltabiglietti = true;
                                    while (sceltabiglietti) {
                                        try {
                                            // Gestione Biglietti

                                            System.out.println("---------- Gestione Biglietti ----------");
                                            System.out.println("1. Visualizza tutti i biglietti");
                                            System.out.println("2. Visualizza biglietti in un intervallo di tempo");
                                            System.out.println("3. Visualizza biglietti emessi da uno specifico distributore");
                                            System.out.println("0. Torna al menù principale");
                                            int sceltaBiglietti = scanner.nextInt();
                                            scanner.nextLine();

                                            switch (sceltaBiglietti) {
                                                case 1:
                                                    bd.findAll(); // Mostra tutti i biglietti
                                                    break;

                                                case 2:
                                                    // Chiedo l'intervallo di date
                                                    System.out.print("Data inizio (yyyy-mm-dd): ");
                                                    LocalDate inizio = LocalDate.parse(scanner.nextLine());

                                                    System.out.print("Data fine (yyyy-mm-dd): ");
                                                    LocalDate fine = LocalDate.parse(scanner.nextLine());

                                                    // Trovo i biglietti nell'intervallo
                                                    bd.findByPeriodo(inizio, fine);
                                                    break;

                                                case 3:
                                                   // Chiedo l'ID del distributore
                                                    System.out.println("Distributori disponibili:");
                                                    dd.findAll();
                                                    System.out.print("ID distributore: ");
                                                    String idDistributore = scanner.nextLine();

                                                    // Trovo i biglietti emessi dal distributore
                                                    List<Biglietto> biglietti = bd.findByDistributore(UUID.fromString(idDistributore));
                                                    for (Biglietto b : biglietti) {
                                                        System.out.println(b);
                                                    }
                                                    break;
                                                case 0:
                                                    sceltabiglietti = false;
                                                    System.out.println("Tornando al menù principale...");
                                                    break;

                                                default:
                                                    System.out.println("Scelta non valida.");
                                            }
                                            break;
                                        } catch (Exception e) {
                                            System.out.println("Errore nella gestione dei biglietti: " + e.getMessage());
                                            sceltabiglietti = false;
                                        }
                                    }
                                    break;

                                case 5:
                                    boolean sceltaAbbonamento = true;
                                    while (sceltaAbbonamento) {
                                        try {
                                            // Gestione Abbonamenti
                                            System.out.println("---------- Gestione Abbonamenti ----------");
                                            System.out.println("1. Visualizza tutti gli abbonamenti");
                                            System.out.println("2. Visualizza abbonamenti in un intervallo di tempo");
                                            System.out.println("3. Visualizza abbonamenti emessi da uno specifico distributore");
                                            System.out.println("0. Torna al menù principale");
                                            int sceltaAbbonamenti = scanner.nextInt();
                                            scanner.nextLine();

                                            switch (sceltaAbbonamenti) {
                                                case 1:
                                                    ad.findAll(); // Mostra tutti gli abbonamenti
                                                    break;
                                                case 2:
                                                    // Chiedo l'intervallo di date
                                                    System.out.print("Data inizio (yyyy-mm-dd): ");
                                                    LocalDate inizio = LocalDate.parse(scanner.nextLine());

                                                    System.out.print("Data fine (yyyy-mm-dd): ");
                                                    LocalDate fine = LocalDate.parse(scanner.nextLine());

                                                    // Trovo gli abbonamenti nell'intervallo
                                                    ad.findByPeriodo(inizio, fine);
                                                    break;

                                                case 3:
                                                    // Chiedo l'ID del distributore
                                                    System.out.print("ID distributore: ");
                                                    String idDistributore = scanner.nextLine();

                                                    // Trovo gli abbonamenti emessi dal distributore
                                                    ad.findByDistributore(UUID.fromString(idDistributore));
                                                    break;
                                                case 0:
                                                    sceltaAbbonamento = false;
                                                    System.out.println("Tornando al menù principale...");
                                                    break;

                                                default:
                                                    System.out.println("Scelta non valida.");
                                            }

                                        } catch (Exception e) {
                                            System.out.println("Errore nella gestione degli abbonamenti: " + e.getMessage());
                                            sceltaAbbonamento = false;
                                        }
                                    }    break;

                                case 6:
                                    try {
                                        // Verifica numero manutenzioni per mezzo
                                        System.out.println("---------- Verifica numero manutenzioni per mezzo ----------");
                                        md.findAll(); // Mostra tutti i mezzi

                                        System.out.print("Inserisci l'ID del mezzo: ");
                                        String idMezzoManutenzione = scanner.nextLine();
                                        Mezzo mezzoManutenzione = md.findById(idMezzoManutenzione);

                                        // Controllo se il mezzo esiste
                                        mand.countManutenzioniPerMezzo(mezzoManutenzione);
                                    } catch (Exception e) {
                                        System.out.println("Errore nella verifica delle manutenzioni: " + e.getMessage());
                                    }
                                    break;
                                case 0:
                                    anncycle=false;
                                    System.out.println("Uscita dal programma.");
                                    break;

                                default:
                                    System.out.println("Scelta non valida.");
                            }
                        } catch (Exception e) {
                            System.out.println("Errore nel menù amministratore: " + e.getMessage());
                        }
                    }
                } else if (email.contains("@utente.com")) {

                    //Menù utente
                    try {
                        System.out.println("Accesso effettuato come UTENTE.");

                        boolean cycleUtente = true;
                        while(cycleUtente){
                        System.out.println("Scegli un'operazione:");
                        System.out.println("1. Crea Biglietto");
                        System.out.println("2. Crea Abbonamento");
                        System.out.println("3. Visualizza tratte disponibili");
                            System.out.println("0. Esci dal programma");
                        int sceltaUtente = scanner.nextInt();
                        scanner.nextLine();

                        switch (sceltaUtente) {

                                    case 1:
                                        try {
                                            // Selezione distributore
                                            System.out.println("---------- Distributori disponibili ----------");
                                            dd.findAll();
                                            System.out.print("Inserisci l'ID del distributore: ");
                                            String idDistributore = scanner.nextLine();
                                            Distributore distributore = dd.findById(UUID.fromString(idDistributore).toString());

                                            // Selezione tratta
                                            System.out.println("---------- Tratte disponibili ----------");
                                            td.findAll();
                                            System.out.print("Inserisci l'ID della tratta: ");
                                            String idTratta = scanner.nextLine();
                                            Tratta trattaSelezionata = td.findById(UUID.fromString(idTratta).toString());

                                            // Selezione mezzo associato alla tratta
                                            System.out.println("---------- Mezzi disponibili per la tratta selezionata ----------");
                                            List<Mezzo> mezziPerTratta = md.findByTratta(trattaSelezionata);
                                            for (Mezzo m : mezziPerTratta) {
                                                System.out.println(m);
                                            }

                                            System.out.print("Inserisci l'ID del mezzo: ");
                                            String idMezzo = scanner.nextLine();
                                            Mezzo mezzo = md.findById(idMezzo);

                                            // Creazione biglietto
                                            Biglietto biglietto = new Biglietto(LocalDate.now(), false, null, mezzo, distributore.getId());
                                            biglietto.setIdDistributore(distributore.getId());
                                            bd.save(biglietto);

                                            System.out.println(" Biglietto creato con successo: " + biglietto);

                                        } catch (Exception e) {
                                            System.out.println(" Errore nella creazione del biglietto: " + e.getMessage());
                                        }
                                        break;


                                    case 2:
                                try {
                                    //Chiedo il tipo di abbonamento (settimanale o mensile)
                                    System.out.println("Abbonamento settimanale o mensile? (s/m)");
                                    String tipoAbbonamento = scanner.nextLine();
                                    boolean abbonamentoScelto; // True se settimanale, False se mensile

                                    //Controllo la scelta dell'utente
                                    if( tipoAbbonamento.equalsIgnoreCase("s")) {
                                        abbonamentoScelto = true;
                                        System.out.println("Abbonamento settimanale selezionato.");
                                    } else if (tipoAbbonamento.equalsIgnoreCase("m")) {
                                        abbonamentoScelto = false;
                                        System.out.println("Abbonamento mensile selezionato.");
                                    } else {
                                        System.out.println("Scelta non valida. Riprova.");
                                        break;
                                    }

                                    //Chiedo l'ID del distributore
                                    dd.findAll();
                                    System.out.println("Seleziona l'ID del distributore:");
                                    String idDistributoreAbb = scanner.nextLine();

                                    //Trovo il distributore

                                    Distributore distributoreAbb = dd.findById(String.valueOf(UUID.fromString(idDistributoreAbb)));

                                    //Controllo se l'utente ha una tessera
                                    Tessera tessera = tessd.findByUtenteId(utente.getId().toString());
                                    if (tessera == null) {
                                        System.out.println("Nessuna tessera trovata per questo utente. Vuoi crearne una? (s/n)");
                                        String creaTessera = scanner.nextLine();
                                        if (creaTessera.equalsIgnoreCase("s")) {
                                            tessera = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1), utente);
                                            tessd.save(tessera);
                                            System.out.println("Tessera creata con successo: " + tessera);
                                        } else {
                                            System.out.println("Operazione annullata.");
                                            break;
                                        }
                                    }

                                    //Emetto l'abbonamento
                                    dd.emettiAbbonamento(distributoreAbb, tessera, abbonamentoScelto);
                                    System.out.println("Abbonamento creato con successo.");
                                } catch (Exception e) {
                                    System.out.println("Errore nella creazione dell'abbonamento: " + e.getMessage());
                                }
                                break;

                            case 3:
                                try {
                                    System.out.println("---------- Tratte disponibili ----------");
                                    td.findAll(); // Mostra tutte le tratte
                                } catch (Exception e) {
                                    System.out.println("Errore nella visualizzazione delle tratte: " + e.getMessage());
                                }
                                break;

                                case 0:
                                    cycleUtente = false;
                                    System.out.println("Uscita dal programma.");
                                    break;

                            default:
                                System.out.println("Scelta non valida.");
                        }}
                    } catch (Exception m) {
                        System.out.println("Errore nel menù utente: " + m.getMessage());
                    }

                } else {
                    System.out.println("Ruolo non riconosciuto.");
                }
            } else {
                System.out.println("Credenziali non valide.");
            }
        } catch (Exception e) {
            System.out.println("Errore generale nell'applicazione: " + e.getMessage());
        } finally {

        }

        //UTENTI
//
//        Utente utente2 = new Utente("Maria", "Rossi", "maria.rossi@amministratore.com", "abcd", LocalDate.now().minusYears(25));
//        Utente utente3 = new Utente("Giovanni", "Bianchi", "giovanni.bianchi@utente.com", "password", LocalDate.now().minusYears(34));
//        Utente utente4 = new Utente("Laura", "Verdi", "laura.verdi@utente.com", "qwerty", LocalDate.now().minusYears(22));
//        Utente utente5 = new Utente("Marco", "Neri", "marco.neri@utente.com", "9876", LocalDate.now().minusYears(30));
//        Utente utente6 = new Utente("Federica", "Gialli", "federica.gialli@utente.com", "12345", LocalDate.now().minusYears(28));
//        Utente utente7 = new Utente("Luca", "Blu", "luca.blu@utente.com", "password1", LocalDate.now().minusYears(35));
//        Utente utente8 = new Utente("Elena", "Perni", "elena.perni@utente.com", "elena123", LocalDate.now().minusYears(31));
//        Utente utente9 = new Utente("Francesco", "Marroni", "francesco.marroni@amministratore.com", "francesco99", LocalDate.now().minusYears(26));
//        Utente utente10 = new Utente("Giulia", "Rossi", "giulia.rossi@utente.com", "giulia2023", LocalDate.now().minusYears(24));
//
//
//        Utente utente2fromdb = ud.findById("2c4866e4-345f-4ebe-9537-e0b149f81812");
//        Utente utente3fromdb = ud.findById("5161b5d2-1068-4433-bc02-a8ee9f11e446");
//        Utente utente4fromdb = ud.findById("a0b14ebc-7b03-477e-9df0-dba76f1c4452");
//        Utente utente5fromdb = ud.findById("bb6b2c71-7a7b-4de3-9acf-31cd63b55a7b");
//        Utente utente6fromdb = ud.findById("c64a7186-0bd6-4147-83a6-80fc4e2dabea");
//        Utente utente7fromdb = ud.findById("cefc0835-176b-4841-835c-4215ea15a989");
//        Utente utente8fromdb = ud.findById("d3a25ba8-746a-49b5-8a1d-6cee4f864863");



//        ud.save(utente1);
       // ud.save(utente2);
       // ud.save(utente3);
//        ud.save(utente4);
//        ud.save(utente5);
//        ud.save(utente6);
//        ud.save(utente7);
//        ud.save(utente8);
//        ud.save(utente9);
//        ud.save(utente10);


        //TRATTE
//        Tratta tratta1= new Tratta("Roma",120,"Frosinone",null);
//        Tratta tratta2 = new Tratta("Frosinone",120, "Afragola",null);
//        Tratta tratta3 = new Tratta("Viterbo",60, "Rieti",null);
//        Tratta tratta4 = new Tratta("Civitavecchia", 50,"Ostia",null);
//        Tratta tratta5 = new Tratta("Ciampino",40, "Frascati",null);
//        Tratta tratta6 = new Tratta("Albano Laziale",70, "Pomezia",null);
//        Tratta tratta7 = new Tratta("Cassi",80, "Anzio",null);
//        Tratta tratta8 = new Tratta("Velletri",120, "Ariccia",null);
//        Tratta tratta9 = new Tratta("Tivoli",20, "Guidonia",null);
//        Tratta tratta10 = new Tratta("Sora",120, "Monteporzio Catone",null);
//
//        Tratta tratta1fromdb= td.findById("02ec6228-1932-41e6-a143-e9a41d619492");
//        Tratta tratta2fromdb= td.findById("162d104f-00b8-4255-9fb1-ca8fb8995d51");
//        Tratta tratta3fromdb= td.findById("1c75d1cb-ffc4-43db-9c6f-8c1186968ccb");
//        Tratta tratta4fromdb= td.findById("218908d0-8960-4a58-b341-487e3240a92e");
//        Tratta tratta5fromdb= td.findById("4dcb9834-811a-4e4c-abea-b594eab7e5b7");
//        Tratta tratta6fromdb= td.findById("98e6364a-879a-4ef7-84eb-b6069021cf84");
//
//        td.save(tratta1);
//        td.save(tratta2);
//        td.save(tratta3);
//        td.save(tratta4);
//        td.save(tratta5);
//        td.save(tratta6);
//        td.save(tratta7);
//        td.save(tratta8);
//        td.save(tratta9);
//        td.save(tratta10);


        //TESSERE
//        Tessera tessera1= new Tessera(LocalDate.now(),LocalDate.now().plusYears(1),utente2fromdb);
//        tessd.save(tessera1);
//        Tessera tessera2= new Tessera(LocalDate.now(),LocalDate.now().plusYears(1),utente3fromdb);
//        tessd.save(tessera2);
//        Tessera tessera3= new Tessera(LocalDate.now(),LocalDate.now().plusYears(1),utente4fromdb);
//        tessd.save(tessera3);
//        Tessera tessera4= new Tessera(LocalDate.now(),LocalDate.now().plusYears(1),utente5fromdb);
//        tessd.save(tessera4);
//        Tessera tessera5= new Tessera(LocalDate.now(),LocalDate.now().plusYears(1),utente6fromdb);
//        tessd.save(tessera5);
//        Tessera tessera6= new Tessera(LocalDate.now(),LocalDate.now().plusYears(1),utente7fromdb);
//        tessd.save(tessera6);
//        Tessera tessera7= new Tessera(LocalDate.now(),LocalDate.now().plusYears(1),utente8fromdb);
//        tessd.save(tessera7);





        //MEZZI
//        Mezzo mezzo1 = new Mezzo(TipoMezzo.TRAM,120, LocalTime.of(10, 30),tratta1fromdb);
//        Mezzo mezzo2 = new Mezzo(TipoMezzo.AUTOBUS, 60,LocalTime.of(11,25),tratta1fromdb);
//        Mezzo mezzo3 = new Mezzo(TipoMezzo.AUTOBUS, 70,LocalTime.of(23,13),tratta4fromdb);
//        Mezzo mezzo4 = new Mezzo(TipoMezzo.AUTOBUS, 80,LocalTime.of(12,30),tratta3fromdb);
//        Mezzo mezzo5 = new Mezzo(TipoMezzo.AUTOBUS, 90,LocalTime.of(9,30),tratta5fromdb);
//        Mezzo mezzo6 = new Mezzo(TipoMezzo.TRAM, 100,LocalTime.of(9,00),tratta5fromdb);
//        Mezzo mezzo7 = new Mezzo(TipoMezzo.TRAM, 120,LocalTime.of(9,30),tratta6fromdb);
//        Mezzo mezzo8 = new Mezzo(TipoMezzo.TRAM, 150,LocalTime.of(11,00),tratta6fromdb);
//        Mezzo mezzo9 = new Mezzo(TipoMezzo.TRAM, 180,LocalTime.of(14,25),tratta2fromdb);
//        Mezzo mezzo10 = new Mezzo(TipoMezzo.TRAM, 200,LocalTime.of(20,25),tratta4fromdb);

//        Mezzo mezzo1fromdb = md.findById("1cd64cfa-334b-4aea-9ff4-9f777ae704bb");
//        Mezzo mezzo2fromdb = md.findById("225d317a-e961-41bb-8d72-48d992efdf57");
//        Mezzo mezzo3fromdb = md.findById("2716c9ba-3ad1-4a00-8792-924fe6bd9719");
//        Mezzo mezzo4fromdb = md.findById("57883f92-3d68-453c-9a05-fc83036c366a");
//        Mezzo mezzo5fromdb = md.findById("60958dde-8e5e-4605-bfc1-905baea3e5ec");
//        Mezzo mezzo6fromdb = md.findById("707a064f-da28-4ba2-87bc-3ac42b652401");

//        md.save(mezzo1);
//        md.save(mezzo2);
//        md.save(mezzo3);
//        md.save(mezzo4);
//        md.save(mezzo5);
//        md.save(mezzo6);
//        md.save(mezzo7);
//        md.save(mezzo8);
//        md.save(mezzo9);
//        md.save(mezzo10);

        //DISTRIBUTORI
//        DistributoreAutomatico distributore2= new DistributoreAutomatico("Frosinone", StatoDistributoreAutomatico.INSERVIZIO);
//        dd.save(distributore2);
//        DistributoreAutomatico distributore3= new DistributoreAutomatico("Afragola", StatoDistributoreAutomatico.INSERVIZIO);
//        dd.save(distributore3);
//        DistributoreAutomatico distributore4= new DistributoreAutomatico("Latina", StatoDistributoreAutomatico.INSERVIZIO);
//        dd.save(distributore4);
//        DistributoreAutomatico distributore5= new DistributoreAutomatico("Monte Porzio Catone", StatoDistributoreAutomatico.INSERVIZIO);
//        dd.save(distributore5);
//        DistributoreAutomatico distributore6= new DistributoreAutomatico("Ciampino", StatoDistributoreAutomatico.FUORISERVIZIO);
//        dd.save(distributore6);
//        RivenditoreAutorizzato distributore7= new RivenditoreAutorizzato("Fiumicino", TipoRivenditoreAutorizzato.EDICOLA);
//        dd.save(distributore7);
//        RivenditoreAutorizzato distributore8= new RivenditoreAutorizzato("Ciampino", TipoRivenditoreAutorizzato.TABACCHINO);
//        dd.save(distributore8);

        //BIGLIETTI
//        Biglietto biglietto1=new Biglietto(LocalDate.now(),false,LocalDate.now().plusDays(1),mezzo1fromdb);
//        bd.save(biglietto1);
//        Biglietto biglietto1fromdb= bd.findById("1608a88d-7947-41c5-a624-7032f3a3c294");

        //ABBONAMENTI
//        Abbonamento abbonamento1=new Abbonamento(LocalDate.now(),LocalDate.now().plusYears(1),tessera1fromdb);
//        Abbonamento abbonamento2 = dd.emettiAbbonamento(tessera1fromdb, false);
//        Abbonamento abbonamento3 = dd.emettiAbbonamento(tessera1fromdb, true);

//        ad.save(abbonamento1);

        //MANUTENZIONI
//        Manutenzione manutenzione1=new Manutenzione(LocalDate.now(),LocalDate.now().minusDays(30),mezzo1fromdb);
//        Manutenzione manutenzione6 = new Manutenzione(LocalDate.now(), LocalDate.now().minusDays(30), mezzo1fromdb);
//        Manutenzione manutenzione2 = new Manutenzione(LocalDate.now().minusDays(5), LocalDate.now().minusDays(35), mezzo2fromdb);
//        Manutenzione manutenzione3 = new Manutenzione(LocalDate.now().minusDays(10), LocalDate.now().minusDays(40), mezzo2fromdb);
//        Manutenzione manutenzione4 = new Manutenzione(LocalDate.now().minusDays(15), LocalDate.now().minusDays(45), mezzo4fromdb);
//        Manutenzione manutenzione5 = new Manutenzione(LocalDate.now().minusDays(20), LocalDate.now().minusDays(50), mezzo5fromdb);

//        mand.save(manutenzione1);
//        mand.save(manutenzione2);
//        mand.save(manutenzione3);
//        mand.save(manutenzione4);
//        mand.save(manutenzione5);
//        mand.save(manutenzione6);

        //PERCORRENZE
//        Percorrenza percorrenza1= new Percorrenza(110,mezzo1fromdb,tratta1fromdb);
//        pd.save(percorrenza1);
//        Percorrenza percorrenza2= new Percorrenza(100,mezzo2fromdb,tratta1fromdb);
//        pd.save(percorrenza1);
//        Percorrenza percorrenza3= new Percorrenza(180,mezzo3fromdb,tratta4fromdb);
//        pd.save(percorrenza1);
//        Percorrenza percorrenza4= new Percorrenza(90,mezzo4fromdb,tratta3fromdb);
//        pd.save(percorrenza1);
//        Percorrenza percorrenza5= new Percorrenza(30,mezzo5fromdb,tratta5fromdb);
//        pd.save(percorrenza1);
//        Percorrenza percorrenza6= new Percorrenza(145,mezzo6fromdb,tratta5fromdb);
//        pd.save(percorrenza1);

        scanner.close();
        emf.close();
        em.close();
    }

}
