package it.corso.accenture.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import it.corso.accenture.DAO.AssicurazioniDAO;
import it.corso.accenture.DAO.AutoDAO;
import it.corso.accenture.DAO.ProprietariDAO;
import it.corso.accenture.DAO.SinistriDAO;
import it.corso.accenture.utils.DBConnection;
import it.corso.accenture.utils.Gruppo2;
import it.corso.accenture.utils.Gruppo3;

public abstract class Query {

	/*
	 * 1. Targa e Marca delle Auto di cilindrata superiore a 2000 cc o di potenza
	 * superiore a 120 CV
	 */
	public static List<Auto> query1() {
		return new AutoDAO().select(
				"select * from AUTO where cilindrata>2000 or Potenza>120");
	}

	/*
	 * 2. Nome del proprietario e Targa delle Auto di cilindrata superiore a 2000 cc
	 * oppure di potenza superiore a 120 CV
	 */
	public static List<Gruppo2<Proprietario, Auto>> query2() {

		String sql = "" +
				"select pr.*,au.* " +
				"from " +
				"	PROPRIETARI pr " +
				"    join AUTO au using(codF) " +
				"where au.cilindrata>2000 or au.Potenza>120;";

		List<Gruppo2<Proprietario, Auto>> lista;

		lista = listaGruppo2(sql,
				rs -> new ProprietariDAO().ricavaDaResultSetRecord(rs),
				rs -> new AutoDAO().ricavaDaResultSetRecord(rs));

		return lista;
	}

	/*
	 * 3. Targa e Nome del proprietario delle Auto di cilindrata superiore a 2000 cc
	 * oppure di potenza superiore a 120 CV, assicurate presso la “SARA”
	 */
	public static List<Gruppo2<Auto, Proprietario>> query3() {

		String sql = "select aut.*,pro.* " +
				"from " +
				"	PROPRIETARI pro " +
				"    join AUTO aut using(codF)" +
				"    join ASSICURAZIONI ass using(codAss)" +
				"where " +
				"	(aut.cilindrata>2000 or aut.Potenza>120)" +
				"	and ass.nome = \"SARA\";";

		List<Gruppo2<Auto, Proprietario>> lista;

		lista = listaGruppo2(sql,
				rs -> new AutoDAO().ricavaDaResultSetRecord(rs),
				rs -> new ProprietariDAO().ricavaDaResultSetRecord(rs));

		return lista;
	}

	/*
	 * 4. Targa e Nome del proprietario delle Auto assicurate presso la “SARA” e
	 * coinvolte in sinistri il 20/01/12
	 */
	public static List<Gruppo2<Auto, Proprietario>> query4() {

		String sql = "select aut.*,pro.* " +
				"from " +
				"	PROPRIETARI pro " +
				"    join AUTO aut using(codF) " +
				"    join ASSICURAZIONI ass using(codAss) " +
				"    join AUTOCOINVOLTE aco using(targa) " +
				"    join SINISTRO sin using(codS) " +
				"where  " +
				"	ass.nome = \"SARA\" " +
				"    and sin.data = \"2012-01-20\";";

		List<Gruppo2<Auto, Proprietario>> lista;

		lista = listaGruppo2(sql,
				rs -> new AutoDAO().ricavaDaResultSetRecord(rs),
				rs -> new ProprietariDAO().ricavaDaResultSetRecord(rs));

		return lista;
	}

	/*
	 * 5. Per ciascuna Assicurazione, il nome, la sede ed il numero di auto
	 * assicurate
	 */
	public static List<Gruppo2<Assicurazione, Integer>> query5() {

		String sql = "select ass.*,count(aut.targa) as numAuto " +
				"from " +
				"	ASSICURAZIONI ass " +
				"    left outer join AUTO aut using(codAss) " +
				"group by ass.codAss,ass.nome,ass.sede;";

		List<Gruppo2<Assicurazione, Integer>> lista;

		lista = listaGruppo2(sql,
				rs -> new AssicurazioniDAO().ricavaDaResultSetRecord(rs),
				rs -> resultSetGetInt(rs, "numAuto"));

		return lista;
	}

	/*
	 * 6. Per ciascuna auto “Fiat”, la targa dell’auto ed il numero di sinistri in
	 * cui è stata coinvolta
	 */
	public static List<Gruppo2<Auto, Integer>> query6() {

		String sql = "select AUTO.*,targhe.numSinistri " +
				"from AUTO " +
				"join ( " +
				"	select aut.*, count(sin.codS) as numSinistri" +
				"	from  " +
				"		AUTO aut " +
				"		left outer join AUTOCOINVOLTE aco using(targa) " +
				"	    left outer join SINISTRO sin using(codS) " +
				"	group by aut.marca, aut.targa) targhe using(targa)";

		List<Gruppo2<Auto, Integer>> lista;

		lista = listaGruppo2(sql,
				rs -> new AutoDAO().ricavaDaResultSetRecord(rs),
				rs -> resultSetGetInt(rs, "numSinistri"));

		return lista;
	}

