package it.corso.accenture.view;

import java.io.Closeable;
import java.util.List;
import java.util.function.Function;

import it.corso.accenture.DAO.AssicurazioniDAO;
import it.corso.accenture.DAO.AutoCoinvolteDAO;
import it.corso.accenture.DAO.AutoDAO;
import it.corso.accenture.DAO.DAO;
import it.corso.accenture.DAO.ProprietariDAO;
import it.corso.accenture.DAO.SinistriDAO;
import it.corso.accenture.entities.Query;
import it.corso.accenture.utils.UIOpzione;
import it.corso.accenture.utils.UserInterface;

public class Menu implements Closeable {

	UserInterface ui;

	public Menu() {
		ui = new UserInterface();
	}

	public void mostra() {

		for (boolean esci = false; !esci;) {

			String messaggioMenu = "--Effettua operazioni sul database di assicurazioni--\n";

			UIOpzione[] opzioni = new UIOpzione[] {
					new UIOpzione("1)Visualizza liste entità,", () -> scegliListaEntita()),
					new UIOpzione("2)Visualizza query,", () -> scegliQuery()),
					new UIOpzione("3)Esci", () -> esci())
			};

			int scelta = ui.sceltaOpzioni(messaggioMenu, opzioni);
			opzioni[scelta - 1].esegui();

			esci = scelta == opzioni.length;

			ui.aspettaUtente();
		}
	}

	public void scegliListaEntita() {

		System.out.println();

		String messaggio = "Scegli la lista da visualizzare:\n";

		UIOpzione[] opzioni = new UIOpzione[] {
				new UIOpzione("1)Proprietari.", () -> visualizzaProprietari()),
				new UIOpzione("2)Auto.", () -> visualizzaAuto()),
				new UIOpzione("3)Assicurazioni.", () -> visualizzaAssicurazioni()),
				new UIOpzione("4)Sinistri", () -> visualizzaSinistri()),
				new UIOpzione("5)AutoCoinvolte.", () -> visualizzaAutoCoinvolte())
		};

		int scelta = ui.sceltaOpzioni(messaggio, opzioni);
		opzioni[scelta - 1].esegui();

	}

	public void visualizzaProprietari() {
		visualizzaListeEntita(
				"Proprietari memorizzati nel database:",
				"Non sono ancora stati inseriti proprietari nel database.",
				new ProprietariDAO());
	}

	public void visualizzaAuto() {
		visualizzaListeEntita(
				"Auto memorizzate nel database:",
				"Non sono ancora state inserite auto nel database.",
				new AutoDAO());
	}

	public void visualizzaAssicurazioni() {
		visualizzaListeEntita(
				"Assicurazioni memorizzate nel database:",
				"Non sono ancora state inserite assicurazioni nel database.",
				new AssicurazioniDAO());
	}

	public void visualizzaSinistri() {
		visualizzaListeEntita(
				"Sinistri memorizzati nel database:",
				"Non sono ancora stati inseriti sinistri nel database.",
				new SinistriDAO());
	}

	public void visualizzaAutoCoinvolte() {
		visualizzaListeEntita(
				"Auto coinvolte memorizzate nel database:",
				"Non sono ancora state inserite auto coinvolte nel database.",
				new AutoCoinvolteDAO());
	}

	public <T> void visualizzaListeEntita(String msg, String msgListaVuota, DAO<T> dao) {
		System.out.println();

		List<T> lista = dao.findAll();
		if (lista.size() > 0) {
			System.out.println(msg);
			lista.forEach(o -> System.out.println(o));
		} else
			System.out.println(msgListaVuota);

		System.out.println();
	}

