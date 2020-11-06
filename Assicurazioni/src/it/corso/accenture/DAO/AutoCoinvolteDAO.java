package it.corso.accenture.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.corso.accenture.entities.AutoCoinvolte;

public class AutoCoinvolteDAO implements DAO<AutoCoinvolte> {

	@Override
	public AutoCoinvolte ricavaDaResultSetRecord(ResultSet rs){
		try {
			return new AutoCoinvolte(
					rs.getString("CodS"),
					rs.getString("Targa"),
					rs.getInt("ImportoDelDanno"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AutoCoinvolte find(String codS) {
		return ricavaDaDB("select * from AUTOCOINVOLTE where CodS=?;", new Object[] { codS });
	}

	public AutoCoinvolte findTarga(String targa) {
		return ricavaDaDB("select * from AUTOCOINVOLTE where Targa=?;", new Object[] { targa });
	}

	public AutoCoinvolte findCodSTarga(String codS, String targa) {
		return ricavaDaDB("select * from AUTOCOINVOLTE where CodS=? and Targa=?;", new Object[] { codS, targa });
	}

	@Override
	public List<AutoCoinvolte> findAll() {
		return ricavaListaDaDB("select * from AUTOCOINVOLTE;", null);
	}

	@Override
	public int insert(AutoCoinvolte t) {
		return eseguiDML("insert into AUTOCOINVOLTE (CodS,Targa,ImportoDelDanno) values (?,?,?);",
				new Object[] { t.getCodS(), t.getTarga(), t.getImportoDelDanno() });
	}

	@Override
	public int update(AutoCoinvolte t) {
		return eseguiDML(
				"update AUTOCOINVOLTE set Targa=?, set ImportoDelDanno=? where CodS=?;",
				new Object[] { t.getTarga(), t.getImportoDelDanno(), t.getCodS() });
	}

	@Override
	public int delete(String codS) {
		return eseguiDML("delete from AUTOCOINVOLTE where CodS=?;",
				new Object[] { codS });
	}

	public int deleteTarga(String targa) {
		return eseguiDML("delete from AUTOCOINVOLTE where Targa=?;",
				new Object[] { targa });
	}

	public int deleteCodSTarga(String CodS, String targa) {
		return eseguiDML("delete from AUTOCOINVOLTE where CodS=? and Targa=?;",
				new Object[] { CodS, targa });
	}

	@Override
	public List<AutoCoinvolte> select(String sql) {
		return ricavaListaDaDB(sql, null);
	}

	@Override
	public List<AutoCoinvolte> select(String sql, Object[] valori) {
		return ricavaListaDaDB(sql, valori);
	}

}
