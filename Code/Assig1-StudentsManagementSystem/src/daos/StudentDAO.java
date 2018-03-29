package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Student;

public class StudentDAO extends AbstractDAO<Student> implements StudentDAO_I {

	@Override
	public String getTableName() {
		return Constants.STUDENTS_TABLE_NAME;
	}

	@Override
	protected PreparedStatement createInsertQuery(Student student, Connection connection) {
		PreparedStatement insertStatement = null;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + this.getTableName());
		sb.append(" (id, first_name, last_name, username, password, id_number, email) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?);");

		String insertQuery = sb.toString();

		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setLong(1, student.getId());
			insertStatement.setString(2, student.getFirstName());
			insertStatement.setString(3, student.getLastName());
			insertStatement.setString(4, student.getUserName());
			insertStatement.setString(5, student.getPassword());
			insertStatement.setString(6, student.getIdNumber());
			insertStatement.setString(7, student.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
		}
		return insertStatement;
	}

	@Override
	protected List<Student> createObjects(ResultSet resultSet) {

		List<Student> students = new ArrayList<Student>();

		try {
			while (resultSet.next()) {
				Student student = new Student();

				student.setId(resultSet.getLong(1));
				student.setFirstName(resultSet.getString(2));
				student.setLastName(resultSet.getString(3));
				student.setUserName(resultSet.getString(4));
				student.setPassword(resultSet.getString(5));
				student.setIdNumber(resultSet.getString(6));
				student.setEmail(resultSet.getString(7));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	protected PreparedStatement createUpdateQuery(Student student, Connection connection) {
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

			updateStatement.setString(1, student.getFirstName());
			updateStatement.setString(2, student.getLastName());
			updateStatement.setString(3, student.getUserName());
			updateStatement.setString(4, student.getPassword());
			updateStatement.setString(5, student.getIdNumber());
			updateStatement.setString(6, student.getEmail());

			updateStatement.setLong(7, student.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(connection);
		}
		return updateStatement;
	}

	public Student findByUsernameAndPassword(String username, String password) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		List<Student> results = null;

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
