package it.corso.accenture.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.corso.accenture.utils.DBConnection;

public interface DAO<T> {

	T find(String cod);

	List<T> findAll();

	int insert(T t);

	int update(T t);

	int delete(String cod);

	List<T> select(String sql);

	List<T> select(String sql, Object[] valori);

	T ricavaDaResultSetRecord(ResultSet rs);

	// metodi di default
	
	default int eseguiDML(String sql,Object[] valori) {		
		int record = 0;
		DBConnection dbConn = new DBConnection();
		record = dbConn.executePrepStatement(sql, valori);
		dbConn.close();
		return record;
	}

	default T ricavaDaDB(String sql, Object[] valori) {
		T t = null;

		try (DBConnection dbConn = new DBConnection();) {

			ResultSet rs = valori != null ? dbConn.queryPrepStatement(sql, valori) : dbConn.queryStatement(sql);

			if (rs.next()) {
				t = ricavaDaResultSetRecord(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return t;
	}

	default List<T> ricavaListaDaDB(String sql, Object[] valori) {
		List<T> lista = new ArrayList<T>();

		try (DBConnection dbConn = new DBConnection();) {

			ResultSet rs = valori != null ? dbConn.queryPrepStatement(sql, valori) : dbConn.queryStatement(sql);

			while (rs.next()) {
				lista.add(ricavaDaResultSetRecord(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