	/*
	 * 7. Per ciascuna auto coinvolta in più di un sinistro, la targa dell’auto, il
	 * nome dell’ Assicurazione ed il totale dei danni riportati
	 */
	public static List<Gruppo3<Auto, Assicurazione, Integer>> query7() {

		String sql = "select aut.*,ass.*,sq.totaleImporto " +
				"from ( " +
				"	select aut.targa,sum(aco.ImportoDelDanno) as totaleImporto " +
				"	from " +
				"		PROPRIETARI pro " +
				"	    join AUTO aut using(codF) " +
				"	    join AUTOCOINVOLTE aco using(targa) " +
				"	    join SINISTRO sin using(codS) " +
				"	group by aut.targa " +
				"	having count(sin.codS)>1) sq " +
				"	join AUTO aut using(Targa) " +
				"	join ASSICURAZIONI ass using(CodAss);";

		List<Gruppo3<Auto, Assicurazione, Integer>> lista;

		lista = listaGruppo3(sql,
				rs -> new AutoDAO().ricavaDaResultSetRecord(rs),
				rs -> new AssicurazioniDAO().ricavaDaResultSetRecord(rs),
				rs -> resultSetGetInt(rs, "totaleImporto"));

		return lista;
	}

	/* 8. CodF e Nome di coloro che possiedono più di un’auto */
	public static List<Gruppo2<Proprietario, Integer>> query8() {

		String sql = "select pro.*,count(aut.targa) as numAuto " +
				"from  " +
				"	PROPRIETARI pro  " +
				"    join AUTO aut using(codF) " +
				"group by pro.codf,pro.nome,pro.residenza " +
				"having count(aut.targa)>1;";

		List<Gruppo2<Proprietario, Integer>> lista;

		lista = listaGruppo2(sql,
				rs -> new ProprietariDAO().ricavaDaResultSetRecord(rs),
				rs -> resultSetGetInt(rs, "numAuto"));

		return lista;
	}

	/*
	 * 9. La targa delle auto che non sono state coinvolte in sinistri dopo il
	 * 20/01/10
	 */
	public static List<Auto> query9() {
		return new AutoDAO().select(
				"select * " +
						"from AUTO " +
						"join ( " +
						"	select aut.targa " +
						"	from " +
						"		SINISTRO sin " +
						"		join AUTOCOINVOLTE aco using(codS) " +
						"		right outer join AUTO aut using(targa) " +
						"	group by aut.targa " +
						"	having max(sin.data)<\"2010-01-20\" or max(sin.data) is null " +
						") targa using(targa);");
	}

	/*
	 * 10. Il codice dei sinistri in cui non sono state coinvolte auto con
	 * cilindrata inferiore a 2000 cc
	 */
	public static List<Sinistro> query10() {
		return new SinistriDAO().select("" +
				"select sin.* " +
				"from " +
				"	SINISTRO sin " +
				"	join AUTOCOINVOLTE aco using(codS) " +
				"	join AUTO aut using(targa) " +
				"group by sin.CodS,sin.Localita,sin.Data " +
				"having min(aut.Cilindrata)>=2000;");
	}

	// ---------Metodi utility query-----------

	//per non dover fare sempre il try e catch dentro la lambda
	private static int resultSetGetInt(ResultSet rs, String colonna) {
		try {
			return rs.getInt(colonna);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	private static <T, N> List<Gruppo2<T, N>> listaGruppo2(String sql, Function<ResultSet, T> creaT,
			Function<ResultSet, N> creaN) {

		List<Gruppo2<T, N>> lista = new ArrayList<Gruppo2<T, N>>();

		try (DBConnection dbConn = new DBConnection()) {
			ResultSet rs = dbConn.queryStatement(sql);

			while (rs.next())
				lista.add(new Gruppo2<T, N>(creaT.apply(rs), creaN.apply(rs)));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	private static <T, N, K> List<Gruppo3<T, N, K>> listaGruppo3(String sql, Function<ResultSet, T> creaT,
			Function<ResultSet, N> creaN, Function<ResultSet, K> creaK) {

		List<Gruppo3<T, N, K>> lista = new ArrayList<Gruppo3<T, N, K>>();

		try (DBConnection dbConn = new DBConnection()) {
			ResultSet rs = dbConn.queryStatement(sql);

			while (rs.next())
				lista.add(new Gruppo3<T, N, K>(creaT.apply(rs), creaN.apply(rs), creaK.apply(rs)));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
}
