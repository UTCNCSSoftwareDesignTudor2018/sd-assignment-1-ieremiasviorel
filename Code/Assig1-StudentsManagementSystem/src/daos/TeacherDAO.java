package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Teacher;

public class TeacherDAO extends AbstractDAO<Teacher> implements TeacherDAO_I {

	@Override
	public String getTableName() {
		return Constants.TEACHERS_TABLE_NAME;
	}

	@Override
	protected PreparedStatement createInsertQuery(Teacher teacher, Connection connection) {
		PreparedStatement insertStatement = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + this.getTableName());
		sb.append(" (id, first_name, last_name, username, password, id_number, email) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?);");
		
		String insertQuery = sb.toString();
		
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setLong(1, teacher.getId());
			insertStatement.setString(2, teacher.getFirstName());
			insertStatement.setString(3, teacher.getLastName());
			insertStatement.setString(4, teacher.getUserName());
			insertStatement.setString(5, teacher.getPassword());
			insertStatement.setString(6, teacher.getIdNumber());
			insertStatement.setString(7, teacher.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
		}	
		return insertStatement;
	}
	
	@Override
	public List<Teacher> createObjects(ResultSet resultSet) {

		List<Teacher> teachers = new ArrayList<Teacher>();

		try {
			while (resultSet.next()) {
				Teacher teacher = new Teacher();
	
				teacher.setId(resultSet.getLong(1));
				teacher.setFirstName(resultSet.getString(2));
				teacher.setLastName(resultSet.getString(3));
				teacher.setUserName(resultSet.getString(4));
				teacher.setPassword(resultSet.getString(5));
				teacher.setIdNumber(resultSet.getString(6));
				teacher.setEmail(resultSet.getString(7));
				
				teachers.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachers;
	}
	
	@Override
	protected PreparedStatement createUpdateQuery(Teacher teacher, Connection connection) {
		PreparedStatement updateStatement = null;

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE " + this.getTableName() + " SET ");
		sb.append("first_name = ?, ");
		sb.append("last_name = ?, ");
		sb.append("username = ?, ");
		sb.append("password = ?, ");
		sb.append("id_number = ?, ");
		sb.append("email = ? ");

		sb.append("WHERE id = ?;");

		String updateQuery = sb.toString();

		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, teacher.getFirstName());
			updateStatement.setString(2, teacher.getLastName());
			updateStatement.setString(3, teacher.getUserName());
			updateStatement.setString(4, teacher.getPassword());
			updateStatement.setString(5, teacher.getIdNumber());
			updateStatement.setString(6, teacher.getEmail());
			updateStatement.setLong(7, teacher.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(connection);
		}

		return updateStatement;
	}

	public Teacher findByUsernameAndPassword(String username, String password) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		List<Teacher> results = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(this.getTableName());
		sb.append(" WHERE username = ? AND password = ?;");

		String query = sb.toString();

		try {
			findStatement = connection.prepareStatement(query);
			findStatement.setString(1, username);
			findStatement.setString(2, password);
			resultSet = findStatement.executeQuery();
			results = createObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		} else {
			return null;
		}
	}
}
