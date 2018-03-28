package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.DBRecord;

public abstract class AbstractDAO<T extends DBRecord> {

	public static final String DB_NAME = "students_management";
	public static Long nextID;

	public AbstractDAO() {
		nextID = getNextId();
	}

	public abstract String getTableName();

	public Long getNextId() {
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Long nextId = null;
		
		String query = "SELECT MAX(id) FROM " + getTableName() + ";";
		try {
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next())
				nextId = resultSet.getLong(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return nextId;
	}
	
	public void insert(T t) {

		Connection connection = ConnectionFactory.getConnection();
		
		t.setId(nextID++);
		PreparedStatement insertStatement = createInsertQuery(t, connection);

		try {
			insertStatement.executeUpdate();
			//AbstractDAO.nextID++;
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
		List<T> results = null;

		String query = createSelectQuery("id");

		try {
			findStatement = connection.prepareStatement(query);
			findStatement.setLong(1, id);
			resultSet = findStatement.executeQuery();
			results = createObjects(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		if (results != null) {
			return results.get(0);
		} else {
			return null;
		}
	}
	
	public List<T> findAll() {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		List<T> ts = null;

		String query = createSelectQuery(null);

		try {
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
		sb.append("SELECT * FROM ");
		sb.append(this.getTableName());
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
		sb.append(this.getTableName());
		sb.append(" WHERE " + field + " = ?");
		return sb.toString();
	}
}
