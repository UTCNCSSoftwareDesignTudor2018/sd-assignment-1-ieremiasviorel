package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.DBRecord;

public abstract class AbstractDAO<T extends DBRecord> {

	public static final String DB_NAME = "students_management";
	public static Long nextID;
	public final String tableName;

	public AbstractDAO() {
		nextID = Long.valueOf(2);
		tableName = getTableName();
	}

	public abstract String getTableName();

	public void insert(T t) {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = createInsertQuery(t, connection);

		try {
			insertStatement.executeUpdate();
			AbstractDAO.nextID++;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
		}
	}

	protected abstract PreparedStatement createInsertQuery(T t, Connection connection);

	public T find(Long id) {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		T t = null;

		String query = createSelectQuery("id");

		try {
			connection = ConnectionFactory.getConnection();
			findStatement = connection.prepareStatement(query);
			findStatement.setLong(1, id);
			resultSet = findStatement.executeQuery();
			t = createObjects(resultSet).get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
	
	public List<T> retrieveAll() {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		List<T> ts = null;

		String query = createSelectQuery(null);

		try {
			connection = ConnectionFactory.getConnection();
			findStatement = connection.prepareStatement(query);
			resultSet = findStatement.executeQuery();
			ts = createObjects(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return ts;
	}

	protected String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM students_management.");
		sb.append(this.tableName);
		if (field != null) {
			sb.append(" WHERE " + field + " = ?;");
			return sb.toString();
		} else {
			return sb.toString();
		}
	}

	protected abstract List<T> createObjects(ResultSet resultSet);

	public void update(T t) {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = createUpdateQuery(t, connection);

		try {
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(connection);
		}
	}

	protected abstract PreparedStatement createUpdateQuery(T t, Connection connection);

	public void delete(Long id) {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;

		String query = createDeleteQuery("id");

		try {
			connection = ConnectionFactory.getConnection();
			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setLong(1, id);
			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(connection);
		}
	}

	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM " + AbstractDAO.DB_NAME + ".");
		sb.append(this.tableName);
		sb.append(" WHERE " + field + " = ?");
		return sb.toString();
	}
}
