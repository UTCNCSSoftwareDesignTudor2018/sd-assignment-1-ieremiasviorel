package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.CourseInformation;
import entities.Student;
import entities.Teacher;

public class CourseInformationDAO extends AbstractDAO<CourseInformation> implements CourseInformationDAO_I {

	public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public String getTableName() {
		return Constants.COURSES_TABLE_NAME;
	}

	@Override
	protected PreparedStatement createInsertQuery(CourseInformation courseInf, Connection connection) {
		PreparedStatement insertStatement = null;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + this.getTableName());
		sb.append(" (id, name, code, description, start_date, end_date, exam_date) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?);");

		String insertQuery = sb.toString();

		try {
			insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setLong(1, courseInf.getId());
			insertStatement.setString(2, courseInf.getName());
			insertStatement.setString(3, courseInf.getCode());
			insertStatement.setString(4, courseInf.getDescription());
			insertStatement.setObject(5, courseInf.getStartDate());
			insertStatement.setObject(6, courseInf.getEndDate());
			insertStatement.setObject(7, courseInf.getExamDate());

		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
		}
		return insertStatement;
	}

	@Override
	protected List<CourseInformation> createObjects(ResultSet resultSet) {
		List<CourseInformation> courses = new ArrayList<CourseInformation>();

		try {
			while (resultSet.next()) {
				CourseInformation courseInfo = new CourseInformation();

				courseInfo.setId(resultSet.getLong(1));
				courseInfo.setName(resultSet.getString(2));
				courseInfo.setCode(resultSet.getString(3));
				courseInfo.setDescription(resultSet.getString(7));
				courseInfo.setStartDate(LocalDate.parse(resultSet.getString(4), dateFormat));
				courseInfo.setEndDate(LocalDate.parse(resultSet.getString(5), dateFormat));
				courseInfo.setExamDate(LocalDate.parse(resultSet.getString(6), dateFormat));

				courses.add(courseInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	protected PreparedStatement createUpdateQuery(CourseInformation courseInfo, Connection connection) {
		PreparedStatement updateStatement = null;

		StringBuilder sb = new StringBuilder();

		sb.append("UPDATE " + this.getTableName() + " SET ");
		sb.append("name = ?, ");
		sb.append("code = ?, ");
		sb.append("description = ?, ");
		sb.append("start_date = ?, ");
		sb.append("end_date = ? ,");
		sb.append("exam_date = ? ");

		sb.append("WHERE id = ?;");

		String updateQuery = sb.toString();

		try {
			updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, courseInfo.getName());
			updateStatement.setString(2, courseInfo.getCode());
			updateStatement.setString(3, courseInfo.getDescription());
			updateStatement.setObject(4, courseInfo.getStartDate());
			updateStatement.setObject(5, courseInfo.getEndDate());
			updateStatement.setObject(6, courseInfo.getExamDate());
			
			updateStatement.setLong(7, courseInfo.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(connection);
		}

		return updateStatement;
	}

	public List<Student> findCourseStudents(Long id) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;

		List<Student> students = new ArrayList<Student>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT students.* FROM courses ");
		sb.append("INNER JOIN enrollments ON courses.id = enrollments.course_id ");
		sb.append("INNER JOIN students ON enrollments.student_id = students.id ");
		sb.append("WHERE courses.id = ?;");

		String query = sb.toString();

		try {
			findStatement = connection.prepareStatement(query);
			findStatement.setLong(1, id);
			resultSet = findStatement.executeQuery();

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
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return students;
	}

	public List<Teacher> findCourseTeachers(Long id) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;

		List<Teacher> teachers = new ArrayList<Teacher>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT teachers.* FROM courses ");
		sb.append("INNER JOIN teaching ON courses.id = teaching.course_id ");
		sb.append("INNER JOIN teachers ON teaching.teacher_id = teachers.id ");
		sb.append("WHERE courses.id = ?;");

		String query = sb.toString();

		try {
			findStatement = connection.prepareStatement(query);
			findStatement.setLong(1, id);
			resultSet = findStatement.executeQuery();

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
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return teachers;
	}
}
