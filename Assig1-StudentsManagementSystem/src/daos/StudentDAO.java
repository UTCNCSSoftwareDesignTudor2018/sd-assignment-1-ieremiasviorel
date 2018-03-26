package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.CourseEnrollment;
import models.CourseInformation;
import models.Student;

public class StudentDAO extends AbstractDAO<Student> {

	@Override
	public String getTableName() {
		return "students";
	}

	@Override
	protected PreparedStatement createInsertQuery(Student student, Connection connection) {
		PreparedStatement insertStatement = null;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + AbstractDAO.DB_NAME + "." + this.tableName);
		sb.append(" (id, first_name, last_name, username, password, id_number, email) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?);");

		String insertQuery = sb.toString();

		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setLong(1, AbstractDAO.nextID);
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
	public List<Student> createObjects(ResultSet resultSet) {

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
		sb.append("UPDATE " + AbstractDAO.DB_NAME + "." + this.tableName + " SET ");
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
		Student student = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT students.*, enrollments.id, enrollments.grade, courses.* FROM students ");
		sb.append("INNER JOIN enrollments ON students.id = enrollments.student_id ");
		sb.append("INNER JOIN courses ON enrollments.course_id = courses.id ");
		sb.append("WHERE students.username = ? AND students.password = ?;");
		
		String query = sb.toString();
		
		try {
			findStatement = connection.prepareStatement(query);
			findStatement.setString(1, username);
			findStatement.setString(2, password);
			resultSet = findStatement.executeQuery();
			
						
			while (resultSet.next()) {
				CourseEnrollment courseEnroll = new CourseEnrollment();
				CourseInformation courseInf = new CourseInformation();
				
				if (student == null) {
					student = new Student();
					student.setId(resultSet.getLong(1));
					student.setFirstName(resultSet.getString(2));
					student.setLastName(resultSet.getString(3));
					student.setUserName(resultSet.getString(4));
					student.setPassword(resultSet.getString(5));
					student.setIdNumber(resultSet.getString(6));
					student.setEmail(resultSet.getString(7));
					student.setCourses(new ArrayList<CourseEnrollment>());
				}
				
				courseEnroll.setId(resultSet.getLong(8));
				courseEnroll.setGrade(resultSet.getFloat(9));
				courseInf.setId(resultSet.getLong(10));
				courseInf.setName(resultSet.getString(11));
				courseInf.setCode(resultSet.getString(12));
				courseInf.setDescription(resultSet.getString(13));
				courseInf.setStartDate(resultSet.getObject(14, LocalDate.class));
				courseInf.setEndDate(resultSet.getObject(15, LocalDate.class));
				courseInf.setExamDate(resultSet.getObject(16, LocalDate.class));
				
				courseEnroll.setStudent(student);
				courseEnroll.setCourse(courseInf);
				
				student.getCourses().add(courseEnroll);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return student;
	}
}