	public void scegliQuery() {

		System.out.println();

		String messaggio = "Scegli la query da eseguire e visualizzare:\n";

		UIOpzione[] opzioni = new UIOpzione[] {
				new UIOpzione(
						"1)Targa e Marca delle Auto di cilindrata superiore a 2000 cc o di potenza superiore a 120 CV.",
						() -> visualizzaQuery1()),
				new UIOpzione(
						"2)Nome del proprietario e Targa delle Auto di cilindrata superiore a 2000 cc oppure di potenza "
								+ "superiore a 120 CV.",
						() -> visualizzaQuery2()),
				new UIOpzione(
						"3)Targa e Nome del proprietario delle Auto di cilindrata superiore a 2000 cc oppure di potenza "
								+ "superiore a 120 CV, assicurate presso la “SARA”.",
						() -> visualizzaQuery3()),
				new UIOpzione(
						"4)Targa e Nome del proprietario delle Auto assicurate presso la “SARA” e coinvolte in sinistri il "
								+ "20/01/12.",
						() -> visualizzaQuery4()),
				new UIOpzione(
						"5)Per ciascuna Assicurazione, il nome, la sede ed il numero di auto assicurate.",
						() -> visualizzaQuery5()),
				new UIOpzione(
						"6)Per ciascuna auto “Fiat”, la targa dell’auto ed il numero di sinistri in cui è stata coinvolta.",
						() -> visualizzaQuery6()),
				new UIOpzione(
						"7)Per ciascuna auto coinvolta in più di un sinistro, la targa dell’auto, il nome dell’ Assicurazione "
								+ "ed il totale dei danni riportati.",
						() -> visualizzaQuery7()),
				new UIOpzione(
						"8)CodF e Nome di coloro che possiedono più di un’auto.",
						() -> visualizzaQuery8()),
				new UIOpzione(
						"9)La targa delle auto che non sono state coinvolte in sinistri dopo il 20/01/10.",
						() -> visualizzaQuery9()),
				new UIOpzione(
						"10)Il codice dei sinistri in cui non sono state coinvolte auto con cilindrata inferiore a 2000 cc.",
						() -> visualizzaQuery10())
		};

		int scelta = ui.sceltaOpzioni(messaggio, opzioni);
		opzioni[scelta - 1].esegui();

	}

	public void visualizzaQuery1() {
		visualizzaQuery(
				"Targa e marca delle auto con cilindrata > 2000cc o potenza > 120CV :",
				Query.query1(),
				a -> "Targa : " + a.getTarga() + ", Marca : " + a.getMarca());
	}

	public void visualizzaQuery2() {
		visualizzaQuery(
				"Nome proprietario e marca delle auto con cilindrata > 2000cc o potenza > 120CV :",
				Query.query2(),
				gr -> "Proprietario : " + gr.getOggetto1().getNome() + ", Targa auto : " + gr.getOggetto2().getTarga());
	}

	public void visualizzaQuery3() {
		visualizzaQuery(
				"Marca auto e nome proprietario di auto con cilindrata > 2000cc o potenza > 120CV con assicurazione \"SARA\" :",
				Query.query3(),
				gr -> "Targa auto : " + gr.getOggetto1().getTarga() + ", Proprietario : " + gr.getOggetto2().getNome());
	}

	public void visualizzaQuery4() {
		visualizzaQuery(
				"Marca auto e nome proprietario di auto con assicurazione \"SARA\" e coinvolte in sinistri il 20/01/12 :",
				Query.query4(),
				gr -> "Targa auto : " + gr.getOggetto1().getTarga() + ", Proprietario : " + gr.getOggetto2().getNome());
	}

	public void visualizzaQuery5() {
		visualizzaQuery(
				"Nome e sede delle assicurazioni con il numero di auto assicurate :",
				Query.query5(),
				gr -> "Assicurazione : " + gr.getOggetto1().getNome() + ", Sede : " + gr.getOggetto1().getSede()
						+ ", numero di auto assicurate : " + gr.getOggetto2());
	}

	public void visualizzaQuery6() {
		visualizzaQuery(
				"Targa e numero di sinistri per le auto Fiat :",
				Query.query6(),
				gr -> "Targa auto : " + gr.getOggetto1().getTarga() + ", Numero sinistri : " + gr.getOggetto2());
	}

	public void visualizzaQuery7() {
		visualizzaQuery(
				"Targa, nome assicurazione e totale danni per auto coinvolte in più sinistri :",
				Query.query7(),
				gr -> "Targa auto : " + gr.getOggetto1().getTarga() + ", Assicurazione : " + gr.getOggetto2().getNome()
						+ ", Totale danni : " + gr.getOggetto3());
	}

	public void visualizzaQuery8() {
		visualizzaQuery(
				"CodF e Nome di coloro che possiedono più di un’auto :",
				Query.query8(),
				gr -> "Codice fiscale : " + gr.getOggetto1().getCodF() + ", Nome : " + gr.getOggetto1().getNome()
						+ ", Numero auto : " + gr.getOggetto2());
	}

	public void visualizzaQuery9() {
		visualizzaQuery(
				"La targa delle auto che non sono state coinvolte in sinistri dopo il 20/01/10 :",
				Query.query9(),
				a -> a.getTarga());
	}
	
	public void visualizzaQuery10() {
		visualizzaQuery(
				"Il codice dei sinistri in cui non sono state coinvolte auto con cilindrata inferiore a 2000 cc :",
				Query.query10(),
				s -> s.getCodS());
	}

	public <T> void visualizzaQuery(String msg, List<T> lista, Function<T, String> cons) {
		System.out.println();
		System.out.println(msg);
		lista.forEach(t -> System.out.println(cons.apply(t)));
		System.out.println();
	}

	public void esci() {
		System.out.println("\nFine Programma\n");
	}

	@Override
	public void close() {
		ui.close();
	}

}