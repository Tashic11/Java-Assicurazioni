package it.corso.accenture.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.corso.accenture.entities.Proprietario;

public class ProprietariDAO implements DAO<Proprietario> {

	@Override
	public Proprietario ricavaDaResultSetRecord(ResultSet rs) {
		try {
			return new Proprietario(
					rs.getString("CodF"),
					rs.getString("Nome"),
					rs.getString("Residenza"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Proprietario find(String codF) {
		return ricavaDaDB("select * from PROPRIETARI where CodF=?;", new Object[] { codF });
	}

	@Override
	public List<Proprietario> findAll() {
		return ricavaListaDaDB("select * from PROPRIETARI;", null);
	}

	@Override
	public int insert(Proprietario t) {
		return eseguiDML("insert into PROPRIETARI (CodF,Nome,Residenza) values (?,?,?);",
				new Object[] { t.getCodF(), t.getNome(), t.getResidenza() });
	}

	@Override
	public int update(Proprietario t) {
		return eseguiDML("update PROPRIETARI set Nome=?, set Residenza=? where CodF=?;",
				new Object[] { t.getNome(), t.getResidenza(), t.getCodF() });
	}

	@Override
	public int delete(String codF) {
		return eseguiDML("delete from PROPRIETARI where CodF=?;",
				new Object[] { codF });
	}

	@Override
	public List<Proprietario> select(String sql) {
		return ricavaListaDaDB(sql, null);
	}

	@Override
	public List<Proprietario> select(String sql, Object[] valori) {
		return ricavaListaDaDB(sql, valori);
	}

}
