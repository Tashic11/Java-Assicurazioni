package it.corso.accenture.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import it.corso.accenture.DAO.AssicurazioniDAO;
import it.corso.accenture.DAO.AutoCoinvolteDAO;
import it.corso.accenture.DAO.AutoDAO;
import it.corso.accenture.DAO.ProprietariDAO;
import it.corso.accenture.DAO.SinistriDAO;
import it.corso.accenture.entities.Assicurazione;
import it.corso.accenture.entities.Auto;
import it.corso.accenture.entities.AutoCoinvolte;
import it.corso.accenture.entities.Proprietario;
import it.corso.accenture.entities.Sinistro;

public abstract class InserisciDatiRandom {

	private static Random r = new Random();

	public static void inserisci() {

		// inserisci proprietari

		List<String> codiciFiscali = new ArrayList<String>();

		for (int i = 0; i < 5; i++) {

			Proprietario p = new Proprietario();
			String nome = nomeRandom();
			String cognome = cognomeRandom();
			p.setNome(nome + " " + cognome);
			p.setCodF(codiceFiscaleRandom(nome, cognome));
			p.setResidenza(localitaRandom());

			codiciFiscali.add(p.getCodF());

			System.out.println(p);
			
			ProprietariDAO dao = new ProprietariDAO();
			dao.insert(p);
		}

		// inserisci assicurazioni

		System.out.println();

		String[] assicurazioni = new String[] {
				"SARA",
				"ASSIC. CA.",
				"ATA assicurazioni",
				"CarSafe" };

		List<String> codiciAssic = new ArrayList<String>();

		for (int i = 0; i < assicurazioni.length; i++) {

			Assicurazione a = new Assicurazione();
			a.setCodAss(codiceRandom(6));
			a.setNome(assicurazioni[i]);
			a.setSede(localitaRandom());

			codiciAssic.add(a.getCodAss());

			System.out.println(a);
			
			AssicurazioniDAO dao = new AssicurazioniDAO();
			dao.insert(a);
		}

		System.out.println();

		List<String> targhe = new ArrayList<String>();

		// inserisci auto
		for (int i = 0; i < 5; i++) {

			Auto a = new Auto();
			a.setTarga(codiceRandom(6));
			a.setMarca(marcaRandom());
			a.setCilindrata(r.nextInt(2000) + 1000);
			a.setPotenza(r.nextInt(200) + 100);
			a.setCodF(codiciFiscali.get(r.nextInt(codiciFiscali.size())));
			a.setCodAss(codiciAssic.get(r.nextInt(codiciAssic.size())));

			targhe.add(a.getTarga());

			System.out.println(a);
			
			AutoDAO dao = new AutoDAO();
			dao.insert(a);
		}

		System.out.println();

		List<String> codiciSinistri = new ArrayList<String>();

		// inserisci sinistri
		for (int i = 0; i < 5; i++) {

			Sinistro s = new Sinistro();
			s.setCodS(codiceRandom(6));
			s.setLocalita(localitaRandom());
			s.setData(dataRandom(2000, 2021));

			codiciSinistri.add(s.getCodS());

			System.out.println(s);
			
			SinistriDAO dao = new SinistriDAO();
			dao.insert(s);
		}

		System.out.println();

		// inserisci autoCoinvolte
		for (int i = 0; i < 5; i++) {

			AutoCoinvolte ac = new AutoCoinvolte();
			ac.setCodS(codiciSinistri.get(r.nextInt(codiciSinistri.size())));
			ac.setTarga(targhe.get(r.nextInt(targhe.size())));
			ac.setImportoDelDanno(r.nextInt(10000)+100);

			System.out.println(ac);
			
			AutoCoinvolteDAO dao = new AutoCoinvolteDAO();
			dao.insert(ac);
		}
	}

	private static String codiceFiscaleRandom(String nome, String cognome) {
		String cf = cognome.substring(0, 3).toUpperCase() +
				nome.substring(0, 3).toUpperCase() +
				(r.nextInt(20) + 10) +
				(letteraRandom()) +
				(r.nextInt(90) + 10);

		while (cf.length() < 16)
			cf += letteraRandom();
		return cf;
	}

	private static char letteraRandom() {
		int charA = 'A';
		int charZ = 'Z';
		return (char) (r.nextInt(charZ - charA) + charA);
	}

	private static String codiceRandom(int lunghezza) {
		char[] codice = new char[lunghezza];
		for (int i = 0; i < lunghezza; i++) {
			codice[i] = letteraRandom();
		}
		return new String(codice);
	}

	private static String nomeRandom() {
		String[] nomi = new String[] {
				"Marco",
				"Andrea",
				"Gabriele",
				"Angela",
				"Mario",
				"Piero",
				"Valentina",
				"Daniele",
				"Gabriella",
				"Valerio",
				"Erica",
				"Francesco",
				"Giorgio",
				"Gianluigi"
		};

		return nomi[r.nextInt(nomi.length)];
	}

	private static String cognomeRandom() {
		String[] cognomi = new String[] {
				"Mereu",
				"Melis",
				"Pintus",
				"Rossi",
				"Picciau",
				"Bianchi",
				"Zanda",
				"Martinelli",
				"Scalas",
				"Berlusconi",
				"Fieri",
				"Smusi"
		};

		return cognomi[r.nextInt(cognomi.length)];
	}

	private static String marcaRandom() {
		String[] nomi = new String[] {
				"Fiat",
				"Alfa Romeo",
				"Toyota",
				"BMW",
				"Subaru",
				"Smart",
				"Mitsubishi",
				"Chevrolet"
		};

		return nomi[r.nextInt(nomi.length)];
	}

	private static String localitaRandom() {
		return "Via " + viaRandom() + " n." + r.nextInt(100) + ", " + cittaRandom();
	}

	private static String cittaRandom() {
		String[] citta = new String[] {
				"Assemini",
				"Cagliari",
				"Elmas",
				"Sassari",
				"Roma",
				"Milano",
				"Napoli",
				"Bibbiena",
				"Olbia"
		};
		return citta[r.nextInt(citta.length)];
	}

	private static String viaRandom() {
		String[] vie = new String[] {
				"Porto Torres",
				"Piave",
				"Roma",
				"San Pietro",
				"Brombeis",
				"Fiume",
				"Carmine",
				"Bologna",
				"Della Marina"
		};
		return vie[r.nextInt(vie.length)];
	}

	private static Calendar dataRandom(int annoMin, int annoMax) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(Calendar.DAY_OF_MONTH, r.nextInt(28) + 1);
		c.set(Calendar.MONTH, r.nextInt(12));
		c.set(Calendar.YEAR, r.nextInt(annoMax - annoMin) + annoMin);
		return c;
	}

}
