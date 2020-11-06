package it.corso.accenture.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.corso.accenture.entities.Assicurazione;

public class AssicurazioniDAO implements DAO<Assicurazione> {

	@Override
	public Assicurazione ricavaDaResultSetRecord(ResultSet rs) {
		try {
			return new Assicurazione(
					rs.getString("CodAss"),
					rs.getString("Nome"),
					rs.getString("Sede"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Assicurazione find(String codAss) {
		return ricavaDaDB("select * from ASSICURAZIONI where CodAss=?;", new Object[] { codAss });
	}

	@Override
	public List<Assicurazione> findAll() {
		return ricavaListaDaDB("select * from ASSICURAZIONI;", null);
	}

	@Override
	public int insert(Assicurazione t) {
		return eseguiDML("insert into ASSICURAZIONI (CodAss,Nome,Sede) values (?,?,?);",
				new Object[] { t.getCodAss(), t.getNome(), t.getSede() });
	}

	@Override
	public int update(Assicurazione t) {
		return eseguiDML("update ASSICURAZIONI set Nome=?, set Sede=? where CodAss=?;",
				new Object[] { t.getNome(), t.getSede(), t.getCodAss() });
	}

	@Override
	public int delete(String codAss) {
		return eseguiDML("delete from ASSICURAZIONI where CodAss=?;",
				new Object[] { codAss });
	}

	@Override
	public List<Assicurazione> select(String sql) {
		return ricavaListaDaDB(sql, null);
	}

	@Override
	public List<Assicurazione> select(String sql, Object[] valori) {
		return ricavaListaDaDB(sql, valori);
	}

}
