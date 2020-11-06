package it.corso.accenture.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import it.corso.accenture.entities.Sinistro;

public class SinistriDAO implements DAO<Sinistro> {

	@Override
	public Sinistro ricavaDaResultSetRecord(ResultSet rs) {
		try {
			GregorianCalendar data = new GregorianCalendar();
			data.setTime(rs.getDate("Data"));
			return new Sinistro(
					rs.getString("CodS"),
					rs.getString("Localita"),
					data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Sinistro find(String codS) {
		return ricavaDaDB("select * from SINISTRO where CodS=?;", new Object[] { codS });
	}

	@Override
	public List<Sinistro> findAll() {
		return ricavaListaDaDB("select * from SINISTRO;", null);
	}

	@Override
	public int insert(Sinistro t) {
		return eseguiDML("insert into SINISTRO (CodS,Localita,Data) values (?,?,?);",
				new Object[] { t.getCodS(), t.getLocalita(), t.getData() });
	}

	@Override
	public int update(Sinistro t) {
		return eseguiDML(
				"update SINISTRO set Localita=?, set Data=? where CodS=?;",
				new Object[] { t.getLocalita(), t.getData(), t.getCodS() });
	}

	@Override
	public int delete(String codS) {
		return eseguiDML("delete from SINISTRO where CodS=?;",
				new Object[] { codS });
	}

	@Override
	public List<Sinistro> select(String sql) {
		return ricavaListaDaDB(sql, null);
	}

	@Override
	public List<Sinistro> select(String sql, Object[] valori) {
		return ricavaListaDaDB(sql, valori);
	}

}
