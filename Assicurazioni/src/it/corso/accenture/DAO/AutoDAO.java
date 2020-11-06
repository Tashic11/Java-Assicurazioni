package it.corso.accenture.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.corso.accenture.entities.Auto;

public class AutoDAO implements DAO<Auto> {

	@Override
	public Auto ricavaDaResultSetRecord(ResultSet rs) {
		try {
			return new Auto(
					rs.getString("Targa"),
					rs.getString("Marca"),
					rs.getInt("Cilindrata"),
					rs.getInt("Potenza"),
					rs.getString("CodF"),
					rs.getString("CodAss"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Auto find(String targa) {
		return ricavaDaDB("select * from AUTO where targa=?;", new Object[] { targa });
	}

	@Override
	public List<Auto> findAll() {
		return ricavaListaDaDB("select * from AUTO;", null);
	}

	@Override
	public int insert(Auto t) {
		return eseguiDML("insert into AUTO (Targa,Marca,Cilindrata,Potenza,CodF,CodAss) values (?,?,?,?,?,?);",
				new Object[] { t.getTarga(), t.getMarca(), t.getCilindrata(), t.getPotenza(), t.getCodF(),
						t.getCodAss() });
	}

	@Override
	public int update(Auto t) {
		return eseguiDML(
				"update AUTO set Marca=?, set Cilindrata=?, set Potenza=?, set CodF=?, set CodAss=? where Targa=?;",
				new Object[] { t.getMarca(), t.getCilindrata(), t.getPotenza(), t.getCodF(), t.getCodAss(),
						t.getTarga() });
	}

	@Override
	public int delete(String targa) {
		return eseguiDML("delete from AUTO where Targa=?;",
				new Object[] { targa });
	}

	@Override
	public List<Auto> select(String sql) {
		return ricavaListaDaDB(sql, null);
	}

	@Override
	public List<Auto> select(String sql, Object[] valori) {
		return ricavaListaDaDB(sql, valori);
	}

}
